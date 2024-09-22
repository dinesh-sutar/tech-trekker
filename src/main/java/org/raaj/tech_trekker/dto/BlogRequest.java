package org.raaj.tech_trekker.dto;

import org.raaj.tech_trekker.constant.BlogCategory;
import org.springframework.web.multipart.MultipartFile;

public record BlogRequest(
        String blogTitle,
        String blogDescription,
        BlogCategory category,
        MultipartFile blogBanner) {

}
