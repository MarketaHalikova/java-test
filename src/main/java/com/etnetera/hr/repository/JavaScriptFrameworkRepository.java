package com.etnetera.hr.repository;

import com.etnetera.hr.domain.HypeLevelEnum;
import org.springframework.data.repository.CrudRepository;

import com.etnetera.hr.domain.JavaScriptFramework;

import java.util.Optional;

/**
 * Spring data repository interface used for accessing the data in database.
 * 
 * @author Etnetera
 *
 */
public interface JavaScriptFrameworkRepository extends CrudRepository<JavaScriptFramework, Long> {
    Optional<JavaScriptFramework> findByName(String name);
    Iterable<JavaScriptFramework> findAllByHypeLevel(HypeLevelEnum hypeLevel);
}
