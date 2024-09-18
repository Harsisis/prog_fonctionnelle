package fr.prog.progFonct.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Pizza {
	private String id;
	private String name;
	private int price;
	private String base;
	private List<String> ingredients;
}
