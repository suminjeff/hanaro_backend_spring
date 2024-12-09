package org.problem1.controller;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.problem1.domain.BorrowRequestDTO;
import org.problem1.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String postBorrow(@RequestParam int bno, BorrowRequestDTO borrowRequestDTO, RedirectAttributes redirectAttributes) {
        service.borrow(bno, borrowRequestDTO.getBorrowerId());
        System.out.println(">>>>>>>>>>>>>>>>" + service.read(bno));
        return "redirect:/book/result";
    }

    @GetMapping("/result")
    public void getResult() {

    }
}