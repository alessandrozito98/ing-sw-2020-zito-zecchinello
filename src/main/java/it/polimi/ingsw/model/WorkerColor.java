package it.polimi.ingsw.model;

import static it.polimi.ingsw.utils.Colors.*;

public enum WorkerColor {
    WHITE(ANSI_BG_WHITE), RED(ANSI_BG_RED), BLUE(ANSI_BG_BLUE);

    public final String label;

    WorkerColor(String label) {
        this.label = label;
    }
}
