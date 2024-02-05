package ru.levolex.wizardservice;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import ru.levolex.wizardservice.entities.TemplateInfo;
import ru.levolex.wizardservice.services.TemplateService;

import java.io.IOException;
import java.util.List;

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
