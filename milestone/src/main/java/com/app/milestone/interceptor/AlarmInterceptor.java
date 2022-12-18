/*
 * 황지수
 * */

package com.app.milestone.interceptor;

import com.app.milestone.domain.AlarmDTO;
import com.app.milestone.entity.Alarm;
import com.app.milestone.repository.AlarmRepository;
import com.app.milestone.repository.UserRepository;
import com.app.milestone.service.AlarmService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/*황지수*/
@Slf4j
@Component
@RequiredArgsConstructor
public class AlarmInterceptor implements HandlerInterceptor {
    private final AlarmService alarmService;

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
    //  모든화면에서 항상 알림에 대한 조회가 필요하기 때문에 Interceptor를 사용하였다.
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        Long userId = (Long) request.getSession().getAttribute("userId");
        if (userId != null) {
            List<AlarmDTO> alarmDTOList = alarmService.showNoneCheckAlaram(userId);
            if (alarmDTOList.size() > 0) {
                modelAndView.addObject("checkAlarm", alarmDTOList);
            }
        }
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }
}
