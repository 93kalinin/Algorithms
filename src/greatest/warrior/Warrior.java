package greatest.warrior;

import java.util.ArrayList;
import java.util.List;

public class Warrior {
    public static final int MAX_XP = 10_000;
    public static final int XP_PER_LEVEL = 100;

    private int experience = XP_PER_LEVEL;
    private final List<String> achievements = new ArrayList<>();

    public int level() {
        return experience / XP_PER_LEVEL;
    }

    public int experience() {
        return experience;
    }

    public String rank() {
        int level = level();
        for (Rank rank : Rank.values()) {
            if (level > rank.minLevel && level < rank.maxLevel) {
                return rank.toString();
            }
        }
        throw new IllegalStateException("Failed to resolve rank for level " + level);
    }

    public void training(String trainingName, int xpForVictory, int opponentLevel) {
        
    }

    private void addXp(int xpArg) {
        int sum = xpArg + experience;
        experience = (sum >= MAX_XP) ? MAX_XP : sum;
    }
}