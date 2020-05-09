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

    public void notify(String message){
        synchronized (observers) {
            for(Observer observer : observers){
                observer.update(message);
            }
        }
    }

    public void notifyBoardChange(BoardChange message){
        synchronized (observers) {
            for(Observer observer : observers){
                observer.updateBoardChange(message);  //QUI DEVO INSERIRE UN METODO UPDATE, MA NON POSSO DIRE A PRIORI QUALE
                                                // SIA PERCHÉ NON LO SO SE STA COSTRUENDO O SI STA MUOVENDO. IN PIU' NON
                                                // E' CORRETTO COSì PERCHÉ L'ATTRIBUTO MESSAGE IN UPDATEBUILD DEVE ESSERE
                                                // DI TIPO BUILDREQUEST, NON DI BOARDCHANGEMESSAGE
            }
        }
    }

    public void notifyResetTurn(ResetTurn message){
        synchronized (observers) {
            for(Observer observer : observers){
                observer.updateResetTurn(message);
            }
        }
    }

    public void notifyPlayerLose(PlayerLose message){
        synchronized (observers) {
            for(Observer observer : observers){
                observer.updatePlayerLose(message);
            }
        }
    }

    public void notifyMoveRequest(MoveRequest message){
        synchronized (observers) {
            for(Observer observer : observers){
                observer.updateMoveRequest(message);
            }
        }
    }

    public void notifyBuildRequest(BuildRequest message){
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
}
