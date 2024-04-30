import org.example.Mage;
import org.example.MageRepository;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.Assert.*;
//https://www.digitalocean.com/community/tutorials/junit-assert-exception-expected

public class TestRepository {
    @Test
    public void findInRepositoryWhenMageNotExist(){
        MageRepository repository = new MageRepository(new ArrayList<>());
        Mage mage = new Mage("Weronika",20);
        repository.save(mage);
        Optional<Mage> m=repository.find("Hania");
        assertEquals(Optional.empty(),m);
    }
    @Test
    public void findInRepositoryWhenMageExist(){
        MageRepository repository = new MageRepository(new ArrayList<>());
        Mage mage = new Mage("Weronika",20);
        repository.save(mage);
        Optional<Mage> m=repository.find("Weronika");
        assertTrue(m.isPresent());
        assertEquals(mage,m.get());
    }
    @Test
    public void saveToRepositoryWhenMageAlreadyExist(){
        MageRepository repository = new MageRepository(new ArrayList<>());
        Mage mage = new Mage("Weronika",20);
        repository.save(mage);
        Exception e =assertThrows(IllegalArgumentException.class, ()->repository.save(mage));
        String expected="Failed to add mage from collection";
        assertEquals(expected,e.getMessage());
    }
    @Test
    public void deleteFromRepositoryWhenMageNotFound(){
        MageRepository repository = new MageRepository(new ArrayList<>());
        Mage mage = new Mage("Weronika",20);
        repository.save(mage);
        Exception e =assertThrows(IllegalArgumentException.class, ()->repository.delete("hania"));
        String expected="Failed to remove mage from collection";
        assertEquals(expected,e.getMessage());
    }

}
