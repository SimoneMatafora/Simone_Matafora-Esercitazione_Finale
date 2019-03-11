package it.tcgroup.vilear.coursemanager.controller.payload.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class UploadRequestV1 {

    @JsonProperty("uuid")
    private UUID id;

    @JsonProperty("blob_type")
    private String blobType;

    @JsonProperty("description")
    private String description;

    @JsonProperty("file_name")
    private String fileName;

    @JsonProperty("file_content")
    private String fileContent;

    @JsonProperty("resource_type")
    private String resourceType;

    @JsonProperty("resource_id")
    private String resourceId;

    public UploadRequestV1() {
    }

    public UploadRequestV1(UUID id, String blobType, String description, String fileName, String fileContent, String resourceType, String resourceId) {
        this.id = id;
        this.blobType = blobType;
        this.description = description;
        this.fileName = fileName;
        this.fileContent = fileContent;
        this.resourceType = resourceType;
        this.resourceId = resourceId;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getBlobType() {
        return blobType;
    }

    public void setBlobType(String blobType) {
        this.blobType = blobType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileContent() {
        return fileContent;
    }

    public void setFileContent(String fileContent) {
        this.fileContent = fileContent;
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    @Override
    public String toString() {
        return "UploadRequestV1{" +
                "id='" + id + '\'' +
                ", blobType='" + blobType + '\'' +
                ", description='" + description + '\'' +
                ", fileName='" + fileName + '\'' +
                ", fileContent='" + fileContent + '\'' +
                ", resourceType='" + resourceType + '\'' +
                ", resourceId='" + resourceId + '\'' +
                '}';
    }
}
