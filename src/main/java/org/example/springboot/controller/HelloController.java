package org.example.springboot.controller;

import org.example.springboot.dto.HelloResponseDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }

    @GetMapping("/hello/dto")
    //외부에서 name(@RequestParam("name")이란 이름으로 넘긴 파라미터를 메소드 파라미터 name(String name)에 저장
    public HelloResponseDTO helloDTO(@RequestParam("name") String name, @RequestParam("amount") int amount){
        return new HelloResponseDTO(name, amount);
    }
}
