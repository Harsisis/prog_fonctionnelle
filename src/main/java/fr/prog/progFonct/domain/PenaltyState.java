package fr.prog.progFonct.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Fonctionnal Prog of penalty system
 */
@Getter
@AllArgsConstructor
public final class PenaltyState {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PenaltyState.class);
	
    private final String teamA;
    private final String teamB;
    private final int scoreTeamA;
    private final int scoreTeamB;
    private boolean done = false;

    public PenaltyState(String teamA, String teamB, int scoreTeamA, int scoreTeamB) {
    	this.teamA = teamA;
    	this.teamB = teamB;
    	this.scoreTeamA = scoreTeamA;
    	this.scoreTeamB = scoreTeamB;
	}
    
    /**
     * Recreate {@link PenaltyState} with updated score and remainingAttempts for TeamA
     * @return
     */
    public PenaltyState successAttemptTeamA() {
        return new PenaltyState(teamA, teamB, scoreTeamA + 1, scoreTeamB);
    }

    /**
     * Recreate {@link PenaltyState} with updated remainingAttempts for TeamA
     * @return
     */
    public PenaltyState failedAttemptTeamA() {
        return new PenaltyState(teamA, teamB, scoreTeamA, scoreTeamB);
    }

    /**
     * Recreate {@link PenaltyState} with updated score and remainingAttempts for TeamB
     * @return
     */
    public PenaltyState successAttemptTeamB() {
        return new PenaltyState(teamA, teamB, scoreTeamA, scoreTeamB + 1);
    }

    /**
     * Recreate {@link PenaltyState} with updated remainingAttempts for TeamB
     * @return
     */
    public PenaltyState failedAttemptTeamB() {
        return new PenaltyState(teamA, teamB, scoreTeamA, scoreTeamB);
    }

    public void printScore() {
    	LOGGER.info("Attempt result : " + teamA + " " + scoreTeamA + " - " + scoreTeamB + " " + teamB);
    }
    
    public void printFinalResult() {
        printScore();
        if (scoreTeamA > scoreTeamB) {
        	LOGGER.info(teamA + " won on penalties !");
        } else if (scoreTeamB > scoreTeamA) {
        	LOGGER.info(teamB + " won on penalties !");
        } else {
        	LOGGER.info("Egality !");
        }
        this.done = true;
    }

    /**
     * Recreate state to keep history
     * @param newState
     * @return
     */
	public PenaltyState recreate(PenaltyState newState) {
		return new PenaltyState(teamA, teamB, 
				scoreTeamA + newState.getScoreTeamA(), 
				scoreTeamB + newState.getScoreTeamB());
	}
	
}