package fr.prog.progFonct.utils;

import java.util.Random;

public class PenaltyUtils {
	
	public static boolean successfullShoot() {
        Random random = new Random();
        return random.nextBoolean();
    }
}
