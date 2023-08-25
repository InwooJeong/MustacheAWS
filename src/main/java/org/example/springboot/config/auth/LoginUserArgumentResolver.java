package org.example.springboot.config.auth;

import lombok.RequiredArgsConstructor;
import org.example.springboot.config.auth.dto.SessionUser;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Component
public class LoginUserArgumentResolver implements HandlerMethodArgumentResolver {

  private final HttpSession httpSession;

  @Override
  //컨트롤러 메서드의 특정 파라미터를 지원하는가?
  //@LoginUser가 있으며 파라미터 타입이 SessionUser 클래스
  public boolean supportsParameter(MethodParameter parameter) {

    boolean isLoginUserAnnno = parameter.getParameterAnnotation(LoginUser.class) != null;
    boolean isUserClass = SessionUser.class.equals(parameter.getParameterType());

    return isLoginUserAnnno && isUserClass;
  }

  @Override
  //파라미터에 전달할 객체 생성(여기서는 세션에서 가져옴)
  public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
    return httpSession.getAttribute("user");
  }
}
