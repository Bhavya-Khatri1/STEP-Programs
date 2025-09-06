import java.util.*;
enum CrewRank {
    CADET(1), OFFICER(2), COMMANDER(3), CAPTAIN(4), ADMIRAL(5);
    private final int level;
    CrewRank(int level) { this.level = level; }
    public int getLevel() { return level; }
}
class SpaceCrew {
    private final String crewId;
    private final String homePlanet;
    private final CrewRank initialRank;

    private String name;
    private CrewRank currentRank;
    private int skillLevel;
    private int missionCount;
    private int spaceHours;

    private static final String STATION_NAME = "Stellar Odyssey";
    private static final int MAX_CREW_CAPACITY = 50;
    private static int totalCrew = 0;
    public SpaceCrew(String name) {
        this(name, getRandomPlanet(), CrewRank.CADET, 0, 0, 0);
    }
    public SpaceCrew(String name, String homePlanet, CrewRank rank) {
        this(name, homePlanet, rank, 1, 0, 0);
    }
    public SpaceCrew(String name, String homePlanet, CrewRank rank, int skill, int missions) {
        this(name, homePlanet, rank, skill, missions, missions * 20);
    }
    public SpaceCrew(String name, String homePlanet, CrewRank rank, int skill, int missions, int hours) {
        this.crewId = UUID.randomUUID().toString().substring(0, 6).toUpperCase();
        this.homePlanet = homePlanet;
        this.initialRank = rank;
        this.name = name;
        this.currentRank = rank;
        this.skillLevel = skill;
        this.missionCount = missions;
        this.spaceHours = hours;
        totalCrew++;
    }
    private static String getRandomPlanet() {
        String[] planets = {"Earth", "Mars", "Venus", "Jupiter", "Neptune"};
        return planets[new Random().nextInt(planets.length)];
    }
    public final String getCrewIdentification() {
        return "ID: " + crewId + " | " + name + " from " + homePlanet;
    }
    public final boolean canBePromoted() {
        return currentRank.getLevel() < CrewRank.ADMIRAL.getLevel();
    }
    public final int calculateSpaceExperience() {
        return (missionCount * 10) + (spaceHours / 5) + skillLevel;
    }
    public void showProfile() {
        System.out.printf("[%s] %s | Rank: %s | Skill: %d | Missions: %d | Hours: %d | Exp: %d%n",
                crewId, name, currentRank, skillLevel, missionCount, spaceHours, calculateSpaceExperience());
    }
    public static int getTotalCrew() {
        return totalCrew;
    }
}
public class SpaceStation {
    public static void main(String[] args) {
        SpaceCrew c1 = new SpaceCrew("Aria");
        SpaceCrew c2 = new SpaceCrew("Zane", "Mars", CrewRank.OFFICER);
        SpaceCrew c3 = new SpaceCrew("Luna", "Venus", CrewRank.CAPTAIN, 80, 12);
        c1.showProfile();
        c2.showProfile();
        c3.showProfile();
        System.out.println("\nTotal Crew Members: " + SpaceCrew.getTotalCrew());
    }
}