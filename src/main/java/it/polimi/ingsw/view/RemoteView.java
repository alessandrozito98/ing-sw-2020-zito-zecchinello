package it.polimi.ingsw.view;

import it.polimi.ingsw.connection.SocketClientConnection;
import it.polimi.ingsw.model.Board;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.Worker;
import it.polimi.ingsw.model.WorkerColor;
import it.polimi.ingsw.observer.messages.*;

public class RemoteView extends View {
    private Player player;
    private SocketClientConnection connection;
    private Board boardCopy;
    private int availableMoveNumber;
    private int availableBuildNumber;
    private boolean hasMoved;
    private boolean hasBuilt;

    public RemoteView(Player player, SocketClientConnection connection, Board boardCopy){
        this.player = player;
        this.connection = connection;
        this.boardCopy = boardCopy;
        this.availableMoveNumber = player.getGodCard().getAvailableMoveNumber();
        this.availableBuildNumber = player.getGodCard().getAvailableBuildNumber();
        this.hasMoved = false;
        this.hasBuilt = false;
    }

    public void chooseAction(){
        String action = " ";
        while(action.equals(" ")){
            connection.send((String)"Chose one of the available actions:");
            String s = "";
            if(availableMoveNumber>0){s.concat("move ");}
            if(availableBuildNumber>0){s.concat("build ");}
            if(hasMoved&&hasBuilt){s.concat("end turn");}
            connection.send((String)s);
            String read = connection.read();
            if((read.equalsIgnoreCase("move")&&availableMoveNumber>0)||(read.equalsIgnoreCase("build")&&availableBuildNumber>0)||(read.equalsIgnoreCase("end turn")&&hasMoved&&hasBuilt)){
                action = read;
            }else{
                connection.send((String)"Error! Invalid action");
            }
        }
        if(action.equalsIgnoreCase("move")){ moveHandler();}
        if(action.equalsIgnoreCase("build")){ buildHandler();}
        if(action.equalsIgnoreCase("end turn")){ endTurnHandler();}
    }

    public void moveHandler(){
        int workerNumber = -1;
        int xCell = -1;
        int yCell = -1;
        while(!(workerNumber == 1||workerNumber == 2)){
            connection.send((String) "Choose which worker you want to move, 1 or 2?");
            try {
                String s = connection.read();
                int number = Integer.parseInt(s);
                if (number == 1 || number == 2) {
                    workerNumber = number;
                } else {
                    connection.send((String) "Error!");
                }
            } catch (NumberFormatException e) {
                connection.send((String) "Error!");
            }
        }
        while (xCell<0&&yCell<0){
            connection.send((Board)boardCopy);
            connection.send((String)"Choose the coordinates 'x,y' of the destination cell from 0 to 4:");
            try {
                String s = connection.read();
                String[] coordinates = s.split(",");
                int x = Integer.parseInt(coordinates[0]);
                int y = Integer.parseInt(coordinates[1]);
                if(x<0||x>4||y<0||y>4){
                    connection.send((String)"Error! cell does not exist");
                }else{
                    xCell = x;
                    yCell = y;
                }
            }catch (NumberFormatException | ArrayIndexOutOfBoundsException e){
                connection.send((String)"Error! Write the coordinates with this format 'x,y'");
            }
        }
        notifyMoveRequest(new MoveRequest(this,this.player,workerNumber,xCell,yCell));
    }

    public void buildHandler(){

    }

    public void endTurnHandler(){
        notifyEndTurnRequest(new EndTurnRequest(this.player));
    }

    public void reportError(String message){
        connection.send((String)message);
        chooseAction();
    }

    public Player getPlayer() {
        return player;
    }

    public SocketClientConnection getConnection() {
        return connection;
    }

    public Board getBoardCopy() {
        return boardCopy;
    }

    public void setBoardCopy(Board boardCopy) {
        this.boardCopy = boardCopy;
    }

    public int getAvailableMoveNumber() {
        return availableMoveNumber;
    }

    public void setAvailableMoveNumber(int availableMoveNumber) {
        this.availableMoveNumber = availableMoveNumber;
    }

    public int getAvailableBuildNumber() {
        return availableBuildNumber;
    }

    public void setAvailableBuildNumber(int availableBuildNumber) {
        this.availableBuildNumber = availableBuildNumber;
    }

    public boolean getHasMoved() {
        return hasMoved;
    }

    public void setHasMoved(boolean hasMoved) {
        this.hasMoved = hasMoved;
    }

    public boolean getHasBuilt() {
        return hasBuilt;
    }

    public void setHasBuilt(boolean hasBuilt) {
        this.hasBuilt = hasBuilt;
    }

    @Override
    public void updateBoardChange(BoardChange message) {

    }

    @Override
    public void updateResetTurn(ResetTurn message) {

    }

    @Override
    public void updatePlayerLose(PlayerLose message) {

    }
}
