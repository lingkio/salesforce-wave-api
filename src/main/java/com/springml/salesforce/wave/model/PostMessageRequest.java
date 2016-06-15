package com.springml.salesforce.wave.model;

import com.springml.salesforce.wave.model.chatter.MessageBody;

public class PostMessageRequest {
    private String feedElementType;
    private String subjectId;
    private MessageBody body;

    public String getFeedElementType() {
        return feedElementType;
    }

    public void setFeedElementType(String feedElementType) {
        this.feedElementType = feedElementType;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public MessageBody getBody() {
        return body;
    }

    public void setBody(MessageBody body) {
        this.body = body;
    }
}
