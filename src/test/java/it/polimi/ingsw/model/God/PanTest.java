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
        Game gameTest = new Game(new Board());
        panTest = new Pan(gameTest);
        cellTest = new Cell(1,1);
        workerTest = new Worker(1,WorkerColor.BLUE);
    }

    @Test
    public void testTestGetterAndSetter(){
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
    public void testIsFeasibleMoveOccupiedCell(){
        cellTest.addWorker(new Worker(2,WorkerColor.WHITE));
        assertFalse(panTest.isFeasibleMove(cellTest,workerTest));
    }

    @Test
    public void testIsFeasibleMoveCellLevelDome(){
        cellTest.setLevel(Level.DOME);
        assertFalse(panTest.isFeasibleMove(cellTest,workerTest));
    }

    @Test
    public void testIsFeasibleMoveCellLevelTooHigh(){
        cellTest.setLevel(Level.LEVEL3);
        Cell workerPosition = new Cell(1,2);
        workerPosition.setLevel(Level.GROUND);
        workerTest.setPosition(workerPosition);
        assertFalse(panTest.isFeasibleMove(cellTest,workerTest));
    }

    @Test
    public void testIsFeasibleMoveCanMoveUpFalse(){
        cellTest.setLevel(Level.LEVEL1);
        Cell workerPosition = new Cell(1,2);
        workerPosition.setLevel(Level.GROUND);
        workerTest.setPosition(workerPosition);
        panTest.setCanMoveUp(false);
        assertFalse(panTest.isFeasibleMove(cellTest,workerTest));
    }

    @Test
    public void testIsFeasibleMoveIncorrectDistance(){
        cellTest.setLevel(Level.LEVEL1);
        Cell workerPosition = new Cell(1,3);
        workerPosition.setLevel(Level.GROUND);
        workerTest.setPosition(workerPosition);
        assertFalse(panTest.isFeasibleMove(cellTest,workerTest));
    }

    @Test
    public void testIsFeasibleMove(){
        cellTest.setLevel(Level.LEVEL1);
        Cell workerPosition = new Cell(1,2);
        workerPosition.setLevel(Level.GROUND);
        workerTest.setPosition(workerPosition);
        assertTrue(panTest.isFeasibleMove(cellTest,workerTest));
    }

    @Test
    public void testMove(){
        cellTest.setLevel(Level.LEVEL3);
        Cell workerPosition = new Cell(1,2);
        workerPosition.addWorker(workerTest);
        workerPosition.setLevel(Level.LEVEL2);
        workerTest.setPosition(workerPosition);
        assertEquals(workerTest, workerPosition.getWorker());
        assertEquals(workerPosition, workerTest.getPosition());
        assertNull(cellTest.getWorker());
        panTest.move(cellTest,workerTest);
        assertNull(workerPosition.getWorker());
        assertEquals(cellTest, workerTest.getPosition());
        assertEquals(workerTest, cellTest.getWorker());
        assertEquals(1,panTest.getAvailableBuildNumber());
        assertEquals(0,panTest.getAvailableMoveNumber());
    }

    @Test
    public void testIsFeasibleBuildOccupiedCell(){
        cellTest.addWorker(new Worker(2,WorkerColor.WHITE));
        assertFalse(panTest.isFeasibleBuild(cellTest,workerTest,Level.LEVEL3));
    }

    @Test
    public void testIsFeasibleBuildCellLevelDome(){
        cellTest.setLevel(Level.DOME);
        assertFalse(panTest.isFeasibleBuild(cellTest,workerTest,Level.LEVEL3));
    }

    @Test
    public void testIsFeasibleBuildWrongBlock(){
        cellTest.setLevel(Level.GROUND);
        assertFalse(panTest.isFeasibleBuild(cellTest,workerTest,Level.LEVEL3));
    }

    @Test
    public void testIsFeasibleBuildIncorrectDistance(){
        Cell workerPosition = new Cell(1,3);
        workerTest.setPosition(workerPosition);
        assertFalse(panTest.isFeasibleBuild(cellTest,workerTest,Level.LEVEL1));
    }

    @Test
    public void testIsFeasibleBuild(){
        Cell workerPosition = new Cell(1,2);
        workerTest.setPosition(workerPosition);
        assertTrue(panTest.isFeasibleBuild(cellTest,workerTest,Level.LEVEL1));
    }

    @Test
    public void testBuild(){
        assertEquals(Level.GROUND, cellTest.getLevel());
        panTest.setAvailableMoveNumber(0);
        panTest.setAvailableBuildNumber(1);
        panTest.build(cellTest,workerTest,Level.LEVEL1);
        assertEquals(Level.LEVEL1, cellTest.getLevel());
        assertEquals(0,panTest.getAvailableBuildNumber());
    }

    @Test
    public void testResetTurn(){
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
    public void testWinControlFalse(){
        assertFalse(panTest.winControl(Level.LEVEL3,Level.LEVEL3));
    }

    @Test
    public void testWinControlTrue1(){
        assertTrue(panTest.winControl(Level.LEVEL2,Level.LEVEL3));
    }

    @Test
    public void testWinControlTrue2(){
        assertTrue(panTest.winControl(Level.LEVEL3,Level.LEVEL1));
        assertTrue(panTest.winControl(Level.LEVEL2,Level.GROUND));
    }
}