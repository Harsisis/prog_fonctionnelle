package fr.prog.progFonct.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import fr.prog.progFonct.domain.pizza.EtatPizza;
import fr.prog.progFonct.domain.pizza.OrderThread;
import fr.prog.progFonct.domain.pizza.PizzaThread;
import fr.prog.progFonct.domain.pizza.Pizzaiolo;

/**
 * Test class
 */
@SpringBootTest
public class PreparePizzaTest {
	
	@Test
    public void test_prepare_and_cook_pizza() {
        Pizzaiolo pizzaiolo = new Pizzaiolo();

        PizzaThread pizza = new PizzaThread(1, EtatPizza.EN_ATTENTE_PREPARATION, null, null);
        CompletableFuture<PizzaThread> futurePizza = pizzaiolo.preparerPizza(pizza).thenCompose(pizzaiolo::cuirePizza);

        PizzaThread pizzaPrete = futurePizza.join();

        assertEquals(EtatPizza.PRETE, pizzaPrete.getEtat());
    }
	
	@Test
    public void test_prepare_and_cook_three_pizzas() {
        Pizzaiolo pizzaiolo = new Pizzaiolo();

        List<PizzaThread> pizzas = new ArrayList<>();
        for (int i = 1; i <= 3; i++) {
            pizzas.add(new PizzaThread(i, EtatPizza.EN_ATTENTE_PREPARATION, null, null));
        }
        OrderThread commande = new OrderThread(pizzas);

        CompletableFuture<List<PizzaThread>> pizzasPretesFuture = pizzaiolo.traiterCommande(commande);
        List<PizzaThread> pizzasPretes = pizzasPretesFuture.join();

        assertTrue(pizzasPretes.stream().allMatch(p -> p.getEtat() == EtatPizza.PRETE));
    }
	
	@Test
    public void test_prepare_and_cook_ten_pizzas() {
        Pizzaiolo pizzaiolo = new Pizzaiolo();

        List<PizzaThread> pizzas = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            pizzas.add(new PizzaThread(i, EtatPizza.EN_ATTENTE_PREPARATION, null, null));
        }
        OrderThread commande = new OrderThread(pizzas);

        CompletableFuture<List<PizzaThread>> pizzasPretesFuture = pizzaiolo.traiterCommande(commande);
        List<PizzaThread> pizzasPretes = pizzasPretesFuture.join();

        assertTrue(pizzasPretes.stream().allMatch(p -> p.getEtat() == EtatPizza.PRETE));
    }
}
