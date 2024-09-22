package org.raaj.tech_trekker.service.impl;

import java.util.List;

import org.raaj.tech_trekker.constant.BlogCategory;
import org.raaj.tech_trekker.model.BlogInfo;
import org.raaj.tech_trekker.model.WriterInfo;
import org.raaj.tech_trekker.repository.BlogInfoRepository;
import org.raaj.tech_trekker.repository.WriterInfoRepository;
import org.raaj.tech_trekker.service.FileService;
import org.raaj.tech_trekker.service.TechTrekkerService;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TechTrekkerServiceImpl implements TechTrekkerService {
    private final WriterInfoRepository repository;
    private final BlogInfoRepository blogrepository;
    private final FileService fileService;
    private final PasswordEncoder encoder;

    @Override
    public void createWritter(WriterInfo info) {
        var bcrypt = encoder.encode(info.getPassword());
        info.setPassword(bcrypt);
        repository.save(info);
    }

    // @Override
    // public void createWritter(WriterInfo info, MultipartFile file) {
    // var bcrypt = encoder.encode(info.getPassword());
    // info.setPassword(bcrypt);
    // String fileName = fileService.uploadProfilePicture(file);
    // info.setProfilePic(fileName);
    // repository.save(info);
    // }

    @Override
    public void createBlog(BlogInfo info, MultipartFile file) {
        String fileName = fileService.uploadBlogBanner(file);
        info.setBlogBanner(fileName);
        blogrepository.save(info);
    }

    @Override
    public List<BlogInfo> getBlogs() {
        return blogrepository.findAll();
    }

    @Override
    public BlogInfo getBlogByID(String id) {
        return blogrepository.findById(id).orElseThrow();
    }

    @Override
    public byte[] getBanner(String blogId) {
        var blog = getBlogByID(blogId);
        var banner = blog.getBlogBanner();
        return fileService.getBlogBanner(banner);
    }

    @Override
    public List<BlogInfo> getTop5Blogs() {
        return blogrepository.findTop5ByOrderByBlogTitle();
    }

    @Override
    public List<BlogInfo> limitedBlogOfCategory(BlogCategory category, int limit) {
        // return blogrepository.getBlogsByCategoryWithLimit(category, limit);
        return blogrepository.findByCategory(category, PageRequest.of(0, limit));
    }

    @Override
    public List<BlogInfo> limitedBlogOfCategory(BlogCategory category, int page, int limit) {
        return blogrepository.findByCategory(category, PageRequest.of(page - 1, limit));
    }

    @Override
    public int countBlogs(BlogCategory blogCategory) {
        return (int) blogrepository.countByCategory(blogCategory);
    }

    @Override
    public List<BlogInfo> blogsofCategoryAndTitle(BlogCategory category, String blogTitle, int page, int limit) {
        return blogrepository.findByCategoryAndBlogTitleContaining(category, blogTitle,
                PageRequest.of(page - 1, limit));
    }

    @Override
    public int coutBlogsofTitle(BlogCategory blogCategory, String blogTitle) {
        return (int) blogrepository.countByCategoryAndBlogTitleContaining(blogCategory, blogTitle);
    }

}
