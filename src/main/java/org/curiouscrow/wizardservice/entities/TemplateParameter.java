package org.curiouscrow.wizardservice.entities;

import lombok.Data;

import java.util.List;

/**
 * Template parameter Data Object
 */
@Data
public class TemplateParameter {

    /** html-form input name */
    String name;

    /** html-label for parameter */
    String title;

    /** detailed parameter info (can be used in input placeholder) */
    String description;

    /** used to mark form input as required */
    boolean isRequired;

    /** selected parameter value */
    String value;

    /** used for combobox and radio-buttons */
    List<String> options;

    //Types

    /** mark parameter as a single-line string */
    boolean isString;

    /** mark parameter as a multiline string */
    boolean isText;

    /** mark parameter as a boolean */
    boolean isCheckbox;

    /** mark parameter as a combobox */
    boolean isOptions;

    /** mark parameter as a set of radio-buttons */
    boolean isRadio;

    /** mark parameter as a number input */
    boolean isNumber;

}
