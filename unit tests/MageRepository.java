package org.example;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import java.util.Optional;

public class MageRepository {
    private Collection<Mage> collection= new ArrayList<>();
    public MageRepository(Collection<Mage> collection) {
        this.collection = collection;
    }
    public Collection<Mage> getCollection(){
        return collection;
    }
    public Optional<Mage> find(String name) {
        for(Mage m:collection){
           if(Objects.equals(m.getName(), name))
              return Optional.of(m);
        }
        return Optional.empty();
    }
    public void delete(String name) {
        Optional<Mage> mage=find(name);
        if (mage.isPresent())
                collection.remove(mage.get());
        else
            throw new IllegalArgumentException("Failed to remove mage from collection");
    }
    public void save(Mage mage){
        String name = mage.getName();
        Optional<Mage> mageExist = find(name);
        if (mageExist.isEmpty())
                collection.add(mage);
        else
            throw new IllegalArgumentException("Failed to add mage from collection");
    }
}

