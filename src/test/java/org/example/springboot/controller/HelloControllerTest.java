package org.example.springboot.controller;

import org.example.springboot.config.auth.SecurityConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//테스트 진행시 JUnit 내장 실행자 외 다른 실행자 실행
//SpringRunner 스프링 실행자 사용 -> 스프링 부트 테스트와 Junit 사이 연결자
@RunWith(SpringRunner.class)
//스프링 테스트 어노테이션 중 WEB에 집중
//선언할 경우 Controller, ContollerAdvice 어노테이션 사용 가능
//Service, Component, Repository 어노테이션 사용 불가
@WebMvcTest(controllers = HelloController.class, excludeFilters = {
        @ComponentScan.Filter(type= FilterType.ASSIGNABLE_TYPE, classes = SecurityConfig.class)
    }
)
public class HelloControllerTest {

    //빈 주입
    @Autowired
    //웹 API 테스트 시 사용
    //스프링 MVC 테스트의 시작점 - GET, POST 등에 대한 API 테스트 가능
    private MockMvc mvc;

    @WithMockUser(roles="USER")
    @Test
    public void hello_will_returned() throws Exception{
        String hello = "hello";

        mvc.perform(get("/hello"))                //MockMVC를 통해 /hello 주소로 GET요청
                .andExpect(status().isOk())                 //mvc.perform 결과 검증, Http Header 상태 검증 OK = 200
                .andExpect(content().string(hello));        //응답 본문 내용 검증 -> Controller 에서 hello를 리턴 하는가?
    }

    @WithMockUser(roles="USER")
    @Test
    public void helloDTO_will_returned() throws Exception{
        String name = "hello";
        int amount = 1000;

        mvc.perform(
          get("/hello/dto")
            .param("name",name)
            .param("amount",String.valueOf(amount)))
          .andExpect(status().isOk())
          .andExpect(jsonPath("$.name",is(name)))       //json 응답값 $필드명
          .andExpect(jsonPath("$.amount",is(amount)));
    }

}
