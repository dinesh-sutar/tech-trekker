package org.raaj.tech_trekker.controller;

import org.raaj.tech_trekker.dto.BlogRequest;
import org.raaj.tech_trekker.mapper.BlogMapper;
import org.raaj.tech_trekker.service.TechTrekkerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/writter")
public class WritterController {
    private final TechTrekkerService service;

    @GetMapping("/create-blog")
    public String createBlog() {
        return "create-blog";
    }

    @PostMapping("/create-blog")
    public String create_Blog(@ModelAttribute BlogRequest request) {
        var createblogInfo = BlogMapper.convertblogRequest(request);
        service.createBlog(createblogInfo, request.blogBanner());
        System.out.println(createblogInfo);
        return "redirect:/tech-trekker/home";
    }
}
