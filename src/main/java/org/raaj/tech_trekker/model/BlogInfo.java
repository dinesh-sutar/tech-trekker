package org.raaj.tech_trekker.model;

import org.raaj.tech_trekker.constant.BlogCategory;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
public class BlogInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String blogId;
    private String blogTitle;
    @Column(columnDefinition = "mediumText")
    private String blogDescription;
    @Enumerated(EnumType.STRING)
    @NotNull
    private BlogCategory category;
    private String blogBanner;
}
