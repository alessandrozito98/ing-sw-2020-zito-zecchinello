package it.polimi.ingsw.model;

import static it.polimi.ingsw.utils.Colors.*;

/**
 * This class represent a level of a building.
 */
public enum Level {
    GROUND(ANSI_BRIGHT_BG_GREEN), LEVEL1(ANSI_BG_PURPLE), LEVEL2(ANSI_BG_CYAN), LEVEL3(ANSI_BG_YELLOW), DOME(ANSI_BRIGHT_BG_BLACK);

    public final String label;

    Level(String label){
        this.label = label;
    }
}
