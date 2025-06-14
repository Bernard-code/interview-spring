package com.bernard.interview.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.bernard.interview.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
