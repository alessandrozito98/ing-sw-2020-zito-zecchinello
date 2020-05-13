package it.polimi.ingsw.model.God;

import it.polimi.ingsw.model.*;

import java.util.ArrayList;

public class Athena extends God {
    private ArrayList<Player> players;

    public Athena(Game game){
        super(game);
        this.players = game.getPlayers();

    }

    public ArrayList<Player> getPlayers(){return this.players;}

    @Override
    public void move(Cell cell, Worker worker) {
        Level oldLevel = worker.getPosition().getLevel();
        if(cell.getLevel().ordinal()==oldLevel.ordinal()+1){
            for (Player player:players) {
                if(!(player.getGodCard() instanceof Athena)){
                    player.getGodCard().setCanMoveUp(false);
                }
            }
        }
        worker.getPosition().removeWorker();
        cell.addWorker(worker);
        worker.setPosition(cell);
        setAvailableMoveNumber(this.getAvailableMoveNumber()-1);
        setAvailableBuildNumber(this.getAvailableBuildNumber()+1);
        setHasMoved(true);
    }
}
