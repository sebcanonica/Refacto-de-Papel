import java.util.Arrays;

public class HeistCrew {
    private int count;
    final private int MAX_MEMBERS = 7;

    // Used as identity
    private String[] codeNames = new String[MAX_MEMBERS];

    // With side effect
    private boolean[] wounded = new boolean[MAX_MEMBERS];
    private int surgicalKitsLeft = 3;

    // Several correlated members
    private int[] ammoPerClip = new int[MAX_MEMBERS];
    private int[] ammoLeftInLoadedClip = new int[MAX_MEMBERS];
    private int[] clipsLeft = new int[MAX_MEMBERS];

    public int count() {
        return count;
    }

    public void enroll(String codeName, int clips, int ammoPerClip) {
        codeNames[count] = codeName;
        clipsLeft[count] = Math.max(0, clips - 1);
        this.ammoPerClip[count] = ammoPerClip;
        ammoLeftInLoadedClip[count] = ammoPerClip;
        count++;
    }

    public int indexOf(String codeName) {
        return Arrays.asList(codeNames).indexOf(codeName);
    }

    public String nameOf(int index) {
        return codeNames[index];
    }


    public void setWounded(int index) {
        wounded[index] = true;
    }

    public void heal(int index) {
        if (surgicalKitsLeft > 0) {
            surgicalKitsLeft--;
            wounded[index] = false;
        }
    }

    public boolean isHealthy(int index) {
        return ! wounded[index];
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
