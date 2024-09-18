package fr.prog.progFonct.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PizzaOrder {
	private String id;
	private String orderedAt;
	private String readyAt;
	private String orderType;
	private String status;
	private int amount;
	private int totalAmount;
	private List<PizzaItem> items;
	private int deliveryCosts;
}
