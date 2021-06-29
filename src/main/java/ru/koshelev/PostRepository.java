package ru.koshelev;

import ru.koshelev.PostController;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;


public class PostRepository {
    private Map<Long, String> posts = new ConcurrentHashMap<>();
    private long id = 0;


    public Map<Long, String> all() {
        return posts;
    }

    public PostController.Post getById(long I) {
        if (posts.containsKey(I)) {
            final var content = posts.get(I);
            return new PostController.Post(I, content);
        } else return null;


    }


    public synchronized PostController.Post save(PostController.Post post) {

        if (post.getId() == 0) {
            id++;
            posts.put(id, post.getContent());
            return new PostController.Post(id, post.getContent());
        } else if (post.getId() != 0  && posts.containsKey(post.getId()) ){
            posts.put(post.getId(), post.getContent());
            return post;
        }
        return null;
    }

    public void removeById(long ID) {
        if (posts.containsKey(ID)) {
            posts.remove(ID);


        }
        }

    }
