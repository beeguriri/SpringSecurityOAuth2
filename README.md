# ✨ Spring Security OAuth2
- Spring Security OAuth2 공부 중 입니다!
- 관련 강의 : [스프링 시큐리티 OAuth2](https://inf.run/o6Xn)

## ⭐ 목차
- [⭐ 개발 환경](#-개발-환경)
- ⭐ Spring Security Fundamentals
  - [1-1. Security Builder](/docs/1-1.Security%20Builder.md)
  - [1-2. AutoConfiguration](/docs/1-2.AutoConfiguration.md)
  - [1-3. AuthenticationEntryPoint](/docs/1-3.AuthenticationEntryPoint.md)
  - [Spring Security 인증 및 인가 요약](#-spring-security-인증-및-인가-요약)
  - [2-0. Keycloak 설치 및 설정](/docs/2-0.Keycloak%20설치%20및%20설정.md)
  - [OAuth 2.0 Roles 요약](#-oauth-20-roles-요약)

## ⭐ 개발 환경
- SpringBoot version `2.7.18`
- Java version `17`
- Build : Gradle
- Dependency 추가
  - spring-boot-starter-web 
  - spring-boot-starter-security
  - lombok

## ⭐ Spring Security 인증 및 인가 요약
![](/images/security_summary.png)
1. DelegatingFilterProxy가 `springSecurityFilterChain` Bean을 찾음
    ```java
    @Override
	  public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

      //...
      //springSecurityFilterChain bean을 찾고
      WebApplicationContext wac = findWebApplicationContext();
      delegateToUse = initDelegate(wac);

      // Let the delegate perform the actual doFilter operation.
      invokeDelegate(delegateToUse, request, response, filterChain);
	}
    ```
2. FilterChainProxy.java에서 filter chain에 등록 된 filter 실행
    ```java
    @Override
    public void doFilter(ServletRequest request, ServletResponse response) throws IOException, ServletException {
      //...
      Filter nextFilter = this.additionalFilters.get(this.currentPosition - 1);
      nextFilter.doFilter(request, response, this);
      }
    ```
3. `ExceptionTranslationFilter`에서 `AccessDenided` -> throw ex
  ![](/images/anonymousUser.png)
4. 다시 `DelegatingFilterProxy` -> `DefaultLoginPageGeneratingFilter` 실행 -> '/login' entry point -> 로그인 form화면 -> 로그인정보 입력
5. `DelegatingFilterProxy` -> `UsernamePasswordAuthenticationFilter` 실행
   - username과 password를 저장하여 `Authentication` 객체 반환
6. ProviderManager에서 인증처리 할 수 있는 클래스(AbstractUserDetailsAuthenticationProvider) 찾아서 위임 
7. `AbstractUserDetailsAuthenticationProvider`에서 인증 성공/실패 처리
   - `UserDetails` 객체 반환
     ![](/images/loaduser.png)
8. ProviderManager는 인증 성공 시 `Authentication` 객체에 UserDetails 정보 저장 
9. `SecurityContext`에 전역적으로 인증받았다는 정보 저장
  ![](/images/securitycontext.png)

# ⭐ OAuth 2.0 Roles 요약
![](images/oauth_roles.JPG)

### ✨ Resource Owner (사용자)
- 보호된 자원에 대한 접근 권한을 부여할 수 있는 주체
- 계정의 일부에 대한 접근 권한을 부여 하는 사람
- 사용자를 대신하여 작용하려는 모든 클라이언트는 사용자의 허가 받아야 함.

### ✨ Resource Server
- 사용자의 자원을 가지고 있는 서버
- 엑세스 토큰 수락 및 검증
- 권한 체계에 따라 요청 승인

### ✨ Authorization Server
- 클라이언트에게 권한 부여 승인 또는 거부하는 서버
- 인가서버가 사용자에게 클라이언트에 대한 권한 부여 요청을 받아서
- 클라이언트에게 엑세스 토큰을 부여함
- (사용자) --- (인가서버) --- (클라이언트)

### ✨ Client
- 사용자를 대신하여 권한을 부여받아 사용자의 리소스에 접근하려는 어플리케이션