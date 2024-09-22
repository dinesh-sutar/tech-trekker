package org.raaj.tech_trekker.repository;

import java.util.List;

import org.raaj.tech_trekker.model.BlogInfo;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.raaj.tech_trekker.constant.BlogCategory;

public interface BlogInfoRepository extends JpaRepository<BlogInfo, String> {
    List<BlogInfo> findTop5ByOrderByBlogTitle();

    List<BlogInfo> findByCategory(BlogCategory category);

    List<BlogInfo> findByCategory(BlogCategory category, Pageable pageable);

    long countByCategory(BlogCategory category);

    List<BlogInfo> findByCategoryAndBlogTitleContaining(BlogCategory category, String blogTitle, Pageable pageable);

    long countByCategoryAndBlogTitleContaining(BlogCategory category, String blogTitle);

    // @Query(nativeQuery = true, value = "Select form * blog_info where category =
    // ? limit ?")
    // @Query("SELECT FROM BlogInfo B WHERE B.category=:ct LIMIT :lm")
    // @Query("SELECT ")
    // List<BlogInfo> getBlogsByCategoryWithLimit(@Param("ct") BlogCategory
    // category, @Param("lm") int limit);
}
