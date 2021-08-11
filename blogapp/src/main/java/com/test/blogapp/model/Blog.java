package com.test.blogapp.model;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Document(collection = "blog")
public class Blog {
	 @Id
	  private String id;

	  private String title;
	  private String content;
	  private String author;
	  private Long upvote; 
	  private Long downvote;
	  public String getId() {
	        return id;
	    }

	    public void setId(String id) {
	        this.id = id;
	    }

	    public String getTitle() {
	        return title;
	    }
	    public void setTitle(String name) {
	        this.title = name;
	    }
	    public String getContent() {
	        return content;
	    }
	    public void setContent(String content) {
	        this.content = content;
	    }
	    public String getAuthor() {
	        return author;
	    }
	    public void setAuthor(String name) {
	        this.author = name;
	    }
	    public Long getUpvote() {
	        return upvote;
	    }

	    public void setUpvote(Long up) {
	        this.upvote = up;
	    }
	    public Long getDownvote() {
	        return downvote;
	    }

	    public void setDownvote(Long down) {
	        this.downvote = down;
	    }
}
