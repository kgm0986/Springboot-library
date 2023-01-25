package com.korit.library.web.api.admin;

import com.korit.library.aop.annotation.ParamsAspect;
import com.korit.library.aop.annotation.ValidAspect;
import com.korit.library.entity.BookImage;
import com.korit.library.entity.BookMst;
import com.korit.library.entity.CategoryView;
import com.korit.library.service.BookService;
import com.korit.library.web.dto.*;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@Api(tags = {"관리자 도서관리 API"})
@RequestMapping("/api/admin")
@RestController
//@CrossOrigin(origins = "http://127.0.0.1:5500")//크로스  오리진 허가되지 않은 서버에서 요청을 날린경우 막아버리서 지정한 서버를 풀어주는것
public class BookApi {

    @Autowired
    private BookService bookService;

    @ParamsAspect
    @ValidAspect
    @GetMapping("/books")
    public ResponseEntity<CMRespDto<List<BookMst>>> searchBook(@Valid SearchReqDto searchReqDto, BindingResult bindingResult) {
        return ResponseEntity
                .ok()
                .body(new CMRespDto<>(HttpStatus.OK.value(), "Successfully", bookService.searchBook(searchReqDto)));
    }

    @GetMapping("/categories")
    public ResponseEntity<CMRespDto<List<CategoryView>>> getCategories() {
        return ResponseEntity
                .ok()
                .body(new CMRespDto<>(HttpStatus.OK.value(), "Successfully", bookService.getCategories()));
    }

    @ParamsAspect
    @ValidAspect
    @PostMapping("/book")
    public ResponseEntity<CMRespDto<?>> registerBook(@Valid @RequestBody BookReqDto bookReqDto, BindingResult bindingResult) {
        bookService.registerBook(bookReqDto);
        return ResponseEntity
                .created(null)
                .body(new CMRespDto<>(HttpStatus.CREATED.value(), "Successfully", true));
    }

    @ParamsAspect
    @ValidAspect
    @PutMapping("/book/{bookCode}")
    public ResponseEntity<CMRespDto<?>> modifyBook(@PathVariable String bookCode, @Valid @RequestBody BookReqDto bookReqDto, BindingResult bindingResult) {
        bookService.modifyBook(bookReqDto);
        return ResponseEntity
                .ok()
                .body(new CMRespDto<>(HttpStatus.OK.value(), "Successfully", true));
    }
    @ValidAspect
    @ParamsAspect
    @PatchMapping("/book/{bookCode}")
    public ResponseEntity<CMRespDto<?>> removeBook(@PathVariable String bookCode, @Valid @RequestBody BookReqDto bookReqDto, BindingResult bindingResult) {
        bookService.maintainModifyBook(bookReqDto);
        return ResponseEntity
                .ok()
                .body(new CMRespDto<>(HttpStatus.OK.value(), "Successfully", true));
    }
    @ParamsAspect
    @DeleteMapping("/book/{bookCode}")
    public ResponseEntity<CMRespDto<?>> removeBook(@PathVariable String bookCode) {
        bookService.removeBook(bookCode);
        return ResponseEntity
                .ok()
                .body(new CMRespDto<>(HttpStatus.OK.value(),"Successfully", true));
    }
    
    @ParamsAspect
    @PostMapping("/book/{bookCode}/images")
    public ResponseEntity<CMRespDto<?>> registerBookImg(@PathVariable String bookCode ,@RequestPart List<MultipartFile> files) {//list로 걸어서 여러개 받기가능
       bookService.registerBookImages(bookCode,files);
        //System.out.println(file.getOriginalFilename());//파일의 실제이름 출력
        return ResponseEntity.ok()
                .body(new CMRespDto<>(HttpStatus.OK.value(),"Successfully",true));
    }

    @ParamsAspect
    @GetMapping("/book/{bookCode}/images")
    public ResponseEntity<CMRespDto<?>> getIMages(@PathVariable String bookCode) {
        List<BookImage> bookImages = bookService.getBooks(bookCode);
        return  ResponseEntity.ok()
                .body(new CMRespDto<>(HttpStatus.OK.value(),"Successfully", bookImages));
    }

    @ParamsAspect
    @DeleteMapping("/books/{bookCode}/images/{imageId}")
    public ResponseEntity<CMRespDto<?>> removeBookImg(@PathVariable String bookCode, @PathVariable int imageId){
        bookService.removeBookImage(imageId);
        return  ResponseEntity.ok().body(new CMRespDto<>(HttpStatus.OK.value(),"Successfully",null));
    }



}
