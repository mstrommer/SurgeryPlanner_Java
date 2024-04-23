package oop.collections;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class SurgeryPlannerTests {

    public static final Surgery HEART_TRANSPLANT = new Surgery(4, "Heart transplant", 4, 120, true);
    public static final Surgery LUNG_X_RAY = new Surgery(5, "Lung x-ray", 3, 45, false);
    public static final Surgery MAKE_PLASTER_CAST = new Surgery(1, "Make plaster cast", 2, 15, true);
    public static final Surgery CHANGE_PLASTER = new Surgery(2, "Change Plaster", 1, 10, false);
    public static final Surgery ROUTINE_HEALTH_CHECK = new Surgery(3, "Routine health check", 1, 30, true);
    public static final Surgery TICK_BORNE_VACCINATION = new Surgery(6, "Tick borne vaccination", 1, 10, false);

    @Test
    public void getAllRegisteredSurgeries_surgeriesAdded_allBeforeAddedSurgeriesReturned() {
        Surgery tickBorneVaccination = TICK_BORNE_VACCINATION;
        SurgeryPlanner instanceUnderTest = new SurgeryPlanner();

        instanceUnderTest.registerSurgery(MAKE_PLASTER_CAST);
        instanceUnderTest.registerSurgery(CHANGE_PLASTER);
        instanceUnderTest.registerSurgery(ROUTINE_HEALTH_CHECK);
        instanceUnderTest.registerSurgery(HEART_TRANSPLANT);
        instanceUnderTest.registerSurgery(LUNG_X_RAY);
        instanceUnderTest.registerSurgery(TICK_BORNE_VACCINATION);

        assertThat(instanceUnderTest.getAllRegisteredSurgeries()).contains(
                MAKE_PLASTER_CAST, CHANGE_PLASTER, ROUTINE_HEALTH_CHECK, HEART_TRANSPLANT, LUNG_X_RAY, tickBorneVaccination
        );
    }

    @Test
    public void getAllRegisteredSurgeries_surgeriesAddedAndRemoved_allBeforeAddedSurgeriesThatHaveNotBeenRemovedAreReturned() {
        SurgeryPlanner instanceUnderTest = new SurgeryPlanner();

        instanceUnderTest.registerSurgery(MAKE_PLASTER_CAST);
        instanceUnderTest.registerSurgery(CHANGE_PLASTER);
        instanceUnderTest.registerSurgery(ROUTINE_HEALTH_CHECK);
        instanceUnderTest.registerSurgery(HEART_TRANSPLANT);
        instanceUnderTest.registerSurgery(LUNG_X_RAY);
        instanceUnderTest.registerSurgery(TICK_BORNE_VACCINATION);
        instanceUnderTest.unregisterSurgery(MAKE_PLASTER_CAST);
        instanceUnderTest.unregisterSurgery(HEART_TRANSPLANT);
        instanceUnderTest.unregisterSurgery(TICK_BORNE_VACCINATION);

        assertThat(instanceUnderTest.getAllRegisteredSurgeries()).contains(
                CHANGE_PLASTER, ROUTINE_HEALTH_CHECK, LUNG_X_RAY
        );
    }

    @Test
    public void registerSurgery_passingNullReference_nullIsNotAddedToTheList() {
        SurgeryPlanner instanceUnderTest = new SurgeryPlanner();

        instanceUnderTest.registerSurgery(null);

        assertThat(instanceUnderTest.getAllRegisteredSurgeries()).isEmpty();
    }

    @Test
    public void unregisterSurgery_passingNullReference_noAttemptToRemoveIsMade() {
        Surgery brokenLeg = MAKE_PLASTER_CAST;
        SurgeryPlanner instanceUnderTest = new SurgeryPlanner();
        instanceUnderTest.registerSurgery(brokenLeg);

        instanceUnderTest.unregisterSurgery(null);

        assertThat(instanceUnderTest.getAllRegisteredSurgeries()).contains(brokenLeg);
    }

    @Test
    public void unregisterSurgery_passingReferenceThatIsNotContained_listOfSurgeriesRemainsUnchanged() {
        Surgery brokenLeg = MAKE_PLASTER_CAST;
        Surgery changePlaster = CHANGE_PLASTER;
        SurgeryPlanner instanceUnderTest = new SurgeryPlanner();
        instanceUnderTest.registerSurgery(brokenLeg);

        instanceUnderTest.unregisterSurgery(changePlaster);

        assertThat(instanceUnderTest.getAllRegisteredSurgeries()).contains(brokenLeg);
    }

    @Test
    public void getSurgeriesOrderedBySeverity_emitsTheAddedSurgeriesOrderedBySeverityStartingWithTheLessSevere() {
        SurgeryPlanner instanceUnderTest = new SurgeryPlanner();
        instanceUnderTest.registerSurgery(MAKE_PLASTER_CAST);
        instanceUnderTest.registerSurgery(CHANGE_PLASTER);
        instanceUnderTest.registerSurgery(HEART_TRANSPLANT);
        instanceUnderTest.registerSurgery(LUNG_X_RAY);

        List<Surgery> surgeriesOrdered = instanceUnderTest.getSurgeriesOrderedBySeverity();

        assertThat(surgeriesOrdered).containsExactlyElementsOf(
                new ArrayList<Surgery>() {{
                    add(CHANGE_PLASTER);
                    add(MAKE_PLASTER_CAST);
                    add(LUNG_X_RAY);
                    add(HEART_TRANSPLANT);
                }}
        );
    }

    @Test
    public void getSurgeriesNeedingDoctor_surgeriesThatAreNeedingADoctorHaveBeenRegistered_emitsProperListOfSurgeries()  {
        SurgeryPlanner instanceUnderTest = new SurgeryPlanner();
        instanceUnderTest.registerSurgery(MAKE_PLASTER_CAST);
        instanceUnderTest.registerSurgery(CHANGE_PLASTER);
        instanceUnderTest.registerSurgery(HEART_TRANSPLANT);
        instanceUnderTest.registerSurgery(LUNG_X_RAY);

        List<Surgery> surgeriesOrdered = instanceUnderTest.getSurgeriesNeedingDoctor();

        assertThat(surgeriesOrdered).contains(HEART_TRANSPLANT, MAKE_PLASTER_CAST);
    }

    @Test
    public void getSurgeriesLongerThan_durationSpecified_emitsTheAddedSurgeriesThatAreTakingLongerThanTheSpecifiedDuration() {
        SurgeryPlanner instanceUnderTest = new SurgeryPlanner();
        instanceUnderTest.registerSurgery(MAKE_PLASTER_CAST);
        instanceUnderTest.registerSurgery(CHANGE_PLASTER);
        instanceUnderTest.registerSurgery(HEART_TRANSPLANT);
        instanceUnderTest.registerSurgery(LUNG_X_RAY);
        int minimumDuration = 12;

        List<Surgery> surgeriesOrdered = instanceUnderTest.getSurgeriesLongerThan(minimumDuration);

        assertThat(surgeriesOrdered).contains(MAKE_PLASTER_CAST, HEART_TRANSPLANT, LUNG_X_RAY);
    }

}
