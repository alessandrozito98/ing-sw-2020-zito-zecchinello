package it.polimi.ingsw.model.God;

import it.polimi.ingsw.model.*;

import java.util.ArrayList;

public class Athena extends God {
    private ArrayList<Player> players;

    public Athena(Game game){
        super(game);
        this.players = game.getPlayers();

    }

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
        super.move(cell, worker);
    }
}
