import com.google.gson.Gson;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Reader;

public class PostController {
    private final PostService service;
    public static final String APPLICATION_JSON = "application/json";
    public static final String FILE_IS_EMPTY = "File is empty";
    public static final String CONTENT_IS_EMPTY = "Content is empty";


    public PostController(PostService service) {
        this.service = service;
    }

    public void all(HttpServletResponse response) throws IOException {
        response.setContentType(APPLICATION_JSON);
        final var data = service.all();
        if (data.isEmpty()) {
            response.getWriter().print(FILE_IS_EMPTY);
            return;
        }
        final var gson = new Gson();
        response.getWriter().print(gson.toJson(data));
    }

    public void getById(long id, HttpServletResponse response) throws IOException {

        final var file = service.getById(id);
        if (file == null) {
            response.getWriter().print(FILE_IS_EMPTY);
            return;
        }else {
            if (file.getContent() == null) {
                response.getWriter().print(CONTENT_IS_EMPTY);
                return;
            }
        }
        response.setContentType(APPLICATION_JSON);
        final var gson = new Gson();
        response.getWriter().print(gson.toJson(file));
    }


    public void save(Reader body, HttpServletResponse response) throws IOException {
        response.setContentType(APPLICATION_JSON);
        final var gson = new Gson();
        final var post = gson.fromJson(body, Post.class);
        final var data = service.save(post);
        if (data == null) {
            response.getWriter().print(FILE_IS_EMPTY);
            return;
        }
        response.getWriter().print(gson.toJson(data));
    }

    public void removeById(long id, HttpServletResponse response) throws IOException {
response.setContentType("text/css");
        service.removeById(id);

        response.getWriter().print("Content deleted  ");
    }
}
