public class VotingSystem {
    public void processVote(String voterId, String candidate) {
        class VoteValidator {
            public boolean validate() {
                return voterId != null && voterId.matches("VOT\\d{4}");
            }
        }

        VoteValidator validator = new VoteValidator();
        if (validator.validate()) {
            System.out.println("Vote accepted for " + candidate + " from " + voterId);
        } else {
            System.out.println("Invalid voter ID: " + voterId + ". Vote rejected.");
        }
    }

    public static void main(String[] args) {
        VotingSystem vs = new VotingSystem();
        vs.processVote("VOT1234", "Candidate A");
        vs.processVote("ABC999", "Candidate B");
    }
}