package com.rev;

import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.rev.service.JdbcOp;

@SpringBootApplication
public class MyDbModifyApplication implements CommandLineRunner{

	@Autowired
	private JdbcOp op;
	
	public static void main(String[] args) {
		SpringApplication.run(MyDbModifyApplication.class, args);
		 
	}
	
	@Override
    public void run(String... args) throws InterruptedException, ExecutionException {
    	op.runjdbc();
    }
    
}
