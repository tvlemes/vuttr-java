package com.vuttr.services.implementation;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import com.vuttr.converters.ToolConverter;
import com.vuttr.dto.ToolDTO;
import com.vuttr.exceptions.DatabaseException;
import com.vuttr.exceptions.ResourceNotFoundException;
import com.vuttr.models.Tool;
import com.vuttr.repositories.ToolRepository;
import com.vuttr.services.ToolService;

@Service
public class ToolServiceImp implements ToolService{

	@Autowired
	ToolRepository repository;
	
	@Autowired
	ToolConverter converter;
	

	/* Get All Tools */
	@Override
	@Transactional(readOnly = true)
	public Page<ToolDTO> findAllToolCustom(Pageable pageable) {
		Page<Tool> list = repository.findAllToolCustom(pageable);
		List<ToolDTO> dtos = converter.entityToDto(list.getContent());
	    return new PageImpl<>(dtos, pageable, list.getTotalElements());
	}
	
	/* Get Tools by Tag */
	@Override
	@Transactional(readOnly = true)
	public Page<ToolDTO> findByTagCustom(String tag, Pageable pageable) {
		Page<Tool> list = repository.findByTagCustom(tag, pageable);
	    List<ToolDTO> dtos = converter.entityToDto(list.getContent());
	    return new PageImpl<>(dtos, pageable, list.getTotalElements());

	}

	/* Get Tool by Id */
	@Override
	@Transactional(readOnly = true)
	public ToolDTO findByIdTool(Long id) {
		Tool tool =  repository.findByIdTool(id);
		if(tool == null) {
			return null;	
		}
		ToolDTO dto = converter.entityToDto(tool);
		return dto;	
					
	}

	/* Create Tool */
	@Override
	@Transactional
	public ToolDTO create(ToolDTO dto) {
		Tool tool = new Tool(null, dto.getTitle(), dto.getLink(), dto.getDescription(), dto.tags());
		tool = repository.save(tool);
		return converter.entityToDto(tool);
	}
	
	/* Update Tool */
	@Override
	@SuppressWarnings("unused")
	@Transactional
	public ToolDTO update(Long id, ToolDTO dto){
		try {
			Tool entity = repository.getOne(id);     
			
            /** Call Method updateDate() */
            updateData(entity, dto);
            Tool tool = new Tool(null, entity.getTitle(), entity.getLink(), entity.getDescription(), entity.tags());
            tool = repository.save(entity);
            return converter.entityToDto(entity);
		} catch (EntityNotFoundException e){
            throw new ResourceNotFoundException(id);
        }
		
	}
	/* Method Update Data */
	private void updateData(Tool entity, ToolDTO obj) {
        entity.setTitle(obj.getTitle());
        entity.setLink(obj.getLink());
        entity.setDescription(obj.getDescription());
        entity.setTags(obj.tags());
	}

	/* Delete Tool */
	@Override
	@Transactional
	public String delete(@PathVariable Long id) {
		try {
			Tool tool =  repository.findByIdTool(id);
			if(tool == null) {
				return "Ferramenta com o código '" + id + "' não encontrada!!!";	
			}
			repository.deleteById(id);     
		}
        catch (EmptyResultDataAccessException e){
            throw new ResourceNotFoundException(id);
        }
        catch (DataIntegrityViolationException e){
            throw new DatabaseException(e.getMessage());
        }
		return "Operação realizada com sucesso!";
	}
		
}
