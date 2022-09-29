package com.etnetera.hr.service.impl;

import com.etnetera.hr.domain.HypeLevelEnum;
import com.etnetera.hr.domain.JavaScriptFramework;
import com.etnetera.hr.dto.CreateJavaScriptFrameworkDto;
import com.etnetera.hr.dto.JavaScriptFrameworkDto;
import com.etnetera.hr.mapper.FrameworkMapper;
import com.etnetera.hr.repository.JavaScriptFrameworkRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

public class JavaScriptFrameworkServiceImplTest {

    @InjectMocks
    private JavaScriptFrameworkServiceImpl frameworkService;
    @Mock
    private JavaScriptFrameworkRepository frameworkRepository;
    @Mock
    private FrameworkMapper frameworkMapper;

    private JavaScriptFramework framework1;
    private JavaScriptFramework framework2;
    private JavaScriptFrameworkDto frameworkDto;
    private CreateJavaScriptFrameworkDto createFrameworkDto;

    @BeforeEach
    public void setUp() {
        initMocks(this);

        framework1 = JavaScriptFramework.builder()
                .name("jQuery")
                .deprecationDate(new Calendar.Builder()
                        .setDate(2020, 2, 21)
                        .build().getTime())
                .hypeLevel(HypeLevelEnum.LETDOWN)
                .versions(Set.of("3.6.0", "3.5.0"))
                .build();
        framework2 = JavaScriptFramework.builder()
                .name("Backbone")
                .hypeLevel(HypeLevelEnum.SOLID)
                .versions(Set.of("1.4.1"))
                .build();
        frameworkDto = JavaScriptFrameworkDto.builder()
                .id(1L)
                .name("VueJs")
                .hypeLevel(HypeLevelEnum.SENSATIONAL)
                .versions(Set.of("3.2.37", "3.2.39"))
                .build();
        createFrameworkDto = CreateJavaScriptFrameworkDto.builder()
                .name("VueJs")
                .hypeLevel(HypeLevelEnum.SENSATIONAL)
                .versions(Set.of("3.2.0"))
                .build();
    }

