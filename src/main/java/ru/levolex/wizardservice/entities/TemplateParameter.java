package ru.levolex.wizardservice.entities;

import lombok.Data;

@Data
public class TemplateParameter {

    String name;

    String type;

    String title;

    String description;

    String value;
}
