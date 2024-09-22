package org.raaj.tech_trekker.controller;

import static org.raaj.tech_trekker.mapper.BlogMapper.*;

import java.util.List;

import org.raaj.tech_trekker.constant.BlogCategory;
import org.raaj.tech_trekker.dto.HomePageResponse;
import org.raaj.tech_trekker.dto.ViewAllResponse;
import org.raaj.tech_trekker.dto.WriterRequest;
import org.raaj.tech_trekker.mapper.BlogMapper;
import org.raaj.tech_trekker.mapper.WriterMapper;
import org.raaj.tech_trekker.model.BlogInfo;
import org.raaj.tech_trekker.service.TechTrekkerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/tech-trekker")
public class BlogController {
    private final TechTrekkerService service;

    @GetMapping({ "/", "/home" })
    public String home(Model model) {
        // List<BlogInfo> blogs = service.getBlogs();
        // // List<HomePageResponse> details =
        // // blogs.stream().map(BlogMapper::convertBlogTHome).toList();
        // List<HomePageResponse> details = new ArrayList<>();
        // for (BlogInfo blog : blogs) {
        // details.add(BlogMapper.convertBlogTHome(blog));
        // }
        // model.addAttribute("blogs", details);
        var recentBlogs = convertBlogToBasic(service.getTop5Blogs());
        var frontendBlogs = convertBlogToBasic(service.limitedBlogOfCategory(BlogCategory.FRONTEND, 8));
        var backendBlogs = convertBlogToBasic(service.limitedBlogOfCategory(BlogCategory.BACKEND, 6));
        var databaseBlogs = convertBlogToBasic(service.limitedBlogOfCategory(BlogCategory.DATABASE, 6));

        var homepageResponse = new HomePageResponse();
        homepageResponse.setRecentBlogs(recentBlogs);
        homepageResponse.setBackendBlogs(backendBlogs);
        homepageResponse.setDatabaseBlogs(databaseBlogs);
        homepageResponse.setFrontendBlogs(frontendBlogs);

        model.addAttribute("response", homepageResponse);

        return "home";
    }

    @GetMapping("/view-all")
    public String viewAll(@RequestParam BlogCategory category,
            @RequestParam(required = false, defaultValue = "1") int pageNum,
            @RequestParam(required = false) String searchTerm,
            Model model) {
        final int limit = 7;
        List<BlogInfo> blogs;
        int totalBlogs;
        if (searchTerm == null) {
            blogs = service.limitedBlogOfCategory(category, pageNum, limit);
            totalBlogs = service.countBlogs(category);
        } else {
            blogs = service.blogsofCategoryAndTitle(category, searchTerm, pageNum, limit);
            totalBlogs = service.coutBlogsofTitle(category, searchTerm);
        }
        var blogResponse = blogs.stream().map(BlogMapper::convertBlogToResponse).toList();

        var viewAllResponse = new ViewAllResponse();
        viewAllResponse.setBlogs(blogResponse);
        viewAllResponse.setCurrentPage(pageNum);
        viewAllResponse.setTotalPage(getTotalPage(totalBlogs, limit));
        viewAllResponse.setSearchTerm(searchTerm);
        model.addAttribute("response", viewAllResponse);
        return "view-all";
    }

    @GetMapping("/blog-details")
    public String blogDetails(@RequestParam String id, Model model) {
        var blog = service.getBlogByID(id);
        model.addAttribute("blog", BlogMapper.convertBlogToResponse(blog));
        return "blog-details";
    }

    @GetMapping("/signup")
    public String signup() {
        return "signup";
    }

    @PostMapping("/signup")
    public String signup(@ModelAttribute WriterRequest request) {
        var writerInfo = WriterMapper.convertRequest(request);
        // binds the data from writerrequest to writerinfo where the variable and
        // data-type are same in both.
        // BeanUtils.copyProperties(request, writerInfo);
        // service.createWritter(writerInfo, request.profilePic());
        service.createWritter(writerInfo);
        System.out.println(writerInfo);
        // writerInfo.setEmail(request.email());
        // writerInfo.setPassword(request.password());
        // writerInfo.setWriterProfession(request.writerProfession());
        // writerInfo.setWriterName(request.writerName());
        return "redirect:/tech-trekker/home";
    }

    @PostMapping("/view-all-search")
    public String viewAllSearch(@RequestParam BlogCategory category, @RequestParam String searchTerm) {
        if (searchTerm == null || searchTerm.isEmpty() || searchTerm.isBlank()) {
            return "redirect:/tech-trekker/view-all?category=" + category;
        } else {
            return "redirect:/tech-trekker/view-all?category=" + category + "&searchTerm=" + searchTerm;
        }
    }

    private int getTotalPage(int totalBlogs, int limit) {
        return (totalBlogs % limit == 0)
                ? (totalBlogs / limit)
                : (totalBlogs / limit) + 1;
    }
}
