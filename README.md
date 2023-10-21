# egc

<div align="left">
    <a href="https://speakeasyapi.dev/"><img src="https://custom-icon-badges.demolab.com/badge/-Built%20By%20Speakeasy-212015?style=for-the-badge&logoColor=FBE331&logo=speakeasy&labelColor=545454" /></a>
    <a href="https://github.com/speakeasy-sdks/egc.git/actions"><img src="https://img.shields.io/github/actions/workflow/status/speakeasy-sdks/bolt-php/speakeasy_sdk_generation.yml?style=for-the-badge" /></a>
    
</div>

<!-- Start SDK Installation -->
## SDK Installation

### Gradle

```groovy
implementation 'au.gov.amsa.egc.egc_client:egc:0.9.0'
```
<!-- End SDK Installation -->

## SDK Example Usage
<!-- Start SDK Example Usage -->
```java
package hello.world;

import au.gov.amsa.egc.egc_client.Egc;
import au.gov.amsa.egc.egc_client.models.operations.CancelMsiRequest;
import au.gov.amsa.egc.egc_client.models.operations.CancelMsiResponse;
import au.gov.amsa.egc.egc_client.models.shared.Security;

public class Application {
    public static void main(String[] args) {
        try {
            Egc sdk = Egc.builder()
                .setSecurity(new Security("string"){{
                    bearer = "";
                }})
                .build();

            CancelMsiRequest req = new CancelMsiRequest("289ee192-fdf5-4070-befc-3bf7291c1386");            

            CancelMsiResponse res = sdk.egc.cancelMsi(req);

            if (res.statusCode == 200) {
                // handle response
            }
        } catch (Exception e) {
            // handle exception
        }
    }
}
```
<!-- End SDK Example Usage -->

<!-- Start SDK Available Operations -->
## Available Resources and Operations

### [Egc SDK](docs/sdks/egc/README.md)

* [cancelMsi](docs/sdks/egc/README.md#cancelmsi) - Cancel MSI
* [createMsi](docs/sdks/egc/README.md#createmsi) - Create MSI
* [getMsi](docs/sdks/egc/README.md#getmsi) - Get MSI
* [getMsiStatus](docs/sdks/egc/README.md#getmsistatus) - Get status of an MSI
* [getMsis](docs/sdks/egc/README.md#getmsis) - List MSIs
* [getToken](docs/sdks/egc/README.md#gettoken) - Authenticate
* [sendMsi](docs/sdks/egc/README.md#sendmsi) - Send MSI
<!-- End SDK Available Operations -->



<!-- Start Dev Containers -->

<!-- End Dev Containers -->

<!-- Placeholder for Future Speakeasy SDK Sections -->



### Maturity

This SDK is in beta, and there may be breaking changes between versions without a major version update. Therefore, we recommend pinning usage
to a specific package version. This way, you can install the same version each time without breaking changes unless you are intentionally
looking for the latest version.

### Contributions

While we value open-source contributions to this SDK, this library is generated programmatically.
Feel free to open a PR or a Github issue as a proof of concept and we'll do our best to include it in a future release!

### SDK Created by [Speakeasy](https://docs.speakeasyapi.dev/docs/using-speakeasy/client-sdks)
