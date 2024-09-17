package fr.prog.progFonct.domain;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Store {
	private String name;
	private List<Fruit> fruits;
	private List<Vegetable> vegetables;
}
