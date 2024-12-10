package org.problem2.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.problem2.model.dto.PostRequestDTO;
import org.problem2.model.entity.Post;
import org.problem2.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Log4j2
@Service
@Transactional
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    public void create(PostRequestDTO.CreatePostDTO createPostDTO) {
        Post post = Post.builder()
                .writer(createPostDTO.getWriter())
                .title(createPostDTO.getTitle())
                .body(createPostDTO.getBody())
                .build();
        postRepository.save(post);
    }

    public void read(Long postId) {
        Optional<Post> post = postRepository.findById(postId);

        return;
    }

    public void readList() {
        postRepository.findAll();
    }

    public void update(PostRequestDTO.UpdatePostDTO updatePostDTO) {

    }

    public void delete() {}
}
