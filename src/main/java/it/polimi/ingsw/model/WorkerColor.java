package it.polimi.ingsw.model;

import static it.polimi.ingsw.utils.Colors.*;

/**
 * This class is used to give a color to the workers.
 */
public enum WorkerColor {
    WHITE(ANSI_BG_BLACK), RED(ANSI_BG_RED), BLUE(ANSI_BG_BLUE);

    public final String label;

    WorkerColor(String label) {
        this.label = label;
    }
}
