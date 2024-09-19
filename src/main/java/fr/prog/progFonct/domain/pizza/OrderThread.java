package fr.prog.progFonct.domain.pizza;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class OrderThread {
	private List<PizzaThread> pizzas;
}
