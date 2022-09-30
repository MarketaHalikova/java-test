package com.etnetera.hr.service.impl;

import com.etnetera.hr.domain.HypeLevelEnum;
import com.etnetera.hr.domain.JavaScriptFramework;
import com.etnetera.hr.dto.CreateJavaScriptFrameworkDto;
import com.etnetera.hr.dto.JavaScriptFrameworkDto;
import com.etnetera.hr.repository.JavaScriptFrameworkRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest()
@Sql(scripts = "/db/create_data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "/db/delete_data.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class IJavaScriptFrameworkServiceImplTest {

    @Autowired
    private JavaScriptFrameworkServiceImpl frameworkService;
    @Autowired
    private JavaScriptFrameworkRepository frameworkRepository;

    @Test
    @DisplayName("Returning all frameworks. Should return all JavaScript frameworks dtos")
    void testGetAllFrameworks() {
        // when
        Iterable<JavaScriptFrameworkDto> frameworks = frameworkService.getAllFrameworks();

        // then
        assertThat(frameworks).isNotNull();
        assertThat(frameworks).hasSize(4);
    }

    @Test
    @DisplayName("Trying to return all frameworks when there are none present. Should return empty Iterable")
    @Sql(scripts = "/db/delete_data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void testReturnAllFrameworks_noFrameworksFound() {
        // when
        Iterable<JavaScriptFrameworkDto> frameworks = frameworkService.getAllFrameworks();

        // then
        assertThat(frameworks).isNotNull();
        assertThat(frameworks).isEmpty();
    }

    @Test
    @DisplayName("Finding framework by id. Should return correct JavaScriptFrameworkDto")
    void testFindFrameworkById() {
        // when
        Optional<JavaScriptFrameworkDto> framework = frameworkService.findFrameworkById(3L);

        // then
        assertThat(framework).isPresent();
        assertThat(framework.get().getId()).isEqualTo(3L);
    }

    @Test
    @DisplayName("Trying to find non existing framework by id. Should return empty Optional")
    @Sql(scripts = "/db/delete_data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void testFindFrameworkById_idNotFound() {
        // when
        Optional<JavaScriptFrameworkDto> framework = frameworkService.findFrameworkById(3L);

        // then
        assertThat(framework).isEmpty();
        assertThat(framework).isNotNull();
    }

    @Test
    @DisplayName("Saving new framework. Should return saved framework mapped to JavaScriptFrameworkDto")
    void testSaveFramework() {
        // given
        CreateJavaScriptFrameworkDto createFrameworkDto = CreateJavaScriptFrameworkDto.builder()
                .name("VueJs")
                .hypeLevel(HypeLevelEnum.SENSATIONAL)
                .versions(Set.of("3.2.0"))
                .build();

        // when
        Optional<JavaScriptFrameworkDto> result = frameworkService.saveFramework(createFrameworkDto);
        Iterable<JavaScriptFramework> frameworks = frameworkRepository.findAll();

        // then
        assertThat(result).isPresent();
        assertThat(result.get().getName()).isEqualTo("VueJs");
        assertThat(frameworks).hasSize(5);
    }

    @Test
    @DisplayName("Trying to save framework with name that already exists. Should return empty Optional")
    void testSaveFramework_existingName() {
        // given
        CreateJavaScriptFrameworkDto createFrameworkDto = CreateJavaScriptFrameworkDto.builder()
                .name("React")
                .hypeLevel(HypeLevelEnum.SENSATIONAL)
                .versions(Set.of("3.2.0"))
                .build();

        // when
        Optional<JavaScriptFrameworkDto> result = frameworkService.saveFramework(createFrameworkDto);
        Iterable<JavaScriptFramework> frameworks = frameworkRepository.findAll();

        // then
        assertThat(result).isEmpty();
        assertThat(result).isNotNull();
        assertThat(frameworks).hasSize(4);
    }

    @Test
    @DisplayName("Deleting existing framework. Should return boolean true")
    void testDeleteFramework() {
        // when
        boolean result = frameworkService.deleteFramework(3L);
        Iterable<JavaScriptFramework> frameworks = frameworkRepository.findAll();

        // then
        assertThat(result).isTrue();
        assertThat(frameworks).hasSize(3);
    }

    @Test
    @DisplayName("Trying to delete non existing framework. Should return boolean false")
    void testDeleteFramework_frameworkNotFound() {
        // when
        boolean result = frameworkService.deleteFramework(5L);
        Iterable<JavaScriptFramework> frameworks = frameworkRepository.findAll();

        // then
        assertThat(result).isFalse();
        assertThat(frameworks).hasSize(4);
    }

    @Test
    @DisplayName("Updating existing framework. Should return updated JavaScriptFrameworkDto")
    void testUpdateFramework() {
        // given
        JavaScriptFrameworkDto frameworkDto = JavaScriptFrameworkDto.builder()
                .id(4L)
                .name("BackbonesNew")
                .hypeLevel(HypeLevelEnum.SENSATIONAL)
                .versions(Set.of("3.2.37", "3.2.39"))
                .build();

        // when
        Optional<JavaScriptFrameworkDto> result = frameworkService.updateFramework(frameworkDto);
        Optional<JavaScriptFrameworkDto> framework = frameworkService.findFrameworkById(4L);
        Iterable<JavaScriptFramework> frameworks = frameworkRepository.findAll();

        // then
        assertThat(result).isPresent();
        assertThat(result.get().getId()).isEqualTo(4L);
        assertThat(frameworks).hasSize(4);
        assertThat(framework).isPresent();
        assertThat(framework.get().getName()).isEqualTo(frameworkDto.getName());
    }

    @Test
    @DisplayName("Trying to update non existing framework. Should return empty Optional")
    void testUpdateFramework_frameworkNotFound() {
        // given
        JavaScriptFrameworkDto frameworkDto = JavaScriptFrameworkDto.builder()
                .id(9L)
                .name("BackbonesNew")
                .hypeLevel(HypeLevelEnum.SENSATIONAL)
                .versions(Set.of("3.2.37", "3.2.39"))
                .build();

        // when
        Optional<JavaScriptFrameworkDto> result = frameworkService.updateFramework(frameworkDto);
        Optional<JavaScriptFrameworkDto> framework = frameworkService.findFrameworkById(9L);
        Iterable<JavaScriptFramework> frameworks = frameworkRepository.findAll();

        // then
        assertThat(result).isEmpty();
        assertThat(frameworks).hasSize(4);
        assertThat(framework).isEmpty();
    }

    @Test
    @DisplayName("Trying to update framework with name that already exists. Should return empty Optional")
    void testUpdateFramework_frameworkNameAlreadyExists() {
        // given
        JavaScriptFrameworkDto frameworkDto = JavaScriptFrameworkDto.builder()
                .id(4L)
                .name("React")
                .hypeLevel(HypeLevelEnum.SENSATIONAL)
                .versions(Set.of("3.2.37", "3.2.39"))
                .build();

        // when
        Optional<JavaScriptFrameworkDto> result = frameworkService.updateFramework(frameworkDto);
        Optional<JavaScriptFrameworkDto> framework = frameworkService.findFrameworkById(4L);
        Iterable<JavaScriptFramework> frameworks = frameworkRepository.findAll();

        // then
        assertThat(result).isEmpty();
        assertThat(frameworks).hasSize(4);
        assertThat(framework).isPresent();
        assertThat(framework.get().getName()).isNotEqualTo(frameworkDto.getName());
    }

    @Test
    @DisplayName("Finding frameworks by HypeLevel. Should return correct collection of JavaScriptFrameworkDtos")
    void testGetFrameworksByHype() {
        // when
        Iterable<JavaScriptFrameworkDto> frameworks = frameworkService.getFrameworksByHype("SENSATIONAL");

        // then
        assertThat(frameworks).isNotNull();
        assertThat(frameworks).hasSize(2);
    }

    @Test
    @DisplayName("Trying to find frameworks by HypeLevel when there are none. Should return empty Iterable")
    void testGetFrameworksByHype_NoFrameworksFound() {
        // when
        Iterable<JavaScriptFrameworkDto> frameworks = frameworkService.getFrameworksByHype("GOOD");

        // then
        assertThat(frameworks).isNotNull();
        assertThat(frameworks).isEmpty();
    }
}
