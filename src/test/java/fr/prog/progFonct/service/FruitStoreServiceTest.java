package fr.prog.progFonct.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.ArrayList;
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
 * Test class for {@link FruitStoreService}
 */
@SpringBootTest
public class FruitStoreServiceTest {

	@Autowired
	private FruitStoreService fruitStoreService;
	
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
		
		this.store = Store.builder()
				.name("FruitStore")
				.fruits(fruits)
				.build();
	}
	
	@Test
	void test_added_product_should_exist_in_store () {
		Optional<Fruit> optFruit = fruitStoreService.findFruitByName(store, "Banana");
		
		assertTrue(optFruit.isPresent());
	}
	
	@Test
	void test_non_added_product_should_not_exist_in_store () {
		Optional<Fruit> optFruit = fruitStoreService.findFruitByName(store, "Iphone");
		
		assertFalse(optFruit.isPresent());
	}
	
	@Test
	void test_stock_should_decrease_when_product_purchased () {
		fruitStoreService.sellFruit(store, "Banana", 5);
		fruitStoreService.showStock(store);
		
		assertThat(fruitStoreService.findFruitByName(store, "Banana").get().getStockQuantity()).isEqualTo(95);
	}
	
	@Test
	void test_bill_amount_should_match_when_product_purchased () {
		BigDecimal billAmount = fruitStoreService.sellFruit(store, "Banana", 5);
		fruitStoreService.showStock(store);
		
		assertThat(billAmount).isEqualTo(BigDecimal.valueOf(10));
	}
	
	@Test
	void test_stock_should_increase_when_stock_added () {
		store = fruitStoreService.addStockToExistingFruit(store, "Apple", 10);
		fruitStoreService.showStock(store);
		
		assertThat(fruitStoreService.findFruitByName(store, "Apple").get().getStockQuantity()).isEqualTo(110);
	}
	
	@Test
	void test_product_should_be_null_when_product_removed () {
		store = fruitStoreService.removeFruitFromStore(store, "Apple");
		fruitStoreService.showStock(store);
		
		assertTrue(fruitStoreService.findFruitByName(store, "Apple").isEmpty());
	}
	
	@Test
	void test_scenario () {
		Store store2 = Store.builder()
				.name("SCENARIO")
				.fruits(new ArrayList<Fruit>())
				.build();
		
		fruitStoreService.addFruitToStore(store2, new CreateFruitDTO("Pomme", 10, 2));
		fruitStoreService.addFruitToStore(store2, new CreateFruitDTO("Poire", 5, 2));
		fruitStoreService.addFruitToStore(store2, new CreateFruitDTO("Ananas", 8, 2));
		
		fruitStoreService.addStockToExistingFruit(store2, "Pomme", 5);
		fruitStoreService.addStockToExistingFruit(store2, "Poire", 8);
		
		fruitStoreService.sellFruit(store2, "Ananas", 2);
		
		fruitStoreService.showStock(store2);
		
		fruitStoreService.removeFruitFromStore(store2, "Ananas");
		
		fruitStoreService.showStock(store2);
		
		assertTrue(fruitStoreService.findFruitByName(store2, "Ananas").isEmpty());
	}
}
