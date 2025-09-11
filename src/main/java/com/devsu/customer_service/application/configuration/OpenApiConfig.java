package es.nexia.agent_configuration_microservices.application.configuration.documentation;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

@Configuration
@PropertySource("classpath:${openapi.language}")
class OpenApiConfig {

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	@Bean
	@ConfigurationProperties(prefix = "openapi")
	public OpenAPI openAPI() throws IOException, XmlPullParserException {
		MavenXpp3Reader reader = new MavenXpp3Reader();
		Model model = reader.read(
		  new InputStreamReader(
			Objects.requireNonNull(getClass().getClassLoader()
			  .getResourceAsStream("META-INF/maven/com.devsu/customer-service/pom.xml")),
			StandardCharsets.UTF_8
		  )
		);

		String projectVersion = model.getVersion();

		return new OpenAPI()
		  .info(new Info().version(projectVersion))
		  .components(new Components());
	}

}