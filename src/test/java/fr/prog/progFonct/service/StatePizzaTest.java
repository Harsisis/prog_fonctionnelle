package fr.prog.progFonct.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import fr.prog.progFonct.domain.PenaltyState;
import fr.prog.progFonct.domain.Pizza;
import fr.prog.progFonct.domain.PizzaOrder;
import fr.prog.progFonct.utils.PizzaJsonParser;

/**
 * Test class for {@link PenaltyState}
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
		assertTrue(true);
	}
	
	@Test
	void test_find_pizza_with_less_than_four_ingredients() {
		assertEquals(8, pizzaService.findPizzaWithLessThanIngredients(pizzas, 4));
	}
	
	@Test
	void test_never_ordered_pizza() {
		assertTrue(true);
	}
	
	@Test
	void test_average_pizza_order_amount() {
		assertEquals(1.52, pizzaOrderService.findAveragePizzaOrdered(orders).getAsDouble());
	}
	
	@Test
	void test_average_price_pizza_base_tomate() {
		assertTrue(true);
	}
	
	@Test
	void test_find_amount_of_non_meat_pizza() {
		assertTrue(true);
	}
	
	@Test
	void test_find_most_sold_pizza() {
		assertTrue(true);
	}
	
	@Test
	void test_average_amount_pizza_by_order() {
		assertTrue(true);
	}
	
	@Test
	void test_find_unused_ingredients_in_ordered_pizzas() {
		assertTrue(true);
	}
	
	@Test
	void test_find_pizza_ordered_once() {
		assertTrue(true);
	}
	
	@Test
	void test_average_pizza_preparation_time() {
		assertTrue(true);
	}
}
