package com.mft.securwa.repositories;

import com.mft.securwa.entities.Document;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentRepository extends CrudRepository<Document, Long> {
    List<Document> findByName(String name);
}
