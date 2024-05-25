package com.Superlee.HR.Backend.Business;

import com.Superlee.HR.Backend.DataAccess.WorkerDTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.Collectors;

public class WorkerFacade {
    private static WorkerFacade instance;

    private Map<String, Worker> workers;
    private final Roles roles = Roles.getInstance();

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
        return workers.values().stream()
                .filter(worker -> worker.getRoles().stream().anyMatch(r -> r == roles.getId(role)))
                .map(WorkerFacade::convertToWorkerToSend)
                .collect(Collectors.toList());
    }

    public List<WorkerToSend> getWorkersByName(String firstname, String surname) {
        return workers.values().stream()
                .filter(worker -> worker.getFirstName().equalsIgnoreCase(firstname) && worker.getSurname().equalsIgnoreCase(surname))
                .map(WorkerFacade::convertToWorkerToSend)
                .collect(Collectors.toList());
    }

    public WorkerToSend getWorkerById(String id) {
        Worker worker = workers.get(id);
        if (worker != null)
            return convertToWorkerToSend(worker);

        return null;
    }

    public boolean assignWorker(String workerId, int shiftId, String role) {
        Worker worker = workers.get(workerId);
        if (worker != null && worker.hasRole(roles.getId(role)) && !worker.isAssigned(shiftId) && worker.isAvailable(shiftId))
            return worker.assign(shiftId);

        return false;
    }

    public boolean unassignWorker(String workerId, int shiftId) {
        Worker worker = workers.get(workerId);
        if (worker != null && worker.isAssigned(shiftId))
            return worker.unassign(shiftId);

        return false;
    }

    public boolean addNewWorker(String id, String firstname, String surname) {
        if (id == null || firstname == null || surname == null)
            return false;

        if (workers.get(id) != null)
            return false;

        if (!id.matches("[0-9]+"))
            return false;

        if (firstname.length() <= 1 || surname.length() <= 1)
            return false;

        Worker worker = new Worker(id, firstname, surname);
        workers.put(worker.getId(), worker);
        return true;
    }

    public boolean addRole(String id, String role) {
        return workers.get(id) != null && roles.getId(role) != -1 && workers.get(id).addRole(roles.getId(role));
    }

    public boolean addAvailability(String workerId, int shiftId) {
        Worker worker = workers.get(workerId);
        if (worker != null && !worker.isAvailable(shiftId))
            return worker.addAvailability(shiftId);

        return false;
    }

    private static WorkerToSend convertToWorkerToSend(Worker worker) {
        return new WorkerToSend(
                worker.getId(), worker.getFirstName(), worker.getSurname(), worker.getEmail(),
                worker.getPhone(), worker.getSalary(), worker.getRoles(),
                worker.getStartDate().toString(), worker.getContract());
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
                wDTO.getContract()
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
}
