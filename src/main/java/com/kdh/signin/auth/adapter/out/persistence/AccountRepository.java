package com.kdh.signin.auth.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

/**
 * @author han
 */
interface AccountRepository extends JpaRepository<AccountJpaEntity, Long> {

    @Query
    int countByEmailOrPhoneNumber(@Param("email") String email, @Param("phoneNumber") String phoneNumber);

    @Query
    Optional<AccountJpaEntity> findFirstByEmail(@Param("email") String email);

    @Query
    Optional<AccountJpaEntity> findFirstByPhoneNumber(@Param("phoneNumber") String phoneNumber);

    @Query(value = "UPDATE AccountJpaEntity a set a.password = :newPassword where a.id = :id")
    @Modifying(clearAutomatically = true)
    void updatePassword(@Param("newPassword") String newPassword, @Param("id") Long id);
}
