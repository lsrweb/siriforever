package orm.sififorever;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

@SpringBootApplication
@ComponentScans({
        @ComponentScan("orm.sififorever"),
        @ComponentScan("com.siriforever.common"),
        @ComponentScan("com.siriforever.framework")
})
public class MainAppSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(MainAppSpringApplication.class, args);
        System.out.println("O(∩_∩)O, SpringBoot启动成功！");
    }

}
