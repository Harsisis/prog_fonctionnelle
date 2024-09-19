package fr.prog.progFonct.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import fr.prog.progFonct.domain.Pizza;
import fr.prog.progFonct.domain.PizzaOrder;
import fr.prog.progFonct.utils.PizzaJsonParser;

/**
 * Test class for {@link PizzaService} and {@link pizzaOrderService}
 */
@SpringBootTest
public class StatePizzaTest {
	
	@Autowired
	private PizzaService pizzaService;
	@Autowired
	private PizzaOrderService pizzaOrderService;
	
	private List<Pizza> pizzas;
	private List<PizzaOrder> orders;
	
	@BeforeEach
	public void init_data() {
		this.pizzas = PizzaJsonParser.pizzaFromJson("D:\\Eclipse\\Repository\\progFonct\\src\\main\\resources\\pizza_json\\pizzas.json").orElseGet(() -> new ArrayList<Pizza>());
		this.orders = PizzaJsonParser.orderFromJson("D:\\Eclipse\\Repository\\progFonct\\src\\main\\resources\\pizza_json\\orders.json").orElseGet(() -> new ArrayList<PizzaOrder>());
	}
	
	@Test
	void test_find_amount_pizza_base() {
		assertEquals(3, pizzaService.findBaseAmount(pizzas));
	}
	
	@Test
	void test_find_amount_pizza_base_tomate() {
		assertEquals(9, pizzaService.findBaseTomateAmount(pizzas));
	}
	
	@Test
	void test_ingredients_amount() {
		assertEquals(20, pizzaService.findAmountIngredient(pizzas));
	}
	
	@Test
	void test_unique_ingredient() {
		List<String> uniqueIngredients = List.of("Ail", "Gorgonzola", "Origan", "Courgettes", "Anchois", "Roquette", "Ananas", "Provola");
		assertTrue(pizzaService.findIngredientsUseOnce(pizzas).containsAll(uniqueIngredients));
	}
	
	@Test
	void test_find_pizza_with_less_than_four_ingredients() {
		assertEquals(8, pizzaService.findPizzaWithLessThanIngredients(pizzas, 4));
	}
	
	@Test
	void test_never_ordered_pizzas() {
		List<String> neverOrderedPizzas = List.of("Boscaiola", "Hawaï");
		assertTrue(pizzaOrderService.findNeverOrderedPizzas(orders, pizzas).containsAll(neverOrderedPizzas));
	}
	
	@Test
	void test_average_order_total_amount() {
		assertEquals(25.25, pizzaOrderService.findAverageOrderTotalAmount(orders));
	}
	
	@Test
	void test_average_price_pizza_base_tomate() {
		assertEquals(9.77, pizzaService.findAveragePriceForPizzaWithBaseTomate(pizzas));
	}
	
	@Test
	void test_find_amount_of_non_meat_pizza() {
		assertEquals(7, pizzaService.findNonMeatPizzas(pizzas));
	}
	
	@Test
	void test_find_most_sold_pizza() {
		String pizzaId = pizzaOrderService.findMostSoldPizza(orders).orElse("not found");
		Optional<Pizza> pizza = pizzaService.findPizzaById(pizzas, pizzaId);
		
		assertTrue(pizza.isPresent());
		assertEquals("Diavola", pizza.get().getName());
	}
	
	@Test
	void test_average_amount_pizza_by_order() {
		assertEquals(1.52, pizzaOrderService.findAverageAmountPizzaByOrder(orders));
	}
	
	@Test
	void test_find_unused_ingredients_in_ordered_pizzas() {
		List<String> unusedIngredients = List.of("Ananas");
		assertTrue(pizzaOrderService.findUnusedIngredientsInOrderedPizzas(orders, pizzas).containsAll(unusedIngredients));
	}
	
	@Test
	void test_find_pizzas_ordered_once() {
		assertTrue(pizzaOrderService.findOrderedOncePizzas(orders, pizzas).isEmpty());
	}
	
	@Test
	void test_average_pizza_preparation_time() {
		assertEquals(9.01, pizzaOrderService.findAveragePreparationTime(orders));
	}
	
	@Test
	void test_average_delivery_costs() {
		assertEquals(2.27, pizzaOrderService.findAverageDeliveryCosts(orders));
	}
	
	@Test
	void test_most_used_ingredient_with_cheese() {
		assertEquals("Champignons", pizzaService.findMostUsedIngredientsWithCheese(pizzas));
	}
}
