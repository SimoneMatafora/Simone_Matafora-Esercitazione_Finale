package it.tcgroup.vilear.coursemanager.common.controller.payload;

import com.fasterxml.jackson.annotation.JsonProperty;

public class VersionResponse {

    @JsonProperty("group-id")
    private String groupId;

    @JsonProperty("artifact-id")
    private String artifactId;

    @JsonProperty("name")
    private String name;

    @JsonProperty("version")
    private String version;

    public VersionResponse(String groupId, String artifactId, String name, String version) {
        this.groupId = groupId;
        this.artifactId = artifactId;
        this.name = name;
        this.version = version;
    }


    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getArtifactId() {
        return artifactId;
    }

    public void setArtifactId(String artifactId) {
        this.artifactId = artifactId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
