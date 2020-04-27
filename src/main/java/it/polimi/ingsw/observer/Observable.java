package it.polimi.ingsw.observer;

import it.polimi.ingsw.model.MoveMessage;
import it.polimi.ingsw.model.WinMessage;

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

    protected void notifyWin(WinMessage message){
        synchronized (observers) {
            for(Observer observer : observers){
                observer.update();
            }
        }
    }

    protected void notifyBuild(MoveMessage message){
        synchronized (observers) {
            for(Observer observer : observers){
                observer.update();
            }
        }
    }

    protected void notifyMove(MoveMessage message){
        synchronized (observers) {
            for(Observer observer : observers){
                observer.update();
            }
        }
    }


}
