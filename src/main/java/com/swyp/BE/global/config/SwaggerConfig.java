package com.swyp.BE.global.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {


    /**
     * OpenAPI 3.0 문서 설정
     *
     * @return OpenAPI 객체
     */
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(apiInfo());
    }

    /**
     * API 문서의 기본 정보 생성
     *
     * @return 제목, 설명, 버전이 포함된 Info 객체
     */
    private Info apiInfo() {
        return new Info()
                .title("ca.pick API")
                .description("ca.pick 백엔드 API 문서")
                .version("1.0.0");
    }
}
