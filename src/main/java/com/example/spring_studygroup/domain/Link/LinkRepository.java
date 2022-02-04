package com.example.spring_studygroup.domain.Link;

import com.example.spring_studygroup.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LinkRepository extends JpaRepository<Link, Integer> {
    Link findByUser(User user);
}
