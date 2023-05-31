package sn.work.lostandfound;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class LostandfoundApplication {

	public static void main(String[] args) {
		SpringApplication.run(LostandfoundApplication.class, args);
	}
}
