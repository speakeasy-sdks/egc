<!-- Start SDK Example Usage -->


```java
package hello.world;

import au.gov.amsa.egc.egc_client.Egc;
import au.gov.amsa.egc.egc_client.models.operations.CancelMsiRequest;
import au.gov.amsa.egc.egc_client.models.operations.CancelMsiResponse;
import au.gov.amsa.egc.egc_client.models.operations.CancelMsiSecurity;

public class Application {
    public static void main(String[] args) {
        try {
            Egc sdk = Egc.builder()
                .build();

            CancelMsiRequest req = new CancelMsiRequest("289ee192-fdf5-4070-befc-3bf7291c1386");            

            CancelMsiResponse res = sdk.cancelMsi(req, new CancelMsiSecurity("corrupti") {{
                bearer = "";
            }});

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