package com.example.program23;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@SpringBootApplication
public class MyApp {

    @Autowired
    private ApplicationContext context;

    public static void main(String[] args) {
        SpringApplication.run(MyApp.class, args);
    }

    @Bean
    public MyBean myBean() {
        return new MyBean();
    }

    @Component
    public static class MyComponent {
        @PostConstruct
        public void init() {
            System.out.println("Створено MyComponent");
        }
    }

    @Controller
    public static class MyController {
        @PostConstruct
        public void init() {
            System.out.println("MyController створено");
        }
    }

    @Repository
    public static class MyRepository {
        @PostConstruct
        public void init() {
            System.out.println("Створено MyRepository");
        }
    }

    @Service
    public static class MyService {
        @PostConstruct
        public void init() {
            System.out.println("Створено MyService");
        }
    }

    @Bean(name = "myBean2")
    public MyBean myBean2() {
        return new MyBean();
    }

    public void showBeans() {
        String[] beans = context.getBeanDefinitionNames();
        for (String bean : beans) {
            System.out.println(bean);
        }
    }

    public void accessBeans() {
        MyBean bean1 = context.getBean(MyBean.class);
        MyComponent component = context.getBean(MyComponent.class);
        MyController controller = context.getBean(MyController.class);
        MyRepository repository = context.getBean(MyRepository.class);
        MyService service = context.getBean(MyService.class);
        MyBean bean2 = (MyBean) context.getBean("myBean2");

        System.out.println(bean1);
        System.out.println(component);
        System.out.println(controller);
        System.out.println(repository);
        System.out.println(service);
        System.out.println(bean2);
    }

}

class MyBean {
    @PostConstruct
    public void init() {
        System.out.println("MyBean created");
    }
}
