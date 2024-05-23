package com.Superlee.HR.Backend.Business;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.Collectors;
import java.util.Collections;

public class WorkerFacade {
    private static WorkerFacade instance;
    private static int nextWorkerId = 0;

    private static final Map<Integer, Worker> workers = new HashMap<>();

    private WorkerFacade() {
    }

    public static WorkerFacade getInstance() {
        if (instance == null)
            instance = new WorkerFacade();
        return instance;
    }

    public List<WorkerToSend> getAllWorkers() {
        return workers.values().stream()
                .map(WorkerFacade::convertToWorkerToSend)
                .collect(Collectors.toList());
    }

    public List<WorkerToSend> getWorkersByRole(String role) {
        return workers.values().stream()
                .filter(worker -> worker.getRoles().stream().anyMatch(r -> r.getValue() == Role.getRoleValue(role)))
                .map(WorkerFacade::convertToWorkerToSend)
                .collect(Collectors.toList());
    }

    public List<WorkerToSend> getWorkersByName(String name) {
        return workers.values().stream()
                .filter(worker -> worker.getName().equalsIgnoreCase(name))
                .map(WorkerFacade::convertToWorkerToSend)
                .collect(Collectors.toList());
    }

    public List<WorkerToSend> getWorkersById(int id) {
        Worker worker = workers.get(id);
        if (worker != null)
            return Collections.singletonList(convertToWorkerToSend(worker));

        return Collections.emptyList();
    }

    public boolean assignWorker(int workerId, int shiftId, String role) {
        Worker worker = workers.get(workerId);
        if (worker != null && worker.hasRole(Role.getRole(role)) && !worker.isAssigned(shiftId))
            return worker.assign(shiftId);

        return false;
    }

    public boolean unassignWorker(int workerId, int shiftId) {
        Worker worker = workers.get(workerId);
        if (worker != null && worker.isAssigned(shiftId))
            return worker.unassign(shiftId);

        return false;
    }

    public boolean addNewWorker(String name) {
        createWorker(name);
        return true;
    }

    public boolean addRole(int id, String role) {
        return workers.get(id).addRole(Role.getRole(role));
    }

    private static WorkerToSend convertToWorkerToSend(Worker worker) {
        return new WorkerToSend(
                worker.getId(), worker.getName(), worker.getEmail(),
                worker.getPhone(), worker.getSalary(), worker.getRoles(),
                worker.getStartDate(), worker.getContract());
    }

    public boolean loadData() {
        return true;
    }

    private Worker createWorker(String name) {
        Worker worker = new Worker(nextWorkerId++, name);
        workers.put(worker.getId(), worker);
        return worker;
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
        nextWorkerId = 0;
    }
}
