package it.polimi.ingsw;

import it.polimi.ingsw.connection.Client;

import java.io.IOException;

public class ClientMain {
    public static void main(String[] args){
        Client client = new Client("127.0.0.1", 2020);
        try{
            client.run();
        }catch (IOException e){
            System.err.println(e.getMessage());
        }
    }
}
