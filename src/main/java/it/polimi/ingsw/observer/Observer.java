package it.polimi.ingsw.observer;

import it.polimi.ingsw.observer.messages.*;

public interface Observer {

// UPDATE DELLA VIEW A SEGUITO DI NOTIFY DEL MODEL
    default void updateBoardChange(BoardChange message){
        throw UnsupportedOperationException;
    }
    default void updateStartTurn(StartTurn message) {
        throw UnsupportedOperationException;
    }
    default void updatePlayerLose(PlayerLose message) {
        throw UnsupportedOperationException;
    }

// UPDATE DEL CONTROLLER A SEGUITO DI NOTIFY DELLA VIEW
    default void updateMoveRequest(MoveRequest message) {
        throw UnsupportedOperationException;
    }
    default void updateBuildRequest(BuildRequest message) {
        throw UnsupportedOperationException;
    }
    default void updateEndTurnRequest(EndTurnRequest message) {
        throw UnsupportedOperationException;
    }
}
