package com.Superlee.HR.Backend.Business;

import java.io.Serializable;

public record ShiftToSend(int id, String name, String startTime, String endTime) implements Serializable {
}
