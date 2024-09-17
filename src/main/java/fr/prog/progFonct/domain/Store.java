package fr.prog.progFonct.domain;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Store {
	private String name;
	private List<Fruit> fruits;
	private List<Vegetable> vegetables;
	
	/**
	 * Recreate {@link Store} with added {@link Vegetable}
	 * @param vegetable
	 * @return
	 */
	public Store addVegetable(Vegetable vegetable) {
		List<Vegetable> vegetables = this.vegetables;
		vegetables.add(vegetable);
		return Store.builder()
				.name(this.name)
				.fruits(this.fruits)
				.vegetables(vegetables)
				.build();
	}
	
	/**
	 * Recreate {@link Store} without given {@link Vegetable}
	 * @param vegetableName
	 * @return
	 */
	public Store removeVegetable(String vegetableName) {
		List<Vegetable> vegetables = this.vegetables.stream()
				.filter(v -> v.getName().equalsIgnoreCase(vegetableName))
				.toList();
		return Store.builder()
				.name(this.name)
				.fruits(this.fruits)
				.vegetables(vegetables)
				.build();
	}
	
	/**
	 * Recreate {@link Store} with recreated {@link Vegetable} and updated stock quantity
	 * @param vegetableName
	 * @param quantity
	 * @return
	 */
	public Store updateVegetableStockQuantity(String vegetableName, int quantity) {
		List<Vegetable> vegetables = this.vegetables.stream()
				.map(v -> v.getName().equalsIgnoreCase(vegetableName) ? v.recreateVegetableByStockQuantity(quantity) : v)
				.toList();
		return Store.builder()
				.name(this.name)
				.fruits(this.fruits)
				.vegetables(vegetables)
				.build();
	}
	
	/**
	 * Recreate {@link Store} with recreated {@link Vegetable} and updated price unit
	 * @param vegetableName
	 * @param quantity
	 * @return
	 */
	public Store updateVegetableStockQuantity(String vegetableName, BigDecimal priceUnit) {
		List<Vegetable> vegetables = this.vegetables.stream()
				.map(v -> v.getName().equalsIgnoreCase(vegetableName) ? v.recreateVegetableByUnitPrice(priceUnit) : v)
				.toList();
		return Store.builder()
				.name(this.name)
				.fruits(this.fruits)
				.vegetables(vegetables)
				.build();
	}
	
	/**
	 * 
	 * @param vegetableName
	 * @return
	 */
	public Optional<Vegetable> findVegetable(String vegetableName){
		return this.vegetables.stream()
				.filter(v -> v.getName().equalsIgnoreCase(vegetableName))
				.findFirst();
	}

	public Store orDefault() {
		return this;
	}
}
