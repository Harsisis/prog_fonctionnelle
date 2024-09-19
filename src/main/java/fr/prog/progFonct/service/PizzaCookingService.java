package fr.prog.progFonct.service;

import java.util.concurrent.ConcurrentLinkedQueue;

import org.springframework.stereotype.Service;

import fr.prog.progFonct.domain.Pizza;
import fr.prog.progFonct.service.runnable.AbstractKitchenPizzaRunnable;
import fr.prog.progFonct.service.runnable.PreparePizzaRunnable;

@Service
public class PizzaCookingService {

	public void addToQueue(ConcurrentLinkedQueue<AbstractKitchenPizzaRunnable> queue, Pizza pizza) {
		queue.add(new PreparePizzaRunnable(pizza));
	}
	
}
