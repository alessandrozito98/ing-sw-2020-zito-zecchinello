package it.polimi.ingsw.observer;

import it.polimi.ingsw.observer.messages.*;

public interface Observer {

// UPDATE DELLA VIEW A SEGUITO DI NOTIFY DEL MODEL
    default void updateBoardChange(BoardChange message){
        throw new UnsupportedOperationException();
    }
    default void updateResetTurn(ResetTurn message) {
        throw new UnsupportedOperationException();
    }
    default void updatePlayerLose(PlayerLose message) {
        throw new UnsupportedOperationException();
    }

// UPDATE DEL CONTROLLER A SEGUITO DI NOTIFY DELLA VIEW
    default void updateMoveRequest(MoveRequest message) {
        throw new UnsupportedOperationException();
    }
    default void updateBuildRequest(BuildRequest message) {
        throw new UnsupportedOperationException();
    }
    default void updateEndTurnRequest(EndTurnRequest message) {
        throw new UnsupportedOperationException();
    }
}
