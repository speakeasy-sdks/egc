# Egc SDK

## Overview

EGC API: # EGC API

## Design considerations

### Two-phase send

Sending a new MSI is two-phase (create then send). This is
because the msiId value is a critical piece of information
for the client (to be able to check status and/or cancel)
and the API design must ensure that the client is safely 
in possession of msiID before a send happens. If the 
method was one phase then there would be no guarantee that
the msiID was received (a failure could happen in any link
in the return snetworking chain like a router, firewall, proxy 
server or indeed a problem at the client application end
that prevented persistence of the msiID for later use).
Moreover, if the method was one phase and a failure in the
network chain occurred then not only would an orphan MSI 
be sent by the provider but the client would not have
knowledge that the MSI had been successfully queued for
sending and would naturally retry the send (multiple times
even) and we end up with the same MSI being sent 2+ times.

To further clarify the problem being solved by a two phase
send here is a [discussion](https://stackoverflow.com/questions/49444723/how-to-make-sure-the-http-response-was-delivered) 
of the delivery guarantees of HTTP responses. 

Note that a one phase call where the client generated a new 
unique id (using a UUID for instance) is a possible solution 
but is less desirable because it introduces a problematic 
edge case where the client accidentally uses the same id more
than once. If two different messages are sent with the same id
(concurrently even) then the service should ensure that only 
one message is accepted and that the service consumer is aware 
that the other message failed. To do this demands coordination
with a single transactional resource (like a relational database) which 
also demands that that resource is highly available (relational 
databases are often not great at that during upgrade cycles). There 
are ways to get high availability (highly available cloud services like 
DynamoDB and many more can offer conditional updates) but there is
a much simpler way with two-phase. 

If instead of the one-phase call the server creates the msiId and communicates 
it to the client then the server side can potentially be scaled
with ease if the msiID is a [UUID](https://en.wikipedia.org/wiki/Universally_unique_identifier) 
for instance (which is effectively unique without coordination
with other nodes). 

For example, a highly available and scalable 
service could be constructed in AWS cloud using API Gateway with
Lambda integrations that for the create message and send actions
does this

  * **Create:** Generate a new UUID, place the message content and UUID on 
to a queue for processing , return the UUID
  * **Send:** Place the UUID parameter on to a queue for processing

A separate component then actions items on the queue(s). When both 
the send and create messages have been read then an actual send can take 
place. What is clear from this design is that many concurrent nodes could
be receiving messages without coordinating with a central node/service 
to ensure id uniqueness.

Note also that to support two-phase send the status value of
 `CREATED` is included.

### Pagination

The List MSIs action uses a paginated response as the number of 
MSIs in a response can get large. Pagination can reduce server
overhead and improve response times. Client-driven pagination is 
where the client specifies an offset (skip) field and that number
of rows is skipped by the server to return the next page. This
can be inefficient for the server-side (see [discussion](
https://use-the-index-luke.com/no-offset)) and it is preferred to 
use server-driven pagination which is where each page returned 
also includes a continuation token to be included in the next page 
call. The nice thing about this approach is that the server side
can simply return an offset in the continuation token if desired but 
we enable more efficient techniques if wanted later.

### Client specific identifiers

Early versions of this API have suggested the inclusion of a 
`NationalSASId` field in the created MSI with the purpose of allowing 
a client to correlate an MSI with its internal data. 

This field is a convenience only and thus theoretically should not be
included. A client should manage its correlations itself by storing the
unique msiId returned by the service paired with its internal identifiers. 

If something is required then it should be labelled something 
like `tag` and have arbitrary values so that the client can use it for 
anything. Labelling it `NationalSASId` suggests more meaning to the
field than it may have. **TODO** confirm.

### Geometry

Note that the api below allows for float precision locations for 
geographic circles and rectangles. An implementation of this API 
may choose to use the location with reduced precision (for example
lat longs rounded to nearest integer).

### Cancellation

A PUT to an `/msi/[id}` path ]with content like `{"isCancelled":true}` 
has been suggested as a way of cancelling a broadcast. This can be 
achieved in a much simpler way with the DELETE verb without content 
(a cancel action can be considered as a logical delete in the context
of this API). A cancelled broadcast cannot be changed in status but 
can be queried. 

### Abstraction of C-Codes

Initial proposals for the API suggested a partial abstraction of C-Codes. 
In particular Priority, MsiType and AreaType were abstracted. This API 
demonstrates a full abstraction of C-Codes. It is equivalent to C-Codes
but has an easier to read and process representation and the mapping to C-Codes 
then becomes a server-side implementation detail. By using the data modelling
constructs of OpenAPI v3 and JSON Schema users can generate code for their 
API-consuming application that imposes compile-time checking (varies on language)
instead of experiencing runtime failures.

**TODO** is there a requirement for full explicit C-Code support (zero 
abstraction)?

### Auth

Initial proposals for this API included a re-authenticate 
method whereby a new token was returned if a currently valid token was 
supplied. This is a security hole in that anyone in possession of one 
valid token (but not the username and password) can stay authenticated 
forever. In the same vein, a sensible limit on validity duration of a 
token should be imposed so that a leaked token cannot be used for long. 
Given the likely usage of the API (in terms of the number of calls made
in a time interval by a client) there should be no significant performance
penalty forcing a refresh of the token each hour (or even less).

Bearer authentication is used ([RFC6750](https://www.rfc-editor.org/rfc/rfc6750.html#page-5)).
Bearer authentication is carried in the `Authorization` request header in this format:

    Authorization: Bearer BASE64_ENCODED_TOKEN

**TODO** The encoded content of the token is not defined (for example, JWT could be used)
but is left to the implementer. Should the authentication flow follow an existing 
standard like OAuth 2.0? 

### Naming
Previous API drafts used the field names `startDate` and `endDate` for an MSI.
Given that those fields refer to timestamps not just dates the names `startTime`
and `endTime` have been used.

### Acknowledgments
Iridium can at times provide receive and read acknowledgements. **TODO** get 
better documentation of the capability and a proper specification for their
response from a list acks call.

### Timings
There may be use cases for the following additional fields on an MSI:

* createdTime
* cancelledTime

A user could record in their own systems when they created or cancelled a broadcast 
but it might help problem diagnosis if that information was together.

**TODO** confirm

It would also be useful in some circumstances for users to know exactly when a
message was broadcast by satellite. Although messages may be scheduled for 
immediate or later broadcast there may be significant delays till the broadcast
occurs and the user should be able to see the actual broadcast times.

**TODO** discuss with satellite providers


### Available Operations

* [cancelMsi](#cancelmsi) - Cancel MSI
* [createMsi](#createmsi) - Create MSI
* [getMsi](#getmsi) - Get MSI
* [getMsiStatus](#getmsistatus) - Get status of an MSI
* [getMsis](#getmsis) - List MSIs
* [getToken](#gettoken) - Authenticate
* [sendMsi](#sendmsi) - Send MSI

## cancelMsi

This is a logical delete of a broadcast in that it
prevents future broadcasts happening for this msiId
(be it a single or repeating broadcast). Once cancelled
an MSI cannot be resent. However, the broadcast details 
are still available to be queried.


### Example Usage

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

            CancelMsiResponse res = sdk.egc.cancelMsi(req, new CancelMsiSecurity("provident") {{
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

### Parameters

| Parameter                                                                                                      | Type                                                                                                           | Required                                                                                                       | Description                                                                                                    |
| -------------------------------------------------------------------------------------------------------------- | -------------------------------------------------------------------------------------------------------------- | -------------------------------------------------------------------------------------------------------------- | -------------------------------------------------------------------------------------------------------------- |
| `request`                                                                                                      | [au.gov.amsa.egc.egc_client.models.operations.CancelMsiRequest](../../models/operations/CancelMsiRequest.md)   | :heavy_check_mark:                                                                                             | The request object to use for the request.                                                                     |
| `security`                                                                                                     | [au.gov.amsa.egc.egc_client.models.operations.CancelMsiSecurity](../../models/operations/CancelMsiSecurity.md) | :heavy_check_mark:                                                                                             | The security requirements to use for the request.                                                              |


### Response

**[au.gov.amsa.egc.egc_client.models.operations.CancelMsiResponse](../../models/operations/CancelMsiResponse.md)**


## createMsi

Submits message content and returns a unique id (across all 
users) that is to be used in the send method. It is ok for
a message to never be sent but the provider should have the 
freedom to clean up (delete) unsent messages after some 
reasonable period or after more than N unsent messages are created.
(**TODO** ask providers what is reasonable minimum period and 
maximum number of unsent messages so it can be documented here). 


### Example Usage

```java
package hello.world;

import au.gov.amsa.egc.egc_client.Egc;
import au.gov.amsa.egc.egc_client.models.operations.CreateMsiResponse;
import au.gov.amsa.egc.egc_client.models.operations.CreateMsiSecurity;
import au.gov.amsa.egc.egc_client.models.shared.Circle;
import au.gov.amsa.egc.egc_client.models.shared.IntervalHours;
import au.gov.amsa.egc.egc_client.models.shared.MetArea;
import au.gov.amsa.egc.egc_client.models.shared.MetBroadcast;
import au.gov.amsa.egc.egc_client.models.shared.MetCoastalWarningArea;
import au.gov.amsa.egc.egc_client.models.shared.MsiContent;
import au.gov.amsa.egc.egc_client.models.shared.NavArea;
import au.gov.amsa.egc.egc_client.models.shared.NavBroadcast;
import au.gov.amsa.egc.egc_client.models.shared.NavCoastalWarningArea;
import au.gov.amsa.egc.egc_client.models.shared.NonSARPriority;
import au.gov.amsa.egc.egc_client.models.shared.NumRepetitions;
import au.gov.amsa.egc.egc_client.models.shared.PiracyBroadcast;
import au.gov.amsa.egc.egc_client.models.shared.Rectangle;
import au.gov.amsa.egc.egc_client.models.shared.Repetition;
import au.gov.amsa.egc.egc_client.models.shared.SARBroadcast;
import au.gov.amsa.egc.egc_client.models.shared.SARPriority;
import au.gov.amsa.egc.egc_client.models.shared.SARServiceType;
import au.gov.amsa.egc.egc_client.models.shared.SubjectIndicator;
import java.time.OffsetDateTime;

public class Application {
    public static void main(String[] args) {
        try {
            Egc sdk = Egc.builder()
                .build();

            au.gov.amsa.egc.egc_client.models.shared.MsiContent req = new MsiContent(                new NavBroadcast(                new NavArea(437587) {{
                                                navAreaCode = 384382;
                                            }}, NonSARPriority.URGENCY) {{
                                navBroadcastArea = new NavCoastalWarningArea("illum", 423655, SubjectIndicator.AIS) {{
                                    coastalWarningAreaCode = "unde";
                                    navAreaCode = 857946;
                                    subjectIndicator = SubjectIndicator.PILOT_SERVICE_MESSAGES;
                                }};
                                priority = NonSARPriority.URGENCY;
                            }}, "a message to be broadcast") {{
                echo = false;
                endTime = OffsetDateTime.parse("2022-04-24T10:25:43.511Z");
                readAcksEnabled = false;
                receiveAcksEnabled = false;
                repetition = new Repetition(IntervalHours.FIVE, NumRepetitions.TILL_CANCELLED);;
                startTime = OffsetDateTime.parse("2022-04-23T10:30:43.511Z");
            }};            

            CreateMsiResponse res = sdk.egc.createMsi(req, new CreateMsiSecurity("ipsa") {{
                bearer = "";
            }});

            if (res.msiId != null) {
                // handle response
            }
        } catch (Exception e) {
            // handle exception
        }
    }
}
```

### Parameters

| Parameter                                                                                                      | Type                                                                                                           | Required                                                                                                       | Description                                                                                                    |
| -------------------------------------------------------------------------------------------------------------- | -------------------------------------------------------------------------------------------------------------- | -------------------------------------------------------------------------------------------------------------- | -------------------------------------------------------------------------------------------------------------- |
| `request`                                                                                                      | [au.gov.amsa.egc.egc_client.models.shared.MsiContent](../../models/shared/MsiContent.md)                       | :heavy_check_mark:                                                                                             | The request object to use for the request.                                                                     |
| `security`                                                                                                     | [au.gov.amsa.egc.egc_client.models.operations.CreateMsiSecurity](../../models/operations/CreateMsiSecurity.md) | :heavy_check_mark:                                                                                             | The security requirements to use for the request.                                                              |


### Response

**[au.gov.amsa.egc.egc_client.models.operations.CreateMsiResponse](../../models/operations/CreateMsiResponse.md)**


## getMsi

Returns the details of an MSI broadcast using the unique MSI identifier.


### Example Usage

```java
package hello.world;

import au.gov.amsa.egc.egc_client.Egc;
import au.gov.amsa.egc.egc_client.models.operations.GetMsiRequest;
import au.gov.amsa.egc.egc_client.models.operations.GetMsiResponse;
import au.gov.amsa.egc.egc_client.models.operations.GetMsiSecurity;

public class Application {
    public static void main(String[] args) {
        try {
            Egc sdk = Egc.builder()
                .build();

            GetMsiRequest req = new GetMsiRequest("289ee192-fdf5-4070-befc-3bf7291c1386");            

            GetMsiResponse res = sdk.egc.getMsi(req, new GetMsiSecurity("delectus") {{
                bearer = "";
            }});

            if (res.msi != null) {
                // handle response
            }
        } catch (Exception e) {
            // handle exception
        }
    }
}
```

### Parameters

| Parameter                                                                                                | Type                                                                                                     | Required                                                                                                 | Description                                                                                              |
| -------------------------------------------------------------------------------------------------------- | -------------------------------------------------------------------------------------------------------- | -------------------------------------------------------------------------------------------------------- | -------------------------------------------------------------------------------------------------------- |
| `request`                                                                                                | [au.gov.amsa.egc.egc_client.models.operations.GetMsiRequest](../../models/operations/GetMsiRequest.md)   | :heavy_check_mark:                                                                                       | The request object to use for the request.                                                               |
| `security`                                                                                               | [au.gov.amsa.egc.egc_client.models.operations.GetMsiSecurity](../../models/operations/GetMsiSecurity.md) | :heavy_check_mark:                                                                                       | The security requirements to use for the request.                                                        |


### Response

**[au.gov.amsa.egc.egc_client.models.operations.GetMsiResponse](../../models/operations/GetMsiResponse.md)**


## getMsiStatus

Returns the status of an MSI broadcast using the unique MSI identifier.


### Example Usage

```java
package hello.world;

import au.gov.amsa.egc.egc_client.Egc;
import au.gov.amsa.egc.egc_client.models.operations.GetMsiStatusRequest;
import au.gov.amsa.egc.egc_client.models.operations.GetMsiStatusResponse;
import au.gov.amsa.egc.egc_client.models.operations.GetMsiStatusSecurity;

public class Application {
    public static void main(String[] args) {
        try {
            Egc sdk = Egc.builder()
                .build();

            GetMsiStatusRequest req = new GetMsiStatusRequest("289ee192-fdf5-4070-befc-3bf7291c1386");            

            GetMsiStatusResponse res = sdk.egc.getMsiStatus(req, new GetMsiStatusSecurity("tempora") {{
                bearer = "";
            }});

            if (res.statusExtended != null) {
                // handle response
            }
        } catch (Exception e) {
            // handle exception
        }
    }
}
```

### Parameters

| Parameter                                                                                                            | Type                                                                                                                 | Required                                                                                                             | Description                                                                                                          |
| -------------------------------------------------------------------------------------------------------------------- | -------------------------------------------------------------------------------------------------------------------- | -------------------------------------------------------------------------------------------------------------------- | -------------------------------------------------------------------------------------------------------------------- |
| `request`                                                                                                            | [au.gov.amsa.egc.egc_client.models.operations.GetMsiStatusRequest](../../models/operations/GetMsiStatusRequest.md)   | :heavy_check_mark:                                                                                                   | The request object to use for the request.                                                                           |
| `security`                                                                                                           | [au.gov.amsa.egc.egc_client.models.operations.GetMsiStatusSecurity](../../models/operations/GetMsiStatusSecurity.md) | :heavy_check_mark:                                                                                                   | The security requirements to use for the request.                                                                    |


### Response

**[au.gov.amsa.egc.egc_client.models.operations.GetMsiStatusResponse](../../models/operations/GetMsiStatusResponse.md)**


## getMsis

Returns Maritime Safety Information broadcasts requested to
be sent by the user. 

Note that none of the parameters are required. If no parameters
are supplied then all MSIs for the current user will be returned
(paged).

The `limit` field may not be honoured exactly by the server
side (in that it might exceed a maximum limit of the server).

The results of this query may return in any order and that order
may vary in repeated calls (for example, the returned MSIs *may* 
not be ordered by timestamp). If the client requires an ordering
by time then all pages should be requested and then sorted 
client-side. **TODO** confirm expectations


### Example Usage

```java
package hello.world;

import au.gov.amsa.egc.egc_client.Egc;
import au.gov.amsa.egc.egc_client.models.operations.GetMsisRequest;
import au.gov.amsa.egc.egc_client.models.operations.GetMsisResponse;
import au.gov.amsa.egc.egc_client.models.operations.GetMsisSecurity;
import au.gov.amsa.egc.egc_client.models.shared.BroadcastType;
import au.gov.amsa.egc.egc_client.models.shared.Status;
import java.time.OffsetDateTime;

public class Application {
    public static void main(String[] args) {
        try {
            Egc sdk = Egc.builder()
                .build();

            GetMsisRequest req = new GetMsisRequest() {{
                broadcastType = BroadcastType.SAR_BROADCAST;
                continuationToken = "10";
                endTimeMax = OffsetDateTime.parse("2022-04-25T14:18:23.000Z");
                endTimeMaxInclusive = true;
                endTimeMin = OffsetDateTime.parse("2022-04-21T18:25:43.511Z");
                endTimeMinInclusive = true;
                limit = 383441;
                startTimeMax = OffsetDateTime.parse("2022-04-24T23:25:43.511Z");
                startTimeMaxInclusive = true;
                startTimeMin = OffsetDateTime.parse("2022-04-20T18:25:43.511Z");
                startTimeMinInclusive = true;
                status = new au.gov.amsa.egc.egc_client.models.shared.Status[]{{
                    add(Status.ACTIVE),
                }};
            }};            

            GetMsisResponse res = sdk.egc.getMsis(req, new GetMsisSecurity("molestiae") {{
                bearer = "";
            }});

            if (res.getMsis200ApplicationJSONObject != null) {
                // handle response
            }
        } catch (Exception e) {
            // handle exception
        }
    }
}
```

### Parameters

| Parameter                                                                                                  | Type                                                                                                       | Required                                                                                                   | Description                                                                                                |
| ---------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------- |
| `request`                                                                                                  | [au.gov.amsa.egc.egc_client.models.operations.GetMsisRequest](../../models/operations/GetMsisRequest.md)   | :heavy_check_mark:                                                                                         | The request object to use for the request.                                                                 |
| `security`                                                                                                 | [au.gov.amsa.egc.egc_client.models.operations.GetMsisSecurity](../../models/operations/GetMsisSecurity.md) | :heavy_check_mark:                                                                                         | The security requirements to use for the request.                                                          |


### Response

**[au.gov.amsa.egc.egc_client.models.operations.GetMsisResponse](../../models/operations/GetMsisResponse.md)**


## getToken

Obtains a token that will be passed in a request header to calls
to other paths on this API for authentication and authorization
purposes.


### Example Usage

```java
package hello.world;

import au.gov.amsa.egc.egc_client.Egc;
import au.gov.amsa.egc.egc_client.models.operations.GetTokenResponse;
import au.gov.amsa.egc.egc_client.models.shared.AuthenticationRequestBody;

public class Application {
    public static void main(String[] args) {
        try {
            Egc sdk = Egc.builder()
                .build();

            au.gov.amsa.egc.egc_client.models.shared.AuthenticationRequestBody req = new AuthenticationRequestBody("a-nice-strong-password", "myrcc") {{
                validityMinutes = 60;
            }};            

            GetTokenResponse res = sdk.egc.getToken(req);

            if (res.authenticationResponse != null) {
                // handle response
            }
        } catch (Exception e) {
            // handle exception
        }
    }
}
```

### Parameters

| Parameter                                                                                                              | Type                                                                                                                   | Required                                                                                                               | Description                                                                                                            |
| ---------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------- |
| `request`                                                                                                              | [au.gov.amsa.egc.egc_client.models.shared.AuthenticationRequestBody](../../models/shared/AuthenticationRequestBody.md) | :heavy_check_mark:                                                                                                     | The request object to use for the request.                                                                             |


### Response

**[au.gov.amsa.egc.egc_client.models.operations.GetTokenResponse](../../models/operations/GetTokenResponse.md)**


## sendMsi

Requests that an existing unsent MSI be sent. If the MSI 
has already been sent or the MSI has been cancelled then 
nothing occurs (this method is idempotent). 


### Example Usage

```java
package hello.world;

import au.gov.amsa.egc.egc_client.Egc;
import au.gov.amsa.egc.egc_client.models.operations.SendMsiRequest;
import au.gov.amsa.egc.egc_client.models.operations.SendMsiResponse;
import au.gov.amsa.egc.egc_client.models.operations.SendMsiSecurity;

public class Application {
    public static void main(String[] args) {
        try {
            Egc sdk = Egc.builder()
                .build();

            SendMsiRequest req = new SendMsiRequest("289ee192-fdf5-4070-befc-3bf7291c1386");            

            SendMsiResponse res = sdk.egc.sendMsi(req, new SendMsiSecurity("minus") {{
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

### Parameters

| Parameter                                                                                                  | Type                                                                                                       | Required                                                                                                   | Description                                                                                                |
| ---------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------- |
| `request`                                                                                                  | [au.gov.amsa.egc.egc_client.models.operations.SendMsiRequest](../../models/operations/SendMsiRequest.md)   | :heavy_check_mark:                                                                                         | The request object to use for the request.                                                                 |
| `security`                                                                                                 | [au.gov.amsa.egc.egc_client.models.operations.SendMsiSecurity](../../models/operations/SendMsiSecurity.md) | :heavy_check_mark:                                                                                         | The security requirements to use for the request.                                                          |


### Response

**[au.gov.amsa.egc.egc_client.models.operations.SendMsiResponse](../../models/operations/SendMsiResponse.md)**

