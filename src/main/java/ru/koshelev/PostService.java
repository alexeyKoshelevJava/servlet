package ru.koshelev;

import ru.koshelev.PostController;
import ru.koshelev.PostRepository;

import java.util.Map;

public class PostService {
    private final PostRepository repository;

    public PostService(PostRepository repository) {
        this.repository = repository;
    }
    public Map<Long,String> all() {

        return repository.all();
    }

    public PostController.Post getById(long id) {
        return repository.getById(id);
    }

    public PostController.Post save(PostController.Post post) {
        return repository.save(post);
    }

    public void removeById(long id) {
        repository.removeById(id);
    }
}
