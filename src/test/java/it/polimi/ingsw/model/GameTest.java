package it.polimi.ingsw.model;

import it.polimi.ingsw.model.God.Apollo;
import it.polimi.ingsw.model.God.Artemis;
import it.polimi.ingsw.model.God.God;
import it.polimi.ingsw.model.God.Pan;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class GameTest {

    Board board = new Board();
    Game gameTest;
    Worker workerTest;
    Worker workerTest2;
    Worker workerTest3;
    Worker workerTest4;
    Worker workerTest5;
    Worker workerTest6;

    ArrayList<Worker> workersTest;
    ArrayList<Worker> workersTest2;
    ArrayList<Worker> workersTest3;

    God godCardTest;
    God godCardTest2;
    God godCardTest3;

    Player playerTest;
    Player playerTest2;
    Player playerTest3;

    @Before
    public void setUp() {
        gameTest = new Game(board);
        workerTest = new Worker(1, WorkerColor.RED);
        workerTest2 = new Worker(2, WorkerColor.RED);
        workerTest3 = new Worker(1, WorkerColor.BLUE);
        workerTest4 = new Worker(2, WorkerColor.BLUE);
        workerTest5 = new Worker(1, WorkerColor.WHITE);
        workerTest6 = new Worker(2, WorkerColor.WHITE);

        workersTest = new ArrayList<>();
        workersTest2 = new ArrayList<>();
        workersTest3 = new ArrayList<>();
        workersTest.add(workerTest);
        workersTest.add(workerTest2);
        workersTest2.add(workerTest3);
        workersTest2.add(workerTest4);
        workersTest3.add(workerTest5);
        workersTest3.add(workerTest6);

        godCardTest = new Pan(gameTest);
        godCardTest2 = new Apollo(gameTest);
        godCardTest3 = new Artemis(gameTest);

        playerTest = new Player("A", 1, workersTest, godCardTest);
        playerTest2 = new Player("B", 2, workersTest2, godCardTest2);
        playerTest3 = new Player("C", 3, workersTest3, godCardTest3);

        gameTest.addPlayer(playerTest);
        gameTest.addPlayer(playerTest2);
        gameTest.addPlayer(playerTest3);
    }

    @Test
    public void getBoardCopy() {
        assertNotEquals(board, gameTest.getBoardCopy());
    }

    @Test
    public void getBoard() {
        assertEquals(board,gameTest.getBoard());
    }

    @Test
    public void getPlayers() {
        ArrayList<Player> playersTest = new ArrayList<>();
        playersTest.add(playerTest);
        playersTest.add(playerTest2);
        playersTest.add(playerTest3);
        assertEquals(gameTest.getPlayers(), playersTest);
    }

    @Test
    public void testGetSinglePlayer() {
        assertEquals(gameTest.getSinglePlayer(1), playerTest);
        assertEquals(gameTest.getSinglePlayer(2), playerTest2);
        assertEquals(gameTest.getSinglePlayer(3), playerTest3);
        assertNotEquals(gameTest.getSinglePlayer(2), playerTest);
        assertNotEquals(gameTest.getSinglePlayer(3), playerTest2);
        assertNotEquals(gameTest.getSinglePlayer(1), playerTest3);
    }

    @Test
    public void addPlayer() {
        Player testPlayer = new Player("test", 4, workersTest, new Pan(gameTest));
        assertEquals(gameTest.getPlayers().size(),3);
        gameTest.getPlayers().add(testPlayer);
        assertEquals(gameTest.getPlayers().size(),4);
    }

    @Test
    public void testPerformMove() throws IOException {
        Cell cellTest = new Cell(1,2);
        workerTest.setPosition(gameTest.getBoard().getCell(1,1));
        gameTest.performMove(playerTest, workerTest.getWorkerNumber(), cellTest.getX(), cellTest.getY());
        assertEquals(gameTest.getBoard().getCell(cellTest.getX(),cellTest.getY()).getWorker(), workerTest);
    }

    @Test
    public void testPerformBuild() throws IOException {
        Level levelTest = Level.LEVEL1;
        workerTest.setPosition(gameTest.getBoard().getCell(1,1));
        gameTest.performBuild(playerTest, workerTest.getWorkerNumber(), 1,2, levelTest);
        assertEquals(levelTest, gameTest.getBoard().getCell(1,2).getLevel());
    }

    @Test
    public void testManageEndTurn() {
        gameTest.manageEndTurn(playerTest);
        assertEquals(godCardTest.getAvailableBuildNumber(), 0);
        assertEquals(godCardTest.getAvailableMoveNumber(),1);
    }

    @Test
    public void testRemove() throws IOException {
        gameTest.remove(playerTest);

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                assertNotEquals(workerTest, gameTest.getBoard().getCell(i,j).getWorker());
                assertNotEquals(workerTest2, gameTest.getBoard().getCell(i,j).getWorker());
            }

        }
        assertEquals(gameTest.getPlayers().size(),2);
    }

    @Test
    public void testCheckMovement() {
        workerTest.setPosition(gameTest.getBoard().getCell(1,1));
        gameTest.getBoard().getCell(1,1).addWorker(workerTest);
        assertEquals(gameTest.checkMovement(playerTest.getPlayerNumber(), workerTest.getWorkerNumber(), gameTest.getBoardCopy()).size(), 8);
        workerTest.setPosition(gameTest.getBoard().getCell(1,2));
        gameTest.getBoard().getCell(1,2).addWorker(workerTest);
        assertEquals(gameTest.checkMovement(playerTest.getPlayerNumber(), workerTest.getWorkerNumber(), gameTest.getBoardCopy()).size(), 7);
    }

    @Test
    public void testCheckBuilding() {
        workerTest.setPosition(gameTest.getBoard().getCell(1,1));
        gameTest.getBoard().getCell(1,1).addWorker(workerTest);
        assertEquals(gameTest.checkBuilding(1,1,gameTest.getBoardCopy()).size(),8);
        workerTest2.setPosition(gameTest.getBoard().getCell(1,2));
        gameTest.getBoard().getCell(1,2).addWorker(workerTest);
        assertEquals(gameTest.checkBuilding(1,1,gameTest.getBoardCopy()).size(),7);
    }
}