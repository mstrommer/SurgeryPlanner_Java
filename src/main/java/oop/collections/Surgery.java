package oop.collections;

public class Surgery implements Comparable<Surgery>{
    private int id;
    private String title;
    private int severity;
    private int duration;
    private boolean needsDoctor;
    public Surgery(int id, String title, int severity, int duration, boolean needsDoctor) {
        this.id = id;
        this.title = title;
        this.severity = severity;
        this.duration = duration;
        this.needsDoctor = needsDoctor;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getSeverity() {
        return severity;
    }

    public int getDuration() {
        return duration;
    }

    public boolean isNeedsDoctor() {
        return needsDoctor;
    }

    @Override
    public int compareTo(Surgery o) {
        return Integer.compare(this.severity, o.severity);
    }
}
