package org.curiouscrow.wizardservice.providers;

import java.util.List;

/**
 * Interface abstraction for template unit
 */
public interface TemplateItem {
    /**
     * Check if item has any children
     * @return true if children() method returns non-empty list
     * */
    boolean hasChildren();

    /**
     * Get list of children items
     * @return list of children items, if no - returns empty list
     * */
    List<TemplateItem> children();

    /**
     *  Get template name
     * @return template string identifier (e.g. template folder or file)
     * */
    String getName();

    /** Get template text
     * @return template text data
     * */
    String templateText();
}

