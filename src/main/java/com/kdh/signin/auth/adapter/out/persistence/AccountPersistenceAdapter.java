package com.kdh.signin.auth.adapter.out.persistence;


import com.kdh.signin.auth.domain.*;
import com.kdh.signin.common.PersistenceAdapter;
import lombok.RequiredArgsConstructor;

import java.util.NoSuchElementException;
import java.util.Optional;

@RequiredArgsConstructor
@PersistenceAdapter
public class AccountPersistenceAdapter {

    private final AccountRepository repository;

    private final AccountMapper mapper;

    public Long save(Email email, NickName nickName, Password password, Name name, Phone phone) {

        AccountJpaEntity entity = mapper.mapToEntity(email, nickName, password, name, phone);
        return repository.save(entity).getId();
    }


    public User findByEmail(Email email) {
        Optional<AccountJpaEntity> firstByEmail = repository.findFirstByEmail(email.getUniqueValue());

        if (firstByEmail.isEmpty()) {
            throw new NoSuchElementException("There is no user from {" + email.getUniqueValue() + "}");
        }

        return mapper.mapToDomain(firstByEmail.get());
    }

    public User findByPhone(Phone phone) {
        Optional<AccountJpaEntity> firstByPhoneNumber = repository.findFirstByPhoneNumber(phone.getUniqueValue());

        if (firstByPhoneNumber.isEmpty()) {
            throw new NoSuchElementException("There is no user from {" + phone.getUniqueValue() + "}");
        }

        return mapper.mapToDomain(firstByPhoneNumber.get());
    }

    public User findById (long id) {
        Optional<AccountJpaEntity> byId = repository.findById(id);

        if (byId.isEmpty()) {
            throw new NoSuchElementException("There is no user");
        }
        return mapper.mapToDomain(byId.get());
    }

    public boolean isNotSignUp(Email email, Phone phone) {
        return !isSignUp(email, phone);
    }

    public boolean isSignUp(Email email, Phone phone) {
        return repository.countByEmailOrPhoneNumber(email.getUniqueValue(), phone.getUniqueValue()) > 0;
    }

    public void updatePassword(User user, Password password) {
        repository.updatePassword(password.getValue(), user.getId().getId());
    }
}
