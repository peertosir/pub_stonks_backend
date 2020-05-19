package dev.peertosir.publicstonks.psapi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import dev.peertosir.publicstonks.psapi.entity.ProjectEntity;

@Repository
public interface ProjectRepository extends CrudRepository<ProjectEntity, Long> {
    ProjectEntity findByTitle(String title);
    ProjectEntity findByProjectId(String projectId);
}