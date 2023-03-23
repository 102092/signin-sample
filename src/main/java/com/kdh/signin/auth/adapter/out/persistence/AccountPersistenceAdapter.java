package com.kdh.signin.auth.adapter.out.persistence;


import com.kdh.signin.auth.domain.*;
import com.kdh.signin.common.PersistenceAdapter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@PersistenceAdapter
public class AccountPersistenceAdapter {

    private final AccountRepository repository;

    private final AccountMapper mapper;

    public Long save(Email email, NickName nickName, Password password, Name name, Phone phone) {

        AccountJpaEntity entity = mapper.mapToEntity(email, nickName, password, name, phone);
        return repository.save(entity).getId();
    }

    public boolean isSignUp(Email email, Phone phone) {
        return repository.countByEmailOrPhoneNumber(email.getUniqueValue(), phone.getUniqueValue()) > 0;
    }
}
