package org.problem1.controller;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.problem1.domain.BorrowRequestDTO;
import org.problem1.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Log4j2
@AllArgsConstructor
@Controller
@RequestMapping("/book")
public class BookController {
    private BookService service;

    @GetMapping("/list")
    public void getList(Model model) {
        model.addAttribute("bList", service.getList());
    }

    @GetMapping("/read")
    public void getBook(@RequestParam int bno, Model model) {
        model.addAttribute("book", service.read(bno));
    }

    @PostMapping("/borrow")
    public String postBorrow(@RequestParam int bno, BorrowRequestDTO borrowRequestDTO) {
        service.borrow(bno, borrowRequestDTO.getBorrowerId());
        return "redirect:/book/result?bno=" + bno;
    }

    @GetMapping("/result")
    public void getResult(@RequestParam int bno, Model model) {
        model.addAttribute("book", service.read(bno));
    }
}
