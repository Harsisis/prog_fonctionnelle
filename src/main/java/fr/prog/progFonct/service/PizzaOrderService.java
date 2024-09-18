package fr.prog.progFonct.service;

import java.util.List;
import java.util.OptionalDouble;

import org.springframework.stereotype.Service;

import fr.prog.progFonct.domain.PizzaOrder;

@Service
public class PizzaOrderService {

	public OptionalDouble findAveragePizzaOrdered(List<PizzaOrder> orders) {
		return orders.stream().map(o -> o.getItems().size()).mapToInt(Integer::intValue).average();
	}

}
