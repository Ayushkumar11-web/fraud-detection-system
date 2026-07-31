package fraud_detection.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI fraudDetectionOpenAPI() {

        final String securitySchemeName = "Bearer Authentication";

        return new OpenAPI()

                .info(new Info()
                        .title("Fraud Detection System API")
                        .version("1.0")
                        .description("REST API Documentation for Fraud Detection System")

                        .contact(new Contact()
                                .name("Ayush Kumar")
                                .email("ayush@example.com")
                        )

                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0")
                        )
                )

                .addSecurityItem(
                        new SecurityRequirement()
                                .addList(securitySchemeName)
                )

                .schemaRequirement(
                        securitySchemeName,
                        new SecurityScheme()
                                .name(securitySchemeName)
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")
                );
    }
}