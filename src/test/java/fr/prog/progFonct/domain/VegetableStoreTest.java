package fr.prog.progFonct.domain;

import static org.junit.jupiter.api.Assertions.assertFalse;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import fr.prog.progFonct.service.FruitStoreService;

/**
 * Test class for {@link FruitStoreService}
 */
@SpringBootTest
public class VegetableStoreTest {

	@Test
	void test_scenario () {
		Store store = Store.builder()
				.name("SCENARIO Legumes")
				.vegetables(new ArrayList<Vegetable>())
				.build();
		
		store = store.addVegetable(
					Vegetable.builder()
						.name("Tomate")
						.stockQuantity(10)
						.unitPrice(BigDecimal.valueOf(2))
						.build())
				.addVegetable(
					Vegetable.builder()
						.name("Haricot")
						.stockQuantity(5)
						.unitPrice(BigDecimal.valueOf(2))
						.build())
				.addVegetable(
					Vegetable.builder()
						.name("Aubergine")
						.stockQuantity(8)
						.unitPrice(BigDecimal.valueOf(2))
						.build())
				.showStoreVegetables()
				.updateVegetableStockQuantity("Tomate", 15)
				.updateVegetableStockQuantity("Haricot", 13)
				.updateVegetableStockQuantity("Aubergine", 6)
				.showStoreVegetables()
				.removeVegetable("Aubergine")
				.showStoreVegetables();
		
		assertFalse(store.findVegetable("Aubergine").isPresent());
	}
}
