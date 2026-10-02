package project.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import project.service.InventoryService;
import project.service.InventoryServiceImpl;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan("project")
public class AppConfig {

    @Bean
    InventoryService inventoryService() {
        return new InventoryServiceImpl();
    }

}
