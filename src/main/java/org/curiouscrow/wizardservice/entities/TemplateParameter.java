package org.curiouscrow.wizardservice.entities;

import lombok.Data;

import java.util.List;

/**
 * Template parameter Data Object
 */
@Data
public class TemplateParameter {

    String name;

    String title;

    String description;

    boolean isRequired;

    String value;

    List<String> options;

    //Types

    boolean isString;

    boolean isText;

    boolean isCheckbox;

    boolean isOptions;

    boolean isRadio;

    boolean isNumber;

}
