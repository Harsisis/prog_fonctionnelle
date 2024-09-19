package fr.prog.progFonct.domain.pizza;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PizzaThread {
	private final int id;
    private final EtatPizza etat;
    private final LocalDateTime debutEtat;
    private final LocalDateTime finEtat;
    
    public PizzaThread changerEtat(EtatPizza nouvelEtat) {
        return new PizzaThread(this.id, nouvelEtat, LocalDateTime.now(), null);
    }

    public PizzaThread completerEtat() {
        return new PizzaThread(this.id, this.etat, this.debutEtat, LocalDateTime.now());
    }
}
