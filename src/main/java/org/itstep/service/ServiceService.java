package org.itstep.service;

import org.itstep.dto.ServiceDTO;
import java.util.List;

public interface ServiceService {
    List<ServiceDTO> findAll();
}
