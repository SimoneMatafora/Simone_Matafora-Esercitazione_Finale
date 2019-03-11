package it.tcgroup.vilear.coursemanager.adapter;

import it.tcgroup.vilear.coursemanager.common.util.DateUtil;
import it.tcgroup.vilear.coursemanager.controller.payload.response.UploadResponseV1;
import it.tcgroup.vilear.coursemanager.entity.jsonb.Attachment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AttachmentAdapter {

    @Autowired
    private DateUtil dateUtil;

    public Attachment adptUploadResponseToAttachment(UploadResponseV1 uploadResponse){

        if(uploadResponse == null)
            return null;

        Attachment attachment = new Attachment();

        attachment.setBlobName(uploadResponse.getBlobName());
        attachment.setBlobSize(uploadResponse.getFileSize());
        attachment.setCreatedAt(dateUtil.getNowDate());
        attachment.setDescription(uploadResponse.getDescription());
        attachment.setFileManagerId(uploadResponse.getId().toString());
        attachment.setFileManagerName(uploadResponse.getFileName());
        attachment.setMimeType(uploadResponse.getMimeType());

        return attachment;

    }
}
