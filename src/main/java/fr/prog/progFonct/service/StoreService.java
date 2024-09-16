package fr.prog.progFonct.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import fr.prog.progFonct.domain.Fruit;
import fr.prog.progFonct.domain.Store;
import fr.prog.progFonct.domain.struct.CreateFruitDTO;
import fr.prog.progFonct.service.Iservice.IStoreService;

/**
 * Store service
 */
@Service
public class StoreService implements IStoreService {

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

	public Store addFruitToStore(Store store, CreateFruitDTO createFruitDTO) {
		List<Fruit> fruits = store.getFruits() == null ? new ArrayList<Fruit>() : store.getFruits();
		fruits.add(new Fruit(createFruitDTO.getName(), createFruitDTO.getStockQuantity(),
				BigDecimal.valueOf(createFruitDTO.getUnitPice())));
		store.setFruits(fruits);
		return store;
	}
	
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
	
	public Store removeFruitFromStore(Store store, String fruitName) {
		store.setFruits(store.getFruits().stream()
				.filter(f -> !f.getName().equalsIgnoreCase(fruitName))
				.toList());
		return store;
	}

	public boolean IsFruitQuantityInStock(Store store, String fruitName, int quantity) {
		return store.getFruits().stream().filter(f -> f.getName().equalsIgnoreCase(fruitName))
				.anyMatch(f -> f.getStockQuantity() >= quantity);
	}

	public Optional<Fruit> findFruitByName(Store store, String fruitName) {
		return store.getFruits().stream().filter(fruit -> fruit.getName().equalsIgnoreCase(fruitName)).findAny();
	}
}
