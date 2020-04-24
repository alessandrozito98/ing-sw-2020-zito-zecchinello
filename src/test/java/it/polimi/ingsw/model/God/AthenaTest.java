package it.polimi.ingsw.model.God;

import it.polimi.ingsw.model.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AthenaTest {
    Athena athenaTest;
    Cell cellTest;
    Worker workerTest;
    @Before
    public void setUp(){
        Game gameTest = new Game();
        athenaTest = new Athena(gameTest);
        cellTest = new Cell(1,1);
        workerTest = new Worker(1, WorkerColor.BLUE);
    }

    @Test
    public void MoveUp(){
        Game gameTest = new Game();
        athenaTest.getPlayers().add(new Player("test",2,null, new Pan(gameTest)));
        cellTest.setLevel(Level.LEVEL3);
        Cell workerPosition = new Cell(1,2);
        workerPosition.addWorker(workerTest);
        workerPosition.setLevel(Level.LEVEL2);
        workerTest.setPosition(workerPosition);
        assertEquals(workerTest, workerPosition.getWorker());
        assertEquals(workerPosition, workerTest.getPosition());
        assertEquals(null, cellTest.getWorker());
        assertTrue(athenaTest.getPlayers().get(0).getGodCard().getCanMoveUp());
        athenaTest.move(cellTest,workerTest);
        assertFalse(athenaTest.getPlayers().get(0).getGodCard().getCanMoveUp());
        assertEquals(null, workerPosition.getWorker());
        assertEquals(cellTest, workerTest.getPosition());
        assertEquals(workerTest, cellTest.getWorker());
        assertEquals(1,athenaTest.getAvailableBuildNumber());
        assertEquals(0,athenaTest.getAvailableMoveNumber());
    }

    @Test
    public void MoveSuper(){
        cellTest.setLevel(Level.LEVEL1);
        Cell workerPosition = new Cell(1,2);
        workerPosition.addWorker(workerTest);
        workerPosition.setLevel(Level.LEVEL2);
        workerTest.setPosition(workerPosition);
        assertEquals(workerTest, workerPosition.getWorker());
        assertEquals(workerPosition, workerTest.getPosition());
        assertEquals(null, cellTest.getWorker());
        athenaTest.move(cellTest,workerTest);
        assertEquals(null, workerPosition.getWorker());
        assertEquals(cellTest, workerTest.getPosition());
        assertEquals(workerTest, cellTest.getWorker());
        assertEquals(1,athenaTest.getAvailableBuildNumber());
        assertEquals(0,athenaTest.getAvailableMoveNumber());
    }
}
