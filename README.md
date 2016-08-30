# Oauth2-Secure-distributed-Rest-API
Oauth2 Security for distributed Rest API

Run "RestOauthServer" as spring boot application and give following command:

# curl -F grant_type=password -F username=john -F password=123 -X POST http://localhost:8080/oauth/token -u clientIdPassword:secret

in response you will see following output:

{
"access_token":"eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE0NzEzNjgxMDgsInVzZXJfbmFtZSI6ImpvaG4iLCJhdXRob3JpdGllcyI6WyJST0xFX0FETUlOIl0sImp0aSI6IjYyMGI2ZDQyLWE0MWUtNDNjOC1hNWE2LWYzZWY3NjdlMGRmMiIsImNsaWVudF9pZCI6ImNsaWVudElkUGFzc3dvcmQiLCJzY29wZSI6WyJyZWFkIl19.NmVpML13D1GPf2IK_ysTkHVFA7ea8nFiVpOvfcgHS5u9EWnXRxlgA-chxd_FbM60j4FnRJbHvtlzghrhubPprDVJxkjetUcCG_kpq-KGqCCQBa6nC_qcFg5qhgYGLd7IuSQTviziz8EJ_S5jdcvgDR4GHMnbMs1btWhlngoZLO35gME_N-y5GJoJnvSwQTj21h6emSphzPH0Y8I5NUapMNO5AYiXRFTJx4F-Rru_vsRbHOzNjEHd7D8aG4BTxooyho8EaUU-W_0whQcDvDn49Ms81EERWjI_RPt8XeKRb1H532O1V_9Psdcwe_tMJ6KIeTsOaK-vssaS1uA30NeXOg",
"token_type":"bearer",
"refresh_token":"eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX25hbWUiOiJqb2huIiwic2NvcGUiOlsicmVhZCJdLCJhdGkiOiI2MjBiNmQ0Mi1hNDFlLTQzYzgtYTVhNi1mM2VmNzY3ZTBkZjIiLCJleHAiOjE0NzM5MTY5MDgsImF1dGhvcml0aWVzIjpbIlJPTEVfQURNSU4iXSwianRpIjoiNzQxZjJjNzUtNzY3YS00OTcyLWE2ZTktNTdlZGFlMWRhZGRiIiwiY2xpZW50X2lkIjoiY2xpZW50SWRQYXNzd29yZCJ9.WyLOI4dwJkNIrqZf4YV97K6pvW_SQOEc6X0XdIY3TfUmXbGBhrA2NCiRIo0AzOskSZLgqSKZ8N5L_uu4qqyaILMLH_XEV6gVuIEefDMVNo52uqjr0rpo_G90eR_3iveaDdGvC63opO2pWm-lxbSQN1DbakiyhyTXpFo0GjDIRuqspdmewu8uyeE3ylDJ8ADyfBtNvN1mXHeVwC4mUEwu6SH2e8ZNyUDBxkyxRlKRoMjN2P5oynme7VLL9EIPVyRzduxSmshcj4PjpzwsmI8vBv2BQclOObzYelYRB_8lDbWYr4C3ugOQPSfDww7kYdu-sYaJSKw1E7y97ZJTgvudJw",
"expires_in":43199,
"scope":"read",
"jti":"620b6d42-a41e-43c8-a5a6-f3ef767e0df2"
}

Now run "RestSecureOauth" as spring boot application and give following command:

# curl -v http://localhost:8998/api/resource -H  'Authorization: Bearer \<token>'

where "\<token>" will be replaced by "access_token" recieved in JSON response from "RestOauthServer" Like below:

curl -v http://localhost:8998/api/resource -H  'Authorization: Bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE0NzEzNjgxMDgsInVzZXJfbmFtZSI6ImpvaG4iLCJhdXRob3JpdGllcyI6WyJST0xFX0FETUlOIl0sImp0aSI6IjYyMGI2ZDQyLWE0MWUtNDNjOC1hNWE2LWYzZWY3NjdlMGRmMiIsImNsaWVudF9pZCI6ImNsaWVudElkUGFzc3dvcmQiLCJzY29wZSI6WyJyZWFkIl19.NmVpML13D1GPf2IK_ysTkHVFA7ea8nFiVpOvfcgHS5u9EWnXRxlgA-chxd_FbM60j4FnRJbHvtlzghrhubPprDVJxkjetUcCG_kpq-KGqCCQBa6nC_qcFg5qhgYGLd7IuSQTviziz8EJ_S5jdcvgDR4GHMnbMs1btWhlngoZLO35gME_N-y5GJoJnvSwQTj21h6emSphzPH0Y8I5NUapMNO5AYiXRFTJx4F-Rru_vsRbHOzNjEHd7D8aG4BTxooyho8EaUU-W_0whQcDvDn49Ms81EERWjI_RPt8XeKRb1H532O1V_9Psdcwe_tMJ6KIeTsOaK-vssaS1uA30NeXOg'


# Applications are drived from http://www.baeldung.com/rest-api-spring-oauth2-angularjs

I have also added a login controller to resource app incase someone dont want share client credentials with users.You can make following command to generate to token:

# curl  -v http://localhost:8998/login -H 'Content-Type: application/json; charset=UTF-8' --data-binary '{"username":"john","password":"123"}'

