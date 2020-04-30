package it.polimi.ingsw.observer;

import it.polimi.ingsw.observer.messages.*;

import java.util.ArrayList;
import java.util.List;

public class Observable {

    private final List<Observer> observers = new ArrayList<>();

    public void addObserver(Observer observer){
        synchronized (observers) {
            observers.add(observer);
        }
    }

    public void removeObserver(Observer observer){
        synchronized (observers) {
            observers.remove(observer);
        }
    }


    protected void notifyAction(BoardChangeMessage message){
        synchronized (observers) {
            for(Observer observer : observers){
                observer.updateBoard(message);
            }
        }
    }

    protected void notifyBuildRequest(BuildRequest message){
        synchronized (observers) {
            for(Observer observer : observers){
                observer.updateBuild(message);
            }
        }
    }

    protected void notifyMoveRequest(MoveRequest message){
        synchronized (observers) {
            for(Observer observer : observers){
                observer.updateMove(message);
            }
        }
    }

    protected void notifyEndTurn(EndTurnRequest message){
        synchronized (observers) {
            for(Observer observer : observers){
                observer.update(message);
            }
        }
    }

    protected void notifyStartTurn(StartTurnMessage message){
        synchronized (observers) {
            for(Observer observer : observers){
                observer.update(message);
            }
        }
    }

}
