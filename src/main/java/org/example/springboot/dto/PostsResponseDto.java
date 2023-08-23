package org.example.springboot.dto;

import lombok.Getter;
import org.example.springboot.domain.posts.Posts;
//entity의 필드 중 일부만 사용 -> 생성자
@Getter
public class PostsResponseDto {

  private Long id;
  private String title;
  private String content;
  private String author;

  public PostsResponseDto(Posts entity){
    this.id = entity.getId();
    this.title = entity.getTitle();
    this.content = entity.getContent();
    this.author = entity.getAuthor();
  }

}
