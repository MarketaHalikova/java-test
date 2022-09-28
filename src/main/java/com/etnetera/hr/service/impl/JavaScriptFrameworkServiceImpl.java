package com.etnetera.hr.service.impl;

import com.etnetera.hr.domain.JavaScriptFramework;
import com.etnetera.hr.service.JavaScriptFrameworkService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class JavaScriptFrameworkServiceImpl implements JavaScriptFrameworkService {
    @Override
    public Iterable<JavaScriptFramework> getAllFrameworks() {
        return null;
    }

    @Override
    public Optional<JavaScriptFramework> findFrameworkById(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<JavaScriptFramework> saveFramework(JavaScriptFramework createFrameworkDto) {
        return Optional.empty();
    }

    @Override
    public boolean deleteFramework(Long id) {
        return false;
    }

    @Override
    public Optional<JavaScriptFramework> updateFramework(JavaScriptFramework framework) {
        return Optional.empty();
    }
}
