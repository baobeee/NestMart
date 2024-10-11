package com.models;

import java.util.List;

public interface EmployeeResponseDAO {
    void addEmployeeResponse(EmployeeResponse employeeResponse);
    List<EmployeeResponse> getResponsesByFeedbackID(int feedbackID);
}
