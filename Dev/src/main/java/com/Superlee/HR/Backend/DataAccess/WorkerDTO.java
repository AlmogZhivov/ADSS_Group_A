package com.Superlee.HR.Backend.DataAccess;

import java.util.List;

public class WorkerDTO {
    private int id;
    private String name;
    private String email;
    private String phone;
    private String password;
    private String bankDetails;
    private int salary;
    private List<RoleDTO> roles;
    private List<Integer> futureShifts;
    private List<Integer> availability;
    private List<Integer> pastShifts;
    private String contract;

    public WorkerDTO(int id, String name, String email, String phone, String password, String bankDetails, int salary, List<RoleDTO> roles, List<Integer> futureShifts, List<Integer> availability, List<Integer> pastShifts, String contract) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.bankDetails = bankDetails;
        this.salary = salary;
        this.roles = roles;
        this.futureShifts = futureShifts;
        this.availability = availability;
        this.pastShifts = pastShifts;
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

        WorkerDTO w1 = new WorkerDTO(1, "John Doe", "a@b.c", "123456789", "password", "123456789", 1000, null, null, null, null, "contract");
        WorkerDTO w2 = new WorkerDTO(2, "Jane Doe", "a@b.c", "123456789", "password", "123456789", 1000, null, null, null, null, "contract");
        WorkerDTO w3 = new WorkerDTO(3, "John Smith", "a@b.c", "123456789", "password", "123456789", 1000, null, null, null, null, "contract");
        WorkerDTO w4 = new WorkerDTO(4, "Jane Smith", "a@b.c", "123456789", "password", "123456789", 1000, null, null, null, null, "contract");
        WorkerDTO w5 = new WorkerDTO(5, "John Johnson", "a@b.c", "123456789", "password", "123456789", 1000, null, null, null, null, "contract");
        WorkerDTO w6 = new WorkerDTO(6, "Jane Johnson", "a@b.c", "123456789", "password", "123456789", 1000, null, null, null, null, "contract");
        WorkerDTO w7 = new WorkerDTO(7, "John Brown", "a@b.c", "123456789", "password", "123456789", 1000, null, null, null, null, "contract");
        WorkerDTO w8 = new WorkerDTO(8, "Jane Brown", "a@b.c", "123456789", "password", "123456789", 1000, null, null, null, null, "contract");
        WorkerDTO w9 = new WorkerDTO(9, "John White", "a@b.c", "123456789", "password", "123456789", 1000, null, null, null, null, "contract");
        WorkerDTO w10 = new WorkerDTO(10, "Jane White", "a@b.c", "123456789", "password", "123456789", 1000, null, null, null, null, "contract");

        return List.of(w1, w2, w3, w4, w5, w6, w7, w8, w9, w10);
    }
}
