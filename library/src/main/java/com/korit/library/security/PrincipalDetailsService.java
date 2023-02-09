package com.korit.library.security;

import com.korit.library.aop.annotation.ParamsAspect;
import com.korit.library.repository.AccountRepository;
import com.korit.library.entity.UserMst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
@Slf4j
@Service
public class PrincipalDetailsService implements UserDetailsService {
    @Autowired
    private AccountRepository accountRepository;

    @ParamsAspect
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //해당 username이 DB(user-mst table)에 존재하는지 확인!
        UserMst user = accountRepository.findUserByUsername(username);//이런이름의 이름름가진 유저를 들고옴

        if (user == null) {
            throw new UsernameNotFoundException("회원정보를 확인 할 수 없음");
        }

     //   log.info("로그인 시도 요청 들어옴?");

        return new PrincipalDeteils(user);
    }
}
