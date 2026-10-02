package project.config;

import org.springframework.aop.Advisor;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import project.advice.LoggingAfterAdvice;
import project.advice.LoggingBeforeAdvice;
import project.advice.LoggingThrowsAdvice;
import project.advice.TimingAdvice;
import project.service.InventoryServiceImpl;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan("project")
public class AppConfig {

    @Bean
    InventoryServiceImpl inventoryServiceTarget() {
        return new InventoryServiceImpl();
    }

    @Bean
    LoggingBeforeAdvice loggingBeforeAdvice() {
        return new LoggingBeforeAdvice();
    }

    @Bean
    LoggingAfterAdvice loggingAfterAdvice() {
        return new LoggingAfterAdvice();
    }


    @Bean
    LoggingThrowsAdvice loggingThrowsAdvice() {
        return new LoggingThrowsAdvice();
    }

    @Bean
    TimingAdvice timingAdvice() {
        return new TimingAdvice();
    }

    @Bean
    public ProxyFactoryBean inventoryService() {
        ProxyFactoryBean factory = new ProxyFactoryBean();
        factory.setTarget(inventoryServiceTarget());

        NameMatchMethodPointcut checkStockPointcut = new NameMatchMethodPointcut();
        checkStockPointcut.setMappedName("checkStock");
        Advisor checkStockAfterAdvisor = new DefaultPointcutAdvisor(checkStockPointcut, new LoggingAfterAdvice());


        NameMatchMethodPointcut reserveStockPointcut = new NameMatchMethodPointcut();
        reserveStockPointcut.setMappedName("reserveStock");
        Advisor reserveStockBeforeAdvisor = new DefaultPointcutAdvisor(reserveStockPointcut, new LoggingBeforeAdvice());


        factory.addAdvisor(reserveStockBeforeAdvisor);
        factory.addAdvisor(checkStockAfterAdvisor);
        factory.addAdvice(new LoggingThrowsAdvice());
        factory.addAdvice(new TimingAdvice());


        return factory;
    }

}
