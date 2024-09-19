package fr.prog.progFonct.service.runnable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.prog.progFonct.domain.Pizza;
import fr.prog.progFonct.service.FruitStoreService;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PreparePizzaRunnable extends AbstractKitchenPizzaRunnable implements Runnable {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(FruitStoreService.class);
	
	private static final int PREPARING_TIME_MS = 5000;
	
	private Pizza pizza;

	@Override
	public void run() {
		try {
			LOGGER.info("Start preparing Pizza : "+this.pizza.getName());
            Thread.sleep(PREPARING_TIME_MS);
            LOGGER.info("Pizza "+this.pizza.getName()+" is ready");
            
            Thread cookPizzaThread = new Thread(() -> new CookPizzaRunnable(pizza));
            cookPizzaThread.start();
            
        } catch (InterruptedException e) {
        	LOGGER.error("Interrupted Thread : "+e.getMessage());
        }
	}
}
