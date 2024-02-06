package org.curiouscrow.wizardservice;

import org.curiouscrow.wizardservice.services.TemplateService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
class WizardServiceApplicationTests {

//    @Test
//    void contextLoads() {
//    }


    @Test
    void testTemplateLoader() throws IOException {
        TemplateService service = new TemplateService();
        service.readTemplateStructure();
    }

}
