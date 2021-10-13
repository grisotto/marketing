package com.marketing.subscription.repository;

import com.marketing.subscription.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository<T, ID> extends NaturalRepository<User, Long> {
}
