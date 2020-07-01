package it.polimi.ingsw;

import it.polimi.ingsw.connection.Client;

import java.io.IOException;
import java.util.Scanner;

public class ClientMain {
    public static void main(String[] args) throws InterruptedException {
        Scanner scn = new Scanner(System.in);
        String ip = "";
        int port = 0;

        System.out.println("Welcome to the Santorini game!");
        Thread.sleep(1000);
        System.out.print("Please type in ip address: ");
        ip = scn.nextLine();
        System.out.print("\nPlease type in port number: ");
        port = Integer.parseInt(scn.nextLine());

        Client client = new Client(ip, port);
        try{
            client.run();
        }catch (IOException e){
            System.err.println(e.getMessage());
        }
    }
}
