package com.orange.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.orange.entities.*;

@Repository
public interface FileSystemRepository extends JpaRepository<FileSystem,Long> {

}
