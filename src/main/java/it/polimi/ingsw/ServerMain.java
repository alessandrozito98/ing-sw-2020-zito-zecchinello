package it.polimi.ingsw;

import it.polimi.ingsw.connection.Server;

import java.io.IOException;
import java.util.Scanner;

public class ServerMain {
    public static void main( String[] args ) throws InterruptedException {
        Server server;
        Scanner scn = new Scanner(System.in);
        int port = 0;
        System.out.println("Starting server...");
        Thread.sleep(2000);
        try {
            System.out.println("Type in port number: ");
            port = Integer.parseInt(scn.nextLine());
            server = new Server(port);
            server.run();
        } catch (IOException e) {
            System.err.println("Impossible to initialize the server: " + e.getMessage() + "!");
        }
    }
}
