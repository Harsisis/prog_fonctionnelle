package fr.prog.progFonct.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	
	Logger logger = LoggerFactory.getLogger(StoreService.class);

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
				
				logger.info("Fruit "+fruitName+" sold from store "+store.getName());
				return BigDecimal.valueOf(quantity).multiply(foundFruit.getUnitPice());
			}
		}
		logger.info("Fruit not found or stock is insufficient");
		return BigDecimal.ZERO;
	}

	public Store addFruitToStore(Store store, CreateFruitDTO createFruitDTO) {
		List<Fruit> fruits = store.getFruits() == null ? new ArrayList<Fruit>() : store.getFruits();
		fruits.add(new Fruit(createFruitDTO.getName(), createFruitDTO.getStockQuantity(),
				BigDecimal.valueOf(createFruitDTO.getUnitPice())));
		store.setFruits(fruits);
		logger.info("Fruit "+createFruitDTO.getName()+" added to store "+store.getName());
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
		logger.info("Fruit "+fruitName+" stock updated from store "+store.getName());
		return store;
	}
	
	public Store removeFruitFromStore(Store store, String fruitName) {
		store.setFruits(store.getFruits().stream()
				.filter(f -> !f.getName().equalsIgnoreCase(fruitName))
				.toList());
		logger.info("Fruit "+fruitName+" removed from store "+store.getName());
		return store;
	}

	public boolean IsFruitQuantityInStock(Store store, String fruitName, int quantity) {
		return store.getFruits().stream().filter(f -> f.getName().equalsIgnoreCase(fruitName))
				.anyMatch(f -> f.getStockQuantity() >= quantity);
	}

	public Optional<Fruit> findFruitByName(Store store, String fruitName) {
		return store.getFruits().stream().filter(fruit -> fruit.getName().equalsIgnoreCase(fruitName)).findAny();
	}

	@Override
	public void showStock(Store store) {
		logger.info("Fruit from store : " + store.getName());
		store.getFruits().stream()
			.forEach(f -> logger.info("Fruit name : " + f.getName() + " | quantity : " + f.getStockQuantity()));
		
	}
}
