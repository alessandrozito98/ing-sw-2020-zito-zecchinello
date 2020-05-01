package it.polimi.ingsw.observer;

import it.polimi.ingsw.observer.messages.*;

public interface Observer {

// UPDATE DELLA VIEW A SEGUITO DI NOTIFY DEL MODEL
    void updateBoardChange(BoardChange message);
    void updateStartTurn(StartTurn message);
    void updatePlayerLose(PlayerLose message);

// UPDATE DEL CONTROLLER A SEGUITO DI NOTIFY DELLA VIEW
    void updateMoveRequest(MoveRequest message);
    void updateBuildRequest(BuildRequest message);
    void updateEndTurnRequest(EndTurnRequest message);
}
