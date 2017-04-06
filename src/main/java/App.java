import java.util.Map;
import java.util.HashMap;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;
import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class App {
  public static void main(String[] args) {
    externalStaticFileLocation(String.format("%s/src/main/resources/public", System.getProperty("user.dir")));

    // staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("posts", Post.all());
      model.put("user", request.session().attribute("user"));
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/posts", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("posts", Post.all());
      model.put("user", request.session().attribute("user"));
      model.put("template", "templates/posts.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());


    post("/login", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      String email = request.queryParams("email");
      User user;
      try {
        user = User.findByEmail(email);
      } catch (IllegalArgumentException exception) {
        user = new User(email);
        user.save();
      }
      request.session().attribute("user", user);
      response.redirect(request.headers("Referer"));
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/users/:id/posts/new", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("tags", Tag.all());
      model.put("user", request.session().attribute("user"));
      model.put("template", "templates/post-form.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/users/:id/posts/:postId", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("post", Post.find(Integer.parseInt(request.params(":postId"))));
      model.put("user", request.session().attribute("user"));
      model.put("template", "templates/post.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/users/:id/posts/new", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      String title = request.queryParams("title");
      String content = request.queryParams("content");
      int userId = Integer.parseInt(request.params(":id"));
      Post newPost = new Post(title, content, userId);
      newPost.save();
      String[] tags = request.queryParamsValues("tags");
      for (String tag : tags) {
        try {
          Tag currentTag = Tag.find(Integer.parseInt(tag));
          newPost.addTag(currentTag);
        } catch (NumberFormatException exception) {
          Tag newTag = new Tag(tag);
          newTag.save();
          newPost.addTag(newTag);
        }
      }
      String url = String.format("/users/%d/posts/%d", userId, newPost.getId());
      response.redirect(url);
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/users/:id/posts/:postId/update", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Post post = Post.find(Integer.parseInt(request.params(":postId")));
      String title = request.queryParams("title");
      String content = request.queryParams("content");
      post.update(title, content);
      response.redirect(request.headers("Referer"));
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/users/:id/posts/:postId/delete", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Post post = Post.find(Integer.parseInt(request.params(":postId")));
      post.delete();
      response.redirect("/");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/users/:id/posts/:postId/comments/new", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Post post = Post.find(Integer.parseInt(request.params(":postId")));
      String commentContent = request.queryParams("commentContent");
      User user = request.session().attribute("user");
      Comment newComment = new Comment(commentContent, post.getId(), user.getId());
      newComment.save();
      response.redirect(request.headers("Referer"));
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/tags/:id/posts", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Tag tag = Tag.find(Integer.parseInt(request.params(":id")));
      model.put("posts", tag.getPosts());
      model.put("user", request.session().attribute("user"));
      model.put("template", "templates/posts.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/search", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      String searchInput = request.queryParams("searchInput");
      List<Post> posts = new ArrayList<Post>();
      posts.addAll(Post.search(searchInput));
      for (Post post: Tag.search(searchInput)) {
        if (!(posts.contains(post))) {
          posts.add(post);
        }
      }
      model.put("posts", posts);
      model.put("user", request.session().attribute("user"));
      model.put("template", "templates/posts.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());


  }
}
