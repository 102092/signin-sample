package com.kdh.signin.auth.adapter.out.persistence;

import com.kdh.signin.auth.domain.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author han
 */

@DataJpaTest
@Transactional
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource(value = "classpath:application-test.properties")
@Import({AccountPersistenceAdapter.class, AccountMapper.class})
class AccountPersistenceAdapterTest {

    @Autowired
    AccountPersistenceAdapter adapter;

    @Autowired
    AccountRepository repository;

    Email email = new Email("email@gmail.com");
    Password password = new Password("password");
    Name name = new Name("name");
    NickName nickName = new NickName("nickname");
    Phone phone = new Phone("010-1234-4567");

    @Test
    void save() {
        assertDoesNotThrow(() -> adapter.save(email, nickName, password, name, phone));
        assertEquals(1, repository.findAll().size());
    }

    @Test
    void isSignUp(){
        assertFalse(adapter.isSignUp(email, phone));

        adapter.save(email, nickName, password, name, phone);

        assertTrue(adapter.isSignUp(email, phone));
    }

    @Test
    void findByIdentifier() {
        adapter.save(email, nickName, password, name, phone);

        User byEmail = adapter.findByEmail(email);
        User byPhone = adapter.findByPhone(phone);

        assertEquals(byEmail.getId(), byPhone.getId());
    }

    @Test
    void throwExceptionIfNotExist() {
        adapter.save(email, nickName, password, name, phone);

        assertThrows(NoSuchElementException.class, () -> adapter.findByEmail(new Email("notexist@gmail.com")));
        assertThrows(NoSuchElementException.class, () -> adapter.findByPhone(new Phone("010-9889-1234")));
    }
}