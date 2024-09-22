package org.raaj.tech_trekker.service;

import java.util.List;

import org.raaj.tech_trekker.constant.BlogCategory;
import org.raaj.tech_trekker.model.BlogInfo;
import org.raaj.tech_trekker.model.WriterInfo;
import org.springframework.web.multipart.MultipartFile;

public interface TechTrekkerService {
    // void createWritter(WriterInfo info, MultipartFile file);

    void createWritter(WriterInfo info);

    void createBlog(BlogInfo info, MultipartFile file);

    List<BlogInfo> getBlogs();

    BlogInfo getBlogByID(String id);

    byte[] getBanner(String blogId);

    List<BlogInfo> getTop5Blogs();

    List<BlogInfo> limitedBlogOfCategory(BlogCategory category, int limit);

    List<BlogInfo> limitedBlogOfCategory(BlogCategory category, int page, int limit);

    int countBlogs(BlogCategory blogCategory);

    List<BlogInfo> blogsofCategoryAndTitle(BlogCategory category, String blogTitle, int page, int limit);

    int coutBlogsofTitle(BlogCategory blogCategory, String blogTitle);
}
