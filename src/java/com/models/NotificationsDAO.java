package com.models;

import java.util.List;

public interface NotificationsDAO {
    public List<Notifications> findAllNotifications();
    public void add(Notifications notification);
    public void delete(int id);
    public Notifications get(String id);
    public void update(Notifications notification);
    public List<Notifications> findNotifications(String keyword);
}
