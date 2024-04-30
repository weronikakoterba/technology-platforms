package org.example;

import jakarta.persistence.*;

@Entity
public class Mage {
    @Id
    private String name;
    private int level;
    @ManyToOne
    private Tower tower;

    public Mage(String name,int level,Tower tower){
        this.name=name;
        this.level=level;
        this.tower=tower;
    }

    public Mage() {

    }
    public String getName(){
        return name;
    }
    public void setTower(Tower tower) {
        this.tower=tower;
    }
    public Tower getTower(){
        return tower;
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Mage: " + "name=").append(name).append(", level=").append(level).append(", tower=");
        if (tower != null)
            sb.append(tower.getName());
        else
            sb.append("no tower");
        return sb.toString();
    }


}
