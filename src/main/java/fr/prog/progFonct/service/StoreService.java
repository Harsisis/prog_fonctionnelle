package fr.prog.progFonct.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import fr.prog.progFonct.domain.Fruit;
import fr.prog.progFonct.domain.Store;
import fr.prog.progFonct.domain.struct.CreateFruitDTO;

/**
 * Store service
 */
@Service
public class StoreService {

	/**
	 * Return billed amount or zero if asked quantity is above {@link Fruit} stock
	 * or {@link Fruit} does not exist
	 * 
	 * @param store
	 * @param fruitName
	 * @param quantity
	 * @return billed amount or zero
	 */
	public BigDecimal sellFruit(Store store, String fruitName, int quantity) {
		if (IsFruitQuantityInStock(store, fruitName, quantity)) {
			Optional<Fruit> optFruit = findFruitByName(store, fruitName);
			if (optFruit.isPresent()) {
				Fruit foundFruit = optFruit.get();

				List<Fruit> fruits = store.getFruits().stream().map(f -> {
					if (f.getName().equalsIgnoreCase(fruitName)) {
						f.setStockQuantity(f.getStockQuantity() - quantity);
					}
					return f;
				}).toList();
				store.setFruits(fruits);

				return BigDecimal.valueOf(quantity).multiply(foundFruit.getUnitPice());
			}
		}
		return BigDecimal.ZERO;
	}

	/**
	 * Add {@link CreateFruitDTO} to {@link Store} {@link Fruit} list
	 * 
	 * @param store
	 * @param createFruitDTO
	 * @return edited {@link Store}
	 */
	public Store addFruitToStore(Store store, CreateFruitDTO createFruitDTO) {
		List<Fruit> fruits = store.getFruits() == null ? new ArrayList<Fruit>() : store.getFruits();
		fruits.add(new Fruit(createFruitDTO.getName(), createFruitDTO.getStockQuantity(),
				BigDecimal.valueOf(createFruitDTO.getUnitPice())));
		store.setFruits(fruits);
		return store;
	}
	
	/**
	 * Add stock quantity to {@link Fruit}
	 * @param store
	 * @param fruitName
	 * @param quantity
	 * @return
	 */
	public Store addStockToExistingFruit(Store store, String fruitName, int quantity) {
		Optional<Fruit> optFruit = findFruitByName(store, fruitName);
		if (optFruit.isPresent()) {
			List<Fruit> fruits = store.getFruits().stream().map(f -> {
				if (f.getName().equalsIgnoreCase(fruitName)) {
					f.setStockQuantity(f.getStockQuantity() + quantity);
				}
				return f;
			}).toList();
			store.setFruits(fruits);
		}
		return store;
	}
	
	/**
	 * Remove {@link Fruit} from given {@link Store}
	 * @param store
	 * @param fruitName
	 * @return
	 */
	public Store removeFruitFromStore(Store store, String fruitName) {
		store.setFruits(store.getFruits().stream()
				.filter(f -> !f.getName().equalsIgnoreCase(fruitName))
				.toList());
		return store;
	}

	/**
	 * Check if given {@link Fruit} is available in quantity at given {@link Store}
	 * 
	 * @param store
	 * @param fruitName
	 * @param quantity
	 * @return true if asked quantity is above stock, false otherwise
	 */
	public boolean IsFruitQuantityInStock(Store store, String fruitName, int quantity) {
		return store.getFruits().stream().filter(f -> f.getName().equalsIgnoreCase(fruitName))
				.anyMatch(f -> f.getStockQuantity() >= quantity);
	}

	/**
	 * Find a {@link Fruit} by name in a given {@link Store}
	 * 
	 * @param store
	 * @param fruitName
	 * @return Optional of {@link Fruit}
	 */
	public Optional<Fruit> findFruitByName(Store store, String fruitName) {
		return store.getFruits().stream().filter(fruit -> fruit.getName().equalsIgnoreCase(fruitName)).findAny();
	}
}
