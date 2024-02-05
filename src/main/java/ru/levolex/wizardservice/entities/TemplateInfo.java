package ru.levolex.wizardservice.entities;

import lombok.Data;

import java.util.List;

@Data
public class TemplateInfo {

    private String folderName;

    private String title;

    private List<TemplateParameter> parameters;

}
