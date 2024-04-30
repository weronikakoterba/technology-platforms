import org.example.Mage;
import org.example.MageController;
import org.example.MageRepository;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
//https://www.baeldung.com/mockito-behavior

public class TestController {
    @Test
    public void deleteFromControllerWhenMageFound(){
        MageRepository repository= mock(MageRepository.class);
        MageController controller=new MageController(repository);
        Mage mage = new Mage("Weronika",20);
        when(repository.find("Weronika")).thenReturn(Optional.of(mage));
        String data=controller.delete("Weronika");
        String expected="done";
        assertEquals(expected,data);

    }
    @Test
    public void deleteFromControllerWhenMageNotFound(){
        MageRepository repository= mock(MageRepository.class);
        MageController controller=new MageController(repository);
        String actual=controller.delete("Hania");
        String expected="not found";
        assertEquals(expected,actual);

    }
    @Test
    public void findInControllerWhenMageNotExist(){
        MageRepository repository= mock(MageRepository.class);
        MageController controller=new MageController(repository);
        String actual=controller.find("Hania");
        String expected="not found";
        assertEquals(expected,actual);

    }
    @Test
    public void findInControllerWhenMageExist(){
        MageRepository repository= mock(MageRepository.class);
        MageController controller=new MageController(repository);
        Mage mage = new Mage("Weronika",20);
        when(repository.find("Weronika")).thenReturn(Optional.of(mage));
        String actual=controller.find("Weronika");
        String expected=mage.toString();
        assertEquals(expected,actual);
    }
    @Test
    public void saveInControllerWhenMageNotExist(){
        MageRepository repository= mock(MageRepository.class);
        MageController controller=new MageController(repository);
        Mage mage = new Mage("Weronika",20);
        when(repository.find("Weronika")).thenReturn(Optional.empty());
        String actual=controller.save("Weronika","20");
        String expected="done";
        assertEquals(expected,actual);
    }
    @Test
    public void saveInControllerWhenMageAlreadyExist(){
        MageRepository repository= mock(MageRepository.class);
        MageController controller=new MageController(repository);
        Mage mage = new Mage("Weronika",20);
        when(repository.find("Weronika")).thenReturn(Optional.of(mage));
        String actual=controller.save("Weronika","20");
        String expected="bad request";
        assertEquals(expected,actual);
    }

}
