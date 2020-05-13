package it.polimi.ingsw.model.God;

import it.polimi.ingsw.model.*;

public class Minotaur extends God {

    public Minotaur(Game game){
        super(game);
    }

    public Board getBoard(){return this.board;}

    @Override
    public void move(Cell cell, Worker worker) {
        Level oldLevel = worker.getPosition().getLevel();
        if(cell.getWorker()!=null){
            int x = cell.getX()-(worker.getPosition().getX()-cell.getX());
            int y = cell.getY()-(worker.getPosition().getY()-cell.getY());
            cell.getWorker().setPosition(board.getCell(x,y));
            board.getCell(x,y).addWorker(cell.getWorker());
            cell.removeWorker();
        }
        worker.getPosition().removeWorker();
        cell.addWorker(worker);
        worker.setPosition(cell);
        setAvailableMoveNumber(this.getAvailableMoveNumber()-1);
        setAvailableBuildNumber(this.getAvailableBuildNumber()+1);
        setHasMoved(true);
    }

    @Override
    public boolean isFeasibleMove(Cell cell, Worker worker) {

        if(!(cell.getWorker()==null)&&(cell.getWorker().getColor()==worker.getColor())) {
            return false;
        }

        if(cell.getLevel()== Level.DOME){
            return false;
        }

        if(cell.getLevel().ordinal()>(worker.getPosition().getLevel().ordinal()+1)){
            return false;
        }

        if(!this.getCanMoveUp()&&(cell.getLevel().ordinal()==(worker.getPosition().getLevel().ordinal()+1))){
            return false;
        }

        if(cell.getX() == worker.getPosition().getX() - 1 || cell.getX() == worker.getPosition().getX() + 1 || cell.getX() == worker.getPosition().getX()) {
            if (cell.getY() == worker.getPosition().getY() - 1 || cell.getY() == worker.getPosition().getY() + 1 || cell.getY() == worker.getPosition().getY()) {
                if(cell.getWorker()==null) {
                    return true;
                }
                else{
                    int x = cell.getX()-(worker.getPosition().getX()-cell.getX());
                    int y = cell.getY()-(worker.getPosition().getY()-cell.getY());
                    if((x >= 0) && (x < 5) && (y >= 0) && (y < 5) && (board.getCell(x,y).getLevel()!=Level.DOME) && (board.getCell(x,y).getWorker()==null)){
                        return true;
                    }
                    else{return false;}
                }
            }
        }
        return false;
    }
}
