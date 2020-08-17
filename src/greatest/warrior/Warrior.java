package greatest.warrior;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.IntBinaryOperator;
import java.util.function.IntPredicate;

public class Warrior {
    public static final int MAX_XP = 10_000;
    public static final int XP_PER_LEVEL = 100;
    public static final int MAX_LEVEL_DIFFERENCE = 5;
    public static final IntPredicate LEVEL_IS_INVALID = level ->  level < 1 || level > 100;
    public static final IntBinaryOperator XP_GAIN_IF_ENEMY_LEVEL_HIGHER = (enemyLevel, ownLevel) -> {
        int levelDifference = enemyLevel - ownLevel;
        return 20 * levelDifference * levelDifference;
    };

    private int experience = XP_PER_LEVEL;
    private final List<String> achievements = new ArrayList<>();

    public int level() {
        return experience / XP_PER_LEVEL;
    }

    public int experience() {
        return experience;
    }

    public List<String> achievements() {
        return Collections.unmodifiableList(achievements);
    }

    public String rank() {
        int level = level();
        return Rank.resolve(level)
                .toString();
    }

    public String training(String trainingName, int xpGain, int minimumLevel) {
        if (this.level() >= minimumLevel) {
            addXp(xpGain);
            achievements.add(trainingName);
            return trainingName;
        } else {
            return "Not strong enough";
        }
    }

    public String battle(int enemyLevel) {
        if (LEVEL_IS_INVALID.test(enemyLevel)) return "Invalid level";
        int ownLevel = level();
        boolean enemyLevelTooHigh = enemyLevel >= ownLevel + MAX_LEVEL_DIFFERENCE;
        boolean enemyRankTooHigh = Rank.resolve(enemyLevel).minLevel > ownLevel;
        if (enemyLevelTooHigh && enemyRankTooHigh) return "You've been defeated";

        String fightResult = (ownLevel >= enemyLevel +2) ? "Easy fight"
                : (ownLevel == enemyLevel +1 || ownLevel == enemyLevel) ? "A good fight"
                : "An intense fight";
        int xpGain = (ownLevel == enemyLevel) ? 10
                : (ownLevel == enemyLevel +1) ? 5
                : (ownLevel >= enemyLevel +2) ? 0
                : XP_GAIN_IF_ENEMY_LEVEL_HIGHER.applyAsInt(enemyLevel, ownLevel);
        addXp(xpGain);
        return fightResult;
    }

    private void addXp(int xpArg) {
        int sum = xpArg + experience;
        experience = (sum >= MAX_XP) ? MAX_XP : sum;
    }
}