package hu.tb;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

public class ServletInitializer extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(BackendApplication.class);
	}

    /**
     * Created by Tivadar Bocz on 2016.09.02..
     */
    @RestController
    public static class TestController {

        @RequestMapping("/test")
        public String getUnsecuredContent() {
            return "It works";
        }

        @RequestMapping("/test2")
        public String getSecuredContent() {
            return "It is secured content!";
        }
    }
}
