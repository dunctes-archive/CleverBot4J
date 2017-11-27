#CleverBot4J [![](https://jitpack.io/v/duncte123/CleverBot4J.svg)](https://jitpack.io/#duncte123/CleverBot4J)

CleverBot4J is a java api for [cleverbot.io](https://cleverbot.io)

##maven package
* Repository: jitpack
* Artifact: **com.github.duncte123:CleverBot4J:-SNAPSHOT**

Using in Gradle:
```groovy
repositories {
    maven {
        name 'jitpack'
        url 'https://jitpack.io'
    }
}

dependencies {
    compile 'com.github.duncte123:CleverBot4J:-SNAPSHOT'
}
```

Using in Maven:
```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>

<dependency>
    <groupId>com.github.duncte123</groupId>
    <artifactId>CleverBot4J</artifactId>
    <version>-SNAPSHOT</version>
</dependency>
```

## Usage
####Setting the api up

First you need to get an api and user key from [this page](https://cleverbot.io/keys).

When you have done that you can set the api up like this.

You can use `setKeys(user, api)` if you want to set both keys at once.

The nickname is optional

```JAVA
CleverbotAPI api = new CleverbotBuilder()
                .setUserKey("CLEVERBOT_USER_KEY")
                .setApiKey("CLEVERBOT_API_KEY")
                .setNickname("Cleverbot4J")
                .build();
```

To send questions to the bot you can use the `askQuestion` method.

```JAVA
String answer = api.askQuestion("How are you?");
```
