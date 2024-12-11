package org.problem2.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.problem2.dto.comment.CommentRequestDTO;
import org.problem2.dto.comment.CommentResponseDTO;
import org.problem2.entity.Comment;
import org.problem2.repository.CommentRepository;
import org.problem2.repository.PostRepository;
import org.problem2.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Log4j2
@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    public void create(CommentRequestDTO.CreateDTO createDTO, Long postId) {
        Comment comment = Comment.builder()
                .writer(userRepository.findByName(createDTO.getWriter()))
                .post(postRepository.findById(postId).orElseThrow())
                .body(createDTO.getBody())
                .build();
        commentRepository.save(comment);
    }

    public List<CommentResponseDTO.ReadListDTO> readList(Long postId) {
        List<Comment> commentList = commentRepository.findAllByPostId(postId);
        List<CommentResponseDTO.ReadListDTO> commentDtoList = new ArrayList<CommentResponseDTO.ReadListDTO>();
        for (Comment comment: commentList) {
            commentDtoList.add(CommentResponseDTO.ReadListDTO.builder()
                    .id(comment.getId())
                    .writer(comment.getWriter().getName())
                    .createAt(comment.getCreateAt())
                    .updateAt(comment.getUpdateAt())
                    .build());
        }
        return commentDtoList;

    }

    public void update(CommentRequestDTO.UpdateDTO updateDTO) {
        Comment comment = commentRepository.findById(updateDTO.getId()).orElseThrow();
        Comment updatedComment = Comment.builder()
                .id(updateDTO.getId())
                .writer(userRepository.findByName(updateDTO.getWriter()))
                .post(comment.getPost())
                .body(updateDTO.getBody())
                .build();
        updatedComment.setCreateAt(comment.getCreateAt());
        commentRepository.save(updatedComment);
    }

    public void delete (Long id) {
        commentRepository.deleteById(id);
    }

}


