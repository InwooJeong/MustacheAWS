package org.example.springboot.domain.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.springboot.domain.BaseTimeEntity;

import javax.persistence.*;

//BaseTimeEntity 상속이 필요!!

@Getter
//기본 생성자 자동 추가
@NoArgsConstructor
//테이블과 링크될 클래스
@Entity
public class Posts extends BaseTimeEntity {

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

  public void update(String title, String content){
    this.title = title;
    this.content = content;
  }

}
