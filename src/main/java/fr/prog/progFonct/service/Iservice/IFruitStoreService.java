package fr.prog.progFonct.service.Iservice;

import java.math.BigDecimal;
import java.util.Optional;

import fr.prog.progFonct.domain.Fruit;
import fr.prog.progFonct.domain.Store;
import fr.prog.progFonct.domain.struct.CreateFruitDTO;

public interface IFruitStoreService {
	/**
	 * Return billed amount or zero if asked quantity is above {@link Fruit} stock
	 * or {@link Fruit} does not exist
	 * 
	 * @param store
	 * @param fruitName
	 * @param quantity
	 * @return billed amount or zero
	 */
	public BigDecimal sellFruit(Store store, String fruitName, int quantity);

	/**
	 * Add {@link CreateFruitDTO} to {@link Store} {@link Fruit} list
	 * 
	 * @param store
	 * @param createFruitDTO
	 * @return edited {@link Store}
	 */
	public Store addFruitToStore(Store store, CreateFruitDTO createFruitDTO);
	
	/**
	 * Add stock quantity to {@link Fruit}
	 * @param store
	 * @param fruitName
	 * @param quantity
	 * @return
	 */
	public Store addStockToExistingFruit(Store store, String fruitName, int quantity);
	
	/**
	 * Remove {@link Fruit} from given {@link Store}
	 * @param store
	 * @param fruitName
	 * @return
	 */
	public Store removeFruitFromStore(Store store, String fruitName);

	/**
	 * Check if given {@link Fruit} is available in quantity at given {@link Store}
	 * 
	 * @param store
	 * @param fruitName
	 * @param quantity
	 * @return true if asked quantity is above stock, false otherwise
	 */
	public boolean IsFruitQuantityInStock(Store store, String fruitName, int quantity);

	/**
	 * Find a {@link Fruit} by name in a given {@link Store}
	 * 
	 * @param store
	 * @param fruitName
	 * @return Optional of {@link Fruit}
	 */
	public Optional<Fruit> findFruitByName(Store store, String fruitName);
	
	/**
	 * Display {@link Fruit}s stock
	 * @param store
	 */
	public void showStock(Store store);
}
