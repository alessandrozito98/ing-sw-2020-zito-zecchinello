package it.polimi.ingsw.model.God;

import it.polimi.ingsw.model.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class DemeterTest {
    Demeter demeterTest;
    Cell cellTest;
    Worker workerTest;

    @Before
    public void setUp(){
        Game gameTest = new Game();
        demeterTest = new Demeter(gameTest);
        cellTest = new Cell(1,1);
        workerTest = new Worker(1, WorkerColor.BLUE);
    }

    @Test
    public void IsFeasibleBuildSameCell(){
        demeterTest.setFirstBuildPosition(cellTest);
        assertFalse(demeterTest.isFeasibleBuild(cellTest,workerTest, Level.LEVEL3));
    }

    @Test
    public void IsFeasibleBuildSuper(){
        cellTest.addWorker(new Worker(2,WorkerColor.WHITE));
        assertFalse(demeterTest.isFeasibleBuild(cellTest,workerTest, Level.LEVEL3));
    }

    @Test
    public void FirstBuild(){
        demeterTest.setAvailableBuildNumber(1);
        assertEquals(Level.GROUND, cellTest.getLevel());
        assertEquals(null,demeterTest.getFirstBuildPosition());
        demeterTest.build(cellTest,workerTest,Level.LEVEL1);
        assertEquals(Level.LEVEL1, cellTest.getLevel());
        assertEquals(cellTest,demeterTest.getFirstBuildPosition());
        assertEquals(1,demeterTest.getAvailableBuildNumber());
    }

    @Test
    public void SecondMove(){
        demeterTest.setAvailableBuildNumber(1);
        Cell firstCell = new Cell(3,3);
        cellTest.setLevel(Level.LEVEL1);
        demeterTest.setFirstBuildPosition(firstCell);
        assertEquals(firstCell,demeterTest.getFirstBuildPosition());
        assertEquals(Level.LEVEL1, cellTest.getLevel());
        demeterTest.build(cellTest,workerTest,Level.LEVEL2);
        assertEquals(Level.LEVEL2, cellTest.getLevel());
        assertEquals(firstCell,demeterTest.getFirstBuildPosition());
        assertEquals(0,demeterTest.getAvailableBuildNumber());
    }

    @Test
    public void ResetTurn(){
        Cell resetCell = new Cell(2,2);
        demeterTest.setAvailableMoveNumber(2);
        demeterTest.setAvailableBuildNumber(2);
        demeterTest.setCanMoveUp(false);
        demeterTest.setFirstBuildPosition(resetCell);
        assertEquals(2,demeterTest.getAvailableMoveNumber());
        assertEquals(2,demeterTest.getAvailableBuildNumber());
        assertFalse(demeterTest.getCanMoveUp());
        assertEquals(resetCell, demeterTest.getFirstBuildPosition());
        demeterTest.resetTurn();
        assertEquals(1,demeterTest.getAvailableMoveNumber());
        assertEquals(0,demeterTest.getAvailableBuildNumber());
        assertTrue(demeterTest.getCanMoveUp());
        assertEquals(null, demeterTest.getFirstBuildPosition());
    }
}

