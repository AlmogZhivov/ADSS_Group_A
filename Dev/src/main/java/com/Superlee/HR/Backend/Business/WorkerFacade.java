package com.Superlee.HR.Backend.Business;

import com.Superlee.HR.Backend.DataAccess.WorkerDTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class WorkerFacade {
    private static WorkerFacade instance;

    private Map<String, Worker> workers;
    private final Roles roles = Roles.getInstance();

    private static Worker loggedInWorker;

    private WorkerFacade() {
        workers = new HashMap<>();
    }

    public static WorkerFacade getInstance() {
        if (instance == null)
            instance = new WorkerFacade();
        return instance;
    }

    public List<WorkerToSend> getAllWorkers() {
        return workers.values().stream().map(WorkerFacade::convertToWorkerToSend).collect(Collectors.toList());
    }

    public List<WorkerToSend> getWorkersByRole(String role) {
        if (Util.isNullOrEmpty(role))
            throw new IllegalArgumentException("Illegal argument");

        if (roles.getId(role) == -1)
            throw new NoSuchElementException("Role not found");

        return workers.values().stream()
                .filter(worker -> worker.getRoles().stream().anyMatch(r -> r == roles.getId(role)))
                .map(WorkerFacade::convertToWorkerToSend)
                .collect(Collectors.toList());
    }

    public List<WorkerToSend> getWorkersByName(String firstname, String surname) {
        if (Util.isNullOrEmpty(firstname, surname))
            throw new IllegalArgumentException("Illegal argument");

        return workers.values().stream()
                .filter(worker -> worker.getFirstName().equalsIgnoreCase(firstname) && worker.getSurname().equalsIgnoreCase(surname))
                .map(WorkerFacade::convertToWorkerToSend)
                .collect(Collectors.toList());
    }

    public WorkerToSend getWorkerById(String id) {
        if (Util.isNullOrEmpty(id))
            throw new IllegalArgumentException("Illegal argument");

        Worker worker = workers.get(id);
        if (worker == null)
            throw new NoSuchElementException("Worker not found");

        return convertToWorkerToSend(worker);
    }

    public boolean assignWorker(String workerId, int shiftId, String role) {
        if (shiftId < 0 || Util.isNullOrEmpty(workerId, role))
            throw new IllegalArgumentException("Illegal argument");

        Worker worker = workers.get(workerId);
        if (worker == null)
            throw new NoSuchElementException("Worker not found");

        if (!worker.hasRole(roles.getId(role)))
            throw new IllegalStateException("Worker does not have the required role");

        if (worker.isAssigned(shiftId))
            throw new IllegalStateException("Worker is already assigned to this shift");

        if (!worker.isAvailable(shiftId))
            throw new IllegalStateException("Worker is not available for this shift");

        return worker.assign(shiftId);
    }

    public boolean unassignWorker(String workerId, int shiftId) {
        if (shiftId < 0 || Util.isNullOrEmpty(workerId))
            throw new IllegalArgumentException("Illegal argument");

        Worker worker = workers.get(workerId);
        if (worker != null && worker.isAssigned(shiftId))
            return worker.unassign(shiftId);

        throw new IllegalStateException("Unexpected error");
    }

    public boolean addNewWorker(String id, String firstname, String surname) {
        if (Util.isNullOrEmpty(id, firstname, surname))
            throw new IllegalArgumentException("Illegal argument");

        if (workers.get(id) != null)
            throw new IllegalArgumentException("Worker already exists");

        if (!id.matches("[0-9]+"))
            throw new IllegalArgumentException("Invalid id");

        if (firstname.length() <= 1 || surname.length() <= 1)
            throw new IllegalArgumentException("Invalid name");

        Worker worker = new Worker(id, firstname, surname);
        workers.put(worker.getId(), worker);
        return true;
    }

    public boolean addRole(String id, String role) {
        if (Util.isNullOrEmpty(id, role))
            throw new IllegalArgumentException("Illegal argument");

        return workers.get(id) != null && roles.getId(role) != -1 && workers.get(id).addRole(roles.getId(role));
    }

    public boolean addAvailability(String workerId, int shiftId) {
        if (shiftId < 0 || Util.isNullOrEmpty(workerId))
            throw new IllegalArgumentException("Illegal argument");

        Worker worker = workers.get(workerId);
        if (worker != null && !worker.isAvailable(shiftId))
            return worker.addAvailability(shiftId);

        throw new IllegalStateException("Unexpected error");
    }

    private static WorkerToSend convertToWorkerToSend(Worker worker) {
        return new WorkerToSend(
                worker.getId(), worker.getFirstName(), worker.getSurname(), worker.getEmail(),
                worker.getPhone(), worker.getSalary(), worker.getRoles(),
                worker.getStartDate().toString(), worker.getContract(), worker.getBranch());
    }

    public boolean loadData() {
        workers = WorkerDTO.getWorkers().stream().map(WorkerFacade::WorkerDTOtoWorker).collect(Collectors.toMap(Worker::getId, w -> w));
        return true;
    }

    private static Worker WorkerDTOtoWorker(WorkerDTO wDTO) {
        return new Worker(
                wDTO.getId(),
                wDTO.getFirstname(),
                wDTO.getSurname(),
                wDTO.getEmail(),
                wDTO.getPhone(),
                wDTO.getPassword(),
                wDTO.getBankDetails(),
                wDTO.getSalary(),
                wDTO.getRoles(),
                LocalDateTime.parse(wDTO.getStartDate()),
                wDTO.getContract(),
                wDTO.getBranch()
        );
    }

    /**
     * Reset the workers map.
     * DEBUGGING PURPOSES ONLY
     * DO NOT USE IN PRODUCTION
     */
    public void reset(int safetyCode) {
        if (safetyCode != 0xC0FFEE)
            System.exit(-1);
        workers.clear();
    }

    public boolean updateWorkerEmail(String id, String email) {
        if (Util.isNullOrEmpty(id, email))
            throw new IllegalArgumentException("Illegal argument");

        if (!Util.validateEmail(email))
            throw new IllegalArgumentException("Invalid email");

        Worker w = workers.get(id);
        if (w == null)
            throw new NoSuchElementException("Worker not found");

        w.setEmail(email);
        return true;
    }

    public boolean updateWorkerPhone(String id, String phone) {
        if (Util.isNullOrEmpty(id, phone))
            throw new IllegalArgumentException("Illegal argument");

        Worker w = workers.get(id);
        if (w == null)
            throw new NoSuchElementException("Worker not found");
        w.setPhone(phone);
        return true;
    }

    public boolean updateWorkerSalary(String id, int salary) {
        if (salary < 0 || Util.isNullOrEmpty(id))
            throw new IllegalArgumentException("Illegal argument");

        Worker w = workers.get(id);
        if (w == null)
            throw new NoSuchElementException("Worker not found");
        w.setSalary(salary);
        return true;
    }

    public boolean updateWorkerContractDetails(String id, String contractDetails) {
        if (Util.isNullOrEmpty(id, contractDetails))
            throw new IllegalArgumentException("Illegal argument");

        Worker w = workers.get(id);
        if (w == null)
            throw new NoSuchElementException("Worker not found");
        w.setContract(contractDetails);
        return true;
    }

    public boolean updateWorkerBankDetails(String id, String bankDetails) {
        if (Util.isNullOrEmpty(id, bankDetails))
            throw new IllegalArgumentException("Illegal argument");

        Worker w = workers.get(id);
        if (w == null)
            throw new NoSuchElementException("Worker not found");
        w.setBankDetails(bankDetails);
        return true;
    }

    public boolean updateWorkerPassword(String id, String password) {
        if (Util.isNullOrEmpty(id, password))
            throw new IllegalArgumentException("Illegal argument");

        Worker w = workers.get(id);
        if (w == null)
            throw new NoSuchElementException("Worker not found");
        w.setPassword(password);
        return true;
    }

    public boolean updateWorkerMainBranch(String id, String branch) {
        if (Util.isNullOrEmpty(id, branch))
            throw new IllegalArgumentException("Illegal argument");

        Worker w = workers.get(id);
        if (w == null)
            throw new NoSuchElementException("Worker not found");
        w.setBranch(branch);
        return true;
    }

    public WorkerToSend login(String id, String password) {
        if (Util.isNullOrEmpty(id, password))
            throw new IllegalArgumentException("Illegal argument");

        Worker w = workers.get(id);
        if (w == null)
            throw new NoSuchElementException("Worker not found");

        if (w.equals(loggedInWorker))
            throw new IllegalStateException("Worker already logged in");

        if (!w.getPassword().equals(password))
            throw new IllegalArgumentException("Invalid password");

        return convertToWorkerToSend(w);
    }
}
