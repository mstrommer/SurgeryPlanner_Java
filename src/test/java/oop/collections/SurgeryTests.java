package oop.collections;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class SurgeryTests {

    public static final Surgery SEVERITY_LEVEL_TWO_SURGERY = new Surgery(1, "Make plaster cast", 2, 15, true);
    public static final Surgery SEVERITY_LEVEL_ONE_SURGERY = new Surgery(2, "Change Plaster", 1, 10, false);
    public static final Surgery ANOTHER_SEVERITY_LEVEL_ONE_SURGERY = new Surgery(2, "Change Plaster", 1, 10, false);

    @Test
    public void compareTo_otherHasHigherSeverity_returnsOne() {
        assertThat(SEVERITY_LEVEL_TWO_SURGERY.compareTo(SEVERITY_LEVEL_ONE_SURGERY)).isEqualTo(1);
    }

    @Test
    public void compareTo_otherHasLessSeverity_returnsMinusOne() {
        assertThat(SEVERITY_LEVEL_ONE_SURGERY.compareTo(SEVERITY_LEVEL_TWO_SURGERY)).isEqualTo(-1);
    }

    @Test
    public void compareTo_otherHasSameSeverity_returnsZero() {
        assertThat(SEVERITY_LEVEL_ONE_SURGERY.compareTo(ANOTHER_SEVERITY_LEVEL_ONE_SURGERY)).isEqualTo(0);
    }

}
