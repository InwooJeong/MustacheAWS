package org.example.springboot.domain.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
//기본 생성자 자동 추가
@NoArgsConstructor
//테이블과 링크될 클래스
@Entity
public class Posts {

  //pk
  @Id
  //pk 생성 규칙 identity - auto increment
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(length = 500, nullable = false)
  private String title;

  @Column(columnDefinition = "TEXT", nullable = false)
  private String content;

  private String author;

  //해당 클래스의 빌더 패턴 클래스
  @Builder
  public Posts(String title, String content, String author){
    this.title = title;
    this.content = content;
    this.author = author;
  }

}
