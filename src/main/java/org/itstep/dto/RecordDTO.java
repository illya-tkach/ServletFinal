package org.itstep.dto;

import org.itstep.model.entity.Barber;
import org.itstep.model.entity.Client;
import org.itstep.model.entity.ServiceStatus;

import java.time.LocalDate;
import java.time.LocalTime;

public class RecordDTO {
    private Long id;

    private Barber barber;

    private LocalDate localDate;

    private LocalTime localTime;

    private Client client;

    private ServiceStatus status;
}
