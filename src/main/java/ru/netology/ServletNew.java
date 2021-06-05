package ru.netology;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import ru.netology.PostController;
import ru.netology.PostRepository;
import ru.netology.PostService;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ServletNew extends HttpServlet {

    PostController controller ;


@Override
public void init() {
//    ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
//            "ApplicationContext.xml"
//    );
//    controller =  context.getBean("postController",PostController.class);

    final var repository = new PostRepository();

    final var service = new PostService(repository);
   controller = new PostController(service);



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

            final var id = Long.parseLong(path.substring(path.lastIndexOf("/") + 1));
            controller.removeById(id, resp);
            return;
        }
        super.doDelete(req, resp);
    }
}