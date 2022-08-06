import com.kamalium.todo.AppRunner;
import com.kamalium.todo.entity.Item;
import com.kamalium.todo.repository.Repository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(classes = AppRunner.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RepositoryTest {

    @Autowired
    private Repository repo;

    @BeforeEach
    public void beforeEachTestCleanup() {
        repo.deleteAll();
    }

    @Test
    public void testRepo_IsEmpty() {
        Iterable<Item> items = repo.findAll();
        Assertions.assertThat(items).isEmpty();
    }

    @Test
    public void testRepo_hasProperty_ItemValue() {
        Item player = repo.save(new Item("Item1"));
        Assertions.assertThat(player).hasFieldOrPropertyWithValue("item_value", "Item1");
    }

    @Test
    public void testRepo_deleteAll() {
        repo.save(new Item("Item #1"));
        repo.save(new Item("Item #2"));
        repo.deleteAll();

        Assertions.assertThat(repo.findAll()).isEmpty();
    }

    @Test
    public void testRepo_findAllItems() {
        Item item1 = new Item("Item1");
        repo.save(item1);
        Item item2 = new Item("Item2");
        repo.save(item2);
        Item item3 = new Item("Item3");
        repo.save(item3);

        Iterable<Item> items = repo.findAll();
        Assertions.assertThat(items).hasSize(3);
    }


    @Test
    public void testRepo_findItem() {
        Item item1 = new Item("Item1");
        repo.save(item1);
        Item item2 = new Item("Item2");
        repo.save(item2);

        Optional<Item> itemFound =  repo.findById(item2.getId());
        Assertions.assertThat(itemFound.get().getId()).isEqualTo(item2.getId());
    }

}

