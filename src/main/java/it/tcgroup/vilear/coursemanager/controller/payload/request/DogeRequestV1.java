package it.tcgroup.vilear.coursemanager.controller.payload.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class DogeRequestV1 {
    @JsonProperty("id_transaction")
    private String idTransaction;

    @JsonProperty("data")
    private Map data;

    @JsonProperty("template")
    private String template;

    @JsonProperty("filename")
    private String filename;

    @JsonProperty("filemanager")
    private FileManager fileManager;


    public static class FileManager {
        @JsonProperty("uuid")
        private String uuid;
        @JsonProperty("resource_id")
        private String resourceId;
        @JsonProperty("resource_type")
        private String resourceType;
        @JsonProperty("blob_type")
        private String blobType;


        public String getUuid() {
            return uuid;
        }

        public void setUuid(String uuid) {
            this.uuid = uuid;
        }

        public String getResourceId() {
            return resourceId;
        }


        public void setResourceId(String resourceId) {
            this.resourceId = resourceId;
        }

        public String getResourceType() {
            return resourceType;
        }

        public void setResourceType(String resourceType) {
            this.resourceType = resourceType;
        }

        public String getBlobType() {
            return blobType;
        }


        public void setBlobType(String blobType) {
            this.blobType = blobType;
        }

        @Override
        public String toString() {
            return "FileManager{" +
                    "uuid='" + uuid + '\'' +
                    ", resourceId=" + resourceId +
                    ", resourceType='" + resourceType + '\'' +
                    ", blobType='" + blobType + '\'' +
                    '}';
        }
    }

    public Map getData() {
        return data;
    }

    public void setData(Map data) {
        this.data = data;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public FileManager getFileManager() {
        return fileManager;
    }

    public void setFileManager(FileManager fileManager) {
        this.fileManager = fileManager;
    }

    public String getIdTransaction() {
        return idTransaction;
    }

    public void setIdTransaction(String idTransaction) {
        this.idTransaction = idTransaction;
    }

    @Override
    public String toString() {
        return "DogeRequestV1{" +
                "idTransaction='" + idTransaction + '\'' +
                ", data=" + data +
                ", template='" + template + '\'' +
                ", filename='" + filename + '\'' +
                ", fileManager=" + fileManager +
                '}';
    }
}
