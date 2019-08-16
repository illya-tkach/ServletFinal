package org.itstep.dao;

import org.itstep.dto.ServiceDTO;

import java.util.List;

public interface ServiceDao {
    List<ServiceDTO> getAll();
}
