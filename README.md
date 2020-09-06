CommandFramework
---

The simplest command framework for Bukkit & Bungee!

This is the old command framework from sk89q's WorldEdit. It has been factored out and modified so it may be used in
other projects without having to include WorldEdit as a dependency.

## Usage
This project is available for anyone to use, and you may implement it with ease:

**Maven Repository**
```xml
<repository>
    <id>bgm</id>
    <url>https://maven.bgmp.cl/repo/</url>
</repository>
```

**Bukkit**
```xml
<!-- Bukkit -->
<dependency>
    <groupId>com.sk89q</groupId>
    <artifactId>command-framework-bukkit</artifactId>
    <version>1.0.2-SNAPSHOT</version>
</dependency>
```

**Bungee**
```xml
<dependency>
    <groupId>com.sk89q</groupId>
    <artifactId>command-framework-bungee</artifactId>
    <version>1.0.2-SNAPSHOT</version>
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
