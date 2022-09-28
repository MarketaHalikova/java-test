package com.etnetera.hr.service;

import com.etnetera.hr.dto.CreateJavaScriptFrameworkDto;
import com.etnetera.hr.dto.JavaScriptFrameworkDto;

import java.util.Optional;

public interface JavaScriptFrameworkService {
    Iterable<JavaScriptFrameworkDto> getAllFrameworks();

    Optional<JavaScriptFrameworkDto> findFrameworkById(Long id);

    Optional<JavaScriptFrameworkDto> saveFramework(CreateJavaScriptFrameworkDto createFrameworkDto);

    boolean deleteFramework(Long id);

    Optional<JavaScriptFrameworkDto> updateFramework(JavaScriptFrameworkDto framework);

}
