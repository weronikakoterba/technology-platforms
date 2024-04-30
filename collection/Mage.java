package org.example;
import java.util.Comparator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class Mage implements Comparable<Mage> {
    private String name;
    private int level;
    private double power;
    private Set<Mage> apprentices;

    public Mage(String name, int level, double power, Set<Mage> apprentices) {
        this.name = name;
        this.level = level;
        this.power = power;
        this.apprentices = apprentices;
    }
    public Set<Mage> getApprentices() {
        return apprentices;
    }

    //programowanie obiektowe 2 semestr, labaoratorum nr 3
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Mage other) {
            return (level == other.level && power == other.power && Objects.equals(name, other.name)&&apprentices==other.apprentices);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return 77 * Double.hashCode((level + Math.pow(power,2))/3) + 3;
    }

    @Override
    public String toString() {
        return "Mage{name=" + name + ", level=" + level + ", power=" + power + "}";
    }

    //https://www.geeksforgeeks.org/comparable-interface-in-java-with-examples/
    //name,level,power
    @Override
    public int compareTo(Mage other) {
        if (this.name.compareTo(other.name) != 0) {
            return this.name.compareTo(other.name);//imiona nie takie same
        }
        if (this.level != other.level) {
            return this.level - other.level;
        }
        else {
            int ipower = (int) this.power;
            int iipower = (int) other.power;
            return ipower - iipower;
        }
    }
    //https://www.baeldung.com/java-comparator-comparable
    //https://stackoverflow.com/questions/26016467/implementing-both-comparable-and-comparator-interface-in-a-single-class-pros-a
    //level,name,power
    public static final Comparator<Mage> com = new Comparator<Mage>() {
        @Override
        public int compare(Mage one, Mage other) {
            if (one.level != other.level) {
                return Integer.compare(one.level, other.level);
            }
            else if (!Objects.equals(one.name, other.name)) {
                return CharSequence.compare(one.name, other.name);
            }
            else {
                return Double.compare(one.power, other.power);
            }
        }
    };
    public int calc_all_apprentices(Set<Mage> apprentices, int n) {
        for (Mage apprentice : apprentices) {
            if (apprentice.getApprentices() != null) {
                n= calc_all_apprentices( apprentice.getApprentices(), n+1);
            } else
                n++;
        }
        return n;
    }
}

