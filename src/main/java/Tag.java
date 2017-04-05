import org.sql2o.*;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.sql.Timestamp;

public class Tag {
  private String name;
  private int id;

  public Tag(String name) {
    this.name = name;
  }

  public String getName() {
    return this.name;
  }

  public int getId() {
    return this.id;
  }

  @Override
  public boolean equals(Object otherTag) {
    if (!(otherTag instanceof Tag)) {
      return false;
    } else {
      Tag newTag = (Tag) otherTag;
      return this.getName().equals(newTag.getName()) &&
             this.getId() == newTag.getId();
    }
  }

  public void save() {
    try (Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO tags (name) VALUES (:name);";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("name", this.name)
        .executeUpdate()
        .getKey();
    }
  }

  public static List<Tag> all() {
    try (Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM tags;";
      return con.createQuery(sql)
        .executeAndFetch(Tag.class);
    }
  }

  public static Tag find(int id) {
    try (Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM tags WHERE id = :id;";
      return con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Tag.class);
    }
  }

  public void update(String name) {
    this.name = name;
    try (Connection con = DB.sql2o.open()) {
      String sql = "UPDATE tags SET name = :name WHERE id = :id;";
      con.createQuery(sql)
        .addParameter("name", this.name)
        .addParameter("id", this.id)
        .executeUpdate();
    }
  }

  public void delete() {
    try (Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM tags WHERE id = :id;";
      con.createQuery(sql)
        .addParameter("id", this.id)
        .executeUpdate();
    }
  }

  public List<Post> getPosts() {
    try (Connection con = DB.sql2o.open()) {
      String sql = "SELECT posts.* FROM posts JOIN posts_tags ON (posts.id = posts_tags.postId) JOIN tags ON (posts_tags.tagId = tags.id) WHERE tags.id = :id;";
      return con.createQuery(sql)
        .addParameter("id", this.id)
        .executeAndFetch(Post.class);
    }
  }

}
