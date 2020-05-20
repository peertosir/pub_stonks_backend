package dev.peertosir.publicstonks.psapi.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name="projects")
public class ProjectEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private long id;
  
    @Column(nullable = false)
    private String projectId;
    
    @Column(nullable = false, length=100, unique = true)
    private String title;
    
    @Column(nullable = false, length=1000)
    private String description;

    @Column(length=200)
    private String gitRepoUrl;
    
    @Column(length=200)
    private String boardUrl;
    
    @Column(length=200)
    private String chatUrl;
    
    @Column(length=200)
    private String docsUrl;
    
    @Enumerated(EnumType.STRING)
    //@Column(columnDefinition = "ENUM('DEVELOPER', 'DESIGNER', 'QA')")    
    private People peopleNeeded;
    
    @Enumerated(EnumType.STRING)
    private ProjectStatus status;

    public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}
    
    public String getProjectId() {
        return this.projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGitRepoUrl() {
        return this.gitRepoUrl;
    }

    public void setGitRepoUrl(String gitRepoUrl) {
        this.gitRepoUrl = gitRepoUrl;
    }

    public String getBoardUrl() {
        return this.boardUrl;
    }

    public void setBoardUrl(String boardUrl) {
        this.boardUrl = boardUrl;
    }
    
    public String getChatUrl() {
        return this.chatUrl;
    }

    public void setChatUrl(String chatUrl) {
        this.chatUrl = chatUrl;
    }

    public String getDocsUrl() {
        return this.docsUrl;
    }

    public void setDocsUrl(String docsUrl) {
        this.docsUrl = docsUrl;
    }

    public People getPeopleNeeded() {
        return this.peopleNeeded;
    }

    public void setPeopleNeeded(People peopleNeeded) {
        this.peopleNeeded = peopleNeeded;
    }

    public ProjectStatus getStatus() {
        return this.status;
    }

    public void setStatus(ProjectStatus status) {
        this.status = status;
    }

    public enum ProjectStatus {
        NEW, APPROVED, ON_HOLD, FINISHED;
    }

    public enum People {
        DEVELOPER, DESIGNER, QA;
    }
}
