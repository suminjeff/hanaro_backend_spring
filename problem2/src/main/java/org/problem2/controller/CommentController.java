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

    @PostMapping("")
    public ResponseEntity<ResponseDTO<Void>> create(@RequestBody CommentRequestDTO.CreateDTO dto, @RequestParam("postId") Long postId) {
        try {
            commentService.create(dto, postId);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(ResponseBuilder.success(HttpStatus.CREATED, null, HttpStatus.CREATED.getReasonPhrase()));
        } catch (NoSuchElementException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(ResponseBuilder.failure(HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.getReasonPhrase()));
        } catch (IllegalArgumentException e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(ResponseBuilder.failure(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.getReasonPhrase()));
        }
    }

    @GetMapping("")
    public ResponseEntity<ResponseDTO<?>> readList(@RequestParam("postId") Long postId) {
        try {
            List<CommentResponseDTO.ReadListDTO> postList = commentService.readList(postId);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(ResponseBuilder.success(HttpStatus.OK, postList, HttpStatus.OK.getReasonPhrase()));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResponseBuilder.failure(HttpStatus.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()));
        }
    }

    @PutMapping("")
    public ResponseEntity<ResponseDTO<Void>> update(@RequestBody CommentRequestDTO.UpdateDTO dto, @RequestParam("postId") Long postId) {
        try {
            commentService.update(dto);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(ResponseBuilder.success(HttpStatus.OK, null, HttpStatus.OK.getReasonPhrase()));
        } catch (NoSuchElementException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(ResponseBuilder.failure(HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.getReasonPhrase()));
        } catch (IllegalArgumentException e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(ResponseBuilder.failure(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.getReasonPhrase()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO<Void>> delete(@PathVariable("id") Long id) {
        try {
            commentService.delete(id);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(ResponseBuilder.success(HttpStatus.OK, null, HttpStatus.OK.getReasonPhrase()));
        } catch (NoSuchElementException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(ResponseBuilder.failure(HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.getReasonPhrase()));
        }
    }
}
