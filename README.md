# Metadata-API

[![Metadata-API CI](https://github.com/InseeFr/Metadata-API/actions/workflows/ci.yml/badge.svg)](https://github.com/InseeFr/Metadata-API/actions/workflows/ci.yml)
[![CII Best Practices](https://bestpractices.coreinfrastructure.org/projects/3702/badge)](https://bestpractices.coreinfrastructure.org/projects/3702)

An API specification for accessing statistical metadata

## Contribute to Metadata-API

If you contribute to Metadata-API, you may fall in troubles with IntelliJ because the module [`metadata-api-interface`](./interface)
contains generated source code which can be ignored by IntelliJ and cause failures in `metadata-api-impl` builds. To prevent this, 
the directory `interface/target/generated-sources/openapi/src/main/java` must be marked as a _Generated Sources Root_. This can be done either :
- by executing the action "Reload All Maven Projects" : the directory is marked automatically
- by marking the directory manually : right-click on the directory in project explorer > "Mark directory as"
