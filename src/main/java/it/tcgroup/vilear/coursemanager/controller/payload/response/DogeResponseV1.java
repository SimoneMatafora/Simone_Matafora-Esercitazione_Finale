package it.tcgroup.vilear.coursemanager.controller.payload.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DogeResponseV1 {
    @JsonProperty("document_id")
    private String documentId;

    @JsonProperty("action_result")
    private ActionResult actionResult;

    public DogeResponseV1() {
    }


    public DogeResponseV1(String documentId, ActionResult actionResult) {
        this.documentId = documentId;
        this.actionResult = actionResult;
    }

    public static class ActionResult {
        private int code;
        private String message;
        private String details;

        public ActionResult() {
        }

        public ActionResult(int code, String message) {
            this.code = code;
            this.message = message;
        }

        public ActionResult(int code, String message, String details) {
            this.code = code;
            this.message = message;
            this.details = details;
        }

        public int getCode() {
            return code;
        }

        public String getMessage() {
            return message;
        }

        public String getDetails() {
            return details;
        }

        @Override
        public String toString() {
            return "ActionResult{" +
                    "code=" + code +
                    ", message='" + message + '\'' +
                    ", details='" + details + '\'' +
                    '}';
        }
    }


    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public ActionResult getActionResult() {
        return actionResult;
    }

    public void setActionResult(ActionResult actionResult) {
        this.actionResult = actionResult;
    }
}
