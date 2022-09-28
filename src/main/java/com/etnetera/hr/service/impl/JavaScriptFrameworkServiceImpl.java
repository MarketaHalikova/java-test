package com.etnetera.hr.service.impl;

import com.etnetera.hr.domain.HypeLevelEnum;
import com.etnetera.hr.domain.JavaScriptFramework;
import com.etnetera.hr.dto.CreateJavaScriptFrameworkDto;
import com.etnetera.hr.dto.JavaScriptFrameworkDto;
import com.etnetera.hr.mapper.FrameworkMapper;
import com.etnetera.hr.repository.JavaScriptFrameworkRepository;
import com.etnetera.hr.service.JavaScriptFrameworkService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RequiredArgsConstructor
@Service
public class JavaScriptFrameworkServiceImpl implements JavaScriptFrameworkService {


    private final JavaScriptFrameworkRepository javaScriptFrameworkRepository;
    private final FrameworkMapper mapper;

    @Override
    public Iterable<JavaScriptFrameworkDto> getAllFrameworks() {
        return StreamSupport.stream(javaScriptFrameworkRepository.findAll().spliterator(), false)
                .map(mapper::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<JavaScriptFrameworkDto> findFrameworkById(Long id) {
        Optional<JavaScriptFramework> framework = javaScriptFrameworkRepository.findById(id);
        if (framework.isPresent()) {
            return Optional.of(mapper.entityToDto(framework.get()));
        } else {
            return Optional.empty();
        }

    }

    @Override
    public Optional<JavaScriptFrameworkDto> saveFramework(CreateJavaScriptFrameworkDto createFrameworkDto) {

        Optional<JavaScriptFramework> framework = javaScriptFrameworkRepository.findByName(createFrameworkDto.getName());

        if (framework.isEmpty()) {
            return Optional.of(mapper.entityToDto(javaScriptFrameworkRepository.save(mapper.createDtoToEntity(createFrameworkDto))));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public boolean deleteFramework(Long id) {

        Optional<JavaScriptFramework> framework = javaScriptFrameworkRepository.findById(id);

        if (framework.isPresent()) {
            javaScriptFrameworkRepository.delete(framework.get());
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Optional<JavaScriptFrameworkDto> updateFramework(JavaScriptFrameworkDto frameworkDto) {
        Optional<JavaScriptFramework> frameworkById = javaScriptFrameworkRepository.findById(frameworkDto.getId());
        Optional<JavaScriptFramework> frameworkByName = javaScriptFrameworkRepository.findByName(frameworkDto.getName());

        if (frameworkById.isPresent() && frameworkByName.isEmpty()) {
            mapper.updateFrameworkFromDto(frameworkDto, frameworkById.get());
            return Optional.of(mapper.entityToDto(javaScriptFrameworkRepository.save(frameworkById.get())));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Iterable<JavaScriptFrameworkDto> getFrameworksByHype(String hypeLevel) {
        return StreamSupport.stream(javaScriptFrameworkRepository.findAllByHypeLevel(fromStringToEnum(hypeLevel)).spliterator(), false)
                .map(mapper::entityToDto)
                .collect(Collectors.toList());
    }

    private HypeLevelEnum fromStringToEnum(String text) {
        for (HypeLevelEnum e : HypeLevelEnum.values()) {
            if (e.name().equalsIgnoreCase(text)) {
                return e;
            }
        }
        return null;
    }
}
