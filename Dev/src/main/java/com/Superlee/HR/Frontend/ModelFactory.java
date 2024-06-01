package com.Superlee.HR.Frontend;

import com.google.gson.Gson;

public class ModelFactory {
    public static BranchModel createBranchModel(String data) {
        return new Gson().fromJson(data, BranchModel.class);
    }

    public static WorkerModel createWorkerModel(String data) {
        return new Gson().fromJson(data, WorkerModel.class);
    }

    public static ShiftModel createShiftModel(String data) {
        return new Gson().fromJson(data, ShiftModel.class);
    }
}
