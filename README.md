CommandFramework
---

The simplest command framework for Bukkit & Bungee 1.8+.

This is the old command framework from sk89q's WorldEdit. It has been factored out and modified, so it may be used in
other projects without having to include WorldEdit as a dependency.

The groupId of the project has been changed to prevent clashes with WorldEdit's command utility classes, given that
it is usually present in upstream Minecraft servers.

## Usage
This project is available for anyone to use. In order to include this project as a dependency, you must
[authenticate to GitHub Packages](https://docs.github.com/en/packages/working-with-a-github-packages-registry/working-with-the-apache-maven-registry#authenticating-to-github-packages)
via your Maven `settings.xml`.

After you accomplish that, simply add the repository and dependencies as follows:

**Maven Repository**
```xml
<repository>
    <id>github</id>
    <url>https://maven.pkg.github.com/BGMP/CommandFramework</url>
</repository>
```

**Bukkit**
```xml
<dependency>
    <groupId>cl.bgm</groupId>
    <artifactId>command-framework-bukkit</artifactId>
    <version>1.0.4-SNAPSHOT</version>
</dependency>
```

**Bungee**
```xml
<dependency>
    <groupId>cl.bgm</groupId>
    <artifactId>command-framework-bungee</artifactId>
    <version>1.0.4-SNAPSHOT</version>
</dependency>
```

Documentation is still being worked on, but for a hands-on example I have taken care of arranging a
[simple plugin](https://github.com/BGMP/CommandFramework/tree/master/example-commands-bukkit) to illustrate
the framework's features and usage.

## Prerequisites
* Java 8 or above
* [Maven](http://maven.apache.org/) (Dependency Management)

## Compiling
Clone the repository, and launch your command prompt within it to run the following Maven command:

  > `mvn clean package`

## Contributing
Your submissions have to be licenced under the GNU General Public Licence v3.
