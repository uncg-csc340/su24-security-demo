# su24-security-demo
## Notes:
- This repository includes a dependency to Spring Security. This is how it handles authentication and authorization.
- Once this dependency is included, Security must be configured. The following are the elements needed for that:
  -  A Security configuaration class - [Security Config](https://github.com/uncg-csc340/su24-security-demo/blob/5d7b01e32725bde964eb07f15fbf9983e27c0b41/src/main/java/com/csc340/security_demo/security/SecurityConfig.java)
      -   Annotated as `@Configuration` and `@EnableWebSecurity`
      -   A [filter chain](https://github.com/uncg-csc340/su24-security-demo/blob/5d7b01e32725bde964eb07f15fbf9983e27c0b41/src/main/java/com/csc340/security_demo/security/SecurityConfig.java#L21). This is where the rules for authorization are configured. For this example, all requests that start with `VENDOR` are only allowed for people who have the VENDOR authorization. The same applies to `ADMIN`. These are the resources that are explicitly secured. 
      -   Any other requests must be authenticated, meaning everyone needs to login before they can do anything on the app.
      -   There are other rules for authorization [here](https://docs.spring.io/spring-security/reference/servlet/authorization/authorize-http-requests.html#authorize-requests)
      -   Provide a login configuration. This can either be [default or customized](https://docs.spring.io/spring-security/reference/servlet/authentication/passwords/form.html).
      -   Add an [exception handler](https://docs.spring.io/spring-security/reference/servlet/authentication/passwords/form.html). If a request is not authorized based on the rules defined above, the app will send a GET request to /403. You can customize this whatever you want but you MUST have the endpointed mapped in some controller.
      -   
