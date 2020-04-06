package it.polimi.ingsw.model;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;


public class WorkerTest {



    @Test
    public void getPosition() {
        Worker workerTest = new Worker();
        assertEquals(workerTest.getPosition(),null);
    }

    @Test
    public void setPosition() {
    }

    @Test
    public void getColor() {
        Worker workerTest = new Worker();
        workerTest.setColor(WorkerColor.BLUE);
        assertEquals(workerTest.getColor(),WorkerColor.BLUE);
    }

    @Test
    public void setColor() {
    }
}
