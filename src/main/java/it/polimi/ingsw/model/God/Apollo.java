package it.polimi.ingsw.model.God;


import it.polimi.ingsw.model.Cell;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.Level;
import it.polimi.ingsw.model.Worker;

public class Apollo extends God {

    public Apollo(Game game){
        super(game);
    }

    @Override
    public void move(Cell cell, Worker worker) {
        if(cell.getWorker()!=null){
            cell.getWorker().setPosition(worker.getPosition());
            worker.getPosition().removeWorker();
            worker.getPosition().addWorker(cell.getWorker());
            cell.removeWorker();
        }
        else{
            worker.getPosition().removeWorker();
        }
        cell.addWorker(worker);
        worker.setPosition(cell);
        setAvailableMoveNumber(this.getAvailableMoveNumber()-1);
        setAvailableBuildNumber(this.getAvailableBuildNumber()+1);
        setHasMoved(true);
    }

    @Override
    public boolean isFeasibleMove(Cell cell, Worker worker) {

        if(cell.getWorker()!=null&&(cell.getWorker().getColor()==worker.getColor())) {
            return false;
        }

        if(cell.getLevel()== Level.DOME){
            return false;
        }

        if(cell.getLevel().ordinal()>(worker.getPosition().getLevel().ordinal()+1)){
            return false;
        }

        if(!this.getCanMoveUp()&& cell.getLevel().ordinal()== worker.getPosition().getLevel().ordinal()+1){
            return false;
        }

        if(cell.getX() == worker.getPosition().getX() - 1 || cell.getX() == worker.getPosition().getX() + 1 || cell.getX() == worker.getPosition().getX()) {
            if (cell.getY() == worker.getPosition().getY() - 1 || cell.getY() == worker.getPosition().getY() + 1 || cell.getY() == worker.getPosition().getY()) {
                return true;
            }
        }
        return false;
    }
}
