package it.polimi.ingsw.observer;

import it.polimi.ingsw.observer.messages.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Observable {

    private final List<Observer> observers = new ArrayList<>();

    public void addObserver(Observer observer) {
        synchronized (observers) {
            observers.add(observer);
        }
    }

    public void notifyWin(Win message) throws IOException {
        synchronized (observers) {
            for(Observer observer : observers){
                observer.updateWin(message);
            }
        }
    }

    public void notifyBoardChange(BoardChange message) throws IOException {
        synchronized (observers) {
            for(Observer observer : observers){
                observer.updateBoardChange(message);
            }
        }
    }

    public void notifyResetTurn(ResetTurn message) {
        synchronized (observers) {
            for(Observer observer : observers) {
                observer.updateResetTurn(message);
            }
        }
    }

    public void notifyPlayerLose(PlayerLose message) throws IOException {
        synchronized (observers) {
            for(Observer observer : observers){
                observer.updatePlayerLose(message);
            }
        }
    }

    public void notifyMoveRequest(MoveRequest message) throws IOException {
        synchronized (observers) {
            for(Observer observer : observers){
                observer.updateMoveRequest(message);
            }
        }
    }

    public void notifyBuildRequest(BuildRequest message) throws IOException {
        synchronized (observers) {
            for(Observer observer : observers){
                observer.updateBuildRequest(message);
            }
        }
    }

    public void notifyEndTurnRequest(EndTurnRequest message){
        synchronized (observers) {
            for(Observer observer : observers){
                observer.updateEndTurnRequest(message);
            }
        }
    }

    public void notifyEndGame(){
        synchronized (observers) {
            for(Observer observer : observers){
                observer.updateEndGame();
            }
        }
    }
}
