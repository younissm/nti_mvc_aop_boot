package project;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import project.config.AppConfig;
import project.service.InventoryService;

public class MainApp {
    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        InventoryService invService = (InventoryService)context.getBean("inventoryService");

        invService.checkStock("0120102");
        invService.reserveStock("32414", 3);
        invService.reserveStock("32414", 3);
        invService.reserveStock("32414", 399);
    }
}
