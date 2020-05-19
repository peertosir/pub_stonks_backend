package dev.peertosir.publicstonks.psapi.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.peertosir.publicstonks.psapi.exceptions.projects.ProjectAlreadyExistsException;
import dev.peertosir.publicstonks.psapi.model.request.projects.ProjectDetailsRequestModel;
import dev.peertosir.publicstonks.psapi.model.response.general.OperationStatusModel;
import dev.peertosir.publicstonks.psapi.model.response.projects.ProjectRest;
import dev.peertosir.publicstonks.psapi.service.projects.ProjectService;
import dev.peertosir.publicstonks.psapi.shared.dto.ProjectDto;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("api/v1/projects")

public class ProjectController {
    
    @Autowired
    ProjectService projectService;

    @GetMapping(path="/{id}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public ProjectRest getProject(@PathVariable String id) {

        ProjectRest returnValue = new ProjectRest();
        ProjectDto projectDto = projectService.getProjectByProjectId(id);
        BeanUtils.copyProperties(projectDto, returnValue);
        return returnValue;
    }
    
    @PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public ProjectRest createProject(@RequestBody ProjectDetailsRequestModel projectDetails) {

        if (projectDetails.getTitle().isEmpty()) throw new ProjectAlreadyExistsException();
           
        ProjectRest returnValue = new ProjectRest();
        ProjectDto projectDto = new ProjectDto();
        BeanUtils.copyProperties(projectDetails, projectDto);

        ProjectDto createdProject = projectService.createProject(projectDto);
        BeanUtils.copyProperties(createdProject, returnValue);
        
        return returnValue;
    }

    @PutMapping(path = "/{id}",
    consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE },
    produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE } )
    public ProjectRest updateProject(@PathVariable String id, 
    @RequestBody ProjectDetailsRequestModel projectDetails) {
        
        ProjectRest returnValue = new ProjectRest();
        ProjectDto projectDto = new ProjectDto();
        BeanUtils.copyProperties(projectDetails, projectDto);

        ProjectDto updatedProject = projectService.updateProject(id, projectDto);
        BeanUtils.copyProperties(updatedProject, returnValue);
        
        return returnValue;
    }

    @DeleteMapping(path = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public OperationStatusModel deleteProject(@PathVariable String id) {

        OperationStatusModel operationStatusModel = new OperationStatusModel();
        operationStatusModel.setOperationName("Project deleted");

        projectService.deleteProject(id);

        operationStatusModel.setOperationResult("SUCCESS");
        return operationStatusModel;
    }

  

}