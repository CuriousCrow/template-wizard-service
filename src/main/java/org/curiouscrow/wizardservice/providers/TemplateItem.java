package org.curiouscrow.wizardservice.providers;

import java.util.List;

/**
 * Interface abstraction for template unit
 */
public interface TemplateItem {
    boolean hasChildren();

    List<TemplateItem> children();

    String getName();

    String templateText();
}

