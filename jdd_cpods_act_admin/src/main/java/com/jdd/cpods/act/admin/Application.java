package com.jdd.cpods.act.admin;

import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySources;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfigurationExcludeFilter;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.TypeExcludeFilter;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

import static org.springframework.core.env.StandardEnvironment.SYSTEM_ENVIRONMENT_PROPERTY_SOURCE_NAME;
import static org.springframework.core.env.StandardEnvironment.SYSTEM_PROPERTIES_PROPERTY_SOURCE_NAME;

@NacosPropertySources({
        @NacosPropertySource(
                name = "db",
                dataId = "jdd_cpods_act",
                groupId = "db",
                first = true,
                before = SYSTEM_PROPERTIES_PROPERTY_SOURCE_NAME,
                after = SYSTEM_ENVIRONMENT_PROPERTY_SOURCE_NAME,
                autoRefreshed = true
        ),
        @NacosPropertySource(
                name = "common",
                dataId = "jdd_cpods_act",
                groupId = "common",
                first = true,
                before = SYSTEM_PROPERTIES_PROPERTY_SOURCE_NAME,
                after = SYSTEM_ENVIRONMENT_PROPERTY_SOURCE_NAME,
                autoRefreshed = true
        ),
        @NacosPropertySource(
                name = "redis",
                dataId = "jdd_cpods_act",
                groupId = "redis",
                first = true,
                before = SYSTEM_PROPERTIES_PROPERTY_SOURCE_NAME,
                after = SYSTEM_ENVIRONMENT_PROPERTY_SOURCE_NAME,
                autoRefreshed = true
        )
})
@SpringBootApplication
@ComponentScan(
        basePackages = {
                "com.jdd.cpods.act"
        },
        excludeFilters = {
                @ComponentScan.Filter(
                        type = FilterType.CUSTOM,
                        classes = {TypeExcludeFilter.class}
                ),
                @ComponentScan.Filter(
                        type = FilterType.CUSTOM,
                        classes = {AutoConfigurationExcludeFilter.class}
                ),
                @ComponentScan.Filter(
                        type = FilterType.REGEX,
                        pattern = "com.jdd.cpods.act.api.swagger.*"
                ),
                @ComponentScan.Filter(
                        type = FilterType.REGEX,
                        pattern = "org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration"
                )
        }
)
@EnableCaching
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
