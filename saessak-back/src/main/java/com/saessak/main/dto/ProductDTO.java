package com.saessak.main.dto;

import com.querydsl.core.annotations.QueryProjection;
import com.saessak.constant.SellStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

// 메인, 검색 상품 조회에 사용될 DTO
@Builder
@Getter
@Setter
@ToString
public class ProductDTO {

    private Long id;

    private String title;

    private Integer price;

    private SellStatus sellStatus;

    private String imgUrl;

    private LocalDateTime regTime;

    private LocalDateTime updateTime;

    private String searchBy;

    private String searchQuery;

    @QueryProjection
    public ProductDTO(Long id, String title, Integer price, SellStatus sellStatus, String imgUrl, LocalDateTime regTime, LocalDateTime updateTime, String searchBy, String searchQuery) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.sellStatus = sellStatus;
        this.imgUrl = imgUrl;
        this.regTime = regTime;
        this.updateTime = updateTime;
        this.searchBy = searchBy;
        this.searchQuery = searchQuery;
    }
}
