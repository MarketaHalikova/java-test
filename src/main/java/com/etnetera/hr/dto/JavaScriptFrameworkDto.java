package com.etnetera.hr.dto;

import com.etnetera.hr.domain.HypeLevelEnum;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Set;

@Data
@Builder
public class JavaScriptFrameworkDto {
    @NotNull
    private Long id;
    private String name;
    private Set<String> versions;
    private Date deprecationDate;
    private HypeLevelEnum hypeLevel;
}
