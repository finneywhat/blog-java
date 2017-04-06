import org.sql2o.*;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.sql.Timestamp;

public class Post {
  private String title;
  private int id;
  private String content;
  private int userId;

  public Post(String title, String content, int userId) {
    this.title = title;
    this.content = content;
    this.userId = userId;
  }

  public String getTitle() {
    return this.title;
  }

  public String getContent() {
    return this.content;
  }

  public int getId() {
    return this.id;
  }

  public int getUserId() {
    return this.userId;
  }

  public String getUserName() {
    try (Connection con = DB.sql2o.open()) {
      String sql = "SELECT name FROM users WHERE id = :userId;";
      return con.createQuery(sql)
        .addParameter("userId", this.userId)
        .executeAndFetchFirst(String.class);
    }
  }

  @Override
  public boolean equals(Object otherPost) {
    if (!(otherPost instanceof Post)) {
      return false;
    } else {
      Post newPost = (Post) otherPost;
      return this.getTitle().equals(newPost.getTitle()) &&
             this.getContent().equals(newPost.getContent()) &&
             this.getUserId() == newPost.getUserId() &&
             this.getId() == newPost.getId();
    }
  }

  public void save() {
    try (Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO posts (title, content, userId) VALUES (:title, :content, :userId);";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("title", this.title)
        .addParameter("content", this.content)
        .addParameter("userId", this.userId)
        .executeUpdate()
        .getKey();
    }
  }

  public static List<Post> all() {
    try (Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM posts;";
      return con.createQuery(sql)
        .executeAndFetch(Post.class);
    }
  }

  public static Post find(int id) {
    try (Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM posts WHERE id = :id;";
      return con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Post.class);
    }
  }

  public void update(String title, String content) {
    this.title = title;
    this.content = content;
    try (Connection con = DB.sql2o.open()) {
      String sql = "UPDATE posts SET (title, content) = (:title, :content) WHERE id = :id;";
      con.createQuery(sql)
        .addParameter("title", this.title)
        .addParameter("content", this.content)
        .addParameter("id", this.id)
        .executeUpdate();
    }
  }

  public void delete() {
    try (Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM posts_tags WHERE postId = :id;";
      con.createQuery(sql)
        .addParameter("id", this.id)
        .executeUpdate();
    }
    try (Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM posts WHERE id = :id;";
      con.createQuery(sql)
        .addParameter("id", this.id)
        .executeUpdate();
    }
  }

  public void addTag(Tag newTag) {
    try (Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO posts_tags (postId, tagId) VALUES (:postId, :tagId);";
      con.createQuery(sql)
        .addParameter("postId", this.id)
        .addParameter("tagId", newTag.getId())
        .executeUpdate();
    }
  }

  public List<Tag> getTags() {
    try (Connection con = DB.sql2o.open()) {
      String sql = "SELECT tags.* FROM tags JOIN posts_tags ON (tags.id = posts_tags.tagId) JOIN posts ON (posts_tags.postId = posts.id) WHERE posts.id = :id;";
      return con.createQuery(sql)
        .addParameter("id", this.id)
        .executeAndFetch(Tag.class);
    }
  }

  public void removeTag(Tag newTag) {
    try (Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM posts_tags WHERE postId = :postId AND tagId = :tagId;";
      con.createQuery(sql)
      .addParameter("postId", this.id)
      .addParameter("tagId", newTag.getId())
      .executeUpdate();
    }
  }

  public List<Comment> getComments() {
    try (Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM comments WHERE postId = :id;";
      return con.createQuery(sql)
        .addParameter("id", this.id)
        .executeAndFetch(Comment.class);
    }
  }

  public static List<Post> search(String input) {
    String newInput = "%" + input + "%";
    try (Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM posts WHERE lower(title) LIKE lower(:newInput);";
      // String sql = "SELECT * FROM posts WHERE lower(title) LIKE lower(:newInput) OR lower(content) LIKE lower(:newInput);";
      return con.createQuery(sql)
        .addParameter("newInput", newInput)
        .executeAndFetch(Post.class);
    }
  }

}
