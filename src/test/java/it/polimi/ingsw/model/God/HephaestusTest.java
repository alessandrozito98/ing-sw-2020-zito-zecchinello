package it.polimi.ingsw.model.God;

import it.polimi.ingsw.model.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class HephaestusTest {
    Hephaestus hephaestusTest;
    Cell cellTest;
    Worker workerTest;

    @Before
    public void setUp(){
        Game gameTest = new Game(new Board());
        hephaestusTest = new Hephaestus(gameTest);
        cellTest = new Cell(1,1);
        workerTest = new Worker(1, WorkerColor.BLUE);
    }

    @Test
    public void FirstBuild(){
        hephaestusTest.setAvailableBuildNumber(1);
        assertEquals(Level.GROUND, cellTest.getLevel());
        assertEquals(null,hephaestusTest.getFirstBuildPosition());
        hephaestusTest.build(cellTest,workerTest,Level.LEVEL1);
        assertEquals(Level.LEVEL1, cellTest.getLevel());
        assertEquals(cellTest,hephaestusTest.getFirstBuildPosition());
        assertEquals(1,hephaestusTest.getAvailableBuildNumber());
    }

    @Test
    public void SecondBuild(){
        hephaestusTest.setAvailableBuildNumber(1);
        Cell firstCell = cellTest;
        cellTest.setLevel(Level.LEVEL1);
        hephaestusTest.setFirstBuildPosition(firstCell);
        assertEquals(firstCell,hephaestusTest.getFirstBuildPosition());
        assertEquals(Level.LEVEL1, cellTest.getLevel());
        hephaestusTest.build(cellTest,workerTest,Level.LEVEL2);
        assertEquals(Level.LEVEL2, cellTest.getLevel());
        assertEquals(firstCell,hephaestusTest.getFirstBuildPosition());
        assertEquals(0,hephaestusTest.getAvailableBuildNumber());
    }

    @Test
    public void IsFeasibleBuildSuper(){
        cellTest.addWorker(new Worker(2,WorkerColor.WHITE));
        assertFalse(hephaestusTest.isFeasibleBuild(cellTest,workerTest, Level.LEVEL3));
    }

    @Test
    public void IsFeasibleSecondBuildSameCell(){
        cellTest.setLevel(Level.LEVEL1);
        hephaestusTest.setFirstBuildPosition(cellTest);
        assertTrue(hephaestusTest.isFeasibleBuild(cellTest,workerTest,Level.LEVEL2));
    }

    @Test
    public void IsFeasibleSecondBuildOtherCell(){
        cellTest.setLevel(Level.LEVEL1);
        Cell firstCell = new Cell(2,1);
        hephaestusTest.setFirstBuildPosition(firstCell);
        assertFalse(hephaestusTest.isFeasibleBuild(cellTest,workerTest,Level.LEVEL2));
    }

    @Test
    public void ResetTurn(){
        Cell resetCell = new Cell(2,2);
        hephaestusTest.setAvailableMoveNumber(2);
        hephaestusTest.setAvailableBuildNumber(2);
        hephaestusTest.setCanMoveUp(false);
        hephaestusTest.setFirstBuildPosition(resetCell);
        assertEquals(2,hephaestusTest.getAvailableMoveNumber());
        assertEquals(2,hephaestusTest.getAvailableBuildNumber());
        assertFalse(hephaestusTest.getCanMoveUp());
        assertEquals(resetCell, hephaestusTest.getFirstBuildPosition());
        hephaestusTest.resetTurn();
        assertEquals(1,hephaestusTest.getAvailableMoveNumber());
        assertEquals(0,hephaestusTest.getAvailableBuildNumber());
        assertTrue(hephaestusTest.getCanMoveUp());
        assertEquals(null, hephaestusTest.getFirstBuildPosition());
    }
}
