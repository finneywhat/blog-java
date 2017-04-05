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

public class TagTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void tag_instantiatesCorrectly() {
    Tag testTag = new Tag("Travel");
    assertTrue(testTag instanceof Tag);
  }

  @Test
  public void getName_returnsCorrectTagName() {
    Tag testTag = new Tag("Travel");
    assertEquals("Travel", testTag.getName());
  }

  @Test
  public void equals_comparesTagsBasedOnName() {
    Tag testTag1 = new Tag("Travel");
    Tag testTag2 = new Tag("Travel");
    assertTrue(testTag1.equals(testTag2));
  }

  @Test
  public void save_savesTagToDB() {
    Tag testTag = new Tag("Travel");
    testTag.save();
    assertTrue(Tag.all().get(0).equals(testTag));
  }

  @Test
  public void all_returnsAllTagsInDB() {
    Tag testTag1 = new Tag("Travel");
    testTag1.save();
    Tag testTag2 = new Tag("DIY");
    testTag2.save();
    assertTrue(Tag.all().get(0).equals(testTag1));
    assertTrue(Tag.all().get(1).equals(testTag2));
  }

  @Test
  public void save_assignsIDToTag() {
    Tag testTag = new Tag("Travel");
    testTag.save();
    assertEquals(testTag.getId(), Tag.all().get(0).getId());
  }

  @Test
  public void getId_returnsAnId() {
    Tag testTag = new Tag("Travel");
    testTag.save();
    assertTrue(testTag.getId() > 0);
  }

  @Test
  public void find_returnsTagWithMatchingId() {
    Tag testTag = new Tag("Travel");
    testTag.save();
    assertEquals(testTag, Tag.find(testTag.getId()));
  }

  @Test
  public void update_updatesTagName() {
    Tag testTag = new Tag("Travel");
    testTag.save();
    testTag.update("DIY");
    assertEquals("DIY", Tag.find(testTag.getId()).getName());
    assertEquals("DIY", testTag.getName());
  }

  @Test
  public void delete_removesTagFromDB() {
    Tag testTag = new Tag("Travel");
    testTag.save();
    int testTagId = testTag.getId();
    testTag.delete();
    assertEquals(null, Tag.find(testTagId));
  }

  @Test
  public void getPosts_getsAllPostsWithTag() {
    Tag testTag = new Tag("Travel");
    testTag.save();
    Post testPost1 = new Post("my travels", "travel blog post content");
    testPost1.save();
    Post testPost2 = new Post("my other travels", "more travel blog post content");
    testPost2.save();
    Post testPost3 = new Post("my workouts", "workout blog post content");
    testPost3.save();
    testPost1.addTag(testTag);
    testPost2.addTag(testTag);
    Post[] posts = new Post[] {testPost1, testPost2};
    assertTrue(testTag.getPosts().containsAll(Arrays.asList(posts)));
    assertFalse(testTag.getPosts().contains(testPost3));
  }
}
