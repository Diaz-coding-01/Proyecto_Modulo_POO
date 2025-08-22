package EdwinDiaz20220281.Edwin_Adrian_Diaz_Henriquez_20220281;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EdwinAdrianDiazHenriquez20220281Application {

	public static void main(String[] args) {
		Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();
		dotenv.entries().forEach(entry ->
				System.setProperty(entry.getKey(), entry.getValue())
		);

		SpringApplication.run(EdwinAdrianDiazHenriquez20220281Application.class, args);
	}

}
