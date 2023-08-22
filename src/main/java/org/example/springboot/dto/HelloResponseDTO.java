package org.example.springboot.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
//선언된 모든 final 필드가 포함된 생성자를 생성
@RequiredArgsConstructor
public class HelloResponseDTO {

  private final String name;
  private final int amount;

}
