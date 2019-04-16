package it.tcgroup.vilear.coursemanager.service;

import it.tcgroup.vilear.coursemanager.controller.payload.request.UploadRequestV1;
import it.tcgroup.vilear.coursemanager.controller.payload.response.DownloadResponseV1;
import it.tcgroup.vilear.coursemanager.controller.payload.response.UploadResponseV1;

import java.io.IOException;
import java.util.UUID;

public interface FilemanagerService {

    UploadResponseV1 uploadFile(UploadRequestV1 uploadRequest)throws IOException;

    DownloadResponseV1 downloadFile(String idFile) throws IOException;
}
