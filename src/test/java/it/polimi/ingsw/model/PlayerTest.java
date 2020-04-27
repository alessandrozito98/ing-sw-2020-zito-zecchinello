package it.polimi.ingsw.model;

import it.polimi.ingsw.model.God.Athena;
import it.polimi.ingsw.model.God.God;
import it.polimi.ingsw.model.God.Pan;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class PlayerTest extends TestCase {

    Worker workerTest;
    Worker workerTest2;
    ArrayList<Worker> workersTest;
    God godCardTest;
    Player playerTest;

    @Before
    public void setUp() {
        Game gameTest = new Game();
        workerTest = new Worker(1, WorkerColor.BROWN);
        workerTest2 = new Worker(2, WorkerColor.BROWN);
        workersTest = new ArrayList<Worker>();
        workersTest.add(workerTest);
        workersTest.add(workerTest2);
        godCardTest = new Pan(gameTest);
        playerTest = new Player("Alessandro", 2, workersTest, godCardTest);
    }



    @Test
    public void testGetSingleWorker() {
        assertEquals(playerTest.getSingleWorker(1),workerTest);
        assertEquals(playerTest.getSingleWorker(2),workerTest2);
        assertNotSame(playerTest.getSingleWorker(1),workerTest2);
        assertNotSame(playerTest.getSingleWorker(2),workerTest);
    }
}