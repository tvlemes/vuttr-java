package com.vuttr.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.vuttr.dto.ToolDTO;

public interface ToolService {

	/* Interface Get All Tools */
	public Page<ToolDTO> findAllToolCustom(Pageable pageable);
	
	/* Interface Get Tools by Name */
	public Page<ToolDTO> findByTagCustom(String name, Pageable pageable);
	
	/* Interface Get Tool by Id*/
	public ToolDTO findByIdTool(Long id);
	
	/* Interface Create Tool */
	public ToolDTO create(ToolDTO user);
	
	/* Interface Update Tool */
	public ToolDTO update(Long id, ToolDTO dto);
	
	/* Interface Delete Tool */
	public String delete(Long id);
}
