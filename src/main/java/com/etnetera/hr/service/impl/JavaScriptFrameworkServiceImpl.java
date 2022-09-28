package com.etnetera.hr.service.impl;

import com.etnetera.hr.dto.CreateJavaScriptFrameworkDto;
import com.etnetera.hr.dto.JavaScriptFrameworkDto;
import com.etnetera.hr.service.JavaScriptFrameworkService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class JavaScriptFrameworkServiceImpl implements JavaScriptFrameworkService {

    @Override
    public Iterable<JavaScriptFrameworkDto> getAllFrameworks() {
        return null;
    }

    @Override
    public Optional<JavaScriptFrameworkDto> findFrameworkById(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<JavaScriptFrameworkDto> saveFramework(CreateJavaScriptFrameworkDto createFrameworkDto) {
        return Optional.empty();
    }

    @Override
    public boolean deleteFramework(Long id) {
        return false;
    }

    @Override
    public Optional<JavaScriptFrameworkDto> updateFramework(JavaScriptFrameworkDto framework) {
        return Optional.empty();
    }
}
