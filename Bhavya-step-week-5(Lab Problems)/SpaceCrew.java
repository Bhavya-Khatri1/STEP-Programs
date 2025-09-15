class SpaceCrew {
    private final String crewId;
    private final String homeplanet;
    private final SecurityClearance clearance;
    private final CrewRank initialRank;

    private CrewRank currentRank;
    private int missionCount;
    private double spaceHours;

    public static final String STATION_NAME = "Stellar Odyssey";
    public static final int MAX_CREW_CAPACITY = 50;

    // Constructor chaining
    public SpaceCrew(String crewId, String homeplanet) {
        this(crewId, homeplanet, CrewRank.createCadet(), new SecurityClearance("C-" + crewId, "Low", new String[]{"Dock"}, System.currentTimeMillis() + 1000000));
    }

    public SpaceCrew(String crewId, String homeplanet, CrewRank rank, SecurityClearance clearance) {
        this.crewId = crewId;
        this.homeplanet = homeplanet;
        this.initialRank = rank;
        this.currentRank = rank;
        this.clearance = clearance;
        this.missionCount = 0;
        this.spaceHours = 0;
    }

    // Getters/Setters
    public String getCrewId() { return crewId; }
    public String getHomeplanet() { return homeplanet; }
    public CrewRank getCurrentRank() { return currentRank; }
    public void setCurrentRank(CrewRank currentRank) { this.currentRank = currentRank; }

    public int getMissionCount() { return missionCount; }
    public void setMissionCount(int missionCount) { this.missionCount = missionCount; }

    public double getSpaceHours() { return spaceHours; }
    public void setSpaceHours(double spaceHours) { this.spaceHours = spaceHours; }

    public boolean canAccess(String section) {
        return clearance.canAccess(section);
    }

    @Override
    public String toString() {
        return "Crew[" + crewId + ", Planet=" + homeplanet + ", Rank=" + currentRank + "]";
    }
}