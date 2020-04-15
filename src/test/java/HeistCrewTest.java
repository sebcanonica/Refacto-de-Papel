import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

public class HeistCrewTest {
    private HeistCrew crew = new HeistCrew();

    @Test
    public void can_be_empty() {
        assertThat(crew.count()).isEqualTo(0);
    }

    @Test
    public void can_have_7_members() {
        enrollFullSquad();
        assertThat(crew.count()).isEqualTo(7);
    }

    @Test
    public void query_by_code_name() {
        enrollFullSquad();
        assertThat(crew.indexOf("Ber" + "lin")).isEqualTo(4);
        assertThat(crew.indexOf("Stockholm")).isEqualTo(-1);
    }

    @Test
    public void get_code_name() {
        enrollFullSquad();
        assertThat(crew.nameOf(4)).isEqualTo("Berlin");
    }

    @Test
    public void heal_wounded() {
        enrollFullSquad();
        crew.setWounded(0);
        crew.heal(0);
        assertThat(crew.isHealthy(0)).isTrue();
    }

    @Test
    public void cannot_heal_more_than_3_times() {
        enrollFullSquad();
        IntStream.range(0, 4).forEach(i -> {
            crew.setWounded(i);
            crew.heal(i);
        });
        assertThat(crew.isHealthy(3)).isFalse();
    }

    @ParameterizedTest
    @CsvSource({
            "0, 6, 6, 54, 8",
            "1, 15, 15, 135, 4",
            "2, 12, 12, 48, 7",
            "3, 20, 20, 85, 5",
            "4, 10, 5, 0, 0",
            "6, 10, 0, 0, 0"
    })
    public void order_fire(int index, int ammo, int expectedFired, int expectedAmmoLeft, int expectedClipLeft) {
        enrollFullSquad();
        int bulletFired = crew.fire(index, ammo);
        assertThat(bulletFired).isEqualTo(expectedFired);
        assertThat(crew.ammoLeft(index)).isEqualTo(expectedAmmoLeft);
        assertThat(crew.clipsLeft(index)).isEqualTo(expectedClipLeft);
    }

    private void enrollFullSquad() {
        crew.enroll("Tokyo", 10, 6);
        crew.enroll("Nairobi", 5, 30);
        crew.enroll("Denver", 10, 6);
        crew.enroll("Rio", 7, 15);
        crew.enroll("Berlin", 5, 1);
        crew.enroll("Helsinki", 2, 100);
        crew.enroll("Moscow", 0, 0);
    }
}
