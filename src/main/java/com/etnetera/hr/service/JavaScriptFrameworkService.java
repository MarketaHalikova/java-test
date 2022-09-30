package com.etnetera.hr.service;

import com.etnetera.hr.dto.CreateJavaScriptFrameworkDto;
import com.etnetera.hr.dto.JavaScriptFrameworkDto;

import java.util.Optional;

/**
 * Service interface for JavaScript Framework operations
 *
 * @author Marketa Halikova
 */
public interface JavaScriptFrameworkService {
    /**
     * Get all JavaScript frameworks
     *
     * @return Iterable of JavaScriptFrameworkDto
     */
    Iterable<JavaScriptFrameworkDto> getAllFrameworks();

    /**
     * Find JavaScript framework by id
     *
     * @param id JavaScript framework id
     * @return Optional of JavaScriptFrameworkDto
     */
    Optional<JavaScriptFrameworkDto> findFrameworkById(Long id);

    /**
     * Save new JavaScript framework
     *
     * @param createFrameworkDto new JavaScript framework for saving
     * @return Optional of saved JavaScriptFrameworkDto
     */
    Optional<JavaScriptFrameworkDto> saveFramework(CreateJavaScriptFrameworkDto createFrameworkDto);

    /**
     * Delete existing JavaScript framework by id
     *
     * @param id JavaScript framework id
     * @return boolean
     */
    boolean deleteFramework(Long id);

    /**
     * Update existing JavaScript framework
     *
     * @param framework updated JavaScript framework
     * @return Optional of updated JavaScriptFrameworkDto
     */
    Optional<JavaScriptFrameworkDto> updateFramework(JavaScriptFrameworkDto framework);

    /**
     * Find all JavaScript frameworks by hype level
     *
     * @param hypeLevel JavaScript framework hype level
     * @return Iterable of JavaScriptFrameworkDto
     */
    Iterable<JavaScriptFrameworkDto> getFrameworksByHype(String hypeLevel);
}
