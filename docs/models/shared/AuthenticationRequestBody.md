# AuthenticationRequestBody

Holds the credentials and validity preference used to 
create a token to be passed as a request header in calls
to the API



## Fields

| Field                                                                                                                                                                                                                                                                                                                                                                                                                                                      | Type                                                                                                                                                                                                                                                                                                                                                                                                                                                       | Required                                                                                                                                                                                                                                                                                                                                                                                                                                                   | Description                                                                                                                                                                                                                                                                                                                                                                                                                                                | Example                                                                                                                                                                                                                                                                                                                                                                                                                                                    |
| ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| `password`                                                                                                                                                                                                                                                                                                                                                                                                                                                 | *String*                                                                                                                                                                                                                                                                                                                                                                                                                                                   | :heavy_check_mark:                                                                                                                                                                                                                                                                                                                                                                                                                                         | N/A                                                                                                                                                                                                                                                                                                                                                                                                                                                        | a-nice-strong-password                                                                                                                                                                                                                                                                                                                                                                                                                                     |
| `username`                                                                                                                                                                                                                                                                                                                                                                                                                                                 | *String*                                                                                                                                                                                                                                                                                                                                                                                                                                                   | :heavy_check_mark:                                                                                                                                                                                                                                                                                                                                                                                                                                         | N/A                                                                                                                                                                                                                                                                                                                                                                                                                                                        | myrcc                                                                                                                                                                                                                                                                                                                                                                                                                                                      |
| `validityMinutes`                                                                                                                                                                                                                                                                                                                                                                                                                                          | *Integer*                                                                                                                                                                                                                                                                                                                                                                                                                                                  | :heavy_minus_sign:                                                                                                                                                                                                                                                                                                                                                                                                                                         | The duration in minutes that the token will be valid, <br/>capped by the maximum allowed server-decided duration.  <br/>If not supplied (this parameter is optional) then the<br/>server default will be applied. Either way the response<br/>from this method supplies the expiry time for the<br/>token. The token should be refreshed before the expiry <br/>time to avoid auth related failures. Note that this field<br/>is only a suggestion to the service and may be ignored.<br/> | 60                                                                                                                                                                                                                                                                                                                                                                                                                                                         |