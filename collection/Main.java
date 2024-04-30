package org.example;
import java.util.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Set<Mage> mageSet=null;
        Set<Mage> mageSet1=null;
        Set<Mage> mageSet2=null;
        Set<Mage> mageSet3=null;
        Map<Mage,Integer> map=null;

        if(args.length>0) {
            if (args[0].equals("1")) {
                mageSet = new TreeSet<>();
                mageSet1 = new TreeSet<>();
                mageSet2 = new TreeSet<>();
                mageSet3 = new TreeSet<>();
                map = new TreeMap<>();
            } else if (args[0].equals("2")) {
                mageSet = new TreeSet<>(Mage.com);
                mageSet1 = new TreeSet<>(Mage.com);
                mageSet2 = new TreeSet<>(Mage.com);
                mageSet3 = new TreeSet<>(Mage.com);
                map = new TreeMap<>(Mage.com);

            }
            else
                return;
        }
        else {
            mageSet = new HashSet<>();
            mageSet1 = new HashSet<>();
            mageSet2 = new HashSet<>();
            mageSet3 = new HashSet<>();
            map =new HashMap<>();
        }

        Mages mainMages=new Mages(mageSet);

        Mages.create_mages(mageSet,mageSet1,mageSet2,mageSet3);

        mainMages.map_mages(mageSet,map);
        System.out.println("Mag Map");
        for (Map.Entry<Mage, Integer> start : map.entrySet()) {
            System.out.println(start.getKey() + "= " + start.getValue());
        }

        System.out.println("Mag Tree");
        mainMages.show_mages(mageSet,1);
    }
}

