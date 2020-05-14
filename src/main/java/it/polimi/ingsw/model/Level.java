package it.polimi.ingsw.model;

public enum Level {
    GROUND("G"), LEVEL1("1"), LEVEL2("2"), LEVEL3("3"), DOME("D");

    public final String label;

    Level(String label){
        this.label = label;
    }
}
