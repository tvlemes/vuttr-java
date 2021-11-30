package com.vuttr.dto;

import javax.validation.constraints.NotBlank;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ToolDTO {

	
    private Long id;
    
    @NotBlank(message = "O campo TÍTULO é obrigatório!")
    private String title;
    
    @NotBlank(message = "O campo LINK é obrigatório!")    
    private String link;
    
    @NotBlank(message = "O campo DESCRIÇÃO é obrigatório!")
    private String description;
    
    @NotBlank(message = "O campo TAGS é obrigatório!")
    private String tags;
    
    public ToolDTO(Long id, String title, String link, String description, String tags) {
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
	
	/* Method to insert Tags */ 
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
