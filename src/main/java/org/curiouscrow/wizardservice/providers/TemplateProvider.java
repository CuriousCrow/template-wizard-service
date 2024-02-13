package org.curiouscrow.wizardservice.providers;

/**
 * Interface encapsulating template loading process
 */
public interface TemplateProvider {

    TemplateItem loadTemplate(String templateName);
}
