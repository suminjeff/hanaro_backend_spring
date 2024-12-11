package org.problem2.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.problem2.dto.comment.CommentRequestDTO;
import org.problem2.dto.general.apiResponse.ResponseBuilder;
import org.problem2.dto.general.apiResponse.ResponseDTO;
import org.problem2.dto.comment.CommentResponseDTO;
import org.problem2.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/comments")
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/create/{postId}")
    public ResponseEntity<ResponseDTO<Void>> create(@RequestBody CommentRequestDTO.CreateDTO dto, @PathVariable("postId") Long postId) {
        try {
            commentService.create(dto, postId);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(ResponseBuilder.success(HttpStatus.CREATED, null, "댓글 등록 성공"));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(ResponseBuilder.failure(HttpStatus.NOT_FOUND, "게시글이 존재하지 않습니다."));
        }
    }

    @GetMapping("/read/{postId}")
    public ResponseEntity<ResponseDTO<?>> readList(@PathVariable("postId") Long postId) {
        try {
            List<CommentResponseDTO.ReadListDTO> postList = commentService.readList(postId);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(ResponseBuilder.success(HttpStatus.OK, postList, "댓글 목록 조회 성공"));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResponseBuilder.failure(HttpStatus.INTERNAL_SERVER_ERROR, "목록 조회 중 에러 발생"));
        }
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseDTO<Void>> update(@RequestBody CommentRequestDTO.UpdateDTO dto) {
        try {
            commentService.update(dto);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(ResponseBuilder.success(HttpStatus.OK, null, "댓글 수정 성공"));
        } catch (NoSuchElementException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(ResponseBuilder.failure(HttpStatus.NOT_FOUND, "댓글이 존재하지 않습니다."));
        }
    }

    @DeleteMapping("/delete/{postId}/{id}")
    public ResponseEntity<ResponseDTO<Void>> delete(@PathVariable("postId") Long postId, @PathVariable("id") Long id) {
        try {
            commentService.delete(id);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(ResponseBuilder.success(HttpStatus.OK, null, "댓글 삭제 성공"));
        } catch (NoSuchElementException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(ResponseBuilder.failure(HttpStatus.NOT_FOUND, "댓글이 존재하지 않습니다."));
        }
    }
}
