package com.icia.solo_boardproject.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberPagingDTO {
    private Long id;
    private String memberEmail;
    private String memberName;
    private String memberPhonenum;

}
