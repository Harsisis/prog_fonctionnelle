package fr.prog.progFonct.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import fr.prog.progFonct.domain.Fruit;
import fr.prog.progFonct.domain.Store;
import fr.prog.progFonct.domain.struct.CreateFruitDTO;

/**
 * Test class for {@link StoreService}
 */
@SpringBootTest
public class StoreServiceTest {

	@Autowired
	private StoreService storeService;
	
	private Store store;
	
	@BeforeEach
	void SetupVariable() {
		Fruit fruit1 = new Fruit("Banana", 100, BigDecimal.valueOf(2));
		Fruit fruit2 = new Fruit("Apple", 100, BigDecimal.valueOf(1));
		Fruit fruit3 = new Fruit("Pineapple", 100, BigDecimal.valueOf(1.5));
		Fruit fruit4 = new Fruit("Raspberry", 100, BigDecimal.valueOf(2.2));
		Fruit fruit5 = new Fruit("Strawberry", 100, BigDecimal.valueOf(0.3));
		Fruit fruit6 = new Fruit("Pear", 100, BigDecimal.valueOf(1));
		Fruit fruit7 = new Fruit("Grapes", 100, BigDecimal.valueOf(1.4));
		
		List<Fruit> fruits = List.of(fruit1, fruit2, fruit3, fruit4, fruit5, fruit6, fruit7);
		
		this.store = new Store("FruitStore", fruits);
	}
	
	@Test
	void test_added_product_should_exist_in_store () {
		Optional<Fruit> optFruit = storeService.findFruitByName(store, "Banana");
		
		assertTrue(optFruit.isPresent());
	}
	
	@Test
	void test_non_added_product_should_not_exist_in_store () {
		Optional<Fruit> optFruit = storeService.findFruitByName(store, "Iphone");
		
		assertFalse(optFruit.isPresent());
	}
	
	@Test
	void test_stock_should_decrease_when_product_purchased () {
		storeService.sellFruit(store, "Banana", 5);
		storeService.showStock(store);
		
		assertThat(storeService.findFruitByName(store, "Banana").get().getStockQuantity()).isEqualTo(95);
	}
	
	@Test
	void test_bill_amount_should_match_when_product_purchased () {
		BigDecimal billAmount = storeService.sellFruit(store, "Banana", 5);
		storeService.showStock(store);
		
		assertThat(billAmount).isEqualTo(BigDecimal.valueOf(10));
	}
	
	@Test
	void test_stock_should_increase_when_stock_added () {
		store = storeService.addStockToExistingFruit(store, "Apple", 10);
		storeService.showStock(store);
		
		assertThat(storeService.findFruitByName(store, "Apple").get().getStockQuantity()).isEqualTo(110);
	}
	
	@Test
	void test_product_should_be_null_when_product_removed () {
		store = storeService.removeFruitFromStore(store, "Apple");
		storeService.showStock(store);
		
		assertTrue(storeService.findFruitByName(store, "Apple").isEmpty());
	}
	
	@Test
	void test_scenario () {
		Store store2 = new Store();
		store2.setName("SCENARIO");
		
		storeService.addFruitToStore(store2, new CreateFruitDTO("Pomme", 10, 2));
		storeService.addFruitToStore(store2, new CreateFruitDTO("Poire", 5, 2));
		storeService.addFruitToStore(store2, new CreateFruitDTO("Ananas", 8, 2));
		
		storeService.addStockToExistingFruit(store2, "Pomme", 5);
		storeService.addStockToExistingFruit(store2, "Poire", 8);
		
		storeService.sellFruit(store2, "Ananas", 2);
		
		storeService.showStock(store2);
		
		storeService.removeFruitFromStore(store2, "Ananas");
		
		storeService.showStock(store2);
		
		assertTrue(storeService.findFruitByName(store2, "Ananas").isEmpty());
	}
}
