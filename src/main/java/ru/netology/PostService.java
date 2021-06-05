package ru.netology;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.netology.Post;
import ru.netology.PostRepository;

import java.util.Map;
@Component
public class PostService {
    private final PostRepository repository;
@Autowired
    public PostService(PostRepository repository) {
        this.repository = repository;
    }
    public Map<Long,String> all() {

        return repository.all();
    }

    public Post getById(long id) {
        return repository.getById(id);
    }

    public Post save(Post post) {
        return repository.save(post);
    }

    public void removeById(long id) {
        repository.removeById(id);
    }
}
