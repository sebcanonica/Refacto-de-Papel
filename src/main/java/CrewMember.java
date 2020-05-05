public class CrewMember {
    public String codeName;
    public boolean wounded;
    private int ammoLeftInLoadedClip;
    private int ammoPerClip;
    private int clipsLeft;

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

    void setClipsLeft(int clipsLeft) {
        this.clipsLeft=clipsLeft;
    }

    int getClipsLeft() {
        return clipsLeft;
    }

    void setAmmoPerClip(int ammoPerClip) {
        this.ammoPerClip = ammoPerClip;
    }

    int getAmmoPerClip() {
        return ammoPerClip;
    }

    void setAmmoLeftInLoadedClip(int ammoLeftInLoadedClip) {
        this.ammoLeftInLoadedClip = ammoLeftInLoadedClip;
    }

    int getAmmoLeftInLoadedClip() {
        return this.ammoLeftInLoadedClip;
    }

    public int fire(int ammo) {
        int leftToFire = ammo;
        while (leftToFire > 0 && (getAmmoLeftInLoadedClip() > 0 || getClipsLeft() > 0)) {
            int firedFromLoadedClip = Math.min(leftToFire, getAmmoLeftInLoadedClip());
            leftToFire = leftToFire - firedFromLoadedClip;
            setAmmoLeftInLoadedClip(getAmmoLeftInLoadedClip() - firedFromLoadedClip);
            boolean shouldReload = getAmmoLeftInLoadedClip() == 0 && getClipsLeft() > 0;
            if (shouldReload) {
                setAmmoLeftInLoadedClip(getAmmoPerClip());
                setClipsLeft(getClipsLeft() - 1);
            }
        }
        return ammo - leftToFire;
    }

    public int ammoLeft() {
        return getClipsLeft() * getAmmoPerClip() + getAmmoLeftInLoadedClip();
    }
}
