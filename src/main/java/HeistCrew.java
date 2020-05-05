
import java.util.ArrayList;
import java.util.List;

public class HeistCrew {
    private int count;

    // Used as identity
    private List<CrewMember> crewMembers = new ArrayList<>();

    // With side effect
    private int surgicalKitsLeft = 3;

    public int count() {
        return count;
    }

    public void enroll(String codeName, int clips, int ammoPerClip) {
        crewMembers.add(new CrewMember(codeName));
        int index=count;
        crewMembers.get(index).setClipsLeft(Math.max(0, clips - 1));
        crewMembers.get(index).setAmmoPerClip(ammoPerClip);
        crewMembers.get(index).setAmmoLeftInLoadedClip(ammoPerClip);
        count++;
    }

    public int indexOf(String codeName) {
        return crewMembers.stream()
                .filter(m -> codeName.equals(m.codeName))
                .findFirst()
                .map(m -> crewMembers.indexOf(m))
                .orElse(-1);
    }

/*    public int indexOf(String codeName) {
        return crewMembers.stream()
                .filter(m -> codeName.equals(m.codeName))
                .findFirst()
                .map(m -> crewMembers.indexOf(m))
                .orElse(-1);
    }*/
    public String nameOf(int index) {
        return crewMembers.get(index).getCodeName();
    }


    public void setWounded(int index) {
        crewMembers.get(index).setWounded(true);
    }

    public void heal(int index) {
        boolean consume = !consumeSurgicalKit();
        crewMembers.get(index).setWounded(consume);
    }

    private boolean consumeSurgicalKit() {
        if (surgicalKitsLeft == 0) return false;
        surgicalKitsLeft--;
        return true;
    }

    public boolean isHealthy(int index) {
        return crewMembers.get(index).isHealthy();
    }


    public int fire(int index, int ammo) {
        return crewMembers.get(index).fire(ammo);
    }

    public int ammoLeft(int index) {
        return crewMembers.get(index).ammoLeft();
    }

    public int clipsLeft(int index) {
        return crewMembers.get(index).getClipsLeft();
    }
}
