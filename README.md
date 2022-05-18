# cameo-risk-plugin
Risk plugin for Cameo

## Required tools
* GIT: https://community.chocolatey.org/packages/git#testingResults
* JDK 11: https://adoptium.net/
* Maven: https://community.chocolatey.org/packages/maven
* Cameo 2021x

For windows I highly recommend to use the package manager Chocolatey: https://chocolatey.org/

## Build 

* Clone the project using git
* Modify magicdraw.home in pom.xml so that it's pointing to your local MagicDraw/Cameo directory
* Execute maven command: mvn package

## Edit

### IntelliJ

#### How to configure javadoc

* Project -> Open Module Settings
* Modules -> Riskanalyzer
* Right mouse click on jar file -> Edit 
* Press add button
* Select javadoc in cameo_home/openapi/docs/javadoc.zip
* Select type as javadoc


