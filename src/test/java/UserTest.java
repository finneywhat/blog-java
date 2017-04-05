import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.sql.Timestamp;

public class UserTest {

  @Rule
  public DatabaseRule databse = new DatabaseRule();

  @Test
  public void user_instantiatesCorrectly() {
    User testUser = new User("user@gmail.com");
    assertTrue(testUser instanceof User);
  }

  @Test
  public void getEmail_returnsCorrectUserEmail() {
    User testUser = new User("user@gmail.com");
    assertEquals("user@gmail.com", testUser.getEmail());
  }

  @Test
  public void equals_comparesUsersBasedOnEmail() {
    User testUser1 = new User("user@gmail.com");
    User testUser2 = new User("user@gmail.com");
    assertTrue(testUser1.equals(testUser2));
  }

  @Test
  public void save_savesUserToDB() {
    User testUser = new User("user@gmail.com");
    testUser.save();
    assertTrue(User.all().get(0).equals(testUser));
  }

  @Test
  public void all_returnsAllUsersInDB() {
    User testUser1 = new User("user@gmail.com");
    testUser1.save();
    User testUser2 = new User("other-user@gmail.com");
    testUser2.save();
    assertTrue(User.all().get(0).equals(testUser1));
    assertTrue(User.all().get(1).equals(testUser2));
  }

  @Test
  public void save_assignsIDToUser() {
    User testUser = new User("user@gmail.com");
    testUser.save();
    assertEquals(testUser.getId(), User.all().get(0).getId());
  }

  @Test
  public void getId_returnsAnId() {
    User testUser = new User("user@gmail.com");
    testUser.save();
    assertTrue(testUser.getId() > 0);
  }

  @Test
  public void find_returnsUserWithMatchingId() {
    User testUser = new User("user@gmail.com");
    testUser.save();
    assertEquals(testUser, User.find(testUser.getId()));
  }

  @Test
  public void update_updatesUserEmail() {
    User testUser = new User("user@gmail.com");
    testUser.save();
    testUser.update("other-user@gmail.com");
    assertEquals("other-user@gmail.com", User.find(testUser.getId()).getEmail());
    assertEquals("other-user@gmail.com", testUser.getEmail());
  }

  @Test
  public void delete_removesUserFromDB() {
    User testUser = new User("user@gmail.com");
    testUser.save();
    int testUserId = testUser.getId();
    testUser.delete();
    assertEquals(null, User.find(testUserId));
  }

}
