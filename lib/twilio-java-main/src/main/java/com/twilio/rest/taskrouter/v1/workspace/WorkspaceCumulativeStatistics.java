/*
 * This code was generated by
 * ___ _ _ _ _ _    _ ____    ____ ____ _    ____ ____ _  _ ____ ____ ____ ___ __   __
 *  |  | | | | |    | |  | __ |  | |__| | __ | __ |___ |\ | |___ |__/ |__|  | |  | |__/
 *  |  |_|_| | |___ | |__|    |__| |  | |    |__] |___ | \| |___ |  \ |  |  | |__| |  \
 *
 * Twilio - Taskrouter
 * This is the public Twilio REST API.
 *
 * NOTE: This class is auto generated by OpenAPI Generator.
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */

package com.twilio.rest.taskrouter.v1.workspace;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.twilio.base.Resource;
import com.twilio.converter.DateConverter;
import com.twilio.exception.ApiConnectionException;
import com.twilio.exception.ApiException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.time.ZonedDateTime;
import java.util.Map;
import java.util.Map;
import java.util.Objects;
import lombok.ToString;
import lombok.ToString;

@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
public class WorkspaceCumulativeStatistics extends Resource {

    private static final long serialVersionUID = 84911824165722L;

    public static WorkspaceCumulativeStatisticsFetcher fetcher(
        final String pathWorkspaceSid
    ) {
        return new WorkspaceCumulativeStatisticsFetcher(pathWorkspaceSid);
    }

    /**
     * Converts a JSON String into a WorkspaceCumulativeStatistics object using the provided ObjectMapper.
     *
     * @param json Raw JSON String
     * @param objectMapper Jackson ObjectMapper
     * @return WorkspaceCumulativeStatistics object represented by the provided JSON
     */
    public static WorkspaceCumulativeStatistics fromJson(
        final String json,
        final ObjectMapper objectMapper
    ) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(
                json,
                WorkspaceCumulativeStatistics.class
            );
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    /**
     * Converts a JSON InputStream into a WorkspaceCumulativeStatistics object using the provided
     * ObjectMapper.
     *
     * @param json Raw JSON InputStream
     * @param objectMapper Jackson ObjectMapper
     * @return WorkspaceCumulativeStatistics object represented by the provided JSON
     */
    public static WorkspaceCumulativeStatistics fromJson(
        final InputStream json,
        final ObjectMapper objectMapper
    ) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(
                json,
                WorkspaceCumulativeStatistics.class
            );
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    private final String accountSid;
    private final Integer avgTaskAcceptanceTime;
    private final ZonedDateTime startTime;
    private final ZonedDateTime endTime;
    private final Integer reservationsCreated;
    private final Integer reservationsAccepted;
    private final Integer reservationsRejected;
    private final Integer reservationsTimedOut;
    private final Integer reservationsCanceled;
    private final Integer reservationsRescinded;
    private final Map<String, Object> splitByWaitTime;
    private final Map<String, Object> waitDurationUntilAccepted;
    private final Map<String, Object> waitDurationUntilCanceled;
    private final Integer tasksCanceled;
    private final Integer tasksCompleted;
    private final Integer tasksCreated;
    private final Integer tasksDeleted;
    private final Integer tasksMoved;
    private final Integer tasksTimedOutInWorkflow;
    private final String workspaceSid;
    private final URI url;

    @JsonCreator
    private WorkspaceCumulativeStatistics(
        @JsonProperty("account_sid") final String accountSid,
        @JsonProperty(
            "avg_task_acceptance_time"
        ) final Integer avgTaskAcceptanceTime,
        @JsonProperty("start_time") final String startTime,
        @JsonProperty("end_time") final String endTime,
        @JsonProperty("reservations_created") final Integer reservationsCreated,
        @JsonProperty(
            "reservations_accepted"
        ) final Integer reservationsAccepted,
        @JsonProperty(
            "reservations_rejected"
        ) final Integer reservationsRejected,
        @JsonProperty(
            "reservations_timed_out"
        ) final Integer reservationsTimedOut,
        @JsonProperty(
            "reservations_canceled"
        ) final Integer reservationsCanceled,
        @JsonProperty(
            "reservations_rescinded"
        ) final Integer reservationsRescinded,
        @JsonProperty("split_by_wait_time") final Map<
            String,
            Object
        > splitByWaitTime,
        @JsonProperty("wait_duration_until_accepted") final Map<
            String,
            Object
        > waitDurationUntilAccepted,
        @JsonProperty("wait_duration_until_canceled") final Map<
            String,
            Object
        > waitDurationUntilCanceled,
        @JsonProperty("tasks_canceled") final Integer tasksCanceled,
        @JsonProperty("tasks_completed") final Integer tasksCompleted,
        @JsonProperty("tasks_created") final Integer tasksCreated,
        @JsonProperty("tasks_deleted") final Integer tasksDeleted,
        @JsonProperty("tasks_moved") final Integer tasksMoved,
        @JsonProperty(
            "tasks_timed_out_in_workflow"
        ) final Integer tasksTimedOutInWorkflow,
        @JsonProperty("workspace_sid") final String workspaceSid,
        @JsonProperty("url") final URI url
    ) {
        this.accountSid = accountSid;
        this.avgTaskAcceptanceTime = avgTaskAcceptanceTime;
        this.startTime = DateConverter.iso8601DateTimeFromString(startTime);
        this.endTime = DateConverter.iso8601DateTimeFromString(endTime);
        this.reservationsCreated = reservationsCreated;
        this.reservationsAccepted = reservationsAccepted;
        this.reservationsRejected = reservationsRejected;
        this.reservationsTimedOut = reservationsTimedOut;
        this.reservationsCanceled = reservationsCanceled;
        this.reservationsRescinded = reservationsRescinded;
        this.splitByWaitTime = splitByWaitTime;
        this.waitDurationUntilAccepted = waitDurationUntilAccepted;
        this.waitDurationUntilCanceled = waitDurationUntilCanceled;
        this.tasksCanceled = tasksCanceled;
        this.tasksCompleted = tasksCompleted;
        this.tasksCreated = tasksCreated;
        this.tasksDeleted = tasksDeleted;
        this.tasksMoved = tasksMoved;
        this.tasksTimedOutInWorkflow = tasksTimedOutInWorkflow;
        this.workspaceSid = workspaceSid;
        this.url = url;
    }

