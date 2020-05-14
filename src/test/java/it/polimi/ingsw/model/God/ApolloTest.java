package it.polimi.ingsw.model.God;

import it.polimi.ingsw.model.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class ApolloTest {

    God apolloTest;
    Cell cellTest;
    Worker workerTest;
    @Before
    public void setUp(){
        Game gameTest = new Game(new Board());
        apolloTest = new Apollo(gameTest);
        cellTest = new Cell(1,1);
        workerTest = new Worker(1, WorkerColor.BLUE);
    }

    @Test
    public void IsFeasibleMoveOccupiedCell(){
        cellTest.addWorker(new Worker(2,WorkerColor.BLUE));
        assertFalse(apolloTest.isFeasibleMove(cellTest,workerTest));
    }

    @Test
    public void IsFeasibleMoveCellLevelDome(){
        cellTest.setLevel(Level.DOME);
        assertFalse(apolloTest.isFeasibleMove(cellTest,workerTest));
    }

    @Test
    public void IsFeasibleMoveCellLevelTooHigh(){
        cellTest.setLevel(Level.LEVEL3);
        Cell workerPosition = new Cell(1,2);
        workerPosition.setLevel(Level.GROUND);
        workerTest.setPosition(workerPosition);
        assertFalse(apolloTest.isFeasibleMove(cellTest,workerTest));
    }

    @Test
    public void IsFeasibleMoveCanMoveUpFalse(){
        cellTest.setLevel(Level.LEVEL1);
        Cell workerPosition = new Cell(1,2);
        workerPosition.setLevel(Level.GROUND);
        workerTest.setPosition(workerPosition);
        apolloTest.setCanMoveUp(false);
        assertFalse(apolloTest.isFeasibleMove(cellTest,workerTest));
    }

    @Test
    public void IsFeasibleMoveIncorrectDistance(){
        cellTest.setLevel(Level.LEVEL1);
        Cell workerPosition = new Cell(1,3);
        workerPosition.setLevel(Level.GROUND);
        workerTest.setPosition(workerPosition);
        assertFalse(apolloTest.isFeasibleMove(cellTest,workerTest));
    }

    @Test
    public void IsFeasibleMove(){
        cellTest.setLevel(Level.LEVEL1);
        Cell workerPosition = new Cell(1,2);
        workerPosition.setLevel(Level.GROUND);
        workerTest.setPosition(workerPosition);
        assertTrue(apolloTest.isFeasibleMove(cellTest,workerTest));
    }

    @Test
    public void MoveWithWorker(){
        cellTest.setLevel(Level.LEVEL3);
        Cell workerPosition = new Cell(1,2);
        Worker opponentWorker = new Worker(1,WorkerColor.WHITE);
        cellTest.addWorker(opponentWorker);
        opponentWorker.setPosition(cellTest);
        workerPosition.addWorker(workerTest);
        workerPosition.setLevel(Level.LEVEL2);
        workerTest.setPosition(workerPosition);
        assertEquals(workerTest, workerPosition.getWorker());
        assertEquals(workerPosition, workerTest.getPosition());
        assertEquals(cellTest, opponentWorker.getPosition());
        assertEquals(opponentWorker, cellTest.getWorker());
        apolloTest.move(cellTest,workerTest);
        assertEquals(opponentWorker, workerPosition.getWorker());
        assertEquals(workerPosition, opponentWorker.getPosition());
        assertEquals(cellTest, workerTest.getPosition());
        assertEquals(workerTest, cellTest.getWorker());
        assertEquals(1,apolloTest.getAvailableBuildNumber());
        assertEquals(0,apolloTest.getAvailableMoveNumber());
    }

    @Test
    public void MoveWithoutWorker(){
        cellTest.setLevel(Level.LEVEL3);
        Cell workerPosition = new Cell(1,2);
        workerPosition.addWorker(workerTest);
        workerPosition.setLevel(Level.LEVEL2);
        workerTest.setPosition(workerPosition);
        assertEquals(workerTest, workerPosition.getWorker());
        assertEquals(workerPosition, workerTest.getPosition());
        assertEquals(null, cellTest.getWorker());
        apolloTest.move(cellTest,workerTest);
        assertEquals(null, workerPosition.getWorker());
        assertEquals(cellTest, workerTest.getPosition());
        assertEquals(workerTest, cellTest.getWorker());
        assertEquals(1,apolloTest.getAvailableBuildNumber());
        assertEquals(0,apolloTest.getAvailableMoveNumber());
    }

    @Test
    public void WinControlFalse(){
        assertFalse(apolloTest.winControl(Level.LEVEL3,Level.LEVEL3));
    }

    @Test
    public void WinControlTrue(){
        assertTrue(apolloTest.winControl(Level.LEVEL2,Level.LEVEL3));
    }
}