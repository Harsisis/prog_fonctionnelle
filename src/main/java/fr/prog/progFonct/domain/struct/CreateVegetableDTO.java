package fr.prog.progFonct.domain.struct;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateVegetableDTO {
	private String name;
	private int stockQuantity;
	private double unitPice;
}
