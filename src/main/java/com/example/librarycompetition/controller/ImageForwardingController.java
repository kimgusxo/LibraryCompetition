package com.example.librarycompetition.controller;

import com.example.librarycompetition.dto.ImageDTO;
import com.example.librarycompetition.exception.ErrorDTO;
import com.example.librarycompetition.service.ImageForwardingService;
import com.example.librarycompetition.service.ImageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

@Tag(name = "imageForwarding", description = "Image Forwarding API")
@Slf4j
@RestController
@RequestMapping("/imageForwarding")
@RequiredArgsConstructor
public class ImageForwardingController {

    private final ImageForwardingService imageForwardingService;
    private final ImageService imageService;
    
    @Operation(summary = "Post Images", description = "대출/반납 시에 책 손상도 판별하기")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "이미지 리스트 판별 성공",  content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Map.class))),
            @ApiResponse(responseCode = "404", description = "이미지 리스트 판별 실패", content = @Content(schema = @Schema(implementation = ErrorDTO.class))),
    })
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Map<String, Object>> postImageForwarding(@Parameter(description = "대출/반납 시 3방향 이미지 리스트") @RequestPart("images") List<MultipartFile> images,
                                                                   @Parameter(description = "대출/반납 시 3방향 이미지 메타데이터 리스트") @RequestPart("metadata") List<ImageDTO> metadata) throws IOException, InterruptedException {
        List<String> urls = imageForwardingService.saveImage(images);

        IntStream.range(0, metadata.size()).forEach(i -> {
            ImageDTO imageDTO = metadata.get(i);
            String url = urls.get(i);

            imageService.createImage(ImageDTO.of(imageDTO.imageId(), url, imageDTO.imageTime(), imageDTO.cameraId(), imageDTO.bookId()));
        });

        return new ResponseEntity<>(imageForwardingService.processingImage(), HttpStatus.OK);
    }
}
