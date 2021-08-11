package com.test.blogapp.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.test.blogapp.model.Blog;
import com.test.blogapp.repository.BlogRepository;
@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/api")
public class BlogController {
	 @Autowired
	  BlogRepository blogRepository;
	 @Autowired
	 private MongoTemplate mongoTemplate;
	 
	 @GetMapping("/blogs")
	  public ResponseEntity<Page<Blog>> getAllblogs(@RequestParam(name="page")int page,@RequestParam(name="size", defaultValue="5")int size) {
		 Page<Blog> pagge = blogRepository.findAll(PageRequest.of(page, size));
	        return ResponseEntity.ok().body(pagge);
		 
	    
	  }

	  @GetMapping("/blogsid/{id}")
	  public ResponseEntity<Page<Blog>> getBlogById(@PathVariable("id") String id,@RequestParam(name="page")int page,@RequestParam(name="size", defaultValue="5")int size) {
		  Page<Blog> pagge = blogRepository.findById(id,PageRequest.of(page, size));
	        return ResponseEntity.ok().body(pagge);

	    
	  }

	  @PostMapping("/blogs")
	  public ResponseEntity<Blog> createBlog(@RequestBody Blog blog) {
		  Blog result= blogRepository.insert(blog);
	        return ResponseEntity.ok().body(result);

	    
	  }

	  @PutMapping("/blogs")
	  public ResponseEntity<Blog> updateBlog(@RequestBody Blog blog)throws Exception {
		  Query query = new Query();
	        query.addCriteria(Criteria.where("id").is(blog.getId()));
	        Update update = new Update();
	        update.set("title", blog.getTitle());
	        update.set("content", blog.getContent());
	        update.set("author", blog.getAuthor());
	        update.set("upvote", blog.getUpvote());
	        update.set("downvote", blog.getDownvote());
	        Blog result= mongoTemplate.findAndModify(query, update, Blog.class);
		  
	        return ResponseEntity.ok().body(result);

	  }

	  @DeleteMapping("/blogs/{id}")
	  public  ResponseEntity<Object> deleteBlog(@PathVariable("id") String id) {
		  blogRepository.deleteById(id);
	        return ResponseEntity.noContent().build();

	  }

	 

	  @GetMapping("/blogstitle/{title}")
	  public ResponseEntity<Page<Blog>> findByTitle(@PathVariable("title") String title,@RequestParam(name="page")int page,@RequestParam(name="size", defaultValue="5")int size)throws Exception {
		  if (title == null) {
	            throw new Exception("null title");
	        }
		  Page<Blog> pagge = blogRepository.findByTitleContaining(title,PageRequest.of(page, size));
	        return ResponseEntity.ok().body(pagge);
	    
	  }
	  @GetMapping("/blogsauthor/{author}")
	  public ResponseEntity<Page<Blog>> findByAuthor(@PathVariable("author") String author,@RequestParam(name="page")int page,@RequestParam(name="size", defaultValue="5")int size)throws Exception {
		  if (author == null) {
	            throw new Exception("null author");
	        }
		  Page<Blog> pagge = blogRepository.findByAuthorContaining(author,PageRequest.of(page, size));
	        return ResponseEntity.ok().body(pagge);
	    
	  }
	  @GetMapping("/blogscontent/{content}")
	  public ResponseEntity<Page<Blog>> findByContent(@PathVariable("content") String content,@RequestParam(name="page")int page,@RequestParam(name="size", defaultValue="5")int size)throws Exception {
		  if (content == null) {
	            throw new Exception("null content");
	        }
		  Page<Blog> pagge = blogRepository.findByContentContaining(content,PageRequest.of(page, size));
	        return ResponseEntity.ok().body(pagge);
	    
	  }

}
