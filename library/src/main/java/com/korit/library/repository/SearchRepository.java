package com.korit.library.repository;

import com.korit.library.entity.SearchBook;
import com.korit.library.web.dto.SearchBookReqDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SearchRepository {
    public List<SearchBook> userSearchBook(SearchBookReqDto searchBookReqDto);

    public int getUserSearchBookTotalCount(SearchBookReqDto searchBookReqDto);
}