    public final String getAccountSid() {
        return this.accountSid;
    }

    public final Integer getAvgTaskAcceptanceTime() {
        return this.avgTaskAcceptanceTime;
    }

    public final ZonedDateTime getStartTime() {
        return this.startTime;
    }

    public final ZonedDateTime getEndTime() {
        return this.endTime;
    }

    public final Integer getReservationsCreated() {
        return this.reservationsCreated;
    }

    public final Integer getReservationsAccepted() {
        return this.reservationsAccepted;
    }

    public final Integer getReservationsRejected() {
        return this.reservationsRejected;
    }

    public final Integer getReservationsTimedOut() {
        return this.reservationsTimedOut;
    }

    public final Integer getReservationsCanceled() {
        return this.reservationsCanceled;
    }

    public final Integer getReservationsRescinded() {
        return this.reservationsRescinded;
    }

    public final Map<String, Object> getSplitByWaitTime() {
        return this.splitByWaitTime;
    }

    public final Map<String, Object> getWaitDurationUntilAccepted() {
        return this.waitDurationUntilAccepted;
    }

    public final Map<String, Object> getWaitDurationUntilCanceled() {
        return this.waitDurationUntilCanceled;
    }

    public final Integer getTasksCanceled() {
        return this.tasksCanceled;
    }

    public final Integer getTasksCompleted() {
        return this.tasksCompleted;
    }

    public final Integer getTasksCreated() {
        return this.tasksCreated;
    }

    public final Integer getTasksDeleted() {
        return this.tasksDeleted;
    }

    public final Integer getTasksMoved() {
        return this.tasksMoved;
    }

    public final Integer getTasksTimedOutInWorkflow() {
        return this.tasksTimedOutInWorkflow;
    }

    public final String getWorkspaceSid() {
        return this.workspaceSid;
    }

    public final URI getUrl() {
        return this.url;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        WorkspaceCumulativeStatistics other = (WorkspaceCumulativeStatistics) o;

        return (
            Objects.equals(accountSid, other.accountSid) &&
            Objects.equals(
                avgTaskAcceptanceTime,
                other.avgTaskAcceptanceTime
            ) &&
            Objects.equals(startTime, other.startTime) &&
            Objects.equals(endTime, other.endTime) &&
            Objects.equals(reservationsCreated, other.reservationsCreated) &&
            Objects.equals(reservationsAccepted, other.reservationsAccepted) &&
            Objects.equals(reservationsRejected, other.reservationsRejected) &&
            Objects.equals(reservationsTimedOut, other.reservationsTimedOut) &&
            Objects.equals(reservationsCanceled, other.reservationsCanceled) &&
            Objects.equals(
                reservationsRescinded,
                other.reservationsRescinded
            ) &&
            Objects.equals(splitByWaitTime, other.splitByWaitTime) &&
            Objects.equals(
                waitDurationUntilAccepted,
                other.waitDurationUntilAccepted
            ) &&
            Objects.equals(
                waitDurationUntilCanceled,
                other.waitDurationUntilCanceled
            ) &&
            Objects.equals(tasksCanceled, other.tasksCanceled) &&
            Objects.equals(tasksCompleted, other.tasksCompleted) &&
            Objects.equals(tasksCreated, other.tasksCreated) &&
            Objects.equals(tasksDeleted, other.tasksDeleted) &&
            Objects.equals(tasksMoved, other.tasksMoved) &&
            Objects.equals(
                tasksTimedOutInWorkflow,
                other.tasksTimedOutInWorkflow
            ) &&
            Objects.equals(workspaceSid, other.workspaceSid) &&
            Objects.equals(url, other.url)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            accountSid,
            avgTaskAcceptanceTime,
            startTime,
            endTime,
            reservationsCreated,
            reservationsAccepted,
            reservationsRejected,
            reservationsTimedOut,
            reservationsCanceled,
            reservationsRescinded,
            splitByWaitTime,
            waitDurationUntilAccepted,
            waitDurationUntilCanceled,
            tasksCanceled,
            tasksCompleted,
            tasksCreated,
            tasksDeleted,
            tasksMoved,
            tasksTimedOutInWorkflow,
            workspaceSid,
            url
        );
    }
}
