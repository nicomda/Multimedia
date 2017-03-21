
package org.danico.whoru.API;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Candidate {

    @SerializedName("subject_id")
    @Expose
    private String subjectId;
    @SerializedName("confidence")
    @Expose
    private Double confidence;
    @SerializedName("enrollment_timestamp")
    @Expose
    private String enrollmentTimestamp;

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public Double getConfidence() {
        return confidence;
    }

    public void setConfidence(Double confidence) {
        this.confidence = confidence;
    }

    public String getEnrollmentTimestamp() {
        return enrollmentTimestamp;
    }

    public void setEnrollmentTimestamp(String enrollmentTimestamp) {
        this.enrollmentTimestamp = enrollmentTimestamp;
    }

}
