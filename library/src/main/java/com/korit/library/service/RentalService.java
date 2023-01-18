package com.korit.library.service;

import com.korit.library.entity.RentalDtl;
import com.korit.library.entity.RentalMst;
import com.korit.library.exception.CustomValidationException;
import com.korit.library.repository.RentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RentalService {
    @Autowired
    private RentalRepository rootRepository;

    public void rentalOne(int userId, int bookId) {
        availability(userId);

        RentalMst rentalMst =  RentalMst.builder()
                .userId(userId)
                .build();
        rootRepository.saveRentalMst(rentalMst);

        List<RentalDtl> rentalDtlList = new ArrayList<>();
        RentalDtl rentalDtl = RentalDtl.builder()
                .rentalId(rentalMst.getRentalId())
                .bookId(bookId)
                .build();
        rentalDtlList.add(rentalDtl);

        rootRepository.saveRentalDtl(rentalDtlList);
    }
    private void availability(int userId) {
        int rentalCount =  rootRepository.rentalAvailability(userId);
        if(rentalCount > 2) {
            Map<String, String> errorMap = new HashMap<>();
            errorMap.put("rentalCountError", "대여 횟수를 초과 하셨습니다.");
            throw new CustomValidationException(errorMap);
        }
    }
}
