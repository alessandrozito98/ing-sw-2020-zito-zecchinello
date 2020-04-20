package it.polimi.ingsw.model;

import junit.framework.TestCase;

public class WorkerTest extends TestCase {

    Worker workerTest = new Worker(1,WorkerColor.BLUE);
    Cell cellTest = new Cell(2,4);

    public void testGetWorkerNumber() {
        assertEquals(1,workerTest.getWorkerNumber());
    }

    public void testSetWorkerNumber() {
        workerTest.setWorkerNumber(2);
        assertEquals(2,workerTest.getWorkerNumber());
    }

    public void testGetandSetPosition() {
        workerTest.setPosition(cellTest);
        assertEquals(cellTest,workerTest.getPosition());
    }


    public void testGetColor() {
        assertEquals(workerTest.getColor(),WorkerColor.BLUE);
    }

    public void testSetColor() {
        workerTest.setColor(WorkerColor.WHITE);
        assertEquals(workerTest.getColor(),WorkerColor.WHITE);
    }
}