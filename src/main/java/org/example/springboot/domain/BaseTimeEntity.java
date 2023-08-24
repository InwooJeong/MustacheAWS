package org.example.springboot.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

//데이터 생성, 수정 시간을 위한 클래스
@Getter
//JPA Entity 클래스들이 BaseTimeEntity를 상속할 경우 -> 필드들도 칼럼으로 인식
@MappedSuperclass
//BaseTimeEntity에 Auditing 기능을 포함 - Spring Data JPA에 시간에 대해 자동으로 값을 넣어줌
@EntityListeners(AuditingEntityListener.class)
public class BaseTimeEntity {
  
  //Entity가 생성, 저장될 때 시간이 자동 저장
  @CreatedDate
  private LocalDateTime createdDate;
  
  //조회한 Entity 값을 변경할 때 시간이 자동 저장
  @LastModifiedDate
  private LocalDateTime modifiedDate;
  
}
