package org.example;
import java.io.Serializable;

public class Message implements Serializable {
    private int number;
    private String content;
    public Message(int number,String content){
        this.number=number;
        this.content=content;
    }
    //gettery i settery dla pol klasy message
    public int getNumber(){
        return number;
    }

    public String getContent(){
        return content;
    }

    public void setNumber(int number){
        this.number = number;
    }

    public void setContent(String content){
        this.content = content;
    }
}
