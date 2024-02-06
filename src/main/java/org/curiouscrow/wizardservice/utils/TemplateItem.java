package org.curiouscrow.wizardservice.utils;

import java.util.List;

public interface TemplateItem {
    boolean hasChildren();

    List<TemplateItem> children();

    String getName();

    String templateText();
}

