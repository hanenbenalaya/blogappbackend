package com.test.blogapp.repository;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.test.blogapp.model.Blog;

public interface BlogRepository extends MongoRepository<Blog, String>{
	 Page<Blog> findByTitleContaining(String blog,Pageable pageable);
	  Page<Blog> findByAuthorContaining(String  author,Pageable pageable);	
	  Page<Blog> findByContentContaining(String  content,Pageable pageable);	
	  Page<Blog> findById(String  id,Pageable pageable);	

}


