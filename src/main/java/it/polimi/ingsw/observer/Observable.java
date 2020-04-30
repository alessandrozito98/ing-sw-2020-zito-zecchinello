package it.polimi.ingsw.observer;

import it.polimi.ingsw.observer.messages.MoveMessage;
import it.polimi.ingsw.observer.messages.WinMessage;

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
                observer.update(message);
            }
        }
    }

    protected void notifyBuild(BuildMessage message){
        synchronized (observers) {
            for(Observer observer : observers){
                observer.update(message);
            }
        }
    }

    protected void notifyMove(MoveMessage message){
        synchronized (observers) {
            for(Observer observer : observers){
                observer.update(message);
            }
        }
    }


}
