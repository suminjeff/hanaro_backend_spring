package org.problem2.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.problem2.repository.CommentRepository;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository repository;

    public void create() {}

    public void read() {}

    public void readList() {}

    public void update() {}

    public void delete() {}
}
