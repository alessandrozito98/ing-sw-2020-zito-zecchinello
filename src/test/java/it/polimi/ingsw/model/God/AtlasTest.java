package it.polimi.ingsw.model.God;

import it.polimi.ingsw.model.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class AtlasTest {
    God atlasTest;
    Cell cellTest;
    Worker workerTest;
    @Before
    public void setUp(){
        Game gameTest = new Game();
        atlasTest = new Atlas(gameTest);
        cellTest = new Cell(1,1);
        workerTest = new Worker(1,WorkerColor.BLUE);
    }

    @Test
    public void IsFeasibleBuildOccupiedCell(){
        cellTest.addWorker(new Worker(2, WorkerColor.WHITE));
        assertFalse(atlasTest.isFeasibleBuild(cellTest,workerTest, Level.LEVEL3));
    }

    @Test
    public void IsFeasibleBuildCellLevelDome(){
        cellTest.setLevel(Level.DOME);
        assertFalse(atlasTest.isFeasibleBuild(cellTest,workerTest,Level.LEVEL3));
    }

    @Test
    public void IsFeasibleBuildWrongBlock(){
        cellTest.setLevel(Level.GROUND);
        assertFalse(atlasTest.isFeasibleBuild(cellTest,workerTest,Level.LEVEL3));
    }

    @Test
    public void IsFeasibleBuildIncorrectDistance(){
        Cell workerPosition = new Cell(1,3);
        workerTest.setPosition(workerPosition);
        assertFalse(atlasTest.isFeasibleBuild(cellTest,workerTest,Level.LEVEL1));
    }

    @Test
    public void IsFeasibleBuild(){
        Cell workerPosition = new Cell(1,2);
        workerTest.setPosition(workerPosition);
        assertTrue(atlasTest.isFeasibleBuild(cellTest,workerTest,Level.DOME));
    }
}
