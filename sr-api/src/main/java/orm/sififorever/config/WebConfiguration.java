package orm.sififorever.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.siriforever.common.annotaion.ApiRestController;

@Configuration
@MapperScan("orm.sififorever.mapper")
public class WebConfiguration implements WebMvcConfigurer {

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
