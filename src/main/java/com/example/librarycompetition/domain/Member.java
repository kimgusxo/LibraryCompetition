package com.example.librarycompetition.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "member")
public class Member {

    @Id
    private String memberId;

    private String memberName;
    private LocalDate memberBirth;
    private String memberPhoneNumber;
    private String memberWarning;
    private Integer memberDamageCount;
    private String memberImg;

    private List<String> loanIds = new ArrayList<>();

}
