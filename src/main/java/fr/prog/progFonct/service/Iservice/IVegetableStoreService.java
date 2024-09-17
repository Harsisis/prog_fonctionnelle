package fr.prog.progFonct.service.Iservice;

import java.math.BigDecimal;
import java.util.Optional;

import fr.prog.progFonct.domain.Store;
import fr.prog.progFonct.domain.Vegetable;

public interface IVegetableStoreService {
	
	/**
	 * Recreate {@link Store} with added {@link Vegetable}
	 * @param store
	 * @param vegetable
	 * @return
	 */
	public Store addVegetable(Store store, Vegetable vegetable);
	
	/**
	 * Recreate {@link Store} without given {@link Vegetable}
	 * @param store
	 * @param vegetableName
	 * @return
	 */
	public Store removeVegetable(Store store, String vegetableName);
	
	/**
	 * Recreate {@link Store} with recreated {@link Vegetable} and updated stock quantity
	 * @param store
	 * @param vegetableName
	 * @param quantity
	 * @return
	 */
	public Store updateVegetableStockQuantity(Store store, String vegetableName, int quantity);
	
	/**
	 * Recreate {@link Store} with recreated {@link Vegetable} and updated price unit
	 * @param store
	 * @param vegetableName
	 * @param quantity
	 * @return
	 */
	public Store updateVegetableStockQuantity(Store store, String vegetableName, BigDecimal priceUnit);
	
	/**
	 * Print vegetables
	 * @param store
	 * @return
	 */
	public Store showStoreVegetables(Store store);
	
	/**
	 * Find Optional of {@link Vegetable}
	 * @param store
	 * @param vegetableName
	 * @return
	 */
	public Optional<Vegetable> findVegetable(Store store, String vegetableName);

}
