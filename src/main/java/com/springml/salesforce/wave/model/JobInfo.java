package com.springml.salesforce.wave.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties
public class JobInfo implements Serializable {
    private static final long serialVersionUID = 2403943099992427787L;
    private String id;
    private String operation;
    private String object;
    private String createdById;
    private String createdDate;
    private String systemModstamp;
    private String state;
    private String concurrencyMode;
    private String contentType;
    private int numberBatchesQueued;
    private int numberBatchesInProgress;
    private int numberBatchesCompleted;
    private int numberBatchesFailed;
    private int numberBatchesTotal;
    private int numberRecordsProcessed;
    private int numberRecordsFailed;
    private int numberRetries;
    private String apiVersion;
    private String totalProcessingTime;
    private String apiActiveProcessingTime;
    private String apexProcessingTime;

    public JobInfo(String contentType, String object, String operation) {
        this.contentType = contentType;
        this.object = object;
        this.operation = operation;
    }

    public JobInfo(String state) {
        this.state = state;
    }

    // For Jackson
    public JobInfo() {}

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getApiVersion() {
        return apiVersion;
    }

    public void setApiVersion(String apiVersion) {
        this.apiVersion = apiVersion;
    }

    public String getConcurrencyMode() {
        return concurrencyMode;
    }

    public void setConcurrencyMode(String concurrencyMode) {
        this.concurrencyMode = concurrencyMode;
    }

    public String getCreatedById() {
        return createdById;
    }

    public void setCreatedById(String createdById) {
        this.createdById = createdById;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public int getNumberBatchesQueued() {
        return numberBatchesQueued;
    }

    public void setNumberBatchesQueued(int numberBatchesQueued) {
        this.numberBatchesQueued = numberBatchesQueued;
    }

    public int getNumberBatchesInProgress() {
        return numberBatchesInProgress;
    }

    public void setNumberBatchesInProgress(int numberBatchesInProgress) {
        this.numberBatchesInProgress = numberBatchesInProgress;
    }

    public int getNumberBatchesCompleted() {
        return numberBatchesCompleted;
    }

    public void setNumberBatchesCompleted(int numberBatchesCompleted) {
        this.numberBatchesCompleted = numberBatchesCompleted;
    }

    public int getNumberBatchesFailed() {
        return numberBatchesFailed;
    }

    public void setNumberBatchesFailed(int numberBatchesFailed) {
        this.numberBatchesFailed = numberBatchesFailed;
    }

    public int getNumberBatchesTotal() {
        return numberBatchesTotal;
    }

    public void setNumberBatchesTotal(int numberBatchesTotal) {
        this.numberBatchesTotal = numberBatchesTotal;
    }

    public int getNumberRecordsProcessed() {
        return numberRecordsProcessed;
    }

    public void setNumberRecordsProcessed(int numberRecordsProcessed) {
        this.numberRecordsProcessed = numberRecordsProcessed;
    }

    public int getNumberRecordsFailed() {
        return numberRecordsFailed;
    }

    public void setNumberRecordsFailed(int numberRecordsFailed) {
        this.numberRecordsFailed = numberRecordsFailed;
    }

    public int getNumberRetries() {
        return numberRetries;
    }

    public void setNumberRetries(int numberRetries) {
        this.numberRetries = numberRetries;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getSystemModstamp() {
        return systemModstamp;
    }

    public void setSystemModstamp(String systemModstamp) {
        this.systemModstamp = systemModstamp;
    }

    public String getTotalProcessingTime() {
        return totalProcessingTime;
    }

    public void setTotalProcessingTime(String totalProcessingTime) {
        this.totalProcessingTime = totalProcessingTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getApiActiveProcessingTime() {
        return apiActiveProcessingTime;
    }

    public void setApiActiveProcessingTime(String apiActiveProcessingTime) {
        this.apiActiveProcessingTime = apiActiveProcessingTime;
    }

    public String getApexProcessingTime() {
        return apexProcessingTime;
    }

    public void setApexProcessingTime(String apexProcessingTime) {
        this.apexProcessingTime = apexProcessingTime;
    }
}
