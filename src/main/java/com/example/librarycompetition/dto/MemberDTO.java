package com.example.librarycompetition.dto;

import com.example.librarycompetition.domain.Member;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;
import java.util.List;

public record MemberDTO(
        @Schema(example = "1")
        String memberId,
        @Schema(example = "가나다")
        String memberName,
        @Schema(example = "2024-08-21")
        LocalDate memberBirth,
        @Schema(example = "010-1111-2222")
        String memberPhoneNumber,
        @Schema(example = "정상")
        String memberWarning,
        @Schema(example = "1")
        Integer memberDamageCount,
        @Schema(example = "www.test.com")
        String memberImg,
        @Schema(example = "[1, 2, 3]")
        List<String> loanIds
) {

    public static MemberDTO of(String memberId, String memberName, LocalDate memberBirth, String memberPhoneNumber,
                               String memberWarning, Integer memberDamageCount, String memberImg, List<String> loanIds) {
        return new MemberDTO(memberId, memberName, memberBirth, memberPhoneNumber, memberWarning, memberDamageCount, memberImg, loanIds);
    }

    public static MemberDTO from(Member member) {
        return new MemberDTO(
                member.getMemberId(),
                member.getMemberName(),
                member.getMemberBirth(),
                member.getMemberPhoneNumber(),
                member.getMemberWarning(),
                member.getMemberDamageCount(),
                member.getMemberImg(),
                member.getLoanIds()
        );
    }

    public Member toEntity() {
        return Member.builder()
                .memberId(memberId)
                .memberName(memberName)
                .memberBirth(memberBirth)
                .memberPhoneNumber(memberPhoneNumber)
                .memberWarning(memberWarning)
                .memberDamageCount(memberDamageCount)
                .memberImg(memberImg)
                .loanIds(loanIds)
                .build();
    }
}
