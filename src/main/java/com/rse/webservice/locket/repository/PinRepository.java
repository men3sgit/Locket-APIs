package com.rse.webservice.locket.repository;

import com.rse.webservice.locket.model.Pin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PinRepository extends JpaRepository<Pin, Long> {
}
