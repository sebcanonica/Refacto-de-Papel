public class CrewMember {
    public String codeName;
    public boolean wounded;

    public CrewMember(String codeName) {
        this.codeName = codeName;
    }

    String getCodeName() {
        return codeName;
    }

    void setWounded(boolean consume) {
        wounded = consume;
    }

    public boolean isHealthy() {
        return !wounded;
    }
}
