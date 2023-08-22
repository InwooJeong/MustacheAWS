package org.example.springboot.domain.posts;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTest {

  @Autowired
  PostsRepository postsRepository;

  //Junit 단위 테스트가 끝날 때마다 수행되는 메소드
  @After
  public void cleanup(){
    postsRepository.deleteAll();
  }

  @Test
  public void get_content(){
    //given
    String title = "Test Tile";
    String content = "Test Content";

    //posts 테이블에 insert/update 실행
    postsRepository.save(Posts.builder()
      .title(title)
      .content(content)
      .author("inu")
      .build());

    //when
    List<Posts> postsList = postsRepository.findAll();

    //then
    Posts posts = postsList.get(0);
    assertThat(posts.getTitle()).isEqualTo(title);
    assertThat(posts.getContent()).isEqualTo(content);

  }

}
