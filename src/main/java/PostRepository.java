import java.util.*;
import java.util.concurrent.ConcurrentHashMap;


public class PostRepository {
    private Map<Long, String> posts = new ConcurrentHashMap<>();
    private long id = 0;


    public Map<Long, String> all() {
        return posts;
    }

    public Post getById(long I) {
        if (posts.containsKey(I)) {
            final var content = posts.get(I);
            return new Post(I, content);
        } else return null;


    }


    public synchronized Post save(Post post) {

        if (post.getId() == 0) {
            id++;
            posts.put(id, post.getContent());
            return new Post(id, post.getContent());
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
