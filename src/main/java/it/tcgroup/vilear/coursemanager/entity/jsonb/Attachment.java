package it.tcgroup.vilear.coursemanager.entity.jsonb;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;

import java.io.Serializable;
import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Attachment implements Serializable {

    private static final long serialVersionUID = -1400425835635903570L;

    @JsonProperty("file_manager_id")
    private String fileManagerId;

    @JsonProperty("file_manager_name")
    private String fileManagerName;

    @JsonProperty("resource_type")
    private String resourceType;

    @JsonProperty("blob_name")
    private String blobName;

    @JsonProperty("blob_size")
    private Long blobSize;

    @JsonProperty("mime_type")
    private String mimeType;

    @JsonProperty("description")
    private String description;

    @JsonProperty("created_at")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssX")
    private Date createdAt;

    public Attachment() {
    }

    public Attachment(String fileManagerId, String fileManagerName, String resourceType, String blobName, Long blobSize, String mimeType, String description, Date createdAt) {
        this.fileManagerId = fileManagerId;
        this.fileManagerName = fileManagerName;
        this.resourceType = resourceType;
        this.blobName = blobName;
        this.blobSize = blobSize;
        this.mimeType = mimeType;
        this.description = description;
        this.createdAt = createdAt;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getFileManagerId() {
        return fileManagerId;
    }

    public void setFileManagerId(String fileManagerId) {
        this.fileManagerId = fileManagerId;
    }

    public String getFileManagerName() {
        return fileManagerName;
    }

    public void setFileManagerName(String fileManagerName) {
        this.fileManagerName = fileManagerName;
    }

    public String getBlobName() {
        return blobName;
    }

    public void setBlobName(String blobName) {
        this.blobName = blobName;
    }

    public Long getBlobSize() {
        return blobSize;
    }

    public void setBlobSize(Long blobSize) {
        this.blobSize = blobSize;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    @Override
    public String toString() {
        return "Attachment{" +
                "fileManagerId='" + fileManagerId + '\'' +
                ", fileManagerName='" + fileManagerName + '\'' +
                ", blobName='" + blobName + '\'' +
                ", blobSize=" + blobSize +
                ", mimeType='" + mimeType + '\'' +
                ", description='" + description + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
