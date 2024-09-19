package fr.prog.progFonct.service;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import fr.prog.progFonct.domain.Pizza;
import fr.prog.progFonct.domain.PizzaItem;
import fr.prog.progFonct.domain.PizzaOrder;

@Service
public class PizzaOrderService {

	public double findAverageAmountPizzaByOrder(List<PizzaOrder> orders) {
		return Math.floor(
				orders.stream().map(o -> o.getItems().size()).mapToInt(Integer::intValue).average().orElse(0) * 100)
				/ 100;
	}

	public double findAverageOrderTotalAmount(List<PizzaOrder> orders) {
		return Math.floor(
				orders.stream().map(o -> o.getTotalAmount()).mapToInt(Integer::intValue).average().orElse(0) * 100)
				/ 100;
	}

	public Optional<String> findMostSoldPizza(List<PizzaOrder> orders) {
		return Optional.of(orders.stream().flatMap(order -> order.getItems().stream())
				.collect(Collectors.groupingBy(PizzaItem::getPizzaId, Collectors.summingInt(PizzaItem::getQuantity)))
				.entrySet().stream().max(Map.Entry.comparingByValue()).map(Map.Entry::getKey).orElse(null));
	}

	public double findAveragePreparationTime(List<PizzaOrder> orders) {
		return Math.floor(orders.stream().mapToLong(o -> Duration.between(o.getOrderedAt(), o.getReadyAt()).toMinutes())
				.average().orElse(0) * 100) / 100;
	}

	public List<String> findNeverOrderedPizzas(List<PizzaOrder> orders, List<Pizza> pizzas) {
		Set<String> orderedPizzaIds = orders.stream().flatMap(order -> order.getItems().stream())
				.map(PizzaItem::getPizzaId).collect(Collectors.toSet());
		return pizzas.stream().filter(p -> !orderedPizzaIds.contains(p.getId())).map(Pizza::getName).toList();
	}

	public Double findAverageDeliveryCosts(List<PizzaOrder> orders) {
		return Math.floor(orders.stream().mapToLong(o -> o.getDeliveryCosts()).average().orElse(0) * 100) / 100;
	}

	public List<String> findOrderedOncePizzas(List<PizzaOrder> orders, List<Pizza> pizzas) {
		Set<String> orderedOncePizzaIds = orders.stream().flatMap(order -> order.getItems().stream())
				.collect(Collectors.groupingBy(PizzaItem::getPizzaId, Collectors.counting()))
				.entrySet().stream().filter(e -> e.getValue() == 1).map(Map.Entry::getKey).collect(Collectors.toSet());

		return pizzas.stream().filter(p -> orderedOncePizzaIds.contains(p.getId())).map(Pizza::getName).toList();
	}

	public List<String> findUnusedIngredientsInOrderedPizzas(List<PizzaOrder> orders, List<Pizza> pizzas) {
		Set<String> ingredients = pizzas.stream().flatMap(p -> p.getIngredients().stream()).collect(Collectors.toSet());
		
		Set<String> orderedPizzaIds = orders.stream().flatMap(order -> order.getItems().stream())
				.map(PizzaItem::getPizzaId).collect(Collectors.toSet());
		List<Pizza> orderedPizzas =  pizzas.stream().filter(p -> orderedPizzaIds.contains(p.getId())).toList();
		Set<String> orderedIngredients = orderedPizzas.stream().flatMap(p -> p.getIngredients().stream()).collect(Collectors.toSet());
		
		return ingredients.stream().filter(i -> !orderedIngredients.contains(i)).toList();
	}

}
