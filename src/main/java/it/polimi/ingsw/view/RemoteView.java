package it.polimi.ingsw.view;

import it.polimi.ingsw.connection.SocketClientConnection;
import it.polimi.ingsw.model.*;
import it.polimi.ingsw.observer.messages.*;

import java.io.IOException;
import java.util.NoSuchElementException;

import static it.polimi.ingsw.utils.Colors.*;

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

    /**
     * {@inheritDoc}
     */
    public void chooseAction() {
        String action = " ";
        try {
            while (action.equals(" ")) {
                connection.send((String) "Chose one of the available actions:");
                if (availableMoveNumber > 0) {
                    connection.send("move");
                }
                if (availableBuildNumber > 0) {
                    connection.send("build");
                }
                if (hasMoved && hasBuilt) {
                    connection.send("end turn");
                }
                String read = connection.read();
                if ((read.equalsIgnoreCase("move") && availableMoveNumber > 0) || (read.equalsIgnoreCase("build") && availableBuildNumber > 0) || (read.equalsIgnoreCase("end turn") && hasMoved && hasBuilt)) {
                    action = read;
                } else {
                    connection.send((String) "Error! Invalid action");
                }
            }
            if (action.equalsIgnoreCase("move")) {
                moveHandler();
            }
            if (action.equalsIgnoreCase("build")) {
                buildHandler();
            }
            if (action.equalsIgnoreCase("end turn")) {
                endTurnHandler();
            }
        } catch (IOException | NoSuchElementException e) {
            notifyEndGame();
        }
    }

    /**
     * {@inheritDoc}
     */
    public void moveHandler() throws IOException, NoSuchElementException {
        int workerNumber = -1;
        int xCell = -1;
        int yCell = -1;
        while(!(workerNumber == 1 || workerNumber == 2)) {
            connection.send((Board)boardCopy);
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
        while (xCell < 0 && yCell < 0){
            connection.send((Board)boardCopy);
            connection.send((String)"Choose the coordinates 'x,y' of the destination cell from 0 to 4:");
            try {
                String s = connection.read();
                String[] coordinates = s.split(",");
                int x = Integer.parseInt(coordinates[0]);
                int y = Integer.parseInt(coordinates[1]);
                if(x < 0 || x > 4 || y < 0 || y > 4) {
                    connection.send((String)"Error! cell does not exist");
                }else {
                    xCell = x;
                    yCell = y;
                }
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                connection.send((String)"Error! Write the coordinates with this format 'x,y'");
            }
        }
        notifyMoveRequest(new MoveRequest(this, this.player, workerNumber, xCell, yCell));
    }

    /**
     * {@inheritDoc}
     */
    public void buildHandler() throws IOException, NoSuchElementException {
        int workerNumber = -1;
        int xCell = -1;
        int yCell = -1;
        Level level = Level.GROUND;
        while(!(workerNumber == 1 || workerNumber == 2)) {
            connection.send((Board)boardCopy);
            connection.send((String) "Choose which worker should build, 1 or 2?");
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
        while (xCell < 0 && yCell < 0){
            connection.send((Board)boardCopy);
            connection.send((String)"Choose the coordinates 'x,y' of the destination cell of the build from 0 to 4:");
            try {
                String s = connection.read();
                String[] coordinates = s.split(",");
                int x = Integer.parseInt(coordinates[0]);
                int y = Integer.parseInt(coordinates[1]);
                if(x < 0 || x > 4 || y < 0 || y > 4) {
                    connection.send((String)"Error! cell does not exist");
                }else{
                    xCell = x;
                    yCell = y;
                }
            }catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                connection.send((String)"Error! Write the coordinates with this format 'x,y'");
            }
        }
        while(level.equals(Level.GROUND)) {
            connection.send((Board)boardCopy);
            connection.send((String) "Choose the level block you want to build:\n'1' for Level 1 " + ANSI_BG_PURPLE+ "  " + ANSI_RESET + "\n'2' for Level 2 " + ANSI_BG_CYAN + "  " + ANSI_RESET + "\n'3' for Level 3 "+ ANSI_BG_YELLOW + "  " + ANSI_RESET + "\n'd' for Dome    " + ANSI_BRIGHT_BG_WHITE + "  " + ANSI_RESET);
            String s = connection.read();
            if (s.equals("1")) {
                level = Level.LEVEL1;
            } else if(s.equals("2")) {
                level = Level.LEVEL2;
            } else if(s.equals("3")) {
                level = Level.LEVEL3;
            } else if(s.equalsIgnoreCase("d")) {
                level = Level.DOME;
            } else {
                connection.send((String) "Error!");
            }
        }
        notifyBuildRequest(new BuildRequest(this, this.player, workerNumber, xCell, yCell, level));
    }

    /**
     * {@inheritDoc}
     */
    public void endTurnHandler(){
        notifyEndTurnRequest(new EndTurnRequest(this.player));
    }

    /**
     * {@inheritDoc}
     */
    public void reportError(Object message) throws IOException {
        connection.send((String)message);
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
    public void updateBoardChange(BoardChange message) throws IOException {
        setBoardCopy(message.getBoardCopy());
        connection.send((Board)this.boardCopy);
        if(message.getPlayer() == this.player) {
            this.availableMoveNumber = message.getAvailableMoveNumber();
            this.availableBuildNumber = message.getAvailableBuildNumber();
            this.hasMoved = message.getHasMoved();
            this.hasBuilt = message.getHasBuilt();
        }
    }

    @Override
    public void updateResetTurn(ResetTurn message) {
        if(message.getPreviousPlayer() == this.player) {
            this.availableMoveNumber = message.getAvailableMoveNumber();
            this.availableBuildNumber = message.getAvailableBuildNumber();
            this.hasMoved = message.getHasMoved();
            this.hasBuilt = message.getHasBuilt();
        }
    }

    @Override
    public void updatePlayerLose(PlayerLose message) throws IOException {
        setBoardCopy(message.getBoardCopy());
        connection.send((Board)this.boardCopy);
        if(message.getLosePlayer()==this.player){
            connection.send((String)"YOU LOSE");
        }
    }

    @Override
    public void updateWin(Win message) throws IOException {
        if(message.getPlayer()==this.player) {
            connection.send((String)"YOU WIN\nPress ENTER to close");
        }else {
            connection.send((String)"YOU LOSE\nPress ENTER to close");
        }
        connection.setEndGame();
    }

}
