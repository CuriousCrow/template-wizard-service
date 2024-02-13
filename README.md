# Welcome to Template Wizard Service (by Lev Alekseevskiy)

Template Wizard Service - a Spring Boot library for quickly creating a service of automatic wizard pages and creating parameterizable templates.

## Installation
To use the functionality of the template service in your Spring Boot project:
- add dependency to Maven/Gradle project configuration file:
```xml
<dependency>
    <groupId>org.curiouscrow</groupId>
    <artifactId>template-wizard-service</artifactId>
    <version>0.0.1-SNAPSHOT</version>
</dependency>
```

- add the @EnableTemplateWizardService annotation to any configuration bean in your project
```java
@SpringBootApplication
@EnableTemplateWizardService
public class DemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}
```

## Template configuration settings
The following application settings (`application.properties`) are used to configure the library:

`templatewizard.source-path` - used to set up path to user-generated templates

`templatewizard.descriptor-file` - templates info configuration document

`templatewizard.template-form` - custom html-page mustache template for customizing user template parameters

`templatewizard.result-link-form` - custom html-page mustache template for template download link

`templatewizard.api.enabled` - enable REST api service endpoints

### User workflow
- user requests template customization html form (GET `/templates/form/<templateName>`)

- user requests template preparation sending in the same time required template parameters values (POST `/templates/prepare/<templateName>`). Service returns result-link-form page with download link.
- user requests resulting zip-template file (GET `/templates/get/<Generated template identifier>`)
Generated zip-template file would be available during next 30 seconds, then it will be deleted by special regual task DeleteOldTemplatesTask.

Template info configuration json file (specified in `templatewizard.descriptor-file` property) should correspond `org.curiouscrow.wizardservice.entities.TemplateInfo`:

Example:
```json
[
  {
    "folderName": "simple_maven",
    "title": "Simple java Spring project",
    "parameters": [
      {
        "name": "firstName",
        "title": "First Name",
        "description": "First name of the projects author",
        "isString": true
      },
      {
        "name": "lastName",
        "title": "Last Name",
        "description": "Last name of the projects author",
        "isString": true
      },
      {
        "name": "cv",
        "title": "Curriculum vitae",
        "description": "Add your small Bio",
        "isText": true
      },
      {
        "name": "buildingSystem",
        "title": "Building System",
        "description": "Building system",
        "isOptions": true,
        "options": [
          "Maven",
          "Gradle"
        ]
      },
      {
        "name": "language",
        "title": "Programming language",
        "isRadio": true,
        "options": [
          "Java",
          "Kotlin",
          "Go"
        ]
      },
      {
        "name": "includeTest",
        "title": "Include dummy test classes",
        "isCheckbox": true
      }
    ]
  }
]
```