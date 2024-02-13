package org.curiouscrow.wizardservice.entities;

import lombok.Data;

import java.util.List;

/**
 * Template description Data Object
 */
@Data
public class TemplateInfo {

    private String folderName;

    private String title;

    private List<TemplateParameter> parameters;

}
