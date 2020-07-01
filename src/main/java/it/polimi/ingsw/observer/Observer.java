package it.polimi.ingsw.observer;

import it.polimi.ingsw.observer.messages.*;

import java.io.IOException;

public interface Observer {

    //View's updates after model's notifies
    default void updateBoardChange(BoardChange message) throws IOException {
        throw new UnsupportedOperationException();
    }

    default void updateResetTurn(ResetTurn message) {
        throw new UnsupportedOperationException();
    }

    default void updatePlayerLose(PlayerLose message) throws IOException {
        throw new UnsupportedOperationException();
    }

    default void updateWin(Win message) throws IOException {
        throw new UnsupportedOperationException();
    }

    //Controller's updates after view's notifies
    default void updateMoveRequest(MoveRequest message) throws IOException {
        throw new UnsupportedOperationException();
    }

    default void updateBuildRequest(BuildRequest message) throws IOException {
        throw new UnsupportedOperationException();
    }

    default void updateEndTurnRequest(EndTurnRequest message) {
        throw new UnsupportedOperationException();
    }

    default void updateEndGame() {
        throw new UnsupportedOperationException();
    }

}
