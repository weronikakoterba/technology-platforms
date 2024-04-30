package org.example;

import jakarta.persistence.EntityManager;

import java.util.List;

public class DatabaseMethods {
    private static EntityManager entityManager;
    public DatabaseMethods(EntityManager entityManager){
        DatabaseMethods.entityManager =entityManager;
    }
    public static void addMage(String name, int level, String towerName){
        entityManager.getTransaction().begin();
        Mage mage;
        if(towerName!=null) {
            Tower tower = entityManager.find(Tower.class, towerName);
            if (tower != null) {
                mage=new Mage(name,level,tower);
                tower.addMageToList(mage);
            }else{
                System.out.println("Mage "+ name +" cannot be added, tower with given tower name does not exist");
                entityManager.getTransaction().rollback();
                return;
            }
        } else
            mage=new Mage(name,level,null);

        entityManager.persist(mage);
        System.out.println("Mage "+name+" added");
        entityManager.getTransaction().commit();
    }

    public static void addTower(String name, int height, List<Mage> mageList) {
        entityManager.getTransaction().begin();
        Tower tower;
        if (mageList != null && !mageList.isEmpty()) {
            boolean exist = true;
            for (Mage m : mageList) {
                if (entityManager.find(Mage.class, m.getName()) == null) {
                    exist = false;
                    break;
                }
            }
            if (exist) {
                tower = new Tower(name, height, mageList);
                for (Mage m : mageList)
                    m.setTower(tower);
            } else {
                System.out.println("Tower "+name+" cannot be added, list of mages is incorrect");
                entityManager.getTransaction().rollback();
                return;
            }
        } else {
            tower = new Tower(name, height, null);
        }

        entityManager.persist(tower);
        System.out.println("Tower "+ name +" added");
        entityManager.getTransaction().commit();
    }
    //https://www.baeldung.com/java-hibernate-fetch-entity-list
    public static void showMages(){
        entityManager.getTransaction().begin();
        String command = "SELECT m FROM Mage m";
        List<Mage> mages = entityManager.createQuery(command, Mage.class).getResultList();
        System.out.println("List of mages:");
        for (Mage m : mages) {
            System.out.println(m);
        }
        entityManager.getTransaction().commit();
    }
    public static void showTowers(){
        entityManager.getTransaction().begin();
        String command="SELECT t FROM Tower t";
        List<Tower> towers = entityManager.createQuery(command, Tower.class).getResultList();
        System.out.println("List of towers:");
        for (Tower t : towers) {
            System.out.println(t);
        }
        entityManager.getTransaction().commit();
    }
    //https://www.baeldung.com/delete-with-hibernate
    public static void deleteMage(String mageName){
        entityManager.getTransaction().begin();
        Mage mage = entityManager.find(Mage.class, mageName);
        if(mage!=null) {
            Tower tower= mage.getTower();
            if(tower!=null){
                List<Mage> mages=tower.getMages();
                mages.remove(mage);
            }
            entityManager.remove(mage);
            System.out.println("Mage "+mageName+" removed");
        } else{
            System.out.println("Mage "+mageName+" does not exist, mage cannot be removed");
            entityManager.getTransaction().rollback();
            return;
        }
        entityManager.flush();
        entityManager.clear();
        entityManager.getTransaction().commit();
    }
    public static void deleteTower(String towerName){
        entityManager.getTransaction().begin();
        Tower tower = entityManager.find(Tower.class, towerName);
        if(tower!=null) {
            List<Mage> mages=tower.getMages();
            if(mages!=null){
                for(Mage m:mages){
                    m.setTower(null);
                }
            }
            System.out.println("Tower "+towerName+" removed");
            entityManager.remove(tower);
        }else{
            System.out.println("Tower "+towerName+" does not exist, tower cannot be removed");
            entityManager.getTransaction().rollback();
            return;
        }
        entityManager.flush();
        entityManager.clear();
        entityManager.getTransaction().commit();
    }
    public static void showMagesWithLevel(int level){
        entityManager.getTransaction().begin();
        String command = "SELECT m FROM Mage m WHERE m.level>:level";
        List<Mage> mages = entityManager.createQuery(command, Mage.class).setParameter("level",level).getResultList();
        System.out.println("Mages with level greater than "+level+":");
        for (Mage m : mages) {
            System.out.println(m);
        }
        entityManager.getTransaction().commit();
    }
}
