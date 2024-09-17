package fr.prog.progFonct.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import fr.prog.progFonct.domain.Store;
import fr.prog.progFonct.domain.Vegetable;
import fr.prog.progFonct.service.Iservice.IVegetableStoreService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class VegetableStoreService implements IVegetableStoreService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(FruitStoreService.class);
	
	@Override
	public Store addVegetable(Store store, Vegetable vegetable) {
		List<Vegetable> vegetables = store.getVegetables();
		vegetables.add(vegetable);
		return Store.builder()
				.name(store.getName())
				.fruits(store.getFruits())
				.vegetables(vegetables)
				.build();
	}

	@Override
	public Store removeVegetable(Store store, String vegetableName) {
		List<Vegetable> vegetables = store.getVegetables().stream()
				.filter(v -> !v.getName().equalsIgnoreCase(vegetableName))
				.toList();
		return Store.builder()
				.name(store.getName())
				.fruits(store.getFruits())
				.vegetables(vegetables)
				.build();
	}

	@Override
	public Store updateVegetableStockQuantity(Store store, String vegetableName, int quantity) {
		List<Vegetable> vegetables = store.getVegetables().stream()
				.map(v -> v.getName().equalsIgnoreCase(vegetableName) ? v.recreateVegetableByStockQuantity(quantity) : v)
				.toList();
		return Store.builder()
				.name(store.getName())
				.fruits(store.getFruits())
				.vegetables(vegetables)
				.build();
	}

	@Override
	public Store updateVegetableStockQuantity(Store store, String vegetableName, BigDecimal priceUnit) {
		List<Vegetable> vegetables = store.getVegetables().stream()
				.map(v -> v.getName().equalsIgnoreCase(vegetableName) ? v.recreateVegetableByUnitPrice(priceUnit) : v)
				.toList();
		return Store.builder()
				.name(store.getName())
				.fruits(store.getFruits())
				.vegetables(vegetables)
				.build();
	}

	@Override
	public Store showStoreVegetables(Store store) {
		LOGGER.info("Show vegetables");
		store.getVegetables().stream().forEach(v -> LOGGER.info(v.getName()+" | "+v.getStockQuantity()));
		return Store.builder()
				.name(store.getName())
				.fruits(store.getFruits())
				.vegetables(store.getVegetables())
				.build();
	}

	@Override
	public Optional<Vegetable> findVegetable(Store store, String vegetableName) {
		return store.getVegetables().stream()
				.filter(v -> v.getName().equalsIgnoreCase(vegetableName))
				.findFirst();
	}
}
