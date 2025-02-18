package orm.sififorever.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.siriforever.common.annotaion.ApiRestController;
import com.siriforever.common.config.GlobalConfig;

@Configuration
@MapperScan("orm.sififorever.mapper")
public class WebConfiguration implements WebMvcConfigurer {

    @Autowired
    private GlobalConfig globalConfig;

    @Override
    public void addResourceHandlers(@SuppressWarnings("null") ResourceHandlerRegistry registry) {
        // 直接映射项目根目录下的static目录
        registry.addResourceHandler("/static/**")
                .addResourceLocations("file:./static/")
                .setCachePeriod(0);
    }

    @Override
    public void configurePathMatch(@SuppressWarnings("null") PathMatchConfigurer configurer) {
        configurer
                .addPathPrefix("/api", c -> c.isAnnotationPresent(ApiRestController.class));
    }

}
