/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.models;

import java.util.List;

/**
 *
 * @author Administrator
 */
public interface SupportDAO {
    void saveMessage(Support support);
    List<Support> getMessagesByCustomerId(int customerId);
  List<Support> getMessagesByCustomerIdAndEmployeeId(Integer customerId, Integer employeeId);
        public List<Support> getMessagesByCustomerIdAfterId(int customerId, int lastMessageId);
    public Support getMessageById(int messageId);
 public void updateMessage(Support support);
   public List<Support> getNewMessages();
List<Support> getCustomersWithNewMessages();
    int getUnreadMessageCount(int customerId);
    int deleteMessagesByCustomerIdAndEmployeeId(int customerId, int employeeId);

}

