package com.vuttr.models;

import java.io.Serializable;

import javax.persistence.*;

import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
@Table(name = "tb_tool")
public class Tool implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;
    
    @Column(nullable = false)
    private String link;
    
    @Lob
    @Column(nullable = false)
    private String description;
    
    @Column(nullable = false)
    private String tags;

	public Tool(Long id, String title, String link, String description, String tags) {
		this.id = id;
		this.title = title;
		this.link = link;
		this.description = description;
		this.tags = tags;
	}
            
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
	public String tags() {
		return tags;
	}

	public String[] getTags() {
 		return tags.split(" ");
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	
    
}
