package project;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import project.config.AppConfig;
import project.service.InventoryService;

public class AopProxyFactoryBean {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        InventoryService contextInvService = (InventoryService)context.getBean("inventoryService");

        contextInvService.checkStock("0120102");
        contextInvService.reserveStock("32414", 3);
        contextInvService.reserveStock("32414", 3);
        contextInvService.reserveStock("32414", 399);
    }
}
