package com.Superlee.HR.Backend.Business;

import java.io.Serializable;
import java.util.List;

public record WorkerToSend(String id, String firstname, String surname, String email, String phone, int salary,
                           List<Integer> roles,
                           String startDate, String contract) implements Serializable {
}
