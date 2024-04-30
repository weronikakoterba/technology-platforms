package org.example;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;
//https://www.baeldung.com/a-guide-to-java-sockets
//https://www.youtube.com/watch?v=BqBKEXLqdvI

public class Client {
    private static Socket clientSocket = null;//gniazdo klineta
    private ObjectInputStream input;//strumień wejściowy obiektów
    private ObjectOutputStream out;//strumień wyjściowy obiektów

    // konstruktor klasy Client, który tworzy połączenie z serwerem.
    public Client(String address, int port) {
        try{
            clientSocket = new Socket(address, port);
            System.out.println("connected");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    //metoda rozpoczynająca komunikację z serwerem i odbierającą "ready" wysyłane przez serwer
    public void start_streaming() throws IOException, ClassNotFoundException {
        // tworzenie strumienia wejściowego
        input = new ObjectInputStream(clientSocket.getInputStream());
        // tworzenie strumienia wyjściowego
        out = new ObjectOutputStream(clientSocket.getOutputStream());
        Object ready = input.readObject();
        System.out.println(ready);
    }
    //metoda przesyłająca do serwera ilość przesyłanych obiektów klasy Message, a następnie same obiekty
    public void send_messages() throws IOException, ClassNotFoundException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("enter the number of messages to send:");
        int number=scanner.nextInt();
        out.writeObject(number);//przesłanie ilości obiektów do wysłania

        Object final_ready = input.readObject();//odczytanie stanu gotowości serwera
        System.out.println(final_ready);

        scanner.nextLine();
        //pętla do wysłania wiadomości
        for (int i = 0; i < number; i++) {
            System.out.println("enter the content of message " + (i + 1) + ": ");
            String text = scanner.nextLine();
            Message message = new Message(i + 1, text);
            out.writeObject(message);//przesłanie do serwera podanej ilości obiektów
        }
    }
    //zakończenie połączenia z serwerem
    public void close_connections() {
        try {
            Object finished=input.readObject();
            System.out.println(finished);
            input.close();
            out.close();
            clientSocket.close();
            System.out.println("connections is over");
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Client client = new Client("127.0.0.1", 1111);
        client.start_streaming();
        client.send_messages();
        client.close_connections();
    }
}