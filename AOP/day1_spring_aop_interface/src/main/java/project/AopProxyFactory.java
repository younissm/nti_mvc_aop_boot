package project;

import org.springframework.aop.Advisor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;
import project.advice.LoggingAfterAdvice;
import project.advice.LoggingBeforeAdvice;
import project.advice.LoggingThrowsAdvice;
import project.advice.TimingAdvice;
import project.service.InventoryService;
import project.service.InventoryServiceImpl;

public class AopProxyFactory {
    public static void main(String[] args) {

        InventoryService target = new InventoryServiceImpl();
        ProxyFactory factory = new ProxyFactory(target);

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
        InventoryService invService = (InventoryService) factory.getProxy();
        invService.checkStock("0120102");
        invService.reserveStock("32414", 3);
        invService.reserveStock("32414", 3);
        invService.reserveStock("32414", 399);



    }
}
