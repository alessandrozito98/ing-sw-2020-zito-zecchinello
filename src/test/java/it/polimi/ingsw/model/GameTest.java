package it.polimi.ingsw.model;

import it.polimi.ingsw.model.God.Apollo;
import it.polimi.ingsw.model.God.Artemis;
import it.polimi.ingsw.model.God.God;
import it.polimi.ingsw.model.God.Pan;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class GameTest {


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
        gameTest = new Game(new Board());
        workerTest = new Worker(1, WorkerColor.RED);
        workerTest2 = new Worker(2, WorkerColor.RED);
        workerTest3 = new Worker(1, WorkerColor.BLUE);
        workerTest4 = new Worker(2, WorkerColor.BLUE);
        workerTest5 = new Worker(1, WorkerColor.WHITE);
        workerTest6 = new Worker(2, WorkerColor.WHITE);

        workersTest = new ArrayList<Worker>();
        workersTest2 = new ArrayList<Worker>();
       workersTest3 = new ArrayList<Worker>();
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
    public void getSinglePlayer() {
        assertEquals(gameTest.getSinglePlayer(1), playerTest);
        assertEquals(gameTest.getSinglePlayer(2), playerTest2);
        assertEquals(gameTest.getSinglePlayer(3), playerTest3);
        assertNotSame(gameTest.getSinglePlayer(2), playerTest);
        assertNotSame(gameTest.getSinglePlayer(3), playerTest2);
        assertNotSame(gameTest.getSinglePlayer(1), playerTest3);
    }

    @Test
    public void performMove() {
    }

    @Test
    public void performBuild() {
    }

    @Test
    public void manageEndTurn() {
    }

    @Test
    public void remove() {
    }

    @Test
    public void checkMovement() {
    }

    @Test
    public void checkBuilding() {
    }
}