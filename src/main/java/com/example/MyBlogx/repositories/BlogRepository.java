package com.example.MyBlogx.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.MyBlogx.models.Blog;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Long> {

}