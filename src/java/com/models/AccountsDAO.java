package com.models;

import java.util.List;

public interface AccountsDAO {

    public List<Accounts> findAll();

    void save(Accounts account);

    Accounts findById(int id);

    void update(Accounts account);

    void deleteById(int id);

    boolean existsByPhoneNumber(String phoneNumber);

    boolean existsByEmail(String email);

    Accounts findByEmail(String email);

    public boolean validateUser(String email, String password);

    public boolean isPhoneNumberRegistered(String phone);

    boolean registerUser(Accounts account);

    public Integer getRoleByEmail(String email);

    public String getPasswordByEmail(String email);

    public void updatePassword(String email, String newPassword);

    Accounts findByPhoneNumber(String phoneNumber);

    List<Accounts> getAccountsByRoles(List<Integer> roles);

    public List<Accounts> searchAccounts(String keyword, int page, int pageSize);

    public int getTotalAccounts(String keyword);

    public List<Accounts> getPagedAccounts(String keyword, int page, int pageSize);

    List<Accounts> findEmployeesByRoles(List<Integer> roles);
    Accounts findShipperById(int orderID);
 List<Accounts> findShippers();
}
