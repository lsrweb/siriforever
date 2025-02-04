package orm.sififorever.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "orm.sififorever.repository")
public class JpaConfig {
}
