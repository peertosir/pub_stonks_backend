package dev.peertosir.publicstonks.psapi.service.projects;

import java.util.List;

import dev.peertosir.publicstonks.psapi.shared.dto.ProjectDto;

public interface ProjectService {
    ProjectDto createProject(ProjectDto project); 
    ProjectDto updateProject(String projectId, ProjectDto project); 
    ProjectDto getProjectByProjectId(String projectId);
    void deleteProject(String projectId);
    List<ProjectDto> getProjects(int page, int limit);
}