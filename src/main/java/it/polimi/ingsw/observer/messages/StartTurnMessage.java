package it.polimi.ingsw.observer.messages;

public class StartTurnMessage {
    private int playerID;
    private int availableMoveNumber;
    private int availableBuildNumber;
    private boolean hasMoved;
    private boolean hasBuilt;

    public StartTurnMessage(int playerID, int availableMoveNumber, int availableBuildNumber, boolean hasMoved, boolean hasBuilt){
        this.playerID = playerID;
        this.availableMoveNumber = availableMoveNumber;
        this.availableBuildNumber = availableBuildNumber;
        this.hasMoved = hasMoved;
        this.hasBuilt = hasBuilt;
    }

    public int getPlayerID() {
        return playerID;
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
