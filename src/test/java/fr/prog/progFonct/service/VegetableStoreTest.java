package fr.prog.progFonct.service;

import static org.junit.jupiter.api.Assertions.assertFalse;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import fr.prog.progFonct.domain.Store;
import fr.prog.progFonct.domain.Vegetable;
import fr.prog.progFonct.service.FruitStoreService;
import fr.prog.progFonct.service.VegetableStoreService;

/**
 * Test class for {@link FruitStoreService}
 */
@SpringBootTest
public class VegetableStoreTest {

	@Autowired
	private VegetableStoreService vegetableStoreService;

	@Test
	void test_scenario() {
		Store store = Store.builder().name("SCENARIO Legumes").vegetables(new ArrayList<Vegetable>()).build();

		Store newStore = vegetableStoreService.addVegetable(store,
				Vegetable.builder().name("Tomate").stockQuantity(10).unitPrice(BigDecimal.valueOf(2)).build());

		Store newStore1 = vegetableStoreService.addVegetable(newStore,
				Vegetable.builder().name("Haricot").stockQuantity(5).unitPrice(BigDecimal.valueOf(2)).build());

		Store newStore2 = vegetableStoreService.addVegetable(newStore1,
				Vegetable.builder().name("Aubergine").stockQuantity(8).unitPrice(BigDecimal.valueOf(2)).build());

		Store newStore3 = vegetableStoreService.showStoreVegetables(newStore2);
		Store newStore4 = vegetableStoreService.updateVegetableStockQuantity(newStore3, "Tomate", 15);
		Store newStore5 = vegetableStoreService.updateVegetableStockQuantity(newStore4, "Haricot", 13);
		Store newStore6 = vegetableStoreService.updateVegetableStockQuantity(newStore5, "Aubergine", 6);
		Store newStore7 = vegetableStoreService.showStoreVegetables(newStore6);
		Store newStore8 = vegetableStoreService.removeVegetable(newStore7, "Aubergine");
		Store newStore9 = vegetableStoreService.showStoreVegetables(newStore8);

		assertFalse(vegetableStoreService.findVegetable(newStore9, "Aubergine").isPresent());
	}
}
