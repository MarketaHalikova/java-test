package com.etnetera.hr.controller;

import com.etnetera.hr.dto.CreateJavaScriptFrameworkDto;
import com.etnetera.hr.dto.JavaScriptFrameworkDto;
import com.etnetera.hr.service.JavaScriptFrameworkService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

/**
 * REST controller for accessing application logic.
 *
 * @author Etnetera
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/frameworks")
public class JavaScriptFrameworkController {

    private final JavaScriptFrameworkService javaScriptFrameworkService;

    @GetMapping
    @ResponseBody
    public Iterable<JavaScriptFrameworkDto> getAllFrameworks() {
        return javaScriptFrameworkService.getAllFrameworks();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<JavaScriptFrameworkDto> getFrameworkById(@PathVariable Long id) {
        Optional<JavaScriptFrameworkDto> framework = javaScriptFrameworkService.findFrameworkById(id);
        if (framework.isPresent()) {
            return ResponseEntity.ok().body(framework.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    @ResponseBody
    public ResponseEntity saveFramework(@Valid @RequestBody CreateJavaScriptFrameworkDto createJavaScriptFrameworkDto) {
        Optional<JavaScriptFrameworkDto> framework = javaScriptFrameworkService.saveFramework(createJavaScriptFrameworkDto);
        if (framework.isPresent()) {
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(framework.get());
        } else {
            return ResponseEntity.badRequest().contentType(MediaType.TEXT_PLAIN).body("Framework with this name already exists");
        }
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Void> deleteFramework(@PathVariable(value = "id") Long id) {
        boolean result = javaScriptFrameworkService.deleteFramework(id);
        if (result) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping
    @ResponseBody
    public ResponseEntity updateFramework(@Valid @RequestBody JavaScriptFrameworkDto frameworkDto) {
        Optional<JavaScriptFrameworkDto> framework = javaScriptFrameworkService.updateFramework(frameworkDto);
        if (framework.isPresent()) {
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(framework.get());
        } else {
            return ResponseEntity.badRequest().contentType(MediaType.TEXT_PLAIN).body("Framework with this id was not found or another framework with this name already exists");
        }
    }

    @GetMapping("/hype")
    @ResponseBody
    public Iterable<JavaScriptFrameworkDto> getFrameworksByHype(@Validated @RequestParam("hypeLevel") String hypeLevel) {
        return javaScriptFrameworkService.getFrameworksByHype(hypeLevel);
    }
}
