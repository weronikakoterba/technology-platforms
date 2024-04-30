package org.example;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Handler implements Runnable {
    //https://www.baeldung.com/a-guide-to-java-sockets
//https://www.youtube.com/watch?v=BqBKEXLqdvI

    private final Socket clientSocket;
    private ObjectInputStream input;
    private ObjectOutputStream output;
    private int id;
    public Handler(Socket socket,int number){
        clientSocket=socket;
        id=number;
    }
    // metoda run wywoływana przy uruchomieniu wątku obsługującego klienta.
    public void run() {
            try {
                connection();
                clientSocket.close();
                input.close();
            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        //metoda odpowiedzialna za połączenie z serwerem
        public void connection() throws IOException, ClassNotFoundException {
            Object number;// liczba wiadomości do przesłania
            Object message;// obiekt wiadomości

            // tworzenie strumienia wyjściowego
            output=new ObjectOutputStream(clientSocket.getOutputStream());
            // Tworzenie strumienia wejściowego.
            input=new ObjectInputStream(new BufferedInputStream(clientSocket.getInputStream()));
            output.writeObject("ready");//wysłanie potwierdzenia gotowości serwera
            number=input.readObject();// odczytanie numberu wysłanego przez klineta
            System.out.println("number of messages: "+number+" (from client "+id+")\n");
            output.writeObject("ready for messages");//wysłanie potwierdzenia gotowości na odbieranie obiektów
            for(int i=0;i<(int)number;i++){
                message=input.readObject();//odczytanie wiadomosci od klineta
                Message receivedMessage = (Message) message;//rzutowanie obiektów na typ <essage
                System.out.println("message number "+receivedMessage.getNumber()+" from client "+id+":");
                System.out.println(receivedMessage.getContent()+"\n");
            }
            output.writeObject("finished");//wysłanie potwierdzenia gotowości na odbieranie obiektów
        }
}
