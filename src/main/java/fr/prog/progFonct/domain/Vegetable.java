package fr.prog.progFonct.domain;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Vegetable {
	private String name;
	private int stockQuantity;
	private BigDecimal unitPrice;
	
	public Vegetable recreateVegetableByStockQuantity(int stockQuantity) {
		return Vegetable.builder()
				.name(this.name)
				.stockQuantity(stockQuantity)
				.unitPrice(this.unitPrice)
				.build();
	}
	
	public Vegetable recreateVegetableByUnitPrice(BigDecimal unitPrice) {
		return Vegetable.builder()
				.name(this.name)
				.stockQuantity(this.stockQuantity)
				.unitPrice(unitPrice)
				.build();
	}
}
