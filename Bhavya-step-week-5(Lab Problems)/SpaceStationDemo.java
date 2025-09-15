public class SpaceStationDemo {
    public static void main(String[] args) {
        SecurityClearance c1 = new SecurityClearance("CL1", "High", new String[]{"Dock", "Lab", "Bridge"}, System.currentTimeMillis() + 500000);
        CrewRank r1 = CrewRank.createOfficer();

        SpaceCrew crew1 = new SpaceCrew("Crew01", "Earth", r1, c1);
        System.out.println(crew1);

        System.out.println("Access to Bridge? " + crew1.canAccess("Bridge"));
        crew1.setMissionCount(5);
        crew1.setSpaceHours(120.5);
        System.out.println("Updated Crew Info: " + crew1);
    }
}