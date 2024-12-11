package org.problem2.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.problem2.dto.general.apiResponse.ResponseBuilder;
import org.problem2.dto.general.apiResponse.ResponseDTO;
import org.problem2.dto.post.PostRequestDTO;
import org.problem2.dto.post.PostResponseDTO;
import org.problem2.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/posts")
public class PostController {

    private final PostService postService;

    @PostMapping("")
    public ResponseEntity<ResponseDTO<Void>> create(@RequestBody PostRequestDTO.CreateDTO dto) {
        try {
            postService.create(dto);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(ResponseBuilder.success(HttpStatus.CREATED, null, HttpStatus.CREATED.getReasonPhrase()));

        } catch (IllegalArgumentException e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(ResponseBuilder.failure(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.getReasonPhrase()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO<?>> read(@PathVariable("id") Long id) {
        try {
            PostResponseDTO.ReadDTO post = postService.read(id);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(ResponseBuilder.success(HttpStatus.OK, post, HttpStatus.OK.getReasonPhrase()));
        } catch (NoSuchElementException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(ResponseBuilder.failure(HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.getReasonPhrase()));
        }
    }

    @GetMapping("")
    public ResponseEntity<ResponseDTO<?>> readList() {
        try {
            List<PostResponseDTO.ReadListDTO> postList = postService.readList();
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
    public ResponseEntity<ResponseDTO<Void>> update(@RequestBody PostRequestDTO.UpdateDTO dto) {
        try {
            postService.update(dto);
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
            postService.delete(id);
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
