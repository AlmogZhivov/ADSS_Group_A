package com.Superlee.HR.Backend.Business;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

class Worker {
    private final int id;
    private String name;
    private String email;
    private String phone;
    private String password;
    private String bankDetails;
    private int salary;
    private List<Role> roles;
    private List<Integer> futureShifts;
    private List<Integer> availability;
    private List<Integer> pastShifts;
    private final LocalDateTime startDate;
    private String contract;

    Worker(int id, String name) {
        this(id, name, "", "", "", "", 0, new ArrayList<>(), LocalDateTime.MIN, "");
    }

    Worker(int id, String name, String email, String phone, String password, String bankDetails, int salary, List<Role> roles, LocalDateTime startDate, String contract) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.bankDetails = bankDetails;
        this.salary = salary;
        this.roles = roles != null ? roles : new ArrayList<>();
        this.futureShifts = new ArrayList<>();
        this.availability = new ArrayList<>();
        this.pastShifts = new ArrayList<>();
        this.startDate = startDate;
        this.contract = contract;
    }

    boolean assign(Integer shiftId) {
        if (shiftId != null && !futureShifts.contains(shiftId))
            return futureShifts.add(shiftId);

        return false;
    }

    boolean unassign(Integer shiftId) {
        if (shiftId != null)
            return futureShifts.remove(shiftId);

        return false;
    }

    boolean hasRole(Role role) {
        return roles.contains(role);
    }

    boolean isAvailable(Integer shiftId) {
        return availability.contains(shiftId);
    }

    boolean addAvailability(Integer shiftId) {
        if (shiftId != null && !availability.contains(shiftId))
            return availability.add(shiftId);
        return false;
    }

    boolean removeAvailability(Integer shiftId) {
        if (shiftId != null)
            return availability.remove(shiftId);

        return false;
    }

    boolean addPastShift(Integer shiftId) {
        if (shiftId != null && !pastShifts.contains(shiftId))
            return pastShifts.add(shiftId);

        return false;
    }

    boolean isAssigned(Integer shiftId) {
        return futureShifts.contains(shiftId);
    }


    // Getters and setters

    int getId() {
        return id;
    }

    String getName() {
        return name;
    }

    void setName(String name) {
        this.name = name;
    }

    String getEmail() {
        return email;
    }

    void setEmail(String email) {
        this.email = email;
    }

    String getPhone() {
        return phone;
    }

    void setPhone(String phone) {
        this.phone = phone;
    }

    String getPassword() {
        return password;
    }

    void setPassword(String password) {
        this.password = password;
    }

    String getBankDetails() {
        return bankDetails;
    }

    void setBankDetails(String bankDetails) {
        this.bankDetails = bankDetails;
    }

    int getSalary() {
        return salary;
    }

    void setSalary(int salary) {
        this.salary = salary;
    }

    List<Role> getRoles() {
        return roles;
    }

    void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    List<Integer> getFutureShifts() {
        return futureShifts;
    }

    void setFutureShifts(List<Integer> futureShifts) {
        this.futureShifts = futureShifts;
    }

    List<Integer> getAvailability() {
        return availability;
    }

    void setAvailability(List<Integer> availability) {
        this.availability = availability;
    }

    List<Integer> getPastShifts() {
        return pastShifts;
    }

    void setPastShifts(List<Integer> pastShifts) {
        this.pastShifts = pastShifts;
    }

    LocalDateTime getStartDate() {
        return startDate;
    }

    String getContract() {
        return contract;
    }

    void setContract(String contract) {
        this.contract = contract;
    }

    public boolean addRole(Role role) {
        return roles.contains(role) ? false : roles.add(role);
    }
}
