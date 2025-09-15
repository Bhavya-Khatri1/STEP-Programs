final class CrewRank {
    private final String rankName;
    private final int level;
    private final String[] permissions;

    private CrewRank(String rankName, int level, String[] permissions) {
        this.rankName = rankName;
        this.level = level;
        this.permissions = permissions.clone();
    }

    public static CrewRank createCadet() {
        return new CrewRank("Cadet", 1, new String[]{"Basic Access"});
    }

    public static CrewRank createOfficer() {
        return new CrewRank("Officer", 2, new String[]{"Command Room", "Lab"});
    }

    public static CrewRank createCaptain() {
        return new CrewRank("Captain", 3, new String[]{"All Areas"});
    }

    public String getRankName() { return rankName; }
    public int getLevel() { return level; }
    public String[] getPermissions() { return permissions.clone(); }

    @Override
    public String toString() {
        return "Rank: " + rankName + " (Level " + level + ")";
    }
}