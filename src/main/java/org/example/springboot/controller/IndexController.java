package org.example.springboot.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.springboot.config.auth.LoginUser;
import org.example.springboot.config.auth.dto.SessionUser;
import org.example.springboot.dto.PostsResponseDto;
import org.example.springboot.service.posts.PostsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
@Slf4j
public class IndexController {

  private final PostsService postsService;
  private final HttpSession httpSession;
  private final Logger log = LoggerFactory.getLogger(this.getClass().getSimpleName());

  //소셜 로그인 -> userName을 model에 저장

  @GetMapping("/")
  public String index(Model model, @LoginUser SessionUser user){
    model.addAttribute("posts",postsService.findAllDesc());
    //CustomOAuth2UserService에서 로그인하면 세션에 SessionUser 저장
    //-> @으로 개선
    //SessionUser user = (SessionUser) httpSession.getAttribute("user");
    log.info("user : " + user);
    //세션에 저장된 값이 있을 때만 model에 등록
    //없으면 값이 없으니 로그인 버튼이 보인다.
    if(user != null){
      log.info("userName : " + user.getName());
      //String userName = user.getName();
      log.info("userEmail : " + user.getEmail());
      //String userEmail = user.getEmail();
      //model.addAttribute("user",user);
      model.addAttribute("loginUserName",user.getName());
      model.addAttribute("userEmail",user.getEmail());
    }
    return "index";
  }

  @GetMapping("/posts/save")
  public String postsSave(){
    return "posts-save";
  }

  @GetMapping("/posts/update/{id}")
  public String postsUpdate(@PathVariable Long id, Model model){
    PostsResponseDto dto = postsService.findById(id);
    model.addAttribute("post",dto);

    return "posts-update";
  }

}
