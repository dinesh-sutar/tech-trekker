package org.raaj.tech_trekker.mapper;

import java.util.ArrayList;
import java.util.List;

import org.raaj.tech_trekker.dto.BasicBlogInfo;
import org.raaj.tech_trekker.dto.BlogRequest;
import org.raaj.tech_trekker.dto.BlogResponse;
import org.raaj.tech_trekker.model.BlogInfo;
import org.springframework.beans.BeanUtils;

public class BlogMapper {
    public static BlogInfo convertblogRequest(BlogRequest request) {
        var createblogInfo = new BlogInfo();
        BeanUtils.copyProperties(request, createblogInfo);
        return createblogInfo;
    }

    public static BlogResponse convertBlogToResponse(BlogInfo blog) {
        var response = new BlogResponse();
        BeanUtils.copyProperties(blog, response);
        return response;
    }

    public static List<BasicBlogInfo> convertBlogToBasic(List<BlogInfo> blogs) {
        var list = new ArrayList<BasicBlogInfo>();

        for (BlogInfo blog : blogs) {
            var basicBlogInfo = new BasicBlogInfo();
            BeanUtils.copyProperties(blog, basicBlogInfo);
            list.add(basicBlogInfo);
        }

        return list;

    }
}
