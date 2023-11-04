package omnihealth.api.infra.springdoc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class SpringDocConfigurations {

	@Bean
	OpenAPI customOpenAPI() {
		return new OpenAPI()
				.components(new Components().addSecuritySchemes("bearer-key",
						new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")))
				.info(new Info().title("OmniHealth API").description(
						"Rest API of the OmniHealth application, containing CRUD functionalities for doctors and patients, as well as scheduling and canceling appointments.")
						.contact(new Contact().name("Backend Team").email("backend@omni.health"))
						.license(new License().name("Apache 2.0").url("http://omnihealth/api/license")));
	}

}
