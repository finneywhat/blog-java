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

public class PostTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void post_instantiatesCorrectly() {
    Post testPost = new Post("my travels", "travel blog post content");
    assertTrue(testPost instanceof Post);
  }

  @Test
  public void getTitle_returnsCorrectPostTitle() {
    Post testPost = new Post("my travels", "travel blog post content");
    assertEquals("my travels", testPost.getTitle());
  }

  @Test
  public void getContent_returnsCorrectPostContent() {
    Post testPost = new Post("my travels", "travel blog post content");
    assertEquals("travel blog post content", testPost.getContent());
  }

  @Test
  public void equals_comparesPostsBasedOnTitleAndContent() {
    Post testPost1 = new Post("my travels", "travel blog post content");
    Post testPost2 = new Post("my travels", "travel blog post content");
    assertTrue(testPost1.equals(testPost2));
  }

  @Test
  public void save_savesPostToDB() {
    Post testPost = new Post("my travels", "travel blog post content");
    testPost.save();
    assertTrue(Post.all().get(0).equals(testPost));
  }

  @Test
  public void all_returnsAllPostsInDB() {
    Post testPost1 = new Post("my travels", "travel blog post content");
    testPost1.save();
    Post testPost2 = new Post("my workouts", "workout blog post content");
    testPost2.save();
    assertTrue(Post.all().get(0).equals(testPost1));
    assertTrue(Post.all().get(1).equals(testPost2));
  }

  @Test
  public void save_assignsIDToPost() {
    Post testPost = new Post("my travels", "travel blog post content");
    testPost.save();
    assertEquals(testPost.getId(), Post.all().get(0).getId());
  }

  @Test
  public void getId_returnsAnId() {
    Post testPost = new Post("my travels", "travel blog post content");
    testPost.save();
    assertTrue(testPost.getId() > 0);
  }

  @Test
  public void find_returnsPostWithMatchingId() {
    Post testPost = new Post("my travels", "travel blog post content");
    testPost.save();
    assertEquals(testPost, Post.find(testPost.getId()));
  }

  @Test
  public void update_updatesPostName() {
    Post testPost = new Post("my travels", "travel blog post content");
    testPost.save();
    testPost.update("my workouts", "workout blog post content");
    assertEquals("my workouts", Post.find(testPost.getId()).getTitle());
    assertEquals("my workouts", testPost.getTitle());
    assertEquals("workout blog post content", Post.find(testPost.getId()).getContent());
    assertEquals("workout blog post content", testPost.getContent());
  }

  @Test
  public void delete_removesPostFromDB() {
    Post testPost = new Post("my travels", "travel blog post content");
    testPost.save();
    int testPostId = testPost.getId();
    testPost.delete();
    assertEquals(null, Post.find(testPostId));
  }

  @Test
  public void addTag_addATagToThePost() {
    Post testPost = new Post("my travels", "travel blog post content");
    testPost.save();
    Tag testTag1 = new Tag("Travel");
    testTag1.save();
    Tag testTag2 = new Tag("DIY");
    testTag2.save();
    Tag testTag3 = new Tag("Cooking");
    testTag3.save();
    testPost.addTag(testTag1);
    testPost.addTag(testTag2);
    Tag[] tags = new Tag[] {testTag1, testTag2};
    assertTrue(testPost.getTags().containsAll(Arrays.asList(tags)));
    assertFalse(testPost.getTags().contains(testTag3));
  }

  @Test
  public void removeTag_removesATagFromAPost() {
    Post testPost = new Post("my travels", "travel blog post content");
    testPost.save();
    Tag testTag1 = new Tag("Travel");
    testTag1.save();
    Tag testTag2 = new Tag("DIY");
    testTag2.save();
    Tag testTag3 = new Tag("Cooking");
    testTag3.save();
    testPost.addTag(testTag1);
    testPost.addTag(testTag2);
    testPost.addTag(testTag3);
    Tag[] tags = new Tag[] {testTag1, testTag2, testTag3};
    assertTrue(testPost.getTags().containsAll(Arrays.asList(tags)));
    testPost.removeTag(testTag3);
    assertFalse(testPost.getTags().contains(testTag3));
  }

  @Test
  public void getComments_returnsAllCommentsForAPost() {
    Post testPost = new Post("my travels", "travel blog post content");
    testPost.save();
    Comment testComment1 = new Comment("great post", testPost.getId());
    testComment1.save();
    Comment testComment2 = new Comment("really great post", testPost.getId());
    testComment2.save();
    Comment testComment3 = new Comment("really bad post", 4);
    testComment3.save();
    Comment[] comments = new Comment[] {testComment1, testComment2};
    assertTrue(testPost.getComments().containsAll(Arrays.asList(comments)));
    assertFalse(testPost.getComments().contains(testComment3));
  }

}
