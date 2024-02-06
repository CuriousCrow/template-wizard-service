package org.curiouscrow.wizardservice;

import org.curiouscrow.wizardservice.services.TemplateService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
class WizardServiceApplicationTests {

    @Test
    void TestTemplateLoader() throws IOException {
        TemplateService service = new TemplateService();
        service.descriptionReader.readTemplateStructure();
    }

}
