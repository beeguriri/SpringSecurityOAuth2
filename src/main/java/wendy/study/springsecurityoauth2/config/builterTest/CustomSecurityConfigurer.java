//package wendy.study.springsecurityoauth2.config.builterTest;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//
//@Slf4j
//public class CustomSecurityConfigurer extends AbstractHttpConfigurer<CustomSecurityConfigurer, HttpSecurity> {
//
//    //
//    private boolean isSecure;
//
//    @Override
//    public void init(HttpSecurity builder) throws Exception {
//        super.init(builder);
//        //초기화 과정 때 내가 만든 설정클래스가 적용되는 지 확인
//        log.info("CustomSecurityConfigurer >> init() start");
//    }
//
//    @Override
//    public void configure(HttpSecurity builder) throws Exception {
//        super.configure(builder);
//        //초기화 과정 때 내가 만든 설정클래스가 적용되는 지 확인
//        log.info("CustomSecurityConfigurer >> configure() start");
//        if(isSecure)
//            log.info("Https is required");
//        else
//            log.info("Https is optional");
//    }
//
//    public CustomSecurityConfigurer setFlag(boolean isSecure) {
//        this.isSecure = isSecure;
//        return this;
//    }
//}
