/*
 * 황지수
 * */

package com.app.milestone.interceptor;

import com.app.milestone.domain.AlarmDTO;
import com.app.milestone.entity.File;
import com.app.milestone.service.AlarmService;
import com.app.milestone.service.FileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/*황지수*/
@Slf4j
@Component
@RequiredArgsConstructor
public class LoginInterceptor implements HandlerInterceptor {
    private final FileService fileService;

    //    controller의 메소드에 매핑된 특정 URI가 호출됐을 때 실행되는 메소드로,
//    controller를 경유(접근)하기 직전에 실행되는 메소드이다.
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        HttpSession session = request.getSession();
//        Long userId = (Long) session.getAttribute("userId");
//        log.info("===============================================" + userId);
//        log.info("===============================================" + "");
//        log.info("==================== BEGIN ====================");
//        log.info("Request URI ===> " + request.getRequestURI());
//        return HandlerInterceptor.super.preHandle(request, response, handler);
//    }

    //    컨트롤러를 경유 한 후, 즉 화면(view)으로 결과를 전달하기 전에 실행되는 메소드
    //  모든 화면에 존재하는 헤더에 프로필사진을 항상 불러와야 하기 때문에 각 컨트롤러에 쓸 필요없이 Interceptor로 프로필사진을 화면에 넘겨주었다.
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        Long userId = (Long) request.getSession().getAttribute("userId");
        if (userId !=null) {
            modelAndView.addObject("fileDTO", fileService.showProfile(userId));
        }
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }
}
