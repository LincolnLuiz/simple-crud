# Simple Crud

Generate files in specific folders to representante your architecture.

To generate your files, create a new group, eg. API-BACKEND, define the templates , that are processed with single variable,  the ENTITY; entity is the name your business object, like Product, Client, Order...

Run the command:

```
make --group API-BACKEND --entity Product
```

## Get started

##### Maven

```
<build>s
    <plugins>
        <plugin>
            <groupId>com.github.lincolnluiz</groupId>
            <artifactId>simplecrud-maven-plugin</artifactId>
            <version>0.0.1-SNAPSHOT</version>
            <configuration>
            	<setupFile>src/main/resources/simple-crud-setup.json</setupFile>
            </configuration>
        </plugin>
    </plugins>
</build>
```

All the SimpleCrud is configured by a json file, simple-crud-config.js by convention.
This file can saved in your project, he define your groups and templates, below the sample:

```
[
   {
      "title":"api-backend",
      "rootPath":"src/main/java/",
      "templates":[
         {
            "templatePath":"../resources/templates/entidade.vm",
            "dest":"model/{{entity.lower}}/",
            "finalName":"{{entity}}.java"
         }
      ]
   }
]
```

This file instructs that you has:

- A unique group called `api-backend`;
- The root path `src/min/java`;
- A list of templates;

### Group

The section define a collection of templates, has two attributes:

- Title: title of group, it is used eg. to execute him.
- rootPath: Define the location for all operations of group.

### Templates
