package ru.koshelev;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration

public class SpringConfig {
    @Bean
    public PostRepository postRepository(){
        return new PostRepository();

    }
    @Bean
    public PostService postService(){
        return new PostService(postRepository());
    }
    @Bean
  public   PostController postController(){
        return new PostController(postService());
    }
}
