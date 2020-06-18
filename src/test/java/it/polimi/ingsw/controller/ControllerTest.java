package it.polimi.ingsw.controller;

import it.polimi.ingsw.connection.SocketClientConnection;
import it.polimi.ingsw.model.*;
import it.polimi.ingsw.model.God.God;
import it.polimi.ingsw.model.God.Pan;
import it.polimi.ingsw.observer.messages.BuildRequest;
import it.polimi.ingsw.observer.messages.EndTurnRequest;
import it.polimi.ingsw.observer.messages.MoveRequest;
import it.polimi.ingsw.view.RemoteView;
import it.polimi.ingsw.view.View;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class ControllerTest {

    Board board = new Board();
    Game gameTest;
    Controller controllerTest = new Controller(gameTest,());
    Worker workerTest;
    Worker workerTest2;

    ArrayList<Worker> workersTest;

    God godCardTest;
    Player playerTest;
    Player playerTest2;
    Player playerTest3;
    View view;

    MoveRequest moveRequestTest;
    BuildRequest buildRequestTest;
    EndTurnRequest endTurnRequestTest;

    @Before
    public void setUp () {

        gameTest = new Game(board);

        workerTest = new Worker(1,WorkerColor.RED);
        workerTest2 = new Worker(2, WorkerColor.RED);


        workersTest = new ArrayList<Worker>();
        workersTest.add(workerTest);
        workersTest.add(workerTest2);

        godCardTest = new Pan(gameTest);

        playerTest = new Player("A", 1, workersTest, godCardTest);
        playerTest2 = new Player("B",2,workersTest, godCardTest);
        playerTest3 = new Player("C",3,workersTest,godCardTest);

        moveRequestTest = new MoveRequest(view, playerTest, 1,1 ,1);
        buildRequestTest = new BuildRequest(view, playerTest,1,1,1,Level.LEVEL1);
        endTurnRequestTest = new EndTurnRequest(playerTest);

        gameTest.addPlayer(playerTest);
        gameTest.addPlayer(playerTest2);
        gameTest.addPlayer(playerTest3);
    }

    @Test
    public void getAndSetPlayerTurn() {
        controllerTest.setPlayerTurn(3);
        assertEquals(controllerTest.getPlayerTurn(), 3);
    }

    @Test
    public void getAndSetChosenWorker() {
        controllerTest.setChosenWorker(2);
        assertEquals(controllerTest.getChosenWorker(), 2);
    }

    @Test
    public void handleMove() {
    }

    @Test
    public void handleBuild() {
    }

    @Test
    public void manageTurn() {

    }

    @Test
    public void loseControl() {

    }
}