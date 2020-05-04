package it.robyscr.ppmtool.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import it.robyscr.ppmtool.domain.Project;
import it.robyscr.ppmtool.services.ProjectService;

@Controller
@RequestMapping("/api/project")
public class ProjectController {

	@Autowired
	private ProjectService projectService;
	
	@PostMapping("")
	public ResponseEntity<?> createNewProject(@Valid @RequestBody Project project, BindingResult result){
		
		if(result.hasErrors()) {
			return new ResponseEntity<String>("Invalid object", HttpStatus.BAD_REQUEST);
		}
		
		Project projectSaved = projectService.saveOrUpdateProject(project);
		return new ResponseEntity<>(projectSaved,HttpStatus.CREATED);
	}
	
}
