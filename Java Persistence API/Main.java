package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.logging.Level;
import java.util.logging.Logger;


public class Main {
    public static void main(String[] args) {

        Logger.getLogger("org.hibernate").setLevel(Level.OFF);

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("MagesAndTowers");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        DatabaseMethods manageEntities=new DatabaseMethods(entityManager);

        DatabaseMethods.addTower("tower1",20,null);
        DatabaseMethods.addMage("Weronika",10,"tower1");
        DatabaseMethods.addMage("Wiktoria",20,"tower1");

        DatabaseMethods.showMages();
        DatabaseMethods.showTowers();

        DatabaseMethods.addTower("tower2",30,null);
        DatabaseMethods.addMage("Tomek",10,"tower2");
        DatabaseMethods.addMage("Mateusz",20,"tower2");
        DatabaseMethods.addMage("Agnieszka",30,"tower2");

        DatabaseMethods.showMages();
        DatabaseMethods.showTowers();

        DatabaseMethods.showMagesWithLevel(15);

        DatabaseMethods.deleteMage("Weronika");
        DatabaseMethods.deleteMage("Ala");
        DatabaseMethods.deleteTower("tower2");
        DatabaseMethods.deleteTower("tower3");
        DatabaseMethods.showMages();
        DatabaseMethods.showTowers();

        entityManager.close();
        entityManagerFactory.close();
    }
}