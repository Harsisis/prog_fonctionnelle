package fr.prog.progFonct.service.runnable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.prog.progFonct.domain.Pizza;
import fr.prog.progFonct.service.FruitStoreService;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CookPizzaRunnable extends AbstractKitchenPizzaRunnable implements Runnable {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(FruitStoreService.class);
	
	private static final int COOKING_TIME_MS = 25000;
	
	private Pizza pizza;

	@Override
	public void run() {
		try {
			LOGGER.info("Pizza "+this.pizza.getName()+" is ready to cook");
            Thread.sleep(COOKING_TIME_MS);
            LOGGER.info("Pizza "+this.pizza.getName()+" is cooked");
        } catch (InterruptedException e) {
        	LOGGER.error("Interrupted Thread : "+e.getMessage());
        }
	}

}
