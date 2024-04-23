package oop.collections;

import java.util.Comparator;

public class SeverityComparator implements Comparator<Surgery> {
    @Override
    public int compare(Surgery o1, Surgery o2) {
        return Integer.compare(o1.getSeverity(), o2.getSeverity());
    }
}
