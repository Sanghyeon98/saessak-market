package com.saessak.mypage.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.*;

@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class MemberImageDTO {

    private Long id;

    private String imgName;

    private String oriName;

    private String imgUrl;


}


