package fr.prog.progFonct.domain.pizza;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Pizzaiolo {
	private static final Logger LOGGER = LoggerFactory.getLogger(Pizzaiolo.class);
	
	private static final int COOKING_TIME = 500;
	private static final int PREPARATION_TIME = 100;

	public CompletableFuture<PizzaThread> preparerPizza(PizzaThread pizza) {
        LOGGER.info("Début de la préparation de la pizza " + pizza.getId());
        PizzaThread pizzaPrepa = pizza.changerEtat(EtatPizza.EN_PREPARATION);
        return CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(PREPARATION_TIME);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            LOGGER.info("Pizza " + pizza.getId() + " en attente de cuisson.");
            return pizzaPrepa.changerEtat(EtatPizza.EN_ATTENTE_CUISSON);
        });
    }

    public CompletableFuture<PizzaThread> cuirePizza(PizzaThread pizza) {
        LOGGER.info("Début de la cuisson de la pizza " + pizza.getId());
        PizzaThread pizzaCuisson = pizza.changerEtat(EtatPizza.EN_CUISSON);
        return CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(COOKING_TIME); 
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            LOGGER.info("Pizza " + pizza.getId() + " est prête !");
            return pizzaCuisson.changerEtat(EtatPizza.PRETE);
        });
    }

    public CompletableFuture<List<PizzaThread>> traiterCommande(OrderThread order) {
        List<CompletableFuture<PizzaThread>> futures = order.getPizzas().stream()
                .map(pizza -> preparerPizza(pizza).thenCompose(this::cuirePizza))
                .collect(Collectors.toList());

        return CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]))
                .thenApply(v -> futures.stream().map(CompletableFuture::join).collect(Collectors.toList()));
    }
}
