package com.Superlee.HR.Frontend;

import com.google.gson.Gson;

import java.util.Arrays;
import java.util.List;

public class ModelFactory {
    private static final int VALUE_OFFSET = "value\":".length();

    private static final Gson gson = new Gson();

    public static BranchModel createBranchModel(String data) {
        return gson.fromJson(data.substring(data.indexOf("value") + VALUE_OFFSET, data.length() - 1), BranchModel.class);
    }

    public static WorkerModel createWorkerModel(String data) {
        return gson.fromJson(data.substring(data.indexOf("value") + VALUE_OFFSET, data.length() - 1), WorkerModel.class);
    }

    public static List<WorkerModel> createWorkerModelList(String data) {
        return Arrays.asList(gson.fromJson(data.substring(data.indexOf("value") + VALUE_OFFSET, data.length() - 1), WorkerModel[].class));
    }

    public static ShiftModel createShiftModel(String data) {
        return gson.fromJson(data.substring(data.indexOf("value") + VALUE_OFFSET, data.length() - 1), ShiftModel.class);
    }
}
