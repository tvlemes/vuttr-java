package com.vuttr.converters;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.vuttr.dto.ToolDTO;
import com.vuttr.models.Tool;

@Component
public class ToolConverter implements Serializable{
	private static final long serialVersionUID = 1L;
	
	/* Converter Models to DTO */
	public ToolDTO entityToDto(Tool tool) {
		ToolDTO dto = new ToolDTO();
		dto.setId(tool.getId());
		dto.setTitle(tool.getTitle());
		dto.setLink(tool.getLink());
		dto.setDescription(tool.getDescription());
		dto.setTags(tool.tags());
		return dto;
	}
	
	public List<ToolDTO> entityToDto(List<Tool> tool ){
		return	tool.stream().map(x -> entityToDto(x)).collect(Collectors.toList());
	}
	
	public List<ToolDTO> entityToDtoPage(List<Tool> tool ){
		return	tool.stream().map(x -> entityToDto(x)).collect(Collectors.toList());
	}
	
	/* Converter DTO to Models */
	public Tool dtoToEntity(ToolDTO dto) {
		Tool tool = new Tool();
		tool.setId(dto.getId());
		tool.setTitle(dto.getTitle());
		tool.setLink(dto.getLink());
		tool.setDescription(dto.getDescription());
		tool.setTags(dto.getTags().toString());
		return tool;
	}
	
	public List<Tool> dtoToEntity(List<ToolDTO> dto)
	{
		return dto.stream().map(x -> dtoToEntity(x)).collect(Collectors.toList());
	}

}
