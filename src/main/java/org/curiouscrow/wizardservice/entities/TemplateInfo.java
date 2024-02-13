package org.curiouscrow.wizardservice.entities;

import lombok.Data;

import java.util.List;

/**
 * Template description Data Object
 */
@Data
public class TemplateInfo {

    /** Template folder */
    private String folderName;

    /** Template human-readable name */
    private String title;

    /** Template custom parameters */
    private List<TemplateParameter> parameters;

}
