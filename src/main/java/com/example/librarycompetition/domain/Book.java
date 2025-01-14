package com.example.librarycompetition.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "book")
public class Book {

    @Id
    private String bookId;

    private Integer bookSequence;
    private String bookTitle;
    private String bookAuthor;
    private Integer bookDamage;
    private String bookWarning;
    private String bookLabel;
    private String imgThumbnail;

    private List<String> imageIds = new ArrayList<>();
    private List<String> loanIds = new ArrayList<>();

}