package greatest.warrior;

import static greatest.warrior.Warrior.LEVEL_IS_INVALID;

public enum Rank {
    PUSHOVER(1, 9),
    NOVICE(10, 19),
    FIGHTER(20, 29),
    WARRIOR(30, 39),
    VETERAN(40, 49),
    SAGE(50, 59),
    ELITE(60, 69),
    CONQUEROR(70, 79),
    CHAMPION(80, 89),
    MASTER(90, 99),
    GREATEST(100, 100);

    final int minLevel, maxLevel;

    Rank(int minLevel, int maxLevel) {
        this.minLevel = minLevel;
        this.maxLevel = maxLevel;
    }

    /**
     * @return a rank name with the first letter being upper-case end the rest of them lower-case,
     * e.g. SAGE -> Sage
     */
    @Override
    public String toString() {
        String name = super.toString()
                .toLowerCase();
        return name.substring(0,1).toUpperCase()
                + name.substring(1).toLowerCase();
    }

    static Rank resolve(int level) {
        if (LEVEL_IS_INVALID.test(level)) {
            throw new IllegalArgumentException("Invalid level " + level);
        }
        for (Rank rank : Rank.values()) {
            if (level >= rank.minLevel && level <= rank.maxLevel) {
                return rank;
            }
        }
        throw new RuntimeException("This part of code should never be reached. " +
                "Make sure that level to rank mapping is correct");
    }
}
