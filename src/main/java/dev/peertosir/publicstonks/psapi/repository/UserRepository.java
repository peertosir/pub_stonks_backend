package dev.peertosir.publicstonks.psapi.repository;

import dev.peertosir.publicstonks.psapi.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {
    UserEntity findUserByEmail(Long id);
}