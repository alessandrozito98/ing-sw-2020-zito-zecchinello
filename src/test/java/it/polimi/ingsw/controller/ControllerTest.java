package it.polimi.ingsw.controller;

import it.polimi.ingsw.connection.Server;
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
import java.net.Socket;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class ControllerTest {

    ArrayList<SocketClientConnection> waitingConnectionTest = new ArrayList<SocketClientConnection>();
    Board boardTest = new Board();
    Game gameTest = new Game(boardTest);
    Controller controllerTest;
    Worker workerTest;
    Worker workerTest2;

    ArrayList<Worker> workersTest;

    God godCardTest;
    Player playerTest;
    Player playerTest2;
    Player playerTest3;
    View view;

    SocketClientConnection connectionTest;

    @Before
    public void setUp () throws IOException {
        controllerTest = new Controller(gameTest,waitingConnectionTest);

        workerTest = new Worker(1,WorkerColor.RED);
        workerTest2 = new Worker(2, WorkerColor.RED);
        workerTest.setPosition(boardTest.getCell(1,1));
        workerTest2.setPosition(boardTest.getCell(0,0));

        boardTest.getCell(1,1).addWorker(workerTest);
        boardTest.getCell(0,0).addWorker(workerTest2);

        workersTest = new ArrayList<Worker>();
        workersTest.add(workerTest);
        workersTest.add(workerTest2);

        godCardTest = new Pan(gameTest);

        playerTest = new Player("A", 1, workersTest, godCardTest);
        playerTest2 = new Player("B",2,workersTest, godCardTest);
        playerTest3 = new Player("C",3,workersTest,godCardTest);

        gameTest.addPlayer(playerTest);
        gameTest.addPlayer(playerTest2);
        gameTest.addPlayer(playerTest3);

        connectionTest = new SocketClientConnection(new Socket(), new Server());
        view = new RemoteView(playerTest, connectionTest, gameTest.getBoardCopy());
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
    public void handleMove() throws IOException {
        controllerTest.setPlayerTurn(1);
        MoveRequest moveRequestTest = new MoveRequest(view, playerTest, workerTest.getWorkerNumber(), boardTest.getCell(1,2).getX(), boardTest.getCell(1,2).getY());
        controllerTest.handleMove(moveRequestTest);
        assertEquals(workerTest.getPosition().getX(), 1);
        assertEquals(workerTest.getPosition().getY(),2);
    }

    @Test
    public void handleBuild() throws IOException {
        controllerTest.setPlayerTurn(1);
        BuildRequest buildRequestTest = new BuildRequest(view, playerTest, workerTest.getWorkerNumber(), boardTest.getCell(1,2).getX(), boardTest.getCell(1,2).getY(), Level.LEVEL1);
        controllerTest.handleBuild(buildRequestTest);
        assertEquals(boardTest.getCell(1,2).getLevel(), Level.LEVEL1);
    }

    @Test
    public void manageTurn() {
        controllerTest.setPlayerTurn(1);
        EndTurnRequest endTurnRequestTest = new EndTurnRequest(playerTest);
        controllerTest.manageTurn(endTurnRequestTest);
        assertEquals(controllerTest.getPlayerTurn(),2);
    }

}