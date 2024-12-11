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

    @PostMapping("/create")
    public ResponseEntity<ResponseDTO<Void>> create(@RequestBody PostRequestDTO.CreateDTO dto) {
        postService.create(dto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ResponseBuilder.success(HttpStatus.CREATED, null, "게시물 등록 성공"));
    }

    @GetMapping("/read/{id}")
    public ResponseEntity<ResponseDTO<?>> read(@PathVariable("id") Long id) {
        try {
            PostResponseDTO.ReadDTO post = postService.read(id);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(ResponseBuilder.success(HttpStatus.OK, post, "게시물 조회 성공"));
        } catch (NoSuchElementException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(ResponseBuilder.failure(HttpStatus.NOT_FOUND, "게시물이 존재하지 않음"));
        }
    }

    @GetMapping("/read")
    public ResponseEntity<ResponseDTO<?>> readList() {
        try {
            List<PostResponseDTO.ReadListDTO> postList = postService.readList();
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(ResponseBuilder.success(HttpStatus.OK, postList, "게시물 목록 조회 성공"));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResponseBuilder.failure(HttpStatus.INTERNAL_SERVER_ERROR, "목록 조회 중 에러 발생"));
        }
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseDTO<Void>> update(@RequestBody PostRequestDTO.UpdateDTO dto) {
        try {
            postService.update(dto);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(ResponseBuilder.success(HttpStatus.OK, null, "게시물 수정 성공"));
        } catch (NoSuchElementException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(ResponseBuilder.failure(HttpStatus.NOT_FOUND, "게시물이 존재하지 않습니다."));
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseDTO<Void>> delete(@PathVariable("id") Long id) {
        try {
            postService.delete(id);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(ResponseBuilder.success(HttpStatus.OK, null, "게시물 삭제 성공"));
        } catch (NoSuchElementException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(ResponseBuilder.failure(HttpStatus.NOT_FOUND, "게시물이 존재하지 않습니다."));
        }
    }
}
