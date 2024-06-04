package com.Superlee.HR.Backend.Business;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

class Worker {
    private final String id;
    private String firstname;
    private String surname;
    private String email;
    private String phone;
    private String password;
    private String bankDetails;
    private int salary;
    private List<Integer> roles;
    private List<Integer> futureShifts;
    private List<Integer> availability;
    private List<Integer> pastShifts;
    private final LocalDateTime startDate;
    private String contract;
    private String branch;

    Worker(String id, String firstname, String surname) {
        this(id, firstname, surname, "", "", id, "", 0, new ArrayList<>(), LocalDateTime.now(), "", "");
    }

    Worker(String id, String firstname, String surname, String email, String phone,
           String password, String bankDetails, int salary,
           List<Integer> roles, LocalDateTime startDate, String contract, String branch) {
        this.id = id;
        this.firstname = firstname;
        this.surname = surname;
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
        this.branch = branch;
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

    boolean hasRole(int role) {
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

    String getId() {
        return id;
    }

    String getFirstName() {
        return firstname;
    }

    void setFirstName(String firstname) {
        this.firstname = firstname;
    }

    String getSurname() {
        return surname;
    }

    void setSurname(String surname) {
        this.surname = surname;
    }

    String getFullName() {
        return firstname + " " + surname;
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

    List<Integer> getRoles() {
        return roles;
    }

    void setRoles(List<Integer> roles) {
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

    public boolean addRole(int role) {
        return !roles.contains(role) && roles.add(role);
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getBranch() {
        return branch;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Worker w) {
            return w.getId().equals(this.getId());
        }
        return false;
    }
}
