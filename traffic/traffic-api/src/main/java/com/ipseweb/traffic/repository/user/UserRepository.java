package com.ipseweb.traffic.repository.user;

import com.ipseweb.traffic.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
