package it.polimi.ingsw.model.God;

import it.polimi.ingsw.model.Cell;
import it.polimi.ingsw.model.Worker;
import it.polimi.ingsw.model.WorkerColor;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PanTest {

    @Test
    public void TestGetterAndSetter(){
        God panTest = new Pan();
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

    }

    @Test
    public void IsFeasibleMoveCellLevelDome(){

    }

    @Test
    public void IsFeasibleMoveCellLevelTooHigh(){

    }

    @Test
    public void IsFeasibleMoveCanMoveUpFalse(){

    }

    @Test
    public void IsFeasibleMoveIncorrectDistance(){

    }

    @Test
    public void IsFeasibleMove(){

    }
}