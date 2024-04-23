package oop.collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class SurgeryPlanner {
    private ArrayList<Surgery> surgeries;

    public void registerSurgery(Surgery s){
        surgeries.add(s);
    }

    public void unregisterSurgery(Surgery s){
        surgeries.remove(s);
    }

    public List<Surgery> getSurgeriesLongerThan(int duration){
        List<Surgery> list = new ArrayList<>();
        /*
        for (Surgery tmp : this.surgeries) {
            if (tmp.getDuration() > duration) {
                list.add(tmp);
            }
        }
        */
        list = this.surgeries.stream()
                .filter(tmp -> tmp.getDuration() > duration)
                .collect(Collectors.toList());
        return list;
    }

    public ArrayList<Surgery> allRegisteredSurgeries() {
        return surgeries;
    }

    public List<Surgery> surgeriesOrderedBySeverity(){
        // Neue Liste
        // Die alte soll nicht sortiert werden
        List<Surgery> list = new ArrayList<>(surgeries);
        list.sort(null);
        return list;
    }
    public List<Surgery> surgeriesNeedingDoctor(){
        List<Surgery> list = new ArrayList<>();
        for (Surgery tmp : this.surgeries) {
            if (tmp.isNeedsDoctor()) {
                list.add(tmp);
            }
        }
        /*
        list = this.surgeries.stream()
                .filter(tmp -> tmp.isNeedsDoctor())
                .collect(Collectors.toList());
        */
        return list;
    }

}
