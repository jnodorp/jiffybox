**This project has been archived on May 30th 2024, alongside the JiffyBox product. See the
[official statement by DomainFactory](https://www.df.eu/de/support/df-faq/cloudserver/jiffybox-abschaltung/) (German).**

[![Build Status](https://github.com/jnodorp/jiffybox/actions/workflows/ci.yaml/badge.svg)](https://github.com/jnodorp/jiffybox/actions/workflows/ci.yaml)
[![Coverage Status](https://coveralls.io/repos/jnodorp/jiffybox/badge.svg?branch=main&service=github)](https://coveralls.io/github/jnodorp/jiffybox?branch=main)

# JiffyBox API
Java implementation of the JiffyBox API.

## Introduction
Cloud servers on demand by JiffyBox are virtual servers which's resources are not fixed but coming from a large pool, the "cloud". Without an inflexible price corset and without a minimal runtime the billing is calculated by actual usage only. Further information on features and pricing of JiffyBoxes can be found at the JiffyBox website:

- For German customers: http://www.df.eu/de/cloud-hosting/cloud-server/
- For Austrian customers: http://www.df.eu/at/cloud-hosting/cloud-server/

A complete documentation on the API's features can be found at http://www.df.eu/fileadmin/media/doc/jiffybox-api-dokumentation.pdf

For the javadoc look at http://jnodorp.github.io/jiffybox/

This project is a Java implementation of the JiffyBox API. It is licensed under the MIT License.

## Usage

For details on how to import this package into your project see the published package versions here:
https://github.com/jnodorp/jiffybox/packages

Basic usage is
```java
import io.github.jnodorp.jiffybox.JiffyBoxApi;
import io.github.jnodorp.jiffybox.models.Backup;
import io.github.jnodorp.jiffybox.models.Message;
import io.github.jnodorp.jiffybox.models.Response;
import io.github.jnodorp.jiffybox.modules.ModuleBackups;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Main {

    /**
     * Demonstrate the basic usage of the JiffyBox API.
     */
    public static void main(String args[]) throws IOException {
        // Get an API instance using the <Your API token> token.
        JiffyBoxApi api = new JiffyBoxApi("<Your API token>");

        // Get a module (e. g. backups).
        ModuleBackups backups = api.getModuleBackups();

        // Make a call to the module (e. g. requesting a list of all backups).
        Response<Map<String, Backup>> response = backups.getBackups();

        // Get the messages sent by the API.
        List<Message> messages = response.getMessages();

        // Get the result sent by the API.
        Map<String, Backup> result = response.getResult();
    }
}
```

Make sure to handle `IOExceptions` which occur when either an API limit is exceeded or the API server is unreachable for whatever reason.

For further examples see the test cases.

## Testing
The test cases are written using WireMock (http://wiremock.org/) by Tom Akehurst (http://www.tomakehurst.com/about/). To add or examine test cases there has to be a mapping within the src/test/resources/mappings folder (or a sub folder).
