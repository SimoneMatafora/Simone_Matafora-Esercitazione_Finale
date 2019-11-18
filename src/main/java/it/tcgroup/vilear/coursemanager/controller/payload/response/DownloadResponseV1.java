package it.tcgroup.vilear.coursemanager.controller.payload.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DownloadResponseV1 {

    @JsonProperty("description")
    private String description;

    @JsonProperty("blob_type")
    private String blobType;

    @JsonProperty("file_name")
    private String fileName;

    @JsonProperty("file_size")
    private Long fileSize;

    @JsonProperty("resource_type")
    private String resourceType;

    @JsonProperty("resource_id")
    private String resourceId;

    @JsonProperty("mime_type")
    private String mimeType;

    @JsonProperty("file_content")
    private String fileContent;

    public DownloadResponseV1() {
    }

    public DownloadResponseV1(String description, String blobType, String fileName, Long fileSize, String resourceType, String resourceId, String mimeType, String fileContent) {
        this.description = description;
        this.blobType = blobType;
        this.fileName = fileName;
        this.fileSize = fileSize;
        this.resourceType = resourceType;
        this.resourceId = resourceId;
        this.mimeType = mimeType;
        this.fileContent = fileContent;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBlobType() {
        return blobType;
    }

    public void setBlobType(String blobType) {
        this.blobType = blobType;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
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

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public String getFileContent() {
        return fileContent;
    }

    public void setFileContent(String fileContent) {
        this.fileContent = fileContent;
    }

    @Override
    public String toString() {
        return "DownloadResponseV1{" +
                "description='" + description + '\'' +
                ", blobType='" + blobType + '\'' +
                ", fileName='" + fileName + '\'' +
                ", fileSize=" + fileSize +
                ", resourceType='" + resourceType + '\'' +
                ", resourceId='" + resourceId + '\'' +
                ", mimeType='" + mimeType + '\'' +
                ", fileContent='" + fileContent + '\'' +
                '}';
    }
}