    @Test
    @DisplayName("Returning all frameworks. Should return all JavaScript frameworks dtos")
    void testGetAllFrameworks() {
        Iterable<JavaScriptFramework> frameworks = Arrays.asList(framework1, framework2);
        Mockito.when(frameworkRepository.findAll()).thenReturn(frameworks);
        Mockito.when(frameworkMapper.entityToDto(Mockito.any(JavaScriptFramework.class))).thenReturn(frameworkDto);

        Iterable<JavaScriptFrameworkDto> result = frameworkService.getAllFrameworks();

        assertThat(result).hasSameSizeAs(frameworks);
        assertThat(result).hasSize(2);
        assertThat(result).contains(frameworkDto);
        verify(frameworkMapper, times(2)).entityToDto(Mockito.any(JavaScriptFramework.class));
        verify(frameworkRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Trying to return all frameworks when there are none present. Should return empty Iterable")
    void testGetAllFrameworks_noFrameworksFound() {
        Iterable<JavaScriptFramework> frameworks = new ArrayList<>();
        Mockito.when(frameworkRepository.findAll()).thenReturn(frameworks);
        Mockito.when(frameworkMapper.entityToDto(Mockito.any(JavaScriptFramework.class))).thenReturn(frameworkDto);

        Iterable<JavaScriptFrameworkDto> result = frameworkService.getAllFrameworks();

        assertThat(result).hasSameSizeAs(frameworks);
        assertThat(result).hasSize(0);
        assertThat(result).doesNotContain(frameworkDto);
        verify(frameworkMapper, times(0)).entityToDto(Mockito.any(JavaScriptFramework.class));
        verify(frameworkRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Finding framework by id. Should return correct JavaScriptFrameworkDto")
    void testFindFrameworkById() {
        Mockito.when(frameworkRepository.findById(1L)).thenReturn(Optional.of(framework1));
        Mockito.when(frameworkMapper.entityToDto(framework1)).thenReturn(frameworkDto);

        Optional<JavaScriptFrameworkDto> result = frameworkService.findFrameworkById(1L);

        assertThat(result.get()).isNotNull();
        assertThat(result.get()).isEqualTo(frameworkDto);
        verify(frameworkMapper, times(1)).entityToDto(Mockito.any(JavaScriptFramework.class));
        verify(frameworkRepository, times(1)).findById(1L);
    }

    @Test
    @DisplayName("Trying to find non existing framework by id. Should return empty Optional")
    void testFindFrameworkById_idNotFound() {
        Mockito.when(frameworkRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<JavaScriptFrameworkDto> result = frameworkService.findFrameworkById(1L);

        assertThat(result).isEmpty();
        verify(frameworkMapper, times(0)).entityToDto(Mockito.any(JavaScriptFramework.class));
        verify(frameworkRepository, times(1)).findById(1L);
    }

    @Test
    @DisplayName("Saving new framework. Should return saved framework mapped to JavaScriptFrameworkDto")
    void testSaveFramework() {
        Mockito.when(frameworkMapper.createDtoToEntity(createFrameworkDto)).thenReturn(framework1);
        Mockito.when(frameworkMapper.entityToDto(framework1)).thenReturn(frameworkDto);
        Mockito.when(frameworkRepository.save(framework1)).thenReturn(framework1);
        Mockito.when(frameworkRepository.findByName(createFrameworkDto.getName())).thenReturn(Optional.empty());

        Optional<JavaScriptFrameworkDto> result = frameworkService.saveFramework(createFrameworkDto);


        assertThat(result).isPresent();
        verify(frameworkMapper, times(1)).entityToDto(framework1);
        verify(frameworkMapper, times(1)).createDtoToEntity(createFrameworkDto);
        verify(frameworkRepository, times(1)).save(framework1);
        verify(frameworkRepository, times(1)).findByName(createFrameworkDto.getName());
    }

    @Test
    @DisplayName("Trying to save framework with name that already exists. Should return empty Optional")
    void testSaveFramework_existingName() {
        Mockito.when(frameworkMapper.createDtoToEntity(createFrameworkDto)).thenReturn(framework1);
        Mockito.when(frameworkMapper.entityToDto(framework1)).thenReturn(frameworkDto);
        Mockito.when(frameworkRepository.save(framework1)).thenReturn(framework1);
        Mockito.when(frameworkRepository.findByName(createFrameworkDto.getName())).thenReturn(Optional.of(framework2));

        Optional<JavaScriptFrameworkDto> result = frameworkService.saveFramework(createFrameworkDto);

        assertThat(result).isEmpty();
        verify(frameworkMapper, times(0)).entityToDto(framework1);
        verify(frameworkMapper, times(0)).createDtoToEntity(createFrameworkDto);
        verify(frameworkRepository, times(0)).save(framework1);
        verify(frameworkRepository, times(1)).findByName(createFrameworkDto.getName());
    }

    @Test
    @DisplayName("Deleting existing framework. Should return boolean true")
    void testDeleteFramework() {
        Mockito.when(frameworkRepository.findById(1L)).thenReturn(Optional.of(framework1));

        boolean result = frameworkService.deleteFramework(1L);

        assertThat(result).isTrue();
        verify(frameworkRepository, times(1)).findById(1L);
        verify(frameworkRepository, times(1)).delete(framework1);
    }

    @Test
    @DisplayName("Trying to delete non existing framework. Should return boolean false")
    void testDeleteFramework_frameworkNotFound() {
        Mockito.when(frameworkRepository.findById(1L)).thenReturn(Optional.empty());

        boolean result = frameworkService.deleteFramework(1L);

        assertThat(result).isFalse();
        verify(frameworkRepository, times(1)).findById(1L);
        verify(frameworkRepository, times(0)).delete(Mockito.any(JavaScriptFramework.class));
    }

    @Test
    @DisplayName("Updating existing framework. Should return updated JavaScriptFrameworkDto")
    void testUpdateFramework() {
        Mockito.when(frameworkRepository.findById(frameworkDto.getId())).thenReturn(Optional.of(framework1));
        Mockito.when(frameworkMapper.entityToDto(framework1)).thenReturn(frameworkDto);
        Mockito.when(frameworkRepository.save(framework1)).thenReturn(framework1);
        Mockito.when(frameworkRepository.findByName(createFrameworkDto.getName())).thenReturn(Optional.empty());

        Optional<JavaScriptFrameworkDto> result = frameworkService.updateFramework(frameworkDto);

        assertThat(result).isPresent();
        verify(frameworkRepository, times(1)).findById(frameworkDto.getId());
        verify(frameworkRepository, times(1)).save(framework1);
        verify(frameworkMapper, times(1)).entityToDto(framework1);
        verify(frameworkRepository, times(1)).findByName(frameworkDto.getName());
    }

    @Test
    @DisplayName("Trying to update non existing framework. Should return empty Optional")
    void testUpdateFramework_frameworkNotFound() {
        Mockito.when(frameworkRepository.findById(frameworkDto.getId())).thenReturn(Optional.empty());
        Mockito.when(frameworkRepository.findByName(createFrameworkDto.getName())).thenReturn(Optional.empty());

        Optional<JavaScriptFrameworkDto> result = frameworkService.updateFramework(frameworkDto);

        assertThat(result).isEmpty();
        verify(frameworkRepository, times(1)).findById(frameworkDto.getId());
        verify(frameworkRepository, times(0)).save(framework1);
        verify(frameworkMapper, times(0)).entityToDto(framework1);
        verify(frameworkRepository, times(1)).findByName(frameworkDto.getName());
    }

    @Test
    @DisplayName("Trying to update framework with name that already exists. Should return empty Optional")
    void testUpdateFramework_frameworkNameAlreadyExists() {
        Mockito.when(frameworkRepository.findById(frameworkDto.getId())).thenReturn(Optional.empty());
        Mockito.when(frameworkRepository.findByName(createFrameworkDto.getName())).thenReturn(Optional.of(framework2));

        Optional<JavaScriptFrameworkDto> result = frameworkService.updateFramework(frameworkDto);

        assertThat(result).isEmpty();
        verify(frameworkRepository, times(1)).findById(frameworkDto.getId());
        verify(frameworkRepository, times(0)).save(framework1);
        verify(frameworkMapper, times(0)).entityToDto(framework1);
        verify(frameworkRepository, times(1)).findByName(frameworkDto.getName());
    }

    @Test
    @DisplayName("Finding frameworks by HypeLevel. Should return correct collection of JavaScriptFrameworkDtos")
    void testGetFrameworksByHype() {
        Iterable<JavaScriptFramework> sensationalFrameworks = Arrays.asList(framework1, framework2);
        Mockito.when(frameworkRepository.findAllByHypeLevel(HypeLevelEnum.SENSATIONAL)).thenReturn(sensationalFrameworks);
        Mockito.when(frameworkMapper.entityToDto(Mockito.any(JavaScriptFramework.class))).thenReturn(frameworkDto);

        Iterable<JavaScriptFrameworkDto> result = frameworkService.getFrameworksByHype("sensational");

        assertThat(result).hasSameSizeAs(sensationalFrameworks);
        assertThat(result).hasSize(2);
        assertThat(result).contains(frameworkDto);
        verify(frameworkMapper, times(2)).entityToDto(Mockito.any(JavaScriptFramework.class));
        verify(frameworkRepository, times(1)).findAllByHypeLevel(HypeLevelEnum.SENSATIONAL);
    }

    @Test
    @DisplayName("Trying to find frameworks by HypeLevel when there are none. Should return empty Iterable")
    void testGetFrameworksByHype_NoFrameworksFound() {
        Iterable<JavaScriptFramework> sensationalFrameworks = new ArrayList<>();
        Mockito.when(frameworkRepository.findAllByHypeLevel(HypeLevelEnum.SENSATIONAL)).thenReturn(sensationalFrameworks);
        Mockito.when(frameworkMapper.entityToDto(Mockito.any(JavaScriptFramework.class))).thenReturn(frameworkDto);

        Iterable<JavaScriptFrameworkDto> result = frameworkService.getFrameworksByHype("sensational");

        assertThat(result).hasSameSizeAs(sensationalFrameworks);
        assertThat(result).hasSize(0);
        assertThat(result).doesNotContain(frameworkDto);
        verify(frameworkMapper, times(0)).entityToDto(Mockito.any(JavaScriptFramework.class));
        verify(frameworkRepository, times(1)).findAllByHypeLevel(HypeLevelEnum.SENSATIONAL);
    }
}