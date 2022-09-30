package com.etnetera.hr.dto;

import com.etnetera.hr.domain.HypeLevelEnum;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Set;

/**
 *  DTO for Framework creation
 * @author Marketa Halikova
 */
@Data
@Builder
public class CreateJavaScriptFrameworkDto {
    @NotBlank
    private String name;
    @NotNull
    private Set<String> versions;
    private Date deprecationDate;
    @NotNull
    private HypeLevelEnum hypeLevel;
}
