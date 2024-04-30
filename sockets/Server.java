package org.example;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
//https://www.baeldung.com/a-guide-to-java-sockets
//https://www.youtube.com/watch?v=BqBKEXLqdvI

public class Server {
    private ServerSocket serverSocket;//gniazdo serwera
    private Socket clientSocket;//gniazdo klineta
    // konstruktor klasy Server, który nasłuchuje na połączenia klientów.
    public Server (int port) {
        int i=1;
        try {
            serverSocket = new ServerSocket(port);// tworzenie gniazda serwera na danym porcie.
            System.out.println("waiting for clients");
            while (true) {
                clientSocket = serverSocket.accept();//akceptowanie klientów
                Thread thread = new Thread(new Handler(clientSocket,i));//tworzenie nowego wątku obsługującego połączenie z klientem
                thread.start();
                System.out.println("client "+i+" connected");
                i++;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

        public static void main(String[] args) throws IOException {
        Server server=new Server(1111);
    }
}
