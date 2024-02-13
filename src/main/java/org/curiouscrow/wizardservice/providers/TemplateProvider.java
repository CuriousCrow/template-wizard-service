package org.curiouscrow.wizardservice.providers;

/**
 * Interface encapsulating template loading process
 */
public interface TemplateProvider {

    /** Load template by name
     * @return root template item
     * */
    TemplateItem loadTemplate(String templateName);
}
