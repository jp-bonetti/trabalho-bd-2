package org.satc.sistemamrp.configs;

import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new io.swagger.v3.oas.models.info.Info()
                        .title("Sistema MRP API")
                        .version("1.0.0")
                        .description("API para o Sistema MRP (Material Requirements Planning)"));
    }
}
