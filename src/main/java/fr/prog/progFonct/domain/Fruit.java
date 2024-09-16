package fr.prog.progFonct.domain;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Fruit {
	private String name;
	private int stockQuantity;
	private BigDecimal unitPice;	
}
