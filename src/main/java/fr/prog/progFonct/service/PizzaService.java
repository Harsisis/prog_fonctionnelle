package fr.prog.progFonct.service;

import java.util.List;

import org.springframework.stereotype.Service;

import fr.prog.progFonct.domain.Pizza;

@Service
public class PizzaService {

	public long findBaseAmount(List<Pizza> pizzas) {
		return pizzas.stream().map(Pizza::getBase).distinct().count();
	}

	public long findBaseTomateAmount(List<Pizza> pizzas) {
		return pizzas.stream().filter(p -> p.getBase().equalsIgnoreCase("Tomate")).count();
	}

	public long findPizzaWithLessThanIngredients(List<Pizza> pizzas, int ingredients) {
		return pizzas.stream().filter(p -> p.getIngredients().size() < ingredients).count();
	}

	public long findAmountIngredient(List<Pizza> pizzas) {
		return pizzas.stream().flatMap(p -> p.getIngredients().stream()).distinct().count();
	}

}
