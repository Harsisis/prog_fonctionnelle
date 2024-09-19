package fr.prog.progFonct.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import fr.prog.progFonct.domain.Pizza;

@Service
public class PizzaService {

	private static final String TOMATE = "Tomate";
	private static final List<String> MEAT_INGREDIENT_LIST = List.of("Jambon Cru", "Saucisson Piquant", "Jambon Cuît");

	public Optional<Pizza> findPizzaById(List<Pizza> pizzas, String pizzaId) {
		return pizzas.stream().filter(p -> p.getId().equals(pizzaId)).findFirst();
	}
	
	public long findBaseAmount(List<Pizza> pizzas) {
		return pizzas.stream().map(Pizza::getBase).distinct().count();
	}

	public long findBaseTomateAmount(List<Pizza> pizzas) {
		return pizzas.stream().filter(p -> p.getBase().equalsIgnoreCase(TOMATE)).count();
	}

	public long findPizzaWithLessThanIngredients(List<Pizza> pizzas, int ingredients) {
		return pizzas.stream().filter(p -> p.getIngredients().size() < ingredients).count();
	}

	public long findAmountIngredient(List<Pizza> pizzas) {
		return pizzas.stream().flatMap(p -> p.getIngredients().stream()).distinct().count();
	}

	public long findNonMeatPizzas(List<Pizza> pizzas) {
		return pizzas.stream().filter(p -> p.getIngredients().stream().anyMatch(MEAT_INGREDIENT_LIST::contains)).count();
	}

	public double findAveragePriceForPizzaWithBaseTomate(List<Pizza> pizzas) {
		return Math.floor(pizzas.stream().filter(p -> p.getBase().equalsIgnoreCase(TOMATE)).mapToLong(Pizza::getPrice).average().orElse(0) * 100)/100;
	}

	public List<String> findIngredientsUseOnce(List<Pizza> pizzas) {
		return pizzas.stream().flatMap(p -> p.getIngredients().stream())
				.collect(Collectors.groupingBy(i -> i, Collectors.counting()))
				.entrySet().stream().filter(e -> e.getValue() == 1).map(Map.Entry::getKey).toList();
	}

}
