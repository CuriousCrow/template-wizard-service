package org.curiouscrow.wizardservice;

import org.curiouscrow.wizardservice.entities.TemplateInfo;
import org.curiouscrow.wizardservice.services.TemplateService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Logger;

public class TemplateLoaderTests {

    private static final Logger logger = Logger.getLogger(TemplateLoaderTests.class.getName());

    @Test
    public void TestTemplateListLoading() {
        TemplateService service = new TemplateService();
        Assertions.assertDoesNotThrow(service.descriptionReader::readTemplateStructure);
    }

    @Test
    void TestTemplateParamsForm() throws IOException, URISyntaxException {
        TemplateService service = new TemplateService();
        List<TemplateInfo> templates = service.descriptionReader.readTemplateStructure();
        Assertions.assertEquals(1, templates.size());

        URL templateURL = getClass().getClassLoader().getResource("templates/templateForm.mustache");
        Assertions.assertNotNull(templateURL);
        String templateStr = Files.readString(Paths.get(templateURL.toURI()));
        logger.info(templateStr);

//        String resultForm = TemplateUtils.fillTemplateByValues(templateStr, templates.get(0));
//        logger.info(resultForm);
    }
}
