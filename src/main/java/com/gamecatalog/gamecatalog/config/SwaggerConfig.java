package com.gamecatalog.gamecatalog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {

  @Bean
  public OpenAPI customOpenAPI() {

    Info information = new Info()
        .title("Game Catalog API")
        .version("1.0")
        .description("This is a simple Game Catalog API");

    return new OpenAPI()
        .info(information);
  }
}
