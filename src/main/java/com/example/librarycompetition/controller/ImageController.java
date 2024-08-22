package com.example.librarycompetition.controller;

import com.example.librarycompetition.dto.ImageDTO;
import com.example.librarycompetition.service.ImageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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

@Tag(name = "image", description = "Image API")
@Slf4j
@RestController
@RequestMapping("/image")
@RequiredArgsConstructor
public class ImageController {

    private final ImageService imageService;

    @Operation(summary = "Get Image", description = "이미지 인덱스로 이미지 한개 검색")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "이미지 검색 성공"),
            @ApiResponse(responseCode = "404", description = "이미지가 존재하지 않습니다."),
    })
    @GetMapping("/get/{imageId}")
    public ResponseEntity<ImageDTO> getOneImage(@Parameter(description = "이미지 인덱스")
                                                    @PathVariable String imageId) {
        log.info("getOneImage: imageId = {}", imageId);
        return new ResponseEntity<>(imageService.getOneImage(imageId), HttpStatus.OK);
    }

    @Operation(summary = "Get All Images", description = "모든 이미지 검색하기")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "이미지 리스트 검색 성공"),
            @ApiResponse(responseCode = "404", description = "이미지 리스트가 존재하지 않습니다."),
    })
    @GetMapping("/get/all")
    public ResponseEntity<List<ImageDTO>> getAllImage() {
        log.info("getAllImage");
        return new ResponseEntity<>(imageService.getAllImage(), HttpStatus.OK);
    }

    @Operation(summary = "Get Images", description = "책 인덱스로 이미지 리스트 검색하기")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "이미지 리스트 검색 성공"),
            @ApiResponse(responseCode = "404", description = "이미지 리스트가 존재하지 않습니다."),
    })
    @GetMapping("/get/bookId/{bookId}")
    public ResponseEntity<List<ImageDTO>> getImagesByBookId(@Parameter(description = "책 인덱스")
                                                                @PathVariable String bookId) {
        log.info("getImagesByBookId: bookId = {}", bookId);
        return new ResponseEntity<>(imageService.getImagesByBookId(bookId), HttpStatus.OK);
    }

    @Operation(summary = "Get Images", description = "카메라 인덱스로 이미지 리스트 검색하기")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "이미지 리스트 검색 성공"),
            @ApiResponse(responseCode = "404", description = "이미지 리스트가 존재하지 않습니다."),
    })
    @GetMapping("/get/cameraId/{cameraId}")
    public ResponseEntity<List<ImageDTO>> getImagesByCameraId(@Parameter(description = "책 손상도")
                                                                  @PathVariable Integer cameraId) {
        log.info("getImagesByCameraId: cameraId = {}", cameraId);
        return new ResponseEntity<>(imageService.getImagesByCameraId(cameraId), HttpStatus.OK);
    }

    @Operation(summary = "Get Images", description = "입력한 시간 이후의 찍힌 이미지 리스트 검색하기")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "이미지 리스트 검색 성공"),
            @ApiResponse(responseCode = "404", description = "이미지 리스트가 존재하지 않습니다."),
    })
    @GetMapping("/get/startDate/{startDate}")
    public ResponseEntity<List<ImageDTO>> getImagesByImageTimeGreaterThan(@Parameter(description = "시작 시간")
                                                                              @PathVariable LocalDate startDate) {
        log.info("getImagesByImageTimeGreaterThan: startDate = {}", startDate);
        return new ResponseEntity<>(imageService.getImagesByImageTimeGreaterThan(startDate), HttpStatus.OK);
    }

    @Operation(summary = "Get Images", description = "시작 시간과 끝 시간 사이에 찍힌 이미지 리스트 검색하기")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "이미지 리스트 검색 성공"),
            @ApiResponse(responseCode = "404", description = "이미지 리스트가 존재하지 않습니다."),
    })
    @GetMapping("/get/imageTime")
    public ResponseEntity<List<ImageDTO>> getImagesByImageTimeBetween(@Parameter(description = "시작 시간") @RequestParam LocalDate startDate,
                                                                      @Parameter(description = "끝 시간") @RequestParam LocalDate endDate) {
        log.info("getImagesByImageTimeBetween: startDate = {}, endDate = {}", startDate, endDate);
        return new ResponseEntity<>(imageService.getImagesByImageTimeBetween(startDate, endDate), HttpStatus.OK);
    }

    @Operation(summary = "Create Image", description = "이미지 정보로 이미지 생성하기")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "이미지 생성 성공"),
            @ApiResponse(responseCode = "400", description = "이미지를 생성할 수 없습니다."),
    })
    @PostMapping("/create")
    public ResponseEntity<ImageDTO> createImage(@Parameter(description = "생성할 이미지 정보를 담은 이미지 DTO")
                                                    @RequestBody ImageDTO imageDTO) {
        log.info("createImage: imageDTO = {}", imageDTO);
        return new ResponseEntity<>(imageService.createImage(imageDTO), HttpStatus.CREATED);
    }

    @Operation(summary = "Update Book", description = "이미지 정보로 이미지 업데이트하기")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "이미지 업데이트 성공"),
            @ApiResponse(responseCode = "400", description = "이미지를 업데이트할 수 없습니다."),
    })
    @PutMapping("/update")
    public ResponseEntity<ImageDTO> updateImage(@Parameter(description = "수정할 이미지 정보를 담은 이미지 DTO")
                                                    @RequestBody ImageDTO imageDTO) {
        log.info("updateImage: imageDTO = {}", imageDTO);
        return new ResponseEntity<>(imageService.updateImage(imageDTO), HttpStatus.ACCEPTED);
    }

    @Operation(summary = "Delete Book", description = "이미지 인덱스로 이미지 한개 삭제하기")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "이미지 삭제 성공"),
            @ApiResponse(responseCode = "400", description = "이미지를 삭제할 수 없습니다."),
    })
    @DeleteMapping("/delete/{imageId}")
    public ResponseEntity<Void> deleteImage(@Parameter(description = "이미지 인덱스")
                                                @PathVariable String imageId) {
        log.info("deleteImage: imageId = {}", imageId);
        imageService.deleteImage(imageId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
