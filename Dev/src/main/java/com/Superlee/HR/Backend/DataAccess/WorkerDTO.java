package com.Superlee.HR.Backend.DataAccess;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class WorkerDTO {
    private String id;
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
    private String startDate;
    private String contract;
    private String branch;

    public WorkerDTO(String id, String firstname, String surname, String email, String phone, String password,
                     String bankDetails, int salary, List<Integer> roles, List<Integer> futureShifts,
                     List<Integer> availability, List<Integer> pastShifts, String startDate, String contract, String branch) {
        this.id = id;
        this.firstname = firstname;
        this.surname = surname;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.bankDetails = bankDetails;
        this.salary = salary;
        this.roles = roles;
        this.futureShifts = futureShifts;
        this.availability = availability;
        this.pastShifts = pastShifts;
        this.startDate = startDate;
        this.contract = contract;
        this.branch = branch;
    }

    public String getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBankDetails() {
        return bankDetails;
    }

    public void setBankDetails(String bankDetails) {
        this.bankDetails = bankDetails;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public List<Integer> getRoles() {
        return roles;
    }

    public void setRoles(List<Integer> roles) {
        this.roles = roles;
    }

    public List<Integer> getFutureShifts() {
        return futureShifts;
    }

    public void setFutureShifts(List<Integer> futureShifts) {
        this.futureShifts = futureShifts;
    }

    public List<Integer> getAvailability() {
        return availability;
    }

    public void setAvailability(List<Integer> availability) {
        this.availability = availability;
    }

    public List<Integer> getPastShifts() {
        return pastShifts;
    }

    public void setPastShifts(List<Integer> pastShifts) {
        this.pastShifts = pastShifts;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getContract() {
        return contract;
    }

    public String getBranch() {
        return branch;
    }

    public void setContract(String contract) {
        this.contract = contract;
    }

    public boolean insertWorker() {
        return true;
    }

    public boolean updateWorker() {
        return true;
    }

    public boolean deleteWorker() {
        return true;
    }

    public static List<WorkerDTO> getWorkers() {
        // THIS WILL BE CHANGED UPON CREATING A REAL DB
        WorkerDTO w0 = new WorkerDTO("0", "HR MANAGER", "THE ALMIGHTY", "almighty@hr.superlee.com", "666666666", "123", "123456789", 1000000, new ArrayList<>(List.of(0)), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), LocalDateTime.now().toString(), "permanent", "branch");
        WorkerDTO w1 = new WorkerDTO("1", "John", "Doe", "a@b.c", "123456789", "password", "123456789", 1000, null, null, null, null, LocalDateTime.now().toString(), "contract", "branch");
        WorkerDTO w2 = new WorkerDTO("2", "Jane", "Doe", "a@b.c", "123456789", "password", "123456789", 1000, null, null, null, null, LocalDateTime.now().toString(), "contract", "branch");
        WorkerDTO w3 = new WorkerDTO("3", "John", "Smith", "a@b.c", "123456789", "password", "123456789", 1000, null, null, null, null, LocalDateTime.now().toString(), "contract", "branch");
        WorkerDTO w4 = new WorkerDTO("4", "Jane", "Smith", "a@b.c", "123456789", "password", "123456789", 1000, null, null, null, null, LocalDateTime.now().toString(), "contract", "branch");
        WorkerDTO w5 = new WorkerDTO("5", "John", "Snow", "a@b.c", "123456789", "password", "123456789", 1000, null, null, null, null, LocalDateTime.now().toString(), "contract", "branch");
        WorkerDTO w6 = new WorkerDTO("6", "Jane", "Johnson", "a@b.c", "123456789", "password", "123456789", 1000, null, null, null, null, LocalDateTime.now().toString(), "contract", "branch");
        WorkerDTO w7 = new WorkerDTO("7", "John", "Brown", "a@b.c", "123456789", "password", "123456789", 1000, null, null, null, null, LocalDateTime.now().toString(), "contract", "branch");
        WorkerDTO w8 = new WorkerDTO("8", "Jane", "Brown", "a@b.c", "123456789", "password", "123456789", 1000, null, null, null, null, LocalDateTime.now().toString(), "contract", "branch");
        WorkerDTO w9 = new WorkerDTO("9", "John", "White", "a@b.c", "123456789", "password", "123456789", 1000, null, null, null, null, LocalDateTime.now().toString(), "contract", "branch");
        WorkerDTO w10 = new WorkerDTO("10", "Jane", "White", "a@b.c", "123456789", "password", "123456789", 1000, null, null, null, null, LocalDateTime.now().toString(), "contract", "branch");

        return List.of(w0, w1, w2, w3, w4, w5, w6, w7, w8, w9, w10);
    }
}
