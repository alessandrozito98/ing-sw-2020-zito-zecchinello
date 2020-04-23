package it.polimi.ingsw.model.God;

import it.polimi.ingsw.model.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ArtemisTest {
    Artemis artemisTest;
    Cell cellTest;
    Worker workerTest;

    @Before
    public void setUp(){
        Game gameTest = new Game();
        artemisTest = new Artemis(gameTest);
        cellTest = new Cell(1,1);
        workerTest = new Worker(1,WorkerColor.BLUE);
    }

    @Test
    public void IsFeasibleMoveSecondMoveBack(){
        artemisTest.setFirstMovePosition(cellTest);
        assertFalse(artemisTest.isFeasibleMove(cellTest, workerTest));
    }

    @Test
    public void IsFeasibleMoveSuper(){
        cellTest.addWorker(new Worker(2,WorkerColor.WHITE));
        assertFalse(artemisTest.isFeasibleMove(cellTest,workerTest));
    }

    @Test
    public void FirstMove(){
        cellTest.setLevel(Level.LEVEL3);
        Cell workerPosition = new Cell(1,2);
        workerPosition.addWorker(workerTest);
        workerPosition.setLevel(Level.LEVEL2);
        workerTest.setPosition(workerPosition);
        assertEquals(workerTest, workerPosition.getWorker());
        assertEquals(workerPosition, workerTest.getPosition());
        assertEquals(null, cellTest.getWorker());
        artemisTest.move(cellTest,workerTest);
        assertEquals(null, workerPosition.getWorker());
        assertEquals(cellTest, workerTest.getPosition());
        assertEquals(workerTest, cellTest.getWorker());
        assertEquals(cellTest,artemisTest.getFirstMovePosition());
        assertEquals(1,artemisTest.getAvailableBuildNumber());
        assertEquals(1,artemisTest.getAvailableMoveNumber());
    }

    @Test
    public void SecondMove(){
        cellTest.setLevel(Level.LEVEL3);
        Cell workerPosition = new Cell(1,2);
        workerPosition.addWorker(workerTest);
        workerPosition.setLevel(Level.LEVEL2);
        workerTest.setPosition(workerPosition);
        Cell firstCell = new Cell(2,2);
        artemisTest.setFirstMovePosition(firstCell);
        artemisTest.setAvailableBuildNumber(1);
        artemisTest.setAvailableMoveNumber(1);
        assertEquals(workerTest, workerPosition.getWorker());
        assertEquals(workerPosition, workerTest.getPosition());
        assertEquals(null, cellTest.getWorker());
        artemisTest.move(cellTest,workerTest);
        assertEquals(null, workerPosition.getWorker());
        assertEquals(cellTest, workerTest.getPosition());
        assertEquals(workerTest, cellTest.getWorker());
        assertEquals(cellTest,artemisTest.getFirstMovePosition());
        assertEquals(1,artemisTest.getAvailableBuildNumber());
        assertEquals(0,artemisTest.getAvailableMoveNumber());
    }

    @Test
    public void ResetTurn(){
        Cell resetCell = new Cell(2,2);
        artemisTest.setAvailableMoveNumber(2);
        artemisTest.setAvailableBuildNumber(2);
        artemisTest.setCanMoveUp(false);
        artemisTest.setFirstMovePosition(resetCell);
        assertEquals(2,artemisTest.getAvailableMoveNumber());
        assertEquals(2,artemisTest.getAvailableBuildNumber());
        assertFalse(artemisTest.getCanMoveUp());
        assertEquals(resetCell, artemisTest.getFirstMovePosition());
        artemisTest.resetTurn();
        assertEquals(2,artemisTest.getAvailableMoveNumber());
        assertEquals(0,artemisTest.getAvailableBuildNumber());
        assertTrue(artemisTest.getCanMoveUp());
        assertEquals(null, artemisTest.getFirstMovePosition());
        }
}
