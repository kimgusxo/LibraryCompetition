package com.example.librarycompetition.controller;

import com.example.librarycompetition.dto.LoanDTO;
import com.example.librarycompetition.exception.ErrorDTO;
import com.example.librarycompetition.service.LoanService;
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

import java.time.LocalDate;
import java.util.List;

@Tag(name = "loans", description = "Loan API")
@Slf4j
@RestController
@RequestMapping("/loans")
@RequiredArgsConstructor
public class LoanController {

    private final LoanService loanService;

    @Operation(summary = "Get Loan", description = "대출 인덱스로 대출 한개 검색")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "대출 검색 성공", content = @Content(schema = @Schema(implementation = LoanDTO.class))),
            @ApiResponse(responseCode = "404", description = "대출이 존재하지 않습니다.", content = @Content(schema = @Schema(implementation = ErrorDTO.class))),
    })
    @GetMapping("/{loanId}")
    public ResponseEntity<LoanDTO> getOneLoan(@Parameter(description = "대출 인덱스")
                                                  @PathVariable String loanId) {
        log.info("getOneLoan : loanId = {}", loanId);
        return new ResponseEntity<>(loanService.getOneLoan(loanId), HttpStatus.OK);
    }

    @Operation(summary = "Get All Loans", description = "모든 대출 정보 검색하기")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "대출 리스트 검색 성공", content = @Content(array = @ArraySchema(schema = @Schema(implementation = LoanDTO.class)))),
            @ApiResponse(responseCode = "404", description = "대출 리스트가 존재하지 않습니다.", content = @Content(schema = @Schema(implementation = ErrorDTO.class))),
    })
    @GetMapping
    public ResponseEntity<List<LoanDTO>> getAllLoan() {
        log.info("getAllLoan");
        return new ResponseEntity<>(loanService.getAllLoan(), HttpStatus.OK);
    }

    @Operation(summary = "Get Loans", description = "멤버 인덱스로 대출 리스트 검색하기")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "대출 리스트 검색 성공", content = @Content(array = @ArraySchema(schema = @Schema(implementation = LoanDTO.class)))),
            @ApiResponse(responseCode = "404", description = "대출 리스트가 존재하지 않습니다.", content = @Content(schema = @Schema(implementation = ErrorDTO.class))),
    })
    @GetMapping("/memberId")
    public ResponseEntity<List<LoanDTO>> getLoansByMemberId(@Parameter(description = "멤버 인덱스")
                                                                @RequestParam String memberId) {
        log.info("getLoansByMemberId : memberId = {}", memberId);
        return new ResponseEntity<>(loanService.getLoansByMemberId(memberId), HttpStatus.OK);
    }

    @Operation(summary = "Get Loans", description = "책 인덱스로 대출 리스트 검색하기")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "대출 리스트 검색 성공", content = @Content(array = @ArraySchema(schema = @Schema(implementation = LoanDTO.class)))),
            @ApiResponse(responseCode = "404", description = "대출 리스트가 존재하지 않습니다.", content = @Content(schema = @Schema(implementation = ErrorDTO.class))),
    })
    @GetMapping("/bookId")
    public ResponseEntity<List<LoanDTO>> getLoansByBookId(@Parameter(description = "책 인덱스")
                                                              @RequestParam String bookId) {
        log.info("getLoansByBookId : bookId = {}", bookId);
        return new ResponseEntity<>(loanService.getLoansByBookId(bookId), HttpStatus.OK);
    }

    @Operation(summary = "Get Loans", description = "현재 대출 중인 대출 리스트 검색하기")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "대출 리스트 검색 성공", content = @Content(array = @ArraySchema(schema = @Schema(implementation = LoanDTO.class)))),
            @ApiResponse(responseCode = "404", description = "대출 리스트가 존재하지 않습니다.", content = @Content(schema = @Schema(implementation = ErrorDTO.class))),
    })
    @GetMapping("/current")
    public ResponseEntity<List<LoanDTO>> getLoansByLoanTimeIsNotNullAndReturnTimeIsNull() {
        log.info("getLoansByLoanTimeIsNotNullAndReturnTimeIsNull");
        return new ResponseEntity<>(loanService.getLoansByLoanTimeIsNotNullAndReturnTimeIsNull(), HttpStatus.OK);
    }

    @Operation(summary = "Get Loans", description = "지정한 기간 동안의 대출 리스트 검색하기")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "대출 리스트 검색 성공", content = @Content(array = @ArraySchema(schema = @Schema(implementation = LoanDTO.class)))),
            @ApiResponse(responseCode = "404", description = "대출 리스트가 존재하지 않습니다.", content = @Content(schema = @Schema(implementation = ErrorDTO.class))),
    })
    @GetMapping("/loanTime")
    public ResponseEntity<List<LoanDTO>> getLoansByLoanTimeBetween(@Parameter(description = "시작 시간") @RequestParam LocalDate startDate,
                                                                   @Parameter(description = "끝 시간") @RequestParam LocalDate endDate) {
        log.info("getLoansByLoanTimeBetween : startDate = {}, endDate = {}", startDate, endDate);
        return new ResponseEntity<>(loanService.getLoansByLoanTimeBetween(startDate, endDate), HttpStatus.OK);
    }

    @Operation(summary = "Create Loan", description = "대출 정보로 대출 생성하기")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "대출 생성 성공", content = @Content(schema = @Schema(implementation = LoanDTO.class))),
            @ApiResponse(responseCode = "400", description = "대츨을 생성할 수 없습니다.", content = @Content(schema = @Schema(implementation = ErrorDTO.class))),
    })
    @PostMapping
    public ResponseEntity<LoanDTO> createLoan(@Parameter(description = "생성할 대출 정보를 담은 대출 DTO")
                                                  @RequestBody LoanDTO loanDTO) {
        log.info("createLoan : loanDTO = {}", loanDTO);
        return new ResponseEntity<>(loanService.createLoan(loanDTO), HttpStatus.CREATED);
    }

    @Operation(summary = "Update Loan", description = "대출 정보로 대출 수정하기")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "대출 수정 성공", content = @Content(schema = @Schema(implementation = LoanDTO.class))),
            @ApiResponse(responseCode = "400", description = "대츨을 수정할 수 없습니다.", content = @Content(schema = @Schema(implementation = ErrorDTO.class))),
    })
    @PutMapping
    public ResponseEntity<LoanDTO> updateLoan(@Parameter(description = "수정할 대출 정보를 담은 대출 DTO")
                                                  @RequestBody LoanDTO loanDTO) {
        log.info("updateLoan : loanDTO = {}", loanDTO);
        return new ResponseEntity<>(loanService.updateLoan(loanDTO), HttpStatus.ACCEPTED);
    }

    @Operation(summary = "Delete Loan", description = "대출 인덱스로 대출 한권 삭제하기")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "대출 삭제 성공"),
            @ApiResponse(responseCode = "400", description = "대출을 삭제할 수 없습니다.", content = @Content(schema = @Schema(implementation = ErrorDTO.class))),
    })
    @DeleteMapping("/{loanId}")
    public ResponseEntity<Void> deleteLoan(@Parameter(description = "대출 인덱스")
                                               @PathVariable String loanId) {
        log.info("deleteLoan : loanId = {}", loanId);
        loanService.deleteLoan(loanId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
