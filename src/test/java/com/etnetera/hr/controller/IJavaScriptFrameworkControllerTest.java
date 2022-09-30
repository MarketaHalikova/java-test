package com.etnetera.hr.controller;

import com.etnetera.hr.dto.CreateJavaScriptFrameworkDto;
import com.etnetera.hr.dto.JavaScriptFrameworkDto;
import com.etnetera.hr.service.JavaScriptFrameworkService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.never;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(JavaScriptFrameworkController.class)
public class IJavaScriptFrameworkControllerTest {

    public static final String HYPE_LEVEL = "SOLID";
    @MockBean
    private JavaScriptFrameworkService javaScriptFrameworkService;
    @Autowired
    private MockMvc mvc;

    public static final String DTO_VALID = "{\"id\":2,\"name\":\"Newjkjk\",\"versions\":[\"2.0.0\",\"1.0.0\"],\"deprecationDate\":\"2012-03-21T13:00:00.000+0000\",\"hypeLevel\":\"GREAT\"}";
    public static final String DTO_NON_VALID = "{\"versions\":[\"2.0.0\",\"1.0.0\"],\"deprecationDate\":\"2012-03-21T13:00:00.000+0000\",\"hypeLevel\":\"GREAT\"}";

    @Test
    void testGetAllFrameworks() throws Exception {
        Iterable<JavaScriptFrameworkDto> frameworks = new ArrayList<>();
        Mockito.when(javaScriptFrameworkService.getAllFrameworks()).thenReturn(frameworks);
        mvc.perform(get("/frameworks"))
                .andDo(print())
                .andExpect(status().isOk());
        verify(javaScriptFrameworkService, times(1)).getAllFrameworks();
    }

    @Test
    void testGetFrameworkById() throws Exception {
        Long id = 1L;
        Mockito.when(javaScriptFrameworkService.findFrameworkById(id)).thenReturn(Optional.of(JavaScriptFrameworkDto.builder().build()));
        mvc.perform(get("/frameworks/{id}", id))
                .andDo(print())
                .andExpect(status().isOk());
        verify(javaScriptFrameworkService, times(1)).findFrameworkById(id);
    }

    @Test
    void testGetFrameworkById_noFrameworkFound() throws Exception {
        Long id = 9L;
        Mockito.when(javaScriptFrameworkService.findFrameworkById(id)).thenReturn(Optional.empty());
        mvc.perform(get("/frameworks/{id}", id))
                .andDo(print())
                .andExpect(status().isNotFound());
        verify(javaScriptFrameworkService, times(1)).findFrameworkById(id);
    }

    @Test
    void testGetFrameworkById_nonValidParam() throws Exception {
        mvc.perform(get("/frameworks/{id}", "string"))
                .andDo(print())
                .andExpect(status().isBadRequest());
        verify(javaScriptFrameworkService, never()).findFrameworkById(anyLong());
    }

    @Test
    void testSaveFramework() throws Exception {
        Mockito.when(javaScriptFrameworkService.saveFramework(any(CreateJavaScriptFrameworkDto.class))).thenReturn(Optional.of(JavaScriptFrameworkDto.builder().build()));
        mvc.perform(post("/frameworks")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(DTO_VALID)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());

        verify(javaScriptFrameworkService, times(1)).saveFramework(any(CreateJavaScriptFrameworkDto.class));
    }

    @Test
    void testSaveFramework_notSaved() throws Exception {
        Mockito.when(javaScriptFrameworkService.saveFramework(any(CreateJavaScriptFrameworkDto.class))).thenReturn(Optional.empty());
        mvc.perform(post("/frameworks")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(DTO_VALID)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isBadRequest());

        verify(javaScriptFrameworkService, times(1)).saveFramework(any(CreateJavaScriptFrameworkDto.class));
    }

    @Test
    void testSaveFramework_nonValidBody() throws Exception {
        mvc.perform(post("/frameworks")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(DTO_NON_VALID)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isBadRequest());

        verify(javaScriptFrameworkService, never()).saveFramework(any(CreateJavaScriptFrameworkDto.class));
    }


    @Test
    void testDeleteFramework() throws Exception {
        Long id = 1L;
        Mockito.when(javaScriptFrameworkService.deleteFramework(id)).thenReturn(true);
        mvc.perform(delete("/frameworks/{id}", id))
                .andDo(print())
                .andExpect(status().isOk());
        verify(javaScriptFrameworkService, times(1)).deleteFramework(id);
    }

    @Test
    void testDeleteFramework_noFrameworkFound() throws Exception {
        Long id = 9L;
        Mockito.when(javaScriptFrameworkService.deleteFramework(id)).thenReturn(false);
        mvc.perform(delete("/frameworks/{id}", id))
                .andDo(print())
                .andExpect(status().isNotFound());
        verify(javaScriptFrameworkService, times(1)).deleteFramework(id);
    }

    @Test
    void testDeleteFramework_nonValidParam() throws Exception {
        mvc.perform(delete("/frameworks/{id}", "string"))
                .andDo(print())
                .andExpect(status().isBadRequest());
        verify(javaScriptFrameworkService, never()).deleteFramework(anyLong());
    }

    @Test
    void testUpdateFramework() throws Exception {
        Mockito.when(javaScriptFrameworkService.updateFramework(any(JavaScriptFrameworkDto.class))).thenReturn(Optional.of(JavaScriptFrameworkDto.builder().build()));
        mvc.perform(put("/frameworks")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(DTO_VALID)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());

        verify(javaScriptFrameworkService, times(1)).updateFramework(any(JavaScriptFrameworkDto.class));
    }

    @Test
    void testUpdateFramework_notSaved() throws Exception {
        Mockito.when(javaScriptFrameworkService.updateFramework(any(JavaScriptFrameworkDto.class))).thenReturn(Optional.empty());
        mvc.perform(put("/frameworks")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(DTO_VALID)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isBadRequest());

        verify(javaScriptFrameworkService, times(1)).updateFramework(any(JavaScriptFrameworkDto.class));
    }

    @Test
    void testUpdateFramework_nonValidBody() throws Exception {
        mvc.perform(put("/frameworks")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(DTO_NON_VALID)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isBadRequest());

        verify(javaScriptFrameworkService, never()).updateFramework(any(JavaScriptFrameworkDto.class));
    }

    @Test
    void testGetFrameworksByHype() throws Exception {
        Iterable<JavaScriptFrameworkDto> frameworks = new ArrayList<>();
        when(javaScriptFrameworkService.getFrameworksByHype(HYPE_LEVEL)).thenReturn(frameworks);
        mvc.perform(get("/frameworks/hype")
                        .param("hypeLevel", HYPE_LEVEL))
                .andDo(print())
                .andExpect(status().isOk());
        verify(javaScriptFrameworkService, times(1)).getFrameworksByHype(HYPE_LEVEL);
    }

    @Test
    void testGetFrameworksByHype_nonExistingParam() throws Exception {
        mvc.perform(get("/frameworks/hype"))
                .andDo(print())
                .andExpect(status().isBadRequest());
        verify(javaScriptFrameworkService, never()).getFrameworksByHype(HYPE_LEVEL);
    }
}
