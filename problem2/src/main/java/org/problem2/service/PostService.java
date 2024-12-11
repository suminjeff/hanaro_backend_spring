package org.problem2.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.problem2.dto.post.PostRequestDTO;
import org.problem2.dto.post.PostResponseDTO;
import org.problem2.entity.Post;
import org.problem2.repository.PostRepository;
import org.problem2.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Log4j2
@Service
@Transactional
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public void create(PostRequestDTO.CreateDTO createDTO) {
        Post post = Post.builder()
                .writer(userRepository.findByName(createDTO.getWriter()))
                .title(createDTO.getTitle())
                .body(createDTO.getBody())
                .build();
        postRepository.save(post);
    }

    public PostResponseDTO.ReadDTO read(Long id) {
        Post post = postRepository.findById(id).orElseThrow();
        return PostResponseDTO.ReadDTO.builder()
                .id(post.getId())
                .createAt(post.getCreateAt())
                .updateAt(post.getUpdateAt())
                .body(post.getBody())
                .title(post.getTitle())
                .writer(post.getWriter().getName())
//                    .commentList()
                .build();
    }

    public List<PostResponseDTO.ReadListDTO> readList() {
        List<Post> postList = postRepository.findAll();
        List<PostResponseDTO.ReadListDTO> postDtoList = new ArrayList<PostResponseDTO.ReadListDTO>();
        for (Post post: postList) {
            postDtoList.add(PostResponseDTO.ReadListDTO.builder()
                            .id(post.getId())
                            .title(post.getTitle())
                            .writer(post.getWriter().getName())
                            .createAt(post.getCreateAt())
                            .updateAt(post.getUpdateAt())
//                            .commentCount()
                    .build());
        }
        return postDtoList;

    }

    public void update(PostRequestDTO.UpdateDTO updateDTO) {
        Post post = postRepository.findById(updateDTO.getId()).orElseThrow();
        Post updatedPost = Post.builder()
            .id(updateDTO.getId())
            .writer(userRepository.findByName(updateDTO.getWriter()))
            .title(updateDTO.getTitle())
            .body(updateDTO.getBody())
            .build();
        updatedPost.setCreateAt(post.getCreateAt());
        postRepository.save(updatedPost);
    }

    public void delete (Long id) {
        postRepository.deleteById(id);
    }

}


