package fr.prog.progFonct.domain.struct;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateFruitDTO {
	private String name;
	private int stockQuantity;
	private double unitPice;	
}
