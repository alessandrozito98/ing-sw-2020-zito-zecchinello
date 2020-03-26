package it.polimi.ingsw.model;

public enum GodTypes {

    APOLLO ("God of Music", "Your Move: Your Worker may move into an opponent Worker’s space by forcing their Worker to the space yours just vacated."),
    ARTEMIS ("Goddes of the Hunt","Your Move: Your Worker may move one additional time, but not back to its initial space"),
    ATHENA("Goddess of Wisdom","Opponent’s Turn: If one of your Workers moved up on your last turn, opponent Workers cannot move up this turn" ),
    ATLAS("Titan Shouldering the Heaven","Your Build: Your Worker may build a dome at any level. "),
    DEMETER("Goddess of the Harvest","Your Build: Your Worker may build one additional time, but not on the same space"),
    HEPHAESTUS("God of Blacksmiths","Your Build: Your Worker may build one additional block (not dome) on top of your first block"),
    MINOTAUR("Bull-headed Monster", "Your Move: Your Worker may move into an opponent Worker’s space, if their Worker can be forced one space straight backwards to an unoccupied space at any level."),
    PAN("God of the Wild","Win Condition: You also win if your Worker moves down two or more levels."),
    PROMETHEUS("Titan Benefactor of Mankind","Your Turn: If your Worker does not move up, it may build both before and after moving");

    private String description;
    private String power;

    GodTypes(String description, String power) {
        this.description = description;
        this.power = power;
    }

    public String getDescription() {
        return description;
    }

    public String getPower() {
        return power;
    }
}
