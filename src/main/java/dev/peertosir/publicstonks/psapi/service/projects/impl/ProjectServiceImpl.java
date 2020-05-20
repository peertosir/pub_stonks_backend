package dev.peertosir.publicstonks.psapi.service.projects.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import dev.peertosir.publicstonks.psapi.entity.ProjectEntity;
//import dev.peertosir.publicstonks.psapi.entity.ProjectEntity.ProjectStatus;
import dev.peertosir.publicstonks.psapi.repository.ProjectRepository;
import dev.peertosir.publicstonks.psapi.service.projects.ProjectService;
import dev.peertosir.publicstonks.psapi.shared.dto.ProjectDto;
import dev.peertosir.publicstonks.psapi.shared.utils.HashIdGenerator;

@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final HashIdGenerator hashIdGenerator;

    @Autowired
    public ProjectServiceImpl(ProjectRepository projectRepository, HashIdGenerator hashIdGenerator) {
        this.projectRepository = projectRepository;
        this.hashIdGenerator = hashIdGenerator;
    }

    @Override
    public ProjectDto createProject(ProjectDto project) {
                
        if (projectRepository.findByTitle(project.getTitle()) != null) throw new RuntimeException("Project with the same name already exists");

        ProjectEntity projectEntity = new ProjectEntity();
        BeanUtils.copyProperties(project, projectEntity);
        
        String publicProjectId = hashIdGenerator.generateProjectId(20);
        projectEntity.setProjectId(publicProjectId);
        //projectEntity.setStatus(ProjectStatus.NEW);
        ProjectEntity storedProjectDetails = projectRepository.save(projectEntity);
        
        ProjectDto returnValue = new ProjectDto();
        BeanUtils.copyProperties(storedProjectDetails, returnValue);
        return returnValue;
    }

    @Override
    public ProjectDto getProjectByProjectId(String projectId) {
        ProjectDto returnValue = new ProjectDto();
        ProjectEntity projectEntity = projectRepository.findByProjectId(projectId);
        
        if (projectEntity == null)
            throw new EntityNotFoundException(projectId);

        BeanUtils.copyProperties(projectEntity, returnValue);
        return returnValue;
    }

    @Override
    public ProjectDto updateProject(String projectId, ProjectDto project) {
        ProjectDto returnValue = new ProjectDto();
        ProjectEntity projectEntity = projectRepository.findByProjectId(projectId);

        if (projectEntity == null)
        throw new EntityNotFoundException(projectId);

        projectEntity.setTitle(project.getTitle());
        projectEntity.setBoardUrl(project.getBoardUrl());
        projectEntity.setChatUrl(project.getChatUrl());
        projectEntity.setDocsUrl(project.getDocsUrl());
        projectEntity.setDescription(project.getDescription());

        ProjectEntity updatedProjectDetails = projectRepository.save(projectEntity);
        BeanUtils.copyProperties(updatedProjectDetails, returnValue); 

        return returnValue;
    }

    @Override
    public void deleteProject(String projectId) {
        ProjectEntity projectEntity = projectRepository.findByProjectId(projectId);

        if (projectEntity == null)
        throw new EntityNotFoundException(projectId);

        projectRepository.delete(projectEntity);
    }

    @Override
    public List<ProjectDto> getProjects(int page, int limit) {
        List<ProjectDto> returnValue = new ArrayList<>();

        Pageable pageable = PageRequest.of(page, limit);
        Page<ProjectEntity> projectsPage = projectRepository.findAll(pageable);
        List<ProjectEntity> projects = projectsPage.getContent();

        for (ProjectEntity project : projects) {
            ProjectDto projectDto = new ProjectDto();
            BeanUtils.copyProperties(project, projectDto);
            returnValue.add(projectDto);
        }
        return returnValue;
    }
}