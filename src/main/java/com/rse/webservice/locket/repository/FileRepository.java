package com.rse.webservice.locket.repository;

import com.rse.webservice.locket.model.File;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<File, Long> {

}
