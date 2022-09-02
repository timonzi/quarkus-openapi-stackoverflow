## Description

Based on code-with-quarkus project with quarkus-smallrye-openapi (+ RESTEasy Jackson)

The build fails with Quarkus version 2.11.1.Final or higher. With version 2.10.3.Final it works fine.

It has somehow to do with the class DateType and its @Schema annotation, respectively more specific the Date class.

```
@Schema(implementation = Date.class, name = "DateType")
```

When using for example String.class as implementation property, the project builds again successfully.
This is also the case when using for example String.class as generic parameter.

Example (works fine):
```
@Schema(implementation = Date.class, name = "DateType")
public class DateType extends Type<String> {

    public String getDefaultValue() {
        return null;
    }

    @Override
    public int compareTo(final Type<String> o) {
        return 0;
    }
}
```

But when using Date as generic parameter and as implementation property

```
@Schema(implementation = Date.class, name = "DateType")
public class DateType extends Type<Date> {

    public Date getDefaultValue() {
        return null;
    }

    @Override
    public int compareTo(final Type<Date> o) {
        return 0;
    }
}
```

the build fails with

```
[ERROR] Failed to execute goal io.quarkus.platform:quarkus-maven-plugin:2.12.0.Final:build (default) on project code-with-quarkus: Failed to build quarkus application: io.quarkus.builder.BuildException: Build failure: Build failed due to errors
[ERROR] 	[error]: Build step io.quarkus.smallrye.openapi.deployment.SmallRyeOpenApiProcessor#build threw an exception: java.lang.StackOverflowError
[ERROR] 	at io.smallrye.openapi.api.util.FilterUtil.filterSchema(FilterUtil.java:371)
[ERROR] 	at io.smallrye.openapi.api.util.FilterUtil.filter(FilterUtil.java:109)
[ERROR] 	at io.smallrye.openapi.api.util.FilterUtil.filterSchema(FilterUtil.java:378)
[ERROR] 	at io.smallrye.openapi.api.util.FilterUtil.filter(FilterUtil.java:109)
[ERROR] 	at io.smallrye.openapi.api.util.FilterUtil.filterSchema(FilterUtil.java:378)
[ERROR] 	at io.smallrye.openapi.api.util.FilterUtil.filter(FilterUtil.java:109)
[ERROR] 	at io.smallrye.openapi.api.util.FilterUtil.filterSchema(FilterUtil.java:378)
[ERROR] 	at io.smallrye.openapi.api.util.FilterUtil.filter(FilterUtil.java:109)
[ERROR] 	at io.smallrye.openapi.api.util.FilterUtil.filterSchema(FilterUtil.java:378)
[ERROR] 	at io.smallrye.openapi.api.util.FilterUtil.filter(FilterUtil.java:109)
[ERROR] 	at io.smallrye.openapi.api.util.FilterUtil.filterSchema(FilterUtil.java:378)
...
```