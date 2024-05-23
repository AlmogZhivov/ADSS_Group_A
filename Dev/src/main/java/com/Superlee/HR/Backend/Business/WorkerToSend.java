package com.Superlee.HR.Backend.Business;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

public record WorkerToSend(int id, String name, String email, String phone, int salary, List<Role> roles,
                    LocalDateTime startDate, String contract) implements Serializable {
}
