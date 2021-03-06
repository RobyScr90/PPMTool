package it.robyscr.ppmtool.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.robyscr.ppmtool.domain.Project;
import it.robyscr.ppmtool.exceptions.ProjectIdException;
import it.robyscr.ppmtool.repositories.ProjectRepository;

@Service
public class ProjectService {

	@Autowired
	private ProjectRepository projectRepository;

	public Project saveOrUpdateProject(Project project) {

		try {
			project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
			return projectRepository.save(project);
		} catch (Exception e) {
			throw new ProjectIdException("Project ID '" + project.getProjectIdentifier() + "' already exists");
		}
	}

	public Project findProjectByIdenfier(String projectId) {
		
		Project project = projectRepository.findByProjectIdentifier(projectId.toUpperCase());
		
		if (project == null) {
			throw new ProjectIdException("Project ID '" + projectId.toUpperCase() + "' does not exists");
		}
		
		return project;
	}

	public Iterable<Project> findAllProjects(){
		return projectRepository.findAll();
	}
	
	public void deleteProjectById(String projectId) {
		String projectIdUpperCase = projectId.toUpperCase();
		Project project = projectRepository.findByProjectIdentifier(projectIdUpperCase);
		if (project == null) {
			throw new ProjectIdException("Cannot delete project with ID '"+ projectIdUpperCase + "'. This project does not exists");
		}
		
		projectRepository.delete(project);
	}
}
