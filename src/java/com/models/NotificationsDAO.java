package com.models;

import java.util.List;

public interface NotificationsDAO {
    public List<Notifications> findAllNotifications();
    List<Notifications> findNotificationsByCustomerId(int customerID);
    List<Notifications> findNotificationsByCustomerIdAndStatus(int customerId, String status);
    public List<Notifications> findNotificationsByEmployeeIdAndStatus(int customerId, String status);
    public List<Notifications> findNotificationsByEmployeeId(int customerId);
    public List<Accounts> getAllCustomer();
    public void add(Notifications notification);
    public void delete(int id);
    public Notifications get(int id);
    public void update(Notifications notification);
    public List<Notifications> findNotifications(String keyword);
    public List<Notifications> findNotificationsByEmployeeID(int employeeID, String keyword);
}
