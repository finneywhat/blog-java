import org.junit.rules.ExternalResource;
import org.sql2o.*;

public class DatabaseRule extends ExternalResource {

  @Override
  protected void before() {
    DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/blog_test", null, null);
  }

  @Override
  protected void after() {
    try(Connection con = DB.sql2o.open()) {
      String deleteCommentQuery = "DELETE FROM comments *;";
      con.createQuery(deleteCommentQuery).executeUpdate();
      String deleteTagQuery = "DELETE FROM tags *;";
      con.createQuery(deleteTagQuery).executeUpdate();
      String deletePostQuery = "DELETE FROM posts *;";
      con.createQuery(deletePostQuery).executeUpdate();
    }
  }
}
