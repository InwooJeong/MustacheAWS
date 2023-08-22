package org.example.springboot.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.springboot.domain.posts.Posts;

@Getter
@NoArgsConstructor
public class PostsSaveRequestDto {
  private String title;
  private String content;
  private String author;

  @Builder
  public PostsSaveRequestDto(String title, String content, String author){
    this.title = title;
    this.content = content;
    this.author = author;
  }

  //Entity = 데이터베이스와 맞닿은 핵심 클래스
  public Posts toEntity(){
    return Posts.builder()
      .title(title)
      .content(content)
      .author(author)
      .build();
  }

}
