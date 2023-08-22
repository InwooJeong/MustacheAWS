package org.example.springboot.dto;

import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;
public class HelloResponseDTOTest {

  @Test
  public void lombok_test(){
    //given
    String name = "test";
    int amount = 1000;

    //when
    HelloResponseDTO dto = new HelloResponseDTO(name, amount);

    //then
    //assertj = 테스트 검증 라이브러리 검증 메소드
    //검증하고 싶은 대상을 메소드 인자로 받는다
    assertThat(dto.getName()).isEqualTo(name);
    assertThat(dto.getAmount()).isEqualTo(amount);
  }

}
