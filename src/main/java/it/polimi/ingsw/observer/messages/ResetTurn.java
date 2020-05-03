package it.polimi.ingsw.observer.messages;

public class ResetTurn {
    private final int availableMoveNumber;
    private final int availableBuildNumber;
    private final boolean hasMoved;
    private final boolean hasBuilt;

    public ResetTurn(int availableMoveNumber, int availableBuildNumber, boolean hasMoved, boolean hasBuilt){
        this.availableMoveNumber = availableMoveNumber;
        this.availableBuildNumber = availableBuildNumber;
        this.hasMoved = hasMoved;
        this.hasBuilt = hasBuilt;
    }

    public int getAvailableMoveNumber() {
        return availableMoveNumber;
    }

    public int getAvailableBuildNumber() {
        return availableBuildNumber;
    }

    public boolean getHasMoved() {
        return hasMoved;
    }

    public boolean getHasBuilt() {
        return hasBuilt;
    }
}
