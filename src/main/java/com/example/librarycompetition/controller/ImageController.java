package com.example.librarycompetition.controller;

import com.example.librarycompetition.dto.ImageDTO;
import com.example.librarycompetition.exception.ErrorDTO;
import com.example.librarycompetition.service.ImageService;
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
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Tag(name = "images", description = "Image API")
@Slf4j
@RestController
@RequestMapping("/images")
@RequiredArgsConstructor
public class ImageController {

    private final ImageService imageService;

    @Operation(summary = "Get Image", description = "이미지 인덱스로 이미지 한개 검색")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "이미지 검색 성공", content = @Content(schema = @Schema(implementation = ImageDTO.class))),
            @ApiResponse(responseCode = "404", description = "이미지가 존재하지 않습니다.", content = @Content(schema = @Schema(implementation = ErrorDTO.class))),
    })
    @GetMapping("/{imageId}")
    public ResponseEntity<ImageDTO> getOneImage(@Parameter(description = "이미지 인덱스")
                                                    @PathVariable String imageId) {
        log.info("getOneImage: imageId = {}", imageId);
        return new ResponseEntity<>(imageService.getOneImage(imageId), HttpStatus.OK);
    }

    @Operation(summary = "Get All Images", description = "모든 이미지 검색하기")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "이미지 리스트 검색 성공", content = @Content(array = @ArraySchema(schema = @Schema(implementation = ImageDTO.class)))),
            @ApiResponse(responseCode = "404", description = "이미지 리스트가 존재하지 않습니다.", content = @Content(schema = @Schema(implementation = ErrorDTO.class))),
    })
    @GetMapping
    public ResponseEntity<List<ImageDTO>> getAllImage() {
        log.info("getAllImage");
        return new ResponseEntity<>(imageService.getAllImage(), HttpStatus.OK);
    }

    @Operation(summary = "Get Images", description = "책 인덱스로 이미지 리스트 검색하기")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "이미지 리스트 검색 성공", content = @Content(array = @ArraySchema(schema = @Schema(implementation = ImageDTO.class)))),
            @ApiResponse(responseCode = "404", description = "이미지 리스트가 존재하지 않습니다.", content = @Content(schema = @Schema(implementation = ErrorDTO.class))),
    })
    @GetMapping("/bookId")
    public ResponseEntity<List<ImageDTO>> getImagesByBookId(@Parameter(description = "책 인덱스")
                                                                @RequestParam String bookId) {
        log.info("getImagesByBookId: bookId = {}", bookId);
        return new ResponseEntity<>(imageService.getImagesByBookId(bookId), HttpStatus.OK);
    }

    @Operation(summary = "Get Images", description = "카메라 인덱스로 이미지 리스트 검색하기")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "이미지 리스트 검색 성공", content = @Content(array = @ArraySchema(schema = @Schema(implementation = ImageDTO.class)))),
            @ApiResponse(responseCode = "404", description = "이미지 리스트가 존재하지 않습니다.", content = @Content(schema = @Schema(implementation = ErrorDTO.class))),
    })
    @GetMapping("/cameraId")
    public ResponseEntity<List<ImageDTO>> getImagesByCameraId(@Parameter(description = "책 손상도")
                                                                  @RequestParam Integer cameraId) {
        log.info("getImagesByCameraId: cameraId = {}", cameraId);
        return new ResponseEntity<>(imageService.getImagesByCameraId(cameraId), HttpStatus.OK);
    }

    @Operation(summary = "Get Images", description = "입력한 시간 이후의 찍힌 이미지 리스트 검색하기")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "이미지 리스트 검색 성공", content = @Content(array = @ArraySchema(schema = @Schema(implementation = ImageDTO.class)))),
            @ApiResponse(responseCode = "404", description = "이미지 리스트가 존재하지 않습니다.", content = @Content(schema = @Schema(implementation = ErrorDTO.class))),
    })
    @GetMapping("/startDate")
    public ResponseEntity<List<ImageDTO>> getImagesByImageTimeGreaterThan(@Parameter(description = "시작 시간")
                                                                              @RequestParam LocalDate startDate) {
        log.info("getImagesByImageTimeGreaterThan: startDate = {}", startDate);
        return new ResponseEntity<>(imageService.getImagesByImageTimeGreaterThan(startDate), HttpStatus.OK);
    }

    @Operation(summary = "Get Images", description = "시작 시간과 끝 시간 사이에 찍힌 이미지 리스트 검색하기")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "이미지 리스트 검색 성공", content = @Content(array = @ArraySchema(schema = @Schema(implementation = ImageDTO.class)))),
            @ApiResponse(responseCode = "404", description = "이미지 리스트가 존재하지 않습니다.", content = @Content(schema = @Schema(implementation = ErrorDTO.class))),
    })
    @GetMapping("/imageTime")
    public ResponseEntity<List<ImageDTO>> getImagesByImageTimeBetween(@Parameter(description = "시작 시간") @RequestParam LocalDate startDate,
                                                                      @Parameter(description = "끝 시간") @RequestParam LocalDate endDate) {
        log.info("getImagesByImageTimeBetween: startDate = {}, endDate = {}", startDate, endDate);
        return new ResponseEntity<>(imageService.getImagesByImageTimeBetween(startDate, endDate), HttpStatus.OK);
    }

    @Operation(summary = "Get Image File", description = "이미지 인덱스로 이미지 파일 전송하기")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "이미지 파일 전송 성공", content = @Content(mediaType = "application/octet-stream")),
            @ApiResponse(responseCode = "404", description = "이미지 파일이 존재하지 않습니다.", content = @Content(schema = @Schema(implementation = ErrorDTO.class))),
    })
    @GetMapping("/file/{imageId}")
    public ResponseEntity<Resource> getImageFileByImageId(@Parameter(description = "이미지 인덱스")
                                                                   @PathVariable Integer imageId) throws IOException {
        log.info("getImageFileByImageId: imageId = {}", imageId);
        return new ResponseEntity<>(imageService.getImageFileByImageId(imageId), HttpStatus.OK);
    }

    @Operation(summary = "Post Image Files", description = "이미지 인덱스 리스트로 이미지 파일 리스트 전송하기")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "이미지 파일 리스트 전송 성공", content = @Content(array = @ArraySchema(schema = @Schema(type = "string", format = "binary")))),
            @ApiResponse(responseCode = "404", description = "이미지 파일 리스트가 존재하지 않습니다.", content = @Content(schema = @Schema(implementation = ErrorDTO.class))),
    })
    @PostMapping("/files")
    public ResponseEntity<List<Resource>> postImagesFileByImageIdList(@Parameter(description = "이미지 인덱스")
                                                               @RequestBody List<Integer> imageIdList) throws IOException {
        log.info("postImageFileByImageId: imageIdList = {}", imageIdList);
        return new ResponseEntity<>(imageService.postImagesFileByImageIdList(imageIdList), HttpStatus.OK);
    }

    @Operation(summary = "Create Image", description = "이미지 정보로 이미지 생성하기")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "이미지 생성 성공", content = @Content(schema = @Schema(implementation = ImageDTO.class))),
            @ApiResponse(responseCode = "400", description = "이미지를 생성할 수 없습니다.", content = @Content(schema = @Schema(implementation = ErrorDTO.class))),
    })
    @PostMapping
    public ResponseEntity<ImageDTO> createImage(@Parameter(description = "생성할 이미지 정보를 담은 이미지 DTO")
                                                    @RequestBody ImageDTO imageDTO) {
        log.info("createImage: imageDTO = {}", imageDTO);
        return new ResponseEntity<>(imageService.createImage(imageDTO), HttpStatus.CREATED);
    }

    @Operation(summary = "Update Image", description = "이미지 정보로 이미지 업데이트하기")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "이미지 업데이트 성공", content = @Content(schema = @Schema(implementation = ImageDTO.class))),
            @ApiResponse(responseCode = "400", description = "이미지를 업데이트할 수 없습니다.", content = @Content(schema = @Schema(implementation = ErrorDTO.class))),
    })
    @PutMapping
    public ResponseEntity<ImageDTO> updateImage(@Parameter(description = "수정할 이미지 정보를 담은 이미지 DTO")
                                                    @RequestBody ImageDTO imageDTO) {
        log.info("updateImage: imageDTO = {}", imageDTO);
        return new ResponseEntity<>(imageService.updateImage(imageDTO), HttpStatus.ACCEPTED);
    }

    @Operation(summary = "Delete Image", description = "이미지 인덱스로 이미지 한개 삭제하기")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "이미지 삭제 성공"),
            @ApiResponse(responseCode = "400", description = "이미지를 삭제할 수 없습니다.", content = @Content(schema = @Schema(implementation = ErrorDTO.class))),
    })
    @DeleteMapping("/{imageId}")
    public ResponseEntity<Void> deleteImage(@Parameter(description = "이미지 인덱스")
                                                @PathVariable String imageId) {
        log.info("deleteImage: imageId = {}", imageId);
        imageService.deleteImage(imageId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
