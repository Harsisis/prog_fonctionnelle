package fr.prog.progFonct.service;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import fr.prog.progFonct.domain.PenaltyState;
import fr.prog.progFonct.utils.PenaltyUtils;

/**
 * Test class for {@link PenaltyState}
 */
@SpringBootTest
public class PenaltyTest {

	private static final int PENALTY_ATTEMPT_NUMBER = 5;

	@Test
	void test_scenario() {
		
		PenaltyState state = new PenaltyState("team A", "team B", 0, 0);
		List<Supplier<PenaltyState>> shoots = new ArrayList<>();
		
		for (int i = 0; i < PENALTY_ATTEMPT_NUMBER; i++) {
			shoots.add(() -> PenaltyUtils.successfullShoot() ? state.successAttemptTeamA()
					: state.failedAttemptTeamA());
			shoots.add(() -> PenaltyUtils.successfullShoot() ? state.successAttemptTeamB()
					: state.failedAttemptTeamB());
		}

		PenaltyState finalState = shoots.stream()
				.map(Supplier::get)
				.reduce(state, (currentState, newState) -> {
					newState.printScore();
					return currentState.recreate(newState);
		});

		finalState.printFinalResult();
		
		assertTrue(finalState.isDone());
	}
}
