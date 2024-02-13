package org.curiouscrow.wizardservice.manager;

import java.io.IOException;
import java.util.Map;

/**
 * Interface implementing filling template with property values
 * (can be implemented for different template engines)
 */
public interface TemplateManager {

    /**
     * Prepares result template item string
     * @param templateName template string identifier
     * @param values template parameters values
     * @return template filled with values
     * @throws IOException common exception
     */
    String prepareTemplate(String templateName, Map<String, String> values) throws IOException;
}
