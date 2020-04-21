
import java.util.ArrayList;
import java.util.List;

public class HeistCrew {
    private int count;
    final private int MAX_MEMBERS = 7;

    // Used as identity
    private List<CrewMember> crewMembers = new ArrayList<>();

    // With side effect
    private int surgicalKitsLeft = 3;

    // Several correlated members
    private int[] ammoPerClip = new int[MAX_MEMBERS];
    private int[] ammoLeftInLoadedClip = new int[MAX_MEMBERS];
    private int[] clipsLeft = new int[MAX_MEMBERS];

    public int count() {
        return count;
    }

    public void enroll(String codeName, int clips, int ammoPerClip) {
        crewMembers.add(new CrewMember(codeName));
        clipsLeft[count] = Math.max(0, clips - 1);
        this.ammoPerClip[count] = ammoPerClip;
        ammoLeftInLoadedClip[count] = ammoPerClip;
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
        int leftToFire = ammo;
        while (leftToFire > 0 && (ammoLeftInLoadedClip[index] > 0 || clipsLeft[index] > 0)) {
            int firedFromLoadedClip = Math.min(leftToFire, ammoLeftInLoadedClip[index]);
            leftToFire -= firedFromLoadedClip;
            ammoLeftInLoadedClip[index] -= firedFromLoadedClip;
            boolean shouldReload = ammoLeftInLoadedClip[index] == 0 && clipsLeft[index] > 0;
            if (shouldReload) {
                ammoLeftInLoadedClip[index] = ammoPerClip[index];
                clipsLeft[index]--;
            }
        }
        return ammo - leftToFire;
    }

    public int ammoLeft(int index) {
        return clipsLeft[index] * ammoPerClip[index] + ammoLeftInLoadedClip[index];
    }

    public int clipsLeft(int index) {
        return clipsLeft[index];
    }
}
