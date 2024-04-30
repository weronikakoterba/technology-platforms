package org.example;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
@Entity
public class Tower {
    @Id
    private String name;
    private int height;
    @OneToMany
    private List<Mage> mages;

    public Tower(String name,int height,List<Mage>mages){
        this.name=name;
        this.height=height;
        this.mages=mages;
    }

    public Tower() {
    }
    public void addMageToList(Mage m) {
        if (mages == null) {
            mages = new ArrayList<>();
        }
        mages.add(m);
    }
    public String getName(){
        return name;
    }
    public List<Mage> getMages(){
        return mages;
    }

    public StringBuilder printList(){
        StringBuilder sb = new StringBuilder();
        if(mages!=null) {
            for (Mage m : mages) {
                sb.append("\n  ").append(m);
            }
        }
        else
            sb.append("no mages");
        return sb;
    }

    @Override
    public String toString() {
        return "Tower: " +
                "name=" + name +
                ", height=" + height +
                ", list of mages: "+ printList();
    }
}
