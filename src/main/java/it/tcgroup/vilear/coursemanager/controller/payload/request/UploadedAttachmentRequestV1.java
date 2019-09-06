package it.tcgroup.vilear.coursemanager.controller.payload.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class UploadedAttachmentRequestV1 {

    @JsonProperty("id")
    private UUID id;

    @JsonProperty("blob_type")
    private String blobType;

    @JsonProperty("blob_name")
    private String blobName;

    @JsonProperty("description")
    private String description;

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

    @JsonProperty("status")
    private String status;

    public UploadedAttachmentRequestV1() {
    }

    public UploadedAttachmentRequestV1(UUID id, String blobType, String blobName, String description, String fileName, Long fileSize, String resourceType, String resourceId, String mimeType, String status) {
        this.id = id;
        this.blobType = blobType;
        this.blobName = blobName;
        this.description = description;
        this.fileName = fileName;
        this.fileSize = fileSize;
        this.resourceType = resourceType;
        this.resourceId = resourceId;
        this.mimeType = mimeType;
        this.status = status;
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

    public String getBlobName() {
        return blobName;
    }

    public void setBlobName(String blobName) {
        this.blobName = blobName;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "UploadedAttachmentRequestV1{" +
                "id='" + id + '\'' +
                ", blobType='" + blobType + '\'' +
                ", blobName='" + blobName + '\'' +
                ", description='" + description + '\'' +
                ", fileName='" + fileName + '\'' +
                ", fileSize=" + fileSize +
                ", resourceType='" + resourceType + '\'' +
                ", resourceId='" + resourceId + '\'' +
                ", mimeType='" + mimeType + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

}
