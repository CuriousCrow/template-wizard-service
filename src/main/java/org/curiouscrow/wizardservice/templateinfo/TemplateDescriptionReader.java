package org.curiouscrow.wizardservice.templateinfo;

import org.curiouscrow.wizardservice.entities.TemplateInfo;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Interface for reading templates description structure
 */
public interface TemplateDescriptionReader {

    /** Retrieve template info list
     * @return list of all provided templates
     * @throws IOException commont exception
     * */
    List<TemplateInfo> readTemplateStructure() throws IOException;

    /** Retrieve template info map
     * @return map of all provided template with template name as a map key
     * @throws IOException common exception
     * */
    Map<String, TemplateInfo> readTemplateMap() throws IOException;
}
