package ru.levolex.wizardservice;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.levolex.wizardservice.entities.TemplateInfo;
import ru.levolex.wizardservice.services.TemplateService;
import ru.levolex.wizardservice.utils.TemplateUtils;

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
        Assertions.assertDoesNotThrow(service::readTemplateStructure);
    }

    @Test
    void testTemplateParamsForm() throws IOException, URISyntaxException {
        TemplateService service = new TemplateService();
        List<TemplateInfo> templates = service.readTemplateStructure();
        Assertions.assertEquals(1, templates.size());

        URL templateURL = getClass().getClassLoader().getResource("templates/templateForm.mustache");
        Assertions.assertNotNull(templateURL);
        String templateStr = Files.readString(Paths.get(templateURL.toURI()));
        logger.info(templateStr);

        String resultForm = TemplateUtils.fillTemplateByValues(templateStr, templates.get(0));
        logger.info(resultForm);
    }
}
