package com.example.librarycompetition.controller;

import com.example.librarycompetition.dto.MemberDTO;
import com.example.librarycompetition.exception.ErrorDTO;
import com.example.librarycompetition.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "members", description = "Member API")
@Slf4j
@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @Operation(summary = "Get Member", description = "멤버 인덱스로 멤버 한명 검색하기")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "멤버 검색 성공", content = @Content(schema = @Schema(implementation = MemberDTO.class))),
            @ApiResponse(responseCode = "404", description = "멤버가 존재하지 않습니다.", content = @Content(schema = @Schema(implementation = ErrorDTO.class))),
    })
    @GetMapping("/{memberId}")
    public ResponseEntity<MemberDTO> getOneMember(@Parameter(description = "멤버 인덱스")
                                                      @PathVariable String memberId) {
        log.info("getOneMember : memberId = {}", memberId);
        return new ResponseEntity<>(memberService.getOneMember(memberId), HttpStatus.OK);
    }

    @Operation(summary = "Get All Members", description = "모든 멤버 검색하기")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "멤버 리스트 검색 성공", content = @Content(array = @ArraySchema(schema = @Schema(implementation = MemberDTO.class)))),
            @ApiResponse(responseCode = "404", description = "멤버 리스트가 존재하지 않습니다.", content = @Content(schema = @Schema(implementation = ErrorDTO.class))),
    })
    @GetMapping
    public ResponseEntity<List<MemberDTO>> getAllMembers() {
        log.info("getAllMembers");
        return new ResponseEntity<>(memberService.getAllMember(), HttpStatus.OK);
    }

    @Operation(summary = "Get Members", description = "멤버 이름으로 멤버 리스트 검색하기")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "멤버 리스트 검색 성공", content = @Content(array = @ArraySchema(schema = @Schema(implementation = MemberDTO.class)))),
            @ApiResponse(responseCode = "404", description = "멤버 리스트가 존재하지 않습니다.", content = @Content(schema = @Schema(implementation = ErrorDTO.class))),
    })
    @GetMapping("/memberName")
    public ResponseEntity<List<MemberDTO>> getMembersByMemberName(@Parameter(description = "멤버 이름")
                                                                      @RequestParam String memberName) {
        log.info("getMembersByMemberName : memberName = {}", memberName);
        return new ResponseEntity<>(memberService.getMembersByMemberName(memberName), HttpStatus.OK);
    }

    @Operation(summary = "Get Members", description = "멤버 위험도로 멤버 리스트 검색하기")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "멤버 리스트 검색 성공", content = @Content(array = @ArraySchema(schema = @Schema(implementation = MemberDTO.class)))),
            @ApiResponse(responseCode = "404", description = "멤버 리스트가 존재하지 않습니다.", content = @Content(schema = @Schema(implementation = ErrorDTO.class))),
    })
    @GetMapping("/memberWarning")
    public ResponseEntity<List<MemberDTO>> getMembersByMemberWarning(@Parameter(description = "멤버 위험도")
                                                                         @RequestParam String memberWarning) {
        log.info("getMembersByMemberWarning : memberWarning = {}", memberWarning);
        return new ResponseEntity<>(memberService.getMembersByMemberWarning(memberWarning), HttpStatus.OK);
    }

    @Operation(summary = "Create Member", description = "멤버 정보로 멤버 생성하기")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "멤버 생성 성공", content = @Content(schema = @Schema(implementation = MemberDTO.class))),
            @ApiResponse(responseCode = "400", description = "멤버를 생성할 수 없습니다.", content = @Content(schema = @Schema(implementation = ErrorDTO.class))),
    })
    @PostMapping
    public ResponseEntity<MemberDTO> createMember(@Parameter(description = "생성할 멤버 정보를 담은 멤버 DTO")
                                                  @RequestBody MemberDTO memberDTO) {
        log.info("createMember : memberDTO = {}", memberDTO);
        return new ResponseEntity<>(memberService.createMember(memberDTO), HttpStatus.CREATED);
    }

    @Operation(summary = "Update Member", description = "멤버 정보로 멤버 수정하기")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "멤버 수정 성공", content = @Content(schema = @Schema(implementation = MemberDTO.class))),
            @ApiResponse(responseCode = "400", description = "멤버를 수정할 수 없습니다.", content = @Content(schema = @Schema(implementation = ErrorDTO.class))),
    })
    @PutMapping
    public ResponseEntity<MemberDTO> updateMember(@Parameter(description = "수정할 멤버 정보를 담은 멤버 DTO")
                                                      @RequestBody MemberDTO memberDTO) {
        log.info("updateMember : memberDTO = {}", memberDTO);
        return new ResponseEntity<>(memberService.updateMember(memberDTO), HttpStatus.ACCEPTED);
    }

    @Operation(summary = "Delete Member", description = "멤버 인덱스로 멤버 삭제하기")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "멤버 삭제 성공"),
            @ApiResponse(responseCode = "400", description = "멤버를 삭제할 수 없습니다.", content = @Content(schema = @Schema(implementation = ErrorDTO.class))),
    })
    @DeleteMapping("/{memberId}")
    public ResponseEntity<Void> deleteMember(@Parameter(description = "멤버 인덱스")
                                                 @PathVariable String memberId) {
        log.info("deleteMember : memberId = {}", memberId);
        memberService.deleteMember(memberId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
