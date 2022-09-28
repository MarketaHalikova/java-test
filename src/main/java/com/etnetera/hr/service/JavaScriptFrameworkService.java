package com.etnetera.hr.service;

import com.etnetera.hr.domain.JavaScriptFramework;

import java.util.Optional;

public interface JavaScriptFrameworkService {
    Iterable<JavaScriptFramework> getAllFrameworks();

    Optional<JavaScriptFramework> findFrameworkById(Long id);

    Optional<JavaScriptFramework> saveFramework(JavaScriptFramework createFrameworkDto);

    boolean deleteFramework(Long id);

    Optional<JavaScriptFramework> updateFramework(JavaScriptFramework framework);

}
