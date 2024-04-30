package org.example;

import java.util.Map;
import java.util.Set;

public class Mages {
    private Set<Mage> mainMages;

    public Mages(Set<Mage> mainMages){

        this.mainMages=mainMages;
    }
    public void show_mages(Set<Mage> apprentices,int n) {
            for (Mage apprentice : apprentices) {
                for(int i=0;i<n;i++){
                    System.out.print('-');
                }
                System.out.println(apprentice.toString());
                if (apprentice.getApprentices() != null) {
                    n++;
                    show_mages(apprentice.getApprentices(), n);
                    n--;
                }
            }
    }
    public void map_mages(Set<Mage> mages, Map<Mage,Integer> map){
        for (Mage mage : mages) {
            if (mage.getApprentices() != null) {
                map.put(mage,mage.calc_all_apprentices(mage.getApprentices(),0));
                map_mages(mage.getApprentices(),map);
            }
            else{
                map.put(mage,0);
            }
        }

    }
    public static void create_mages(Set<Mage> mageSet, Set<Mage> mageSet1, Set<Mage> mageSet2, Set<Mage> mageSet3){
        Mage Teacher=new Mage("Teacher",20,600,null);
        Mage Wiktoria=new Mage("Wiktoria",20,600,mageSet1);
        Mage Weronika = new Mage("Weronika", 3, 50.0,null);
        Mage Agnieszka = new Mage("Agnieszka", 2, 40.0,null);
        Mage Hania = new Mage("Hania", 1, 300.0,mageSet2);
        Mage Tomasz = new Mage("Tomasz", 2, 50.0,null);
        Mage Mateusz = new Mage("Mateusz", 2, 40.0,null);
        Mage Igor = new Mage("Igor", 3, 10.0,mageSet3);
        Mage Mag8 = new Mage("Michella", 2, 50.0,null);
        Mage Mag9 = new Mage("Julita", 2, 40.0,null);
        Mage Mag10 = new Mage("Ala", 2, 50.0,null);

        mageSet.add(Wiktoria);
        mageSet.add(Teacher);

        Wiktoria.getApprentices().add(Weronika);
        Wiktoria.getApprentices().add(Agnieszka);
        Wiktoria.getApprentices().add(Hania);
        Wiktoria.getApprentices().add(Mag8);
        Wiktoria.getApprentices().add(Mag9);

        Hania.getApprentices().add(Tomasz);
        Hania.getApprentices().add(Igor);

        Igor.getApprentices().add(Mateusz);
        Igor.getApprentices().add(Mag10);
    }
}
