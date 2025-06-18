package dev.caobaoqi.backend.web;

import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

@Configuration
public class JacksonConfiguration implements Ordered {

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {
        return BaseJacksonUtil.CUSTOMIZER;
    }

    /**
     * 配置优先级，在默认 Jackson 后面，这样 @JsonFormat 注解才能生效
     *
     * @return order
     */
    @Override
    public int getOrder() {
        return -1;
    }

}
