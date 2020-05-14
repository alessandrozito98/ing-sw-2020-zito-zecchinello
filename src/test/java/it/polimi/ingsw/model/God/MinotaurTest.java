package it.polimi.ingsw.model.God;

import it.polimi.ingsw.model.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MinotaurTest {
    Minotaur minotaurTest;
    Cell cellTest;
    Worker workerTest;
    @Before
    public void setUp(){
        Game gameTest = new Game(new Board());
        minotaurTest = new Minotaur(gameTest);
        cellTest = new Cell(1,1);
        workerTest = new Worker(1, WorkerColor.BLUE);
    }

    @Test
    public void IsFeasibleMoveOccupiedCellSameColor(){
        cellTest.addWorker(new Worker(2, WorkerColor.BLUE));
        assertFalse(minotaurTest.isFeasibleMove(cellTest,workerTest));
    }

    @Test
    public void IsFeasibleMoveCellLevelDome(){
        cellTest.setLevel(Level.DOME);
        assertFalse(minotaurTest.isFeasibleMove(cellTest,workerTest));
    }

    @Test
    public void IsFeasibleMoveCellLevelTooHigh(){
        cellTest.setLevel(Level.LEVEL3);
        Cell workerPosition = new Cell(1,2);
        workerPosition.setLevel(Level.GROUND);
        workerTest.setPosition(workerPosition);
        assertFalse(minotaurTest.isFeasibleMove(cellTest,workerTest));
    }

    @Test
    public void IsFeasibleMoveCanMoveUpFalse(){
        cellTest.setLevel(Level.LEVEL1);
        Cell workerPosition = new Cell(1,2);
        workerPosition.setLevel(Level.GROUND);
        workerTest.setPosition(workerPosition);
        minotaurTest.setCanMoveUp(false);
        assertFalse(minotaurTest.isFeasibleMove(cellTest,workerTest));
    }

    @Test
    public void IsFeasibleMoveIncorrectDistance(){
        cellTest.setLevel(Level.LEVEL1);
        Cell workerPosition = new Cell(1,3);
        workerPosition.setLevel(Level.GROUND);
        workerTest.setPosition(workerPosition);
        assertFalse(minotaurTest.isFeasibleMove(cellTest,workerTest));
    }

    @Test
    public void IsFeasibleMoveOccupiedKO(){
        cellTest.setLevel(Level.LEVEL1);
        Worker otherWorker = new Worker(1,WorkerColor.WHITE);
        otherWorker.setPosition(cellTest);
        cellTest.addWorker(otherWorker);
        Cell workerPosition = new Cell(1,2);
        workerPosition.setLevel(Level.GROUND);
        workerTest.setPosition(workerPosition);
        minotaurTest.getBoard().getCell(1,0).setLevel(Level.DOME);
        assertFalse(minotaurTest.isFeasibleMove(cellTest,workerTest));
    }

    @Test
    public void IsFeasibleMoveOccupiedOK(){
        cellTest.setLevel(Level.LEVEL1);
        Worker otherWorker = new Worker(1,WorkerColor.WHITE);
        otherWorker.setPosition(cellTest);
        cellTest.addWorker(otherWorker);
        Cell workerPosition = new Cell(1,2);
        workerPosition.setLevel(Level.GROUND);
        workerTest.setPosition(workerPosition);
        assertTrue(minotaurTest.isFeasibleMove(cellTest,workerTest));
    }

    @Test
    public void IsFeasibleMoveEmpty(){
        cellTest.setLevel(Level.LEVEL1);
        Cell workerPosition = new Cell(1,2);
        workerPosition.setLevel(Level.GROUND);
        workerTest.setPosition(workerPosition);
        assertTrue(minotaurTest.isFeasibleMove(cellTest,workerTest));
    }

    @Test
    public void MoveWithWorker(){
        cellTest.setLevel(Level.LEVEL3);
        Cell workerPosition = new Cell(1,2);
        workerPosition.addWorker(workerTest);
        workerPosition.setLevel(Level.LEVEL2);
        workerTest.setPosition(workerPosition);
        Worker otherWorker = new Worker(1,WorkerColor.WHITE);
        otherWorker.setPosition(cellTest);
        cellTest.addWorker(otherWorker);
        assertEquals(workerTest, workerPosition.getWorker());
        assertEquals(workerPosition, workerTest.getPosition());
        assertEquals(otherWorker, cellTest.getWorker());
        assertEquals(null,minotaurTest.getBoard().getCell(1,0).getWorker());
        minotaurTest.move(cellTest,workerTest);
        assertEquals(null, workerPosition.getWorker());
        assertEquals(otherWorker,minotaurTest.getBoard().getCell(1,0).getWorker());
        assertEquals(minotaurTest.getBoard().getCell(1,0),otherWorker.getPosition());
        assertEquals(cellTest, workerTest.getPosition());
        assertEquals(workerTest, cellTest.getWorker());
        assertEquals(1,minotaurTest.getAvailableBuildNumber());
        assertEquals(0,minotaurTest.getAvailableMoveNumber());
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
        minotaurTest.move(cellTest,workerTest);
        assertEquals(null, workerPosition.getWorker());
        assertEquals(null, minotaurTest.getBoard().getCell(1,0).getWorker());
        assertEquals(cellTest, workerTest.getPosition());
        assertEquals(workerTest, cellTest.getWorker());
        assertEquals(1,minotaurTest.getAvailableBuildNumber());
        assertEquals(0,minotaurTest.getAvailableMoveNumber());
    }
}
