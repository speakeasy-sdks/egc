<!-- Start SDK Example Usage [usage] -->
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
                .setSecurity(new Security(
                ){{
                    bearer = "<YOUR_BEARER_TOKEN_HERE>";
                }})
                .build();

            au.gov.amsa.egc.egc_client.models.operations.CancelMsiRequest req = new CancelMsiRequest(
                "289ee192-fdf5-4070-befc-3bf7291c1386");

            au.gov.amsa.egc.egc_client.models.operations.CancelMsiResponse res = sdk.cancelMsi(req);

            if (res.statusCode == 200) {
                // handle response
            }
        } catch (Exception e) {
            // handle exception
        }
    }
}
```
<!-- End SDK Example Usage [usage] -->