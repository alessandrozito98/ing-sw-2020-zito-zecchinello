package it.polimi.ingsw.model.God;

import it.polimi.ingsw.model.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PanTest {
    God panTest;
    Cell cellTest;
    Worker workerTest;
    @Before
    public void setUp(){
        Game gameTest = new Game();
        panTest = new Pan(gameTest);
        cellTest = new Cell(1,1);
        workerTest = new Worker(1,WorkerColor.BLUE);
    }

    @Test
    public void TestGetterAndSetter(){

        assertEquals(1,panTest.getAvailableMoveNumber());
        assertEquals(0,panTest.getAvailableBuildNumber());
        assertTrue(panTest.getCanMoveUp());
        panTest.setAvailableMoveNumber(5);
        panTest.setAvailableBuildNumber(5);
        panTest.setCanMoveUp(false);
        assertEquals(5,panTest.getAvailableMoveNumber());
        assertEquals(5,panTest.getAvailableBuildNumber());
        assertFalse(panTest.getCanMoveUp());
    }

    @Test
    public void IsFeasibleMoveOccupiedCell(){
        cellTest.addWorker(new Worker(2,WorkerColor.WHITE));
        assertFalse(panTest.isFeasibleMove(cellTest,workerTest));
    }

    @Test
    public void IsFeasibleMoveCellLevelDome(){
        cellTest.setLevel(Level.DOME);
        assertFalse(panTest.isFeasibleMove(cellTest,workerTest));
    }

    @Test
    public void IsFeasibleMoveCellLevelTooHigh(){
        cellTest.setLevel(Level.LEVEL3);
        Cell workerPosition = new Cell(1,2);
        workerPosition.setLevel(Level.GROUND);
        workerTest.setPosition(workerPosition);
        assertFalse(panTest.isFeasibleMove(cellTest,workerTest));
    }

    @Test
    public void IsFeasibleMoveCanMoveUpFalse(){
        cellTest.setLevel(Level.LEVEL1);
        Cell workerPosition = new Cell(1,2);
        workerPosition.setLevel(Level.GROUND);
        workerTest.setPosition(workerPosition);
        panTest.setCanMoveUp(false);
        assertFalse(panTest.isFeasibleMove(cellTest,workerTest));
    }

    @Test
    public void IsFeasibleMoveIncorrectDistance(){
        cellTest.setLevel(Level.LEVEL1);
        Cell workerPosition = new Cell(1,3);
        workerPosition.setLevel(Level.GROUND);
        workerTest.setPosition(workerPosition);
        assertFalse(panTest.isFeasibleMove(cellTest,workerTest));
    }

    @Test
    public void IsFeasibleMove(){
        cellTest.setLevel(Level.LEVEL1);
        Cell workerPosition = new Cell(1,2);
        workerPosition.setLevel(Level.GROUND);
        workerTest.setPosition(workerPosition);
        assertTrue(panTest.isFeasibleMove(cellTest,workerTest));
    }

    @Test
    public void Move(){
        cellTest.setLevel(Level.LEVEL3);
        Cell workerPosition = new Cell(1,2);
        workerPosition.addWorker(workerTest);
        workerPosition.setLevel(Level.LEVEL2);
        workerTest.setPosition(workerPosition);
        assertEquals(workerTest, workerPosition.getWorker());
        assertEquals(workerPosition, workerTest.getPosition());
        assertEquals(null, cellTest.getWorker());
        panTest.move(cellTest,workerTest);
        assertEquals(null, workerPosition.getWorker());
        assertEquals(cellTest, workerTest.getPosition());
        assertEquals(workerTest, cellTest.getWorker());
    }

    @Test
    public void IsFeasibleBuildOccupiedCell(){
        cellTest.addWorker(new Worker(2,WorkerColor.WHITE));
        assertFalse(panTest.isFeasibleBuild(cellTest,workerTest,Level.LEVEL3));
    }

    @Test
    public void IsFeasibleBuildCellLevelDome(){
        cellTest.setLevel(Level.DOME);
        assertFalse(panTest.isFeasibleBuild(cellTest,workerTest,Level.LEVEL3));
    }

    @Test
    public void IsFeasibleBuildWrongBlock(){
        cellTest.setLevel(Level.GROUND);
        assertFalse(panTest.isFeasibleBuild(cellTest,workerTest,Level.LEVEL3));
    }

    @Test
    public void IsFeasibleBuildIncorrectDistance(){
        Cell workerPosition = new Cell(1,3);
        workerTest.setPosition(workerPosition);
        assertFalse(panTest.isFeasibleBuild(cellTest,workerTest,Level.LEVEL1));
    }

    @Test
    public void IsFeasibleBuild(){
        Cell workerPosition = new Cell(1,2);
        workerTest.setPosition(workerPosition);
        assertTrue(panTest.isFeasibleBuild(cellTest,workerTest,Level.LEVEL1));
    }

    @Test
    public void Build(){
        assertEquals(Level.GROUND, cellTest.getLevel());
        panTest.build(cellTest,workerTest,Level.LEVEL1);
        assertEquals(Level.LEVEL1, cellTest.getLevel());
    }

    @Test
    public void ResetTurn(){
        panTest.setAvailableMoveNumber(2);
        panTest.setAvailableBuildNumber(2);
        panTest.setCanMoveUp(false);
        assertEquals(2,panTest.getAvailableMoveNumber());
        assertEquals(2,panTest.getAvailableBuildNumber());
        assertFalse(panTest.getCanMoveUp());
        panTest.resetTurn();
        assertEquals(1,panTest.getAvailableMoveNumber());
        assertEquals(0,panTest.getAvailableBuildNumber());
        assertTrue(panTest.getCanMoveUp());
    }

    @Test
    public void WinControlFalse(){
        assertFalse(panTest.winControl(Level.LEVEL3,Level.LEVEL3));
    }

    @Test
    public void WinControlTrue1(){
        assertTrue(panTest.winControl(Level.LEVEL2,Level.LEVEL3));
    }

    @Test
    public void WinControlTrue2(){
        assertTrue(panTest.winControl(Level.LEVEL3,Level.LEVEL1));
        assertTrue(panTest.winControl(Level.LEVEL2,Level.GROUND));
    }
}