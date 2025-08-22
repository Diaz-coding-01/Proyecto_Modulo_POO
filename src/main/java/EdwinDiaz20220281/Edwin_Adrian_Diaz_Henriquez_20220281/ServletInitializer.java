package EdwinDiaz20220281.Edwin_Adrian_Diaz_Henriquez_20220281;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

public class ServletInitializer extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(EdwinAdrianDiazHenriquez20220281Application.class);
	}

}
