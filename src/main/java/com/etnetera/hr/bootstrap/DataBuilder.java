package com.etnetera.hr.bootstrap;

import com.etnetera.hr.domain.HypeLevelEnum;
import com.etnetera.hr.domain.JavaScriptFramework;
import com.etnetera.hr.repository.JavaScriptFrameworkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Set;

@RequiredArgsConstructor
@Component
public class DataBuilder implements ApplicationListener<ContextRefreshedEvent> {

    private final JavaScriptFrameworkRepository javaScriptFrameworkRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        createFrameworks();
    }

    private void createFrameworks() {
        JavaScriptFramework framework1 = JavaScriptFramework.builder()
                .name("jQuery")
                .deprecationDate(new Calendar.Builder()
                        .setDate(2020, 2, 21)
                        .build().getTime())
                .hypeLevel(HypeLevelEnum.LETDOWN)
                .versions(Set.of("3.6.0", "3.5.0"))
                .build();

        JavaScriptFramework framework2 = JavaScriptFramework.builder()
                .name("Backbone")
                .hypeLevel(HypeLevelEnum.SOLID)
                .versions(Set.of("1.4.1"))
                .build();
        JavaScriptFramework framework3 = JavaScriptFramework.builder()
                .name("VueJs")
                .hypeLevel(HypeLevelEnum.SENSATIONAL)
                .versions(Set.of("3.2.0", "3.2.1"))
                .build();
        javaScriptFrameworkRepository.save(framework1);
        javaScriptFrameworkRepository.save(framework2);
        javaScriptFrameworkRepository.save(framework3);

    }
}

