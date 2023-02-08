package com.korit.library.web.api;

import com.korit.library.security.PrincipalDeteils;
import com.korit.library.service.LikeService;
import com.korit.library.web.dto.CMRespDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class LikeApi {
    private final LikeService likeService;
    @PostMapping("/book/{bookId}/like")
    public ResponseEntity<CMRespDto<Integer>>  like(@PathVariable int bookId,
                                                    @AuthenticationPrincipal PrincipalDeteils principalDeteils) {
        int likeCount = likeService.like(bookId, principalDeteils.getUser().getUserId());

        return ResponseEntity.ok().body(new CMRespDto<>(HttpStatus.OK.value(),"Successfully",likeCount));

    }

    @DeleteMapping ("/book/{bookId}/like")
    public ResponseEntity<CMRespDto<Integer>>  dislike(@PathVariable int bookId,
                                                    @AuthenticationPrincipal PrincipalDeteils principalDeteils) {
        int likeCount = likeService.dislike(bookId, principalDeteils.getUser().getUserId());
        return ResponseEntity.ok().body(new CMRespDto<>(HttpStatus.OK.value(),"Successfully",likeCount));

    }

}
