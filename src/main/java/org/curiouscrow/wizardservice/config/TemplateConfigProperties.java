package org.curiouscrow.wizardservice.config;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "templatewizard")
@Getter
@Setter
public class TemplateConfigProperties {

    public static final String FILLED_TEMPLATE_FOLDER = "out";
    public static final String ZIPPED_TEMPLATE_FOLDER = "results";

    private String sourcePath;

}
