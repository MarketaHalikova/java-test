package com.etnetera.hr.mapper;

import com.etnetera.hr.domain.JavaScriptFramework;
import com.etnetera.hr.dto.CreateJavaScriptFrameworkDto;
import com.etnetera.hr.dto.JavaScriptFrameworkDto;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

/**
 * Framework mapper
 *
 * @author Marketa Halikova
 */
@Mapper(componentModel = "spring")
public interface FrameworkMapper {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFrameworkFromDto(JavaScriptFrameworkDto dto, @MappingTarget JavaScriptFramework entity);

    JavaScriptFramework createDtoToEntity(CreateJavaScriptFrameworkDto dto);

    JavaScriptFrameworkDto entityToDto(JavaScriptFramework entity);
}
