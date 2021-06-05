package ru.netology;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                "ApplicationContext.xml"
        );
         PostController   controller =  context.getBean("postController",PostController.class);
         controller.printHello();
    }

}
