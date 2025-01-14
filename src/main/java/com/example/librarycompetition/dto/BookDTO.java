package com.example.librarycompetition.dto;

import com.example.librarycompetition.domain.Book;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

public record BookDTO(
        @Schema(example = "1")
        String bookId,
        @Schema(example = "1")
        Integer bookSequence,
        @Schema(example = "가나다")
        String bookTitle,
        @Schema(example = "가나다")
        String bookAuthor,
        @Schema(example = "정상")
        String bookWarning,
        @Schema(example = "30")
        Integer bookDamage,
        @Schema(example = "가나다")
        String bookLabel,
        @Schema(example = "www.test.com")
        String imgThumbnail,
        @Schema(example = "[1, 2, 3]")
        List<String> imageIds,
        @Schema(example = "[1, 2, 3]")
        List<String> loanIds
) {

    public static BookDTO of(String bookId, Integer bookSequence, String bookTitle, String bookAuthor,
                             String bookWarning, Integer bookDamage, String bookLabel, String imgThumbnail, List<String> imageIds, List<String> loanIds) {
        return new BookDTO(bookId, bookSequence, bookTitle, bookAuthor, bookWarning, bookDamage, bookLabel, imgThumbnail, imageIds, loanIds);
    }

    public static BookDTO from(Book book) {
        return new BookDTO(
                book.getBookId(),
                book.getBookSequence(),
                book.getBookTitle(),
                book.getBookAuthor(),
                book.getBookWarning(),
                book.getBookDamage(),
                book.getBookLabel(),
                book.getImgThumbnail(),
                book.getImageIds(),
                book.getLoanIds()
        );
    }

    public Book toEntity() {
        return Book.builder()
                .bookId(bookId)
                .bookSequence(bookSequence)
                .bookTitle(bookTitle)
                .bookAuthor(bookAuthor)
                .bookWarning(bookWarning)
                .bookDamage(bookDamage)
                .bookLabel(bookLabel)
                .imgThumbnail(imgThumbnail)
                .imageIds(imageIds)
                .loanIds(loanIds)
                .build();
    }
}