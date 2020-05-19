package dev.peertosir.publicstonks.psapi.model.request.projects;

public class ProjectDetailsRequestModel {
    
    private String title;
    private String description;
    private String gitRepoUrl;
    private String boardUrl;
    private String chatUrl;
    private String docsUrl;
    private People peopleNeeded;
    private ProjectStatus status;

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

    enum People {
        DEVELOPER, DESIGNER, QA;
    }

    enum ProjectStatus {
        NEW, APPROVED, ON_HOLD, FINISHED;
    }
    
}