package com.sascharau.first.project.repo;

import com.sascharau.first.project.model.Photo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.stereotype.Repository;

public interface PhotoRepo extends CrudRepository<Photo, Integer> {
}
