import java.util.*;

final class SecurityClearance {
    private final String clearanceId;
    private final String level;
    private final String[] authorizedSections;
    private final long expirationDate;

    public SecurityClearance(String clearanceId, String level, String[] authorizedSections, long expirationDate) {
        if (clearanceId == null || level == null || authorizedSections == null) {
            throw new IllegalArgumentException("Invalid data for clearance");
        }
        this.clearanceId = clearanceId;
        this.level = level;
        this.authorizedSections = authorizedSections.clone();
        this.expirationDate = expirationDate;
    }

    public String getClearanceId() { return clearanceId; }
    public String getLevel() { return level; }
    public String[] getAuthorizedSections() { return authorizedSections.clone(); }
    public long getExpirationDate() { return expirationDate; }

    public final boolean canAccess(String section) {
        return Arrays.asList(authorizedSections).contains(section);
    }

    public final boolean isExpired() {
        return System.currentTimeMillis() > expirationDate;
    }

    @Override
    public String toString() {
        return "Clearance[" + clearanceId + ", Level=" + level + "]";
    }
}