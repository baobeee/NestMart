package com.twilio.jwt.taskrouter;

/**
 * Utility functions for generating TaskRouter related URLs.
 */
public class UrlUtils {

    private static final String TASKROUTER_BASE_URL = "https://taskrouter.twilio.com";
    private static final String TASKROUTER_VERSION = "v1";

    private UrlUtils() {}

    public static String workspaces() {
        return String.join("/", TASKROUTER_BASE_URL, TASKROUTER_VERSION, "Workspaces");
    }

    public static String allWorkspaces() {
        return String.join("/", TASKROUTER_BASE_URL, TASKROUTER_VERSION, "Workspaces", "**");
    }

    public static String workspace(String workspaceSid) {
        return String.join("/", TASKROUTER_BASE_URL, TASKROUTER_VERSION, "Workspaces", workspaceSid);
    }

    public static String taskQueues(String workspaceSid) {
        return String.join("/", workspace(workspaceSid), "TaskQueues");
    }

    public static String allTaskQueues(String workspaceSid) {
        return String.join("/", workspace(workspaceSid), "TaskQueues", "**");
    }

    public static String taskQueue(String workspaceSid, String taskQueueSid) {
        return String.join("/", workspace(workspaceSid), "TaskQueues", taskQueueSid);
    }

    public static String tasks(String workspaceSid) {
        return String.join("/", workspace(workspaceSid), "Tasks");
    }

    public static String allTasks(String workspaceSid) {
        return String.join("/", workspace(workspaceSid), "Tasks", "**");
    }

    public static String task(String workspaceSid, String taskSid) {
        return String.join("/", workspace(workspaceSid), "Tasks", taskSid);
    }

    public static String activities(String workspaceSid) {
        return String.join("/", workspace(workspaceSid), "Activities");
    }

    public static String allActivities(String workspaceSid) {
        return String.join("/", workspace(workspaceSid), "Activities", "**");
    }

    public static String activity(String workspaceSid, String activitySid) {
        return String.join("/", workspace(workspaceSid), "Activities", activitySid);
    }

    public static String workers(String workspaceSid) {
        return String.join("/", workspace(workspaceSid), "Workers");
    }

    public static String allWorkers(String workspaceSid) {
        return String.join("/", workspace(workspaceSid), "Workers", "**");
    }

    public static String worker(String workspaceSid, String workerSid) {
        return String.join("/", workspace(workspaceSid), "Workers", workerSid);
    }

    public static String reservations(String workspaceSid, String workerSid) {
        return String.join("/", worker(workspaceSid, workerSid), "Reservations");
    }

    public static String allReservations(String workspaceSid, String workerSid) {
        return String.join("/", worker(workspaceSid, workerSid), "Reservations", "**");
    }

    public static String reservation(String workspaceSid, String workerSid, String reservationSid) {
        return String.join("/", worker(workspaceSid, workerSid), "Reservations", reservationSid);
    }

}
