package it.polimi.ingsw.model;

public enum WorkerColor {
    WHITE("w"), RED("r"), BLUE("b");

    public final String label;

    WorkerColor(String label) {
        this.label = label;
    }
}
