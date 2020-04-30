package it.polimi.ingsw.observer;

import it.polimi.ingsw.observer.messages.BuildRequest;
import it.polimi.ingsw.observer.messages.MoveRequest;

public interface Observer<T> {


    void updateMove(MoveRequest message);
    void updateBuild(BuildRequest message);
}
