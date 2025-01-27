package com.springml.salesforce.wave.api;

import com.springml.salesforce.wave.model.BatchInfo;
import com.springml.salesforce.wave.model.BatchInfoList;
import com.springml.salesforce.wave.model.BulkRowCount;
import com.springml.salesforce.wave.model.JobInfo;

/**
 * Java client for Salesforce Bulk API
 * <a href="https://developer.salesforce.com/docs/atlas.en-us.api_asynch.meta/api_asynch/asynch_api_intro.htm">...</a>
 */
public interface BulkAPI {
    /**
     * Create a new Bulk Job
     * <a href="https://developer.salesforce.com/docs/atlas.en-us.api_asynch.meta/api_asynch/asynch_api_quickstart_create_job.htm">...</a>
     * @param object The Salesforce object to be updated
     * @return @JobInfo
     * @throws Exception exception
     */
    public JobInfo createJob(String object) throws Exception;

    /**
     * Create a new Bulk Job
     * <a href="https://developer.salesforce.com/docs/atlas.en-us.api_asynch.meta/api_asynch/asynch_api_quickstart_create_job.htm">...</a>
     * @param object The Salesforce object to be updated
     * @return @JobInfo
     * @throws Exception Exception
     */
    public JobInfo createJob(String object, String operation, String contentType) throws Exception;

    /**
     * Create a new Bulk Job
     * <a href="https://developer.salesforce.com/docs/atlas.en-us.api_asynch.meta/api_asynch/asynch_api_quickstart_create_job.htm">...</a>
     * @param jobInfo with details of the Job to be created
     * @return {@link JobInfo}
     * @throws Exception Exception
     */
    public JobInfo createJob(JobInfo jobInfo) throws Exception;

    /**
     * Add a batch to an existing Job
     * <a href="https://developer.salesforce.com/docs/atlas.en-us.api_asynch.meta/api_asynch/asynch_api_quickstart_add_batch.htm">...</a>
     * @param jobId Salesforce Job Id
     * @param csvContent CSV Content which will be used to update salesforce objects
     * @return {@link BatchInfo}
     * @throws Exception Exception
     */
    public BatchInfo addBatch(String jobId, String csvContent) throws Exception;

    /**
     * Close the specified Salesforce Bulk Job
     * @param jobId Job to be closed
     * @return JobInfo JobInfo
     * @throws Exception Exception
     */
    public JobInfo closeJob(String jobId) throws Exception;

    /**
     * Queries Salesforce to get the BatchInfo of the specified Job
     * Check whether all the Jobs are completed
     * @param jobId Job to be checked for its status
     * @return true - if all batches are completed else false
     * @throws Exception Exception
     */
    public boolean isCompleted(String jobId) throws Exception;

    /**
     * Queries Salesforce to get the BatchInfo of the specified Job
     * Check whether all the Jobs are completed
     * @param jobId Job to be checked for its status
     * @return true - if all batches are completed else false
     * @throws Exception Exception
     */
    public boolean isSuccess(String jobId) throws Exception;

    /**
     * List of the Batches for the specified Job
     * @param jobId jobId
     * @return BatchInfoList
     * @throws Exception Exception
     */
    public BatchInfoList getBatchInfoList(String jobId) throws Exception;

    /**
     * Provides details of the specified Batch
     * @param jobId jobId
     * @param batchId batchId
     * @return BatchInfo
     * @throws Exception Exception
     */
    public BatchInfo getBatchInfo(String jobId, String batchId) throws Exception;
    
    /**
     * Summarizes success and failure row counts for a job that has been completed.
     * @param jobId jobId
     * @return BulkRowCount
     * @throws Exception Exception
     */
    BulkRowCount getRowCount(String jobId) throws Exception;
}
