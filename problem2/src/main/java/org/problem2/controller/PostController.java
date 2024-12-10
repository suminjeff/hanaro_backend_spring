package org.problem2.controller;

import lombok.extern.log4j.Log4j2;
import org.problem2.model.dto.PostRequestDTO;
import org.problem2.model.entity.Post;
import org.problem2.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;
import java.util.Map;

@Log4j2
@RestController
@RequestMapping("/post")
public class PostController {
    private PostService service;

    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody PostRequestDTO.CreatePostDTO dto) {
        service.create(dto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("게시글 등록 성공");
    }

    @PostMapping("/update")
    public ResponseEntity<String> update(@RequestBody PostRequestDTO.UpdatePostDTO dto) {
        service.update(dto);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("게시글 수정 성공");
    }
}
