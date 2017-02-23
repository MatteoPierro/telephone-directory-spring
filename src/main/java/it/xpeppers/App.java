package it.xpeppers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class App {

  @Bean
  public ContactRepository contactRepository(){
    return new InMemoryContactRepository();
  }

  public static void main(String[] args) throws Exception {
    SpringApplication.run(App.class, args);
  }
}