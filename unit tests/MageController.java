package org.example;

import java.util.Objects;
import java.util.Optional;

public class MageController {
    private MageRepository repository;
    public MageController(MageRepository repository) {
        this.repository=repository;
    }
    public String find(String name) {
        Optional<Mage> mage = repository.find(name);
        if (mage.isPresent()) {
            return mage.get().toString();
        } else
            return "not found";
    }
    public String delete(String name) {
        Optional<Mage> mage=repository.find(name);
        if (mage.isPresent()) {
            repository.delete(name);
            return "done";
        }
        return "not found";
    }
    public String save(String name, String level) {
        int l = Integer.parseInt(level);
        Optional<Mage> mage=repository.find(name);
        if(mage.isEmpty()) {
            Mage mageToAdd = new Mage(name, l);
            repository.getCollection().add(mageToAdd);
            return "done";
        }else
            return "bad request";
    }
}
