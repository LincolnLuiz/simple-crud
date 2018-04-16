# Simple Crud

Generate files in specific folders to representante your architecture.

To generate your files, create a new group, eg. API-BACKEND, define the templates , that are processed with single variable,  the ENTITY; entity is the name your business object, like Product, Client, Order...

Run the command:

```
make --group API-BACKEND --entity Product
```

Get start

All the SimpleCrud is configured by a json file, simple-crud-config.js by convention.
This file can saved in your project, he define your groups and templates, below the sample:

```
[
   {
      "title":"api-backend",
      "rootPath":"src/main/java/",
      "template":[
         {
            "path":"../resources/templates/",
            "dest":"model",
            "suffixName":".java",
            "templateName":"entidade.vm"
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
