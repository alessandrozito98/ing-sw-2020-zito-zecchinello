package it.polimi.ingsw.model;


import org.junit.Test;
import org.junit.runner.RunWith;


import static org.junit.Assert.*;


public class CellTest {


    @Test
    public void addWorker() {
        Worker workerTest = new Worker(1,WorkerColor.BLUE);
        Cell cellTest = new Cell(1,1);
        cellTest.addWorker(workerTest);
        assertEquals(cellTest.getWorker(),workerTest);
    }


    @Test
    public void addWorker1() {
        Worker workerTest = new Worker(1,WorkerColor.BLUE);
        Cell cellTest = new Cell(1,1);
        Worker workerTest2 = new Worker(2,WorkerColor.BLUE);
        cellTest.addWorker(workerTest);
        cellTest.addWorker(workerTest2);
        assertEquals(cellTest.getWorker(),workerTest);
    }


    @Test
    public void removeWorker() {
        Worker workerTest = new Worker(1,WorkerColor.BLUE);
        Cell cellTest = new Cell(1,1);
        cellTest.addWorker(workerTest);
        assertNotNull(cellTest.getWorker());
        cellTest.removeWorker();
        assertNull(cellTest.getWorker());
    }

}
