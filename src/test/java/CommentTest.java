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

public class CommentTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void comment_instantiatesCorrectly() {
    Comment testComment = new Comment("great post", 1);
    assertTrue(testComment instanceof Comment);
  }

  @Test
  public void getContent_returnsCorrectCommentContent() {
    Comment testComment = new Comment("great post", 1);
    assertEquals("great post", testComment.getContent());
  }

  @Test
  public void getPostId_returnsCorrectCommentPostId() {
    Comment testComment = new Comment("great post", 1);
    assertEquals(1, testComment.getPostId());
  }

  @Test
  public void equals_comparesCommentsBasedOnContentAndPostId() {
    Comment testComment1 = new Comment("great post", 1);
    Comment testComment2 = new Comment("great post", 1);
    assertTrue(testComment1.equals(testComment2));
  }

  @Test
  public void save_savesCommentToDB() {
    Comment testComment = new Comment("great post", 1);
    testComment.save();
    assertTrue(Comment.all().get(0).equals(testComment));
  }

  @Test
  public void all_returnsAllCommentsInDB() {
    Comment testComment1 = new Comment("great post", 1);
    testComment1.save();
    Comment testComment2 = new Comment("really great post", 2);
    testComment2.save();
    assertTrue(Comment.all().get(0).equals(testComment1));
    assertTrue(Comment.all().get(1).equals(testComment2));
  }

  @Test
  public void save_assignsIDToComment() {
    Comment testComment = new Comment("great post", 1);
    testComment.save();
    assertEquals(testComment.getId(), Comment.all().get(0).getId());
  }

  @Test
  public void getId_returnsAnId() {
    Comment testComment = new Comment("great post", 1);
    testComment.save();
    assertTrue(testComment.getId() > 0);
  }

  @Test
  public void find_returnsCommentWithMatchingId() {
    Comment testComment = new Comment("great post", 1);
    testComment.save();
    assertEquals(testComment, Comment.find(testComment.getId()));
  }

  @Test
  public void update_updatesCommentContent() {
    Comment testComment = new Comment("great post", 1);
    testComment.save();
    testComment.update("really great post");
    assertEquals("really great post", Comment.find(testComment.getId()).getContent());
    assertEquals("really great post", testComment.getContent());
  }

  @Test
  public void delete_removesCommentFromDB() {
    Comment testComment = new Comment("great post", 1);
    testComment.save();
    int testCommentId = testComment.getId();
    testComment.delete();
    assertEquals(null, Comment.find(testCommentId));
  }

}
