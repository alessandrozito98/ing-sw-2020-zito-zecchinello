package it.polimi.ingsw.model.God;

import it.polimi.ingsw.model.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PrometheusTest {
    God prometheusTest;
    Cell cellTest;
    Worker workerTest;
    @Before
    public void setUp(){
        Game gameTest = new Game(new Board());
        prometheusTest = new Prometheus(gameTest);
        cellTest = new Cell(1,1);
        workerTest = new Worker(1, WorkerColor.BLUE);
    }

    @Test
    public void BuildBeforeMove(){
        assertEquals(Level.GROUND, cellTest.getLevel());
        assertTrue(prometheusTest.getCanMoveUp());
        prometheusTest.build(cellTest,workerTest,Level.LEVEL1);
        assertEquals(Level.LEVEL1, cellTest.getLevel());
        assertFalse(prometheusTest.getCanMoveUp());
        assertEquals(0,prometheusTest.getAvailableBuildNumber());
        assertEquals(1,prometheusTest.getAvailableMoveNumber());
    }

    @Test
    public void BuildAfterMove(){
        assertEquals(Level.GROUND, cellTest.getLevel());
        assertTrue(prometheusTest.getCanMoveUp());
        prometheusTest.setAvailableMoveNumber(0);
        prometheusTest.build(cellTest,workerTest,Level.LEVEL1);
        assertEquals(Level.LEVEL1, cellTest.getLevel());
        assertTrue(prometheusTest.getCanMoveUp());
        assertEquals(0,prometheusTest.getAvailableBuildNumber());
        assertEquals(0,prometheusTest.getAvailableMoveNumber());
    }

    @Test
    public void ResetTurn(){
        prometheusTest.setAvailableMoveNumber(2);
        prometheusTest.setAvailableBuildNumber(2);
        prometheusTest.setCanMoveUp(false);
        assertEquals(2,prometheusTest.getAvailableMoveNumber());
        assertEquals(2,prometheusTest.getAvailableBuildNumber());
        assertFalse(prometheusTest.getCanMoveUp());
        prometheusTest.resetTurn();
        assertEquals(1,prometheusTest.getAvailableMoveNumber());
        assertEquals(1,prometheusTest.getAvailableBuildNumber());
        assertTrue(prometheusTest.getCanMoveUp());
    }
}
