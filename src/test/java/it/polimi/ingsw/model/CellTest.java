package it.polimi.ingsw.model;


import org.junit.Test;
import org.junit.runner.RunWith;


import static org.junit.Assert.*;


public class CellTest {


    @Test
    public void addWorker() {
        Worker workerTest = new Worker();
        Cell cellTest = new Cell(1,1,true,null,null);
        cellTest.addWorker(workerTest);
        assertEquals(cellTest.getWorker(),workerTest);
        assertTrue(cellTest.isOccupied());
    }

    @Test
    public void removeWorker() {
        Worker workerTest = new Worker();
        Cell cellTest = new Cell(1,1,false,null,null);
        cellTest.removeWorker();
        assertNull(cellTest.getWorker());
        assertFalse(cellTest.isOccupied());
    }

    @Test
    public void build() {
    }
}
