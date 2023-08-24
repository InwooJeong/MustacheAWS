package org.example.springboot.controller;

import lombok.RequiredArgsConstructor;
import org.example.springboot.dto.PostsResponseDto;
import org.example.springboot.dto.PostsSaveRequestDto;
import org.example.springboot.dto.PostsUpdateRequestDto;
import org.example.springboot.service.posts.PostsService;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class PostsApiController {

  private final PostsService postsService;

  //insert
  @PostMapping("/api/v1/posts")
  public Long save(@RequestBody PostsSaveRequestDto requestDto){
    return postsService.save(requestDto);
  }

  //update
  @PutMapping("/api/v1/posts/{id}")
  public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto){
    return postsService.update(id, requestDto);
  }

  @GetMapping("/api/v1/posts/{id}")
  public PostsResponseDto findById(@PathVariable Long id){
    return postsService.findById(id);
  }

  @DeleteMapping("/api/v1/posts/{id}")
  public Long delete(@PathVariable Long id){
    postsService.delete(id);
    return id;
  }

}
