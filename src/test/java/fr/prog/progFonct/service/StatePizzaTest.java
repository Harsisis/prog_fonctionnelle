package fr.prog.progFonct.service;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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
	
	private List<Pizza> pizzas;
	private List<PizzaOrder> orders;
	
	@BeforeEach
	public void init_data() {
		this.pizzas = PizzaJsonParser.pizzaFromJson("D:\\Eclipse\\Repository\\progFonct\\src\\main\\resources\\pizza_json\\pizzas.json").orElseGet(() -> new ArrayList<Pizza>());
		this.orders = PizzaJsonParser.orderFromJson("D:\\Eclipse\\Repository\\progFonct\\src\\main\\resources\\pizza_json\\orders.json").orElseGet(() -> new ArrayList<PizzaOrder>());
	}

	@Test
	void test_scenario() {
		assertTrue(true);
	}
}
