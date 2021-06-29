package ru.koshelev;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.koshelev.PostController;
import ru.koshelev.PostRepository;
import ru.koshelev.PostService;

import javax.servlet.*;
import javax.servlet.http.*;

import java.io.IOException;


public class ServletNew extends HttpServlet {
    private PostController controller;

    @Override
    public void init() {
       AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        controller = context.getBean("postController",PostController.class);

    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final var path = req.getRequestURI();
        if (path.equals("/api/posts")) {
            controller.all(resp);
            return;
        } else if (path.matches("/api/posts/\\d+")) {
            // easy way
            final var id = Long.parseLong(path.substring(path.lastIndexOf("/") + 1));
            controller.getById(id, resp);
            return;

        }

        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final var path = req.getRequestURI();
        if (path.equals("/api/posts")) {
            controller.save(req.getReader(), resp);
            return;
        }
        super.doPost(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final var path = req.getRequestURI();

        if (path.matches("/api/posts/\\d+")) {

            final var id = Long.parseLong(path.substring(path.lastIndexOf("/")+1));
            controller.removeById(id, resp);
            return;
        }
        super.doDelete(req, resp);
    }
}