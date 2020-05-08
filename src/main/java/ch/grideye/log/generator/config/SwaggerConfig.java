package ch.grideye.log.generator.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.DocExpansion;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger.web.UiConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerConfig implements WebMvcConfigurer {

    @Value("${swagger.documentation.base-package}")
    private String basePackage;
    @Value("${swagger.documentation.info.title}")
    private String title;
    @Value("${swagger.documentation.info.description}")
    private String description;
    @Value("${swagger.documentation.info.version}")
    private String version;
    @Value("${swagger.documentation.info.tos}")
    private String tos;
    @Value("${swagger.documentation.info.contact.name}")
    private String contactName;
    @Value("${swagger.documentation.info.contact.url}")
    private String contactUrl;
    @Value("${swagger.documentation.info.contact.email}")
    private String contactEmail;
    @Value("${swagger.documentation.info.licence.name}")
    private String licence;
    @Value("${swagger.documentation.info.licence.url}")
    private String licenceUrl;

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage(basePackage))
                .paths(PathSelectors.any())
                .build()
                .useDefaultResponseMessages(false)
                .apiInfo(apiInfo());
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addRedirectViewController("/lg/docs/v2/api-docs", "/v2/api-docs");
        registry.addRedirectViewController("/lg/docs/swagger-resources", "/swagger-resources");
        registry.addRedirectViewController("/lg/docs/swagger-resources/configuration/ui", "/swagger-resources/configuration/ui");
        registry.addRedirectViewController("/lg/docs/swagger-resources/configuration/security", "/swagger-resources/configuration/security");
        registry.addRedirectViewController("/lg/docs", "/lg/docs/swagger-ui.html");
        registry.addRedirectViewController("/lg/docs/", "/lg/docs/swagger-ui.html");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/lg/docs/**").addResourceLocations("classpath:/META-INF/resources/");
    }

    @Bean
    UiConfiguration uiConfig() {
        return UiConfigurationBuilder.builder()
                .defaultModelsExpandDepth(-1)
                .docExpansion(DocExpansion.LIST)
                .validatorUrl(null).build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(title, description, version, tos, new Contact(contactName, contactUrl, contactEmail), licence, licenceUrl, Collections.emptyList());
    }
}

