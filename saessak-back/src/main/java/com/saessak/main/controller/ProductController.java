package com.saessak.main.controller;

import com.saessak.dto.ResponseDTO;
import com.saessak.imgfile.FileService;
import com.saessak.main.dto.ProductDTO;
import com.saessak.main.dto.ProductFormDTO;
import com.saessak.main.dto.ProductImageDTO;
import com.saessak.main.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("product")
@RequiredArgsConstructor
@Slf4j
public class ProductController {

    private final ProductService productService;
    private final FileService fileService;

    @GetMapping({"/", "/{page}"})
    public ResponseEntity<?> selectProduct(@RequestBody ProductDTO productDTO,
                                           @PathVariable("page")Optional<Integer> page){

        Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0, 30);

        Page<ProductDTO> result = productService.read(productDTO, pageable);

        try {
            ResponseDTO<ProductDTO> response = ResponseDTO.<ProductDTO>builder()
                    .data(result.getContent())
                    .build();
            return ResponseEntity.ok().body(response);
        }catch (Exception e){
            String errorMsg = e.getMessage();

            ResponseDTO<ProductDTO> response = ResponseDTO.<ProductDTO>builder()
                    .error(errorMsg)
                    .build();
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PostMapping("/searchOne")
    public ResponseEntity<?> selectOneProduct(@RequestBody ProductFormDTO productFormDTO){
        List<ProductFormDTO> contentList = new ArrayList<>();
        ProductFormDTO content = productService.readOneProduct(productFormDTO);
        contentList.add(content);

        try {
            ResponseDTO<ProductFormDTO> response = ResponseDTO.<ProductFormDTO>builder()
                    .data(contentList)
                    .build();
            return ResponseEntity.ok().body(response);
        }catch (Exception e){
            ResponseDTO<ProductFormDTO> response = ResponseDTO.<ProductFormDTO>builder()
                    .error(e.getMessage())
                    .build();
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PostMapping("/new")
    public ResponseEntity<?> createProduct(ProductFormDTO productFormDTO,
                                           @RequestPart List<MultipartFile> productImgFileList){

//        log.info("enter controller=============");
//        log.info(productFormDTO.getTitle());
//        for (MultipartFile productImgFile : productImgFileList){
//            log.info("productImgFile: " + productImgFile.getOriginalFilename());
//        }

        try {
            productService.saveProduct(productFormDTO, productImgFileList);

            List<String> list = new ArrayList<>();
            list.add("success");
            ResponseDTO<String> response = ResponseDTO.<String>builder()
                    .data(list)
                    .build();
            return ResponseEntity.ok().body(response);
        }catch (Exception e){
            e.printStackTrace();

            List<String> list = new ArrayList<>();
            list.add("fail");
            ResponseDTO<String> response = ResponseDTO.<String>builder()
                    .data(list)
                    .build();
            return ResponseEntity.badRequest().body(response);
        }
    }

    // 상품 정보 및 기존 이미지 정보 업데이트 수행
    @PostMapping("/update")
    public ResponseEntity<?> updateProduct(@RequestBody ProductFormDTO productFormDTO){

        System.out.println(productFormDTO.getId());
        System.out.println(productFormDTO.toString());

        // 받아온 데이터에서 기존에 등록된 파일의 url 로 multipart 파일 생성
        try {
            productService.updateProduct(productFormDTO);

            List<String> list = new ArrayList<>();
            list.add("success");
            ResponseDTO<String> response = ResponseDTO.<String>builder()
                    .data(list)
                    .build();

            return ResponseEntity.ok().body(response);
        }catch (Exception e){
            e.printStackTrace();

            List<String> list = new ArrayList<>();
            list.add("fail");
            ResponseDTO<String> response = ResponseDTO.<String>builder()
                    .data(list)
                    .build();
            return ResponseEntity.badRequest().body(response);
        }
    }

    // 상품 수정 시 신규 이미지 업로드
    @PostMapping("/upload")
    public ResponseEntity<?> uploadProductImg(@RequestParam("id") Long id,
                                              @RequestPart List<MultipartFile> productImgList){
        try {
            productService.uploadProductImgOnly(id, productImgList);

            List<String> list = new ArrayList<>();
            list.add("imgUpload success");
            ResponseDTO<String> response = ResponseDTO.<String>builder()
                    .data(list)
                    .build();

            return ResponseEntity.ok().body(response);
        }catch (Exception e){
            e.printStackTrace();

            List<String> list = new ArrayList<>();
            list.add("imgUpload fail");
            ResponseDTO<String> response = ResponseDTO.<String>builder()
                    .data(list)
                    .build();
            return ResponseEntity.badRequest().body(response);
        }
    }
}