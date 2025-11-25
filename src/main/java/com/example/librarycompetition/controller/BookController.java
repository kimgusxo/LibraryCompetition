package com.example.librarycompetition.controller;

import com.example.librarycompetition.dto.BookDTO;
import com.example.librarycompetition.exception.ErrorDTO;
import com.example.librarycompetition.service.BookService;
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

@Tag(name = "books", description = "Book API")
@Slf4j
@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @Operation(summary = "Get Book", description = "책 인덱스로 책 한권 검색하기")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "책 검색 성공", content = @Content(schema = @Schema(implementation = BookDTO.class))),
            @ApiResponse(responseCode = "404", description = "책이 존재하지 않습니다.", content = @Content(schema = @Schema(implementation = ErrorDTO.class))),
    })
    @GetMapping("/{bookId}")
    public ResponseEntity<BookDTO> getOneBook(@Parameter(description = "책 인덱스")
                                                  @PathVariable(name = "bookId") String bookId) {
        log.info("getOneBook : bookId = {}", bookId);
        return new ResponseEntity<>(bookService.getOneBook(bookId), HttpStatus.OK);
    }

    @Operation(summary = "Get All Books", description = "모든 책 정보 검색하기")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "책 리스트 검색 성공", content = @Content(array = @ArraySchema(schema = @Schema(implementation = BookDTO.class)))),
            @ApiResponse(responseCode = "404", description = "책 리스트가 존재하지 않습니다.", content = @Content(schema = @Schema(implementation = ErrorDTO.class))),
    })
    @GetMapping
    public ResponseEntity<List<BookDTO>> getAllBook() {
        log.info("getAllBook");
        return new ResponseEntity<>(bookService.getAllBook(), HttpStatus.OK);
    }

    @Operation(summary = "Get Books", description = "책 이름으로 책 리스트 검색하기")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "책 리스트 검색 성공", content = @Content(array = @ArraySchema(schema = @Schema(implementation = BookDTO.class)))),
            @ApiResponse(responseCode = "404", description = "책 리스트가 존재하지 않습니다.", content = @Content(schema = @Schema(implementation = ErrorDTO.class))),
    })
    @GetMapping("/bookTitle")
    public ResponseEntity<List<BookDTO>> getBooksByBookTitle(@Parameter(description = "잭 제목")
                                                                 @RequestParam String bookTitle) {
        log.info("getBooksByBookTitle : bookTitle = {}", bookTitle);
        return new ResponseEntity<>(bookService.getBooksByBookTitle(bookTitle), HttpStatus.OK);
    }

    @Operation(summary = "Get Books", description = "책 저자로 책 리스트 검색하기")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "책 리스트 검색 성공", content = @Content(array = @ArraySchema(schema = @Schema(implementation = BookDTO.class)))),
            @ApiResponse(responseCode = "404", description = "책 리스트가 존재하지 않습니다.", content = @Content(schema = @Schema(implementation = ErrorDTO.class))),
    })
    @GetMapping("/bookAuthor")
    public ResponseEntity<List<BookDTO>> getBooksByBookAuthor(@Parameter(description = "책 저자")
                                                                  @RequestParam String bookAuthor) {
        log.info("getBooksByBookAuthor : bookAuthor = {}", bookAuthor);
        return new ResponseEntity<>(bookService.getBooksByBookAuthor(bookAuthor), HttpStatus.OK);
    }

    @Operation(summary = "Get Books", description = "입력한 손상도보다 같거나 큰 손상도를 가진 책 리스트 검색하기")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "책 리스트 검색 성공", content = @Content(array = @ArraySchema(schema = @Schema(implementation = BookDTO.class)))),
            @ApiResponse(responseCode = "404", description = "책 리스트가 존재하지 않습니다.", content = @Content(schema = @Schema(implementation = ErrorDTO.class))),
    })
    @GetMapping("/greater/bookDamage")
    public ResponseEntity<List<BookDTO>> getBooksByBookDamageGreaterThanEqual(@Parameter(description = "책 손상도")
                                                                  @RequestParam Integer bookDamage) {
        log.info("getBooksByBookDamageGreaterThanEqual : bookDamage = {}", bookDamage);
        return new ResponseEntity<>(bookService.getBooksByBookDamageGreaterThanEqual(bookDamage), HttpStatus.OK);
    }

    @Operation(summary = "Get Books", description = "입력한 손상도보다 같거나 작은 손상도를 가진 책 리스트 검색하기")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "책 리스트 검색 성공", content = @Content(array = @ArraySchema(schema = @Schema(implementation = BookDTO.class)))),
            @ApiResponse(responseCode = "404", description = "책 리스트가 존재하지 않습니다.", content = @Content(schema = @Schema(implementation = ErrorDTO.class))),
    })
    @GetMapping("/less/bookDamage")
    public ResponseEntity<List<BookDTO>> getBooksByBookDamageLessThanEqual(@Parameter(description = "책 손상도")
                                                                               @RequestParam Integer bookDamage) {
        log.info("getBooksByBookDamageLessThanEqual : bookDamage = {}", bookDamage);
        return new ResponseEntity<>(bookService.getBooksByBookDamageLessThanEqual(bookDamage), HttpStatus.OK);
    }

    @Operation(summary = "Get Books", description = "책 라벨로 책 리스트 검색하기")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "책 리스트 검색 성공", content = @Content(array = @ArraySchema(schema = @Schema(implementation = BookDTO.class)))),
            @ApiResponse(responseCode = "404", description = "책 리스트가 존재하지 않습니다.", content = @Content(schema = @Schema(implementation = ErrorDTO.class))),
    })
    @GetMapping("/bookLabel")
    public ResponseEntity<List<BookDTO>> getBooksByBookLabel(@Parameter(description = "책 라벨")
                                                                 @RequestParam String bookLabel) {
        log.info("getBooksByBookLabel : bookLabel = {}", bookLabel);
        return new ResponseEntity<>(bookService.getBooksByBookLabel(bookLabel), HttpStatus.OK);
    }

    @Operation(summary = "Get Books", description = "책 조건으로 책 리스트 검색하기")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "책 리스트 검색 성공", content = @Content(array = @ArraySchema(schema = @Schema(implementation = BookDTO.class)))),
            @ApiResponse(responseCode = "404", description = "책 리스트가 존재하지 않습니다.", content = @Content(schema = @Schema(implementation = ErrorDTO.class))),
    })
    @GetMapping("/condition")
    public ResponseEntity<List<BookDTO>> getBooksByCondition(@Parameter(description = "책 제목")
                                                                 @RequestParam String bookTitle,
                                                             @Parameter(description = "책 저자")
                                                                 @RequestParam String bookAuthor,
                                                             @Parameter(description = "책 손상도")
                                                                 @RequestParam Integer bookDamage,
                                                             @Parameter(description = "손상도 옵션")
                                                                 @RequestParam String damageOption) {
        log.info("getBooksByCondition : bookTitle = {}, bookAuthor = {}, bookDamage = {}, damageOption = {}", bookTitle, bookAuthor, bookDamage, damageOption);
        return new ResponseEntity<>(bookService.getBooksByCondition(bookTitle, bookAuthor, bookDamage, damageOption), HttpStatus.OK);
    }

    @Operation(summary = "Get Books", description = "책 위험도로 책 리스트 검색하기")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "책 리스트 검색 성공", content = @Content(array = @ArraySchema(schema = @Schema(implementation = BookDTO.class)))),
            @ApiResponse(responseCode = "404", description = "책 리스트가 존재하지 않습니다.", content = @Content(schema = @Schema(implementation = ErrorDTO.class))),
    })
    @GetMapping("/bookWarning")
    public ResponseEntity<List<BookDTO>> getBooksByBookWarning(@Parameter(description = "책 위험도")
                                                                   @RequestParam String bookWarning) {
        log.info("getBooksByBookWarning : bookWarning = {}", bookWarning);
        return new ResponseEntity<>(bookService.getBooksByBookWarning(bookWarning), HttpStatus.OK);
    }

    @Operation(summary = "Create Book", description = "책 정보로 책 생성하기")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "책 생성 성공", content = @Content(schema = @Schema(implementation = BookDTO.class))),
            @ApiResponse(responseCode = "400", description = "책을 생성할 수 없습니다.", content = @Content(schema = @Schema(implementation = ErrorDTO.class))),
    })
    @PostMapping
    public ResponseEntity<BookDTO> createBook(@Parameter(description = "생성할 책 정보를 담은 책 DTO")
                                                  @RequestBody BookDTO bookDTO) {
        log.info("createBook : bookDTO = {}", bookDTO);
        return new ResponseEntity<>(bookService.createBook(bookDTO), HttpStatus.CREATED);
    }

    @Operation(summary = "Update Book", description = "책 정보로 책 업데이트하기")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "책 업데이트 성공", content = @Content(schema = @Schema(implementation = BookDTO.class))),
            @ApiResponse(responseCode = "400", description = "책을 업데이트할 수 없습니다.", content = @Content(schema = @Schema(implementation = ErrorDTO.class))),
    })
    @PutMapping
    public ResponseEntity<BookDTO> updateBook(@Parameter(description = "수정할 책 정보를 담은 책 DTO")
                                                  @RequestBody BookDTO bookDTO) {
        log.info("updateBook : bookDTO = {}", bookDTO);
        return new ResponseEntity<>(bookService.updateBook(bookDTO), HttpStatus.ACCEPTED);
    }

    @Operation(summary = "Delete Book", description = "책 인덱스로 책 한권 삭제하기")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "책 삭제 성공"),
            @ApiResponse(responseCode = "400", description = "책을 삭제할 수 없습니다.", content = @Content(schema = @Schema(implementation = ErrorDTO.class))),
    })
    @DeleteMapping("/{bookId}")
    public ResponseEntity<Void> deleteBook(@Parameter(description = "책 인덱스") @PathVariable String bookId) {
        log.info("deleteBook : bookId = {}", bookId);
        bookService.deleteBook(bookId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
