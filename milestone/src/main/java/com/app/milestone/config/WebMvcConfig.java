/*
* 황지수
* */

package com.app.milestone.config;

import com.app.milestone.interceptor.AlarmInterceptor;
import com.app.milestone.interceptor.LoginInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


//WebMvcConfigurer해당 인터페이스를 구현하면 @EnableWebMvc의 자동 설정을 베이스로 가져간다.
//개발자가 원하는 설정까지 추가 가능
@Configuration
//@EnableWebMvc
@RequiredArgsConstructor
public class WebMvcConfig implements WebMvcConfigurer {
    private final AlarmInterceptor alarmInterceptor;
    private final LoginInterceptor loginInterceptor;

//    addInterceptors 어플리케이션 내에서 인터셉터가 작동할 수 있도록 빈(Bean)으로 등록
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        알람을 조회하는 Interceptor를 추가. addPathPatterns로 url을 미리 적어두어 각 화면으로 이동하는 컨트롤러를 거치고 화면으로 이동할 때 실행하게 설정
        registry.addInterceptor(alarmInterceptor)
                .addPathPatterns("/error/**", "/faq/**", "/find/**", "/introduce/**", "/join/**", "/kakao/**", "/login/**", "/main/**", "/mypage/**", "/notice/**", "/ranking/**", "/school/**", "/talent/**", "/logout/**");
//                excludePathPatterns() 특정 패턴의 주소(URI)를 추가 또는 제외할 수 있다.(반대로 addPathPatterns()는 인터셉터 호출에서 경로를 추가(허용)한다.
//                src/main/resources 디렉터리의 static 폴더에 포함된 정적 리소스 파일을 제외
//                .excludePathPatterns("/css/**", "/imgs/**", "/file/**", "/images/**", "/js/**", "/schoolrest/**");
//        프로필사진을 가져오는 Interceptor를 추가 한다.
        registry.addInterceptor(loginInterceptor).addPathPatterns("/error/**", "/faq/**", "/find/**", "/introduce/**", "/join/**", "/kakao/**", "/login/**", "/main/**", "/mypage/**", "/notice/**", "/ranking/**", "/school/**", "/talent/**", "/logout/**");
    }

}
