package cl.gvidal.neoris;

import cl.gvidal.neoris.model.Roles;
import cl.gvidal.neoris.repository.IRolesRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
@SpringBootApplication
public class SpringSecurityJwtApplication {

	public static void main(String[] args) {

		ApplicationContext context = SpringApplication.run(SpringSecurityJwtApplication.class, args);

	}

}
