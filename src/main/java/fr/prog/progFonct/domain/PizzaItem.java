package fr.prog.progFonct.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PizzaItem {
	private String pizzaId;
	private int quantity;
	private int price;
	private int amount;
}
