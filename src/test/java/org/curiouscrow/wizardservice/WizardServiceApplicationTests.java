package org.curiouscrow.wizardservice;

import org.curiouscrow.wizardservice.entities.TemplateInfo;
import org.curiouscrow.wizardservice.entities.TemplateParameter;
import org.curiouscrow.wizardservice.manager.TemplateManager;
import org.curiouscrow.wizardservice.providers.TemplateProvider;
import org.curiouscrow.wizardservice.templateinfo.TemplateDescriptionReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.List;

@SpringBootTest(classes = {TestServiceConfig.class})
class WizardServiceApplicationTests {

    @Autowired
    TemplateDescriptionReader templateDescriptionReader;

    @Autowired
    TemplateManager templateManager;

    @Autowired
    TemplateProvider templateProvider;

    @Test
    void TestTemplateDescriptionLoader() throws IOException {
        List<TemplateInfo> templates = templateDescriptionReader.readTemplateStructure();
        Assertions.assertNotNull(templates);
        Assertions.assertEquals(1, templates.size());

        TemplateInfo info = templates.get(0);
        Assertions.assertEquals("Simple Java Maven Project", info.getTitle());
        Assertions.assertEquals("simple_maven", info.getFolderName());
        Assertions.assertNotNull(info.getParameters());
        Assertions.assertEquals(6, info.getParameters().size());

        TemplateParameter expectedParam = new TemplateParameter();
        expectedParam.setName("firstName");
        expectedParam.setTitle("First Name");
        expectedParam.setDescription("First name of the projects author");
        expectedParam.setString(true);

        Assertions.assertEquals(expectedParam, info.getParameters().get(0));
    }

    @Test
    void TestTemplateManager() throws IOException {

    }

    @Test
    void TestTemplateProvider() throws IOException {

    }
}
