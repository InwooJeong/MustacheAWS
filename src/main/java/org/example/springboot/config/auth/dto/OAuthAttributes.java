package org.example.springboot.config.auth.dto;

import lombok.Builder;
import lombok.Getter;
import org.example.springboot.domain.user.Role;
import org.example.springboot.domain.user.User;

import java.util.Map;

@Getter
public class OAuthAttributes {
  private Map<String, Object> attributes;
  private String nameAttributeKey;
  private String name;
  private String email;
  private String picture;

  @Builder
  public OAuthAttributes(Map<String, Object> attributes, String nameAttributeKey, String name, String email, String picture){
    this.attributes = attributes;
    this.nameAttributeKey = nameAttributeKey;
    this.name = name;
    this.email = email;
    this.picture = picture;
  }
  
  //OAuth2User 반환 정보 - Map : 값을 하나 하나를 변환
  public static OAuthAttributes of(String registrationId, String userNameAttributeName,Map<String, Object> attributes){
    return ofGoogle(userNameAttributeName, attributes);
  }
  
  private static OAuthAttributes ofGoogle(String userNameAttributeName, Map<String, Object> attributes){
    return OAuthAttributes.builder()
      .name((String) attributes.get("name"))
      .email((String) attributes.get("email"))
      .picture((String) attributes.get("picture"))
      .attributes((attributes))
      .nameAttributeKey(userNameAttributeName)
      .build();
  }

  //User 엔티티 생성
  //OAuthAttributes에서 엔티티 생성 -> 처음 가입 할 때
  //가입 시 기본 권한 GUEST - role 빌더 값에 Role.Guest
  //OAuthAttributes 클래스 생성이 끝났으면 같은 패키지에 SessionUser 클래스를 생성
  public User toEntity(){
    return User.builder()
      .name(name)
      .email(email)
      .picture(picture)
      .role(Role.GUEST)
      .build();
  }

}
