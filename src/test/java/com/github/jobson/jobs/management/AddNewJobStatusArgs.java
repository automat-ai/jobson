/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */


package com.github.jobson.jobs.management;

import com.github.jobson.jobs.JobId;
import com.github.jobson.jobs.JobStatus;

public final class AddNewJobStatusArgs {

    private final JobId jobId;
    private final JobStatus newStatus;
    private final String statusMessage;


    public AddNewJobStatusArgs(JobId jobId, JobStatus newStatus, String statusMessage) {
        this.jobId = jobId;
        this.newStatus = newStatus;
        this.statusMessage = statusMessage;
    }


    public JobId getJobId() {
        return jobId;
    }

    public JobStatus getNewStatus() {
        return newStatus;
    }

    public String getStatusMessage() {
        return statusMessage;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AddNewJobStatusArgs that = (AddNewJobStatusArgs) o;

        if (jobId != null ? !jobId.equals(that.jobId) : that.jobId != null) return false;
        if (newStatus != that.newStatus) return false;
        return statusMessage != null ? statusMessage.equals(that.statusMessage) : that.statusMessage == null;
    }

    @Override
    public int hashCode() {
        int result = jobId != null ? jobId.hashCode() : 0;
        result = 31 * result + (newStatus != null ? newStatus.hashCode() : 0);
        result = 31 * result + (statusMessage != null ? statusMessage.hashCode() : 0);
        return result;
    }
}
