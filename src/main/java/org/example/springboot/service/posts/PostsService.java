package org.example.springboot.service.posts;

import lombok.RequiredArgsConstructor;
import org.example.springboot.domain.posts.Posts;
import org.example.springboot.domain.posts.PostsRepository;
import org.example.springboot.dto.PostsListResponseDto;
import org.example.springboot.dto.PostsResponseDto;
import org.example.springboot.dto.PostsSaveRequestDto;
import org.example.springboot.dto.PostsUpdateRequestDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PostsService {

  private final PostsRepository postsRepository;

  @Transactional
  public Long save(PostsSaveRequestDto requestDto){
    return postsRepository.save(requestDto.toEntity()).getId();
  }

  @Transactional
  public Long update(Long id, PostsUpdateRequestDto requestDto){
    Posts posts = postsRepository.findById(id)
      .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id = " + id));

    posts.update(requestDto.getTitle(), requestDto.getContent());

    return id;
  }

  public PostsResponseDto findById(Long id){
    Posts entity = postsRepository.findById(id)
      .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id = " + id));

    return new PostsResponseDto(entity);
  }

  @Transactional(readOnly = true)   //트랜잭션 범위는 유지하되 조회 기능만 남김 -> 조회 속도 개선
  public List<PostsListResponseDto> findAllDesc(){
    return postsRepository.findAllDesc().stream()
      .map(PostsListResponseDto::new)   //.map(posts -> new PostsListResponseDto(posts))
      .collect(Collectors.toList());
  }
  
  @Transactional
  public void delete(Long id){
    Posts posts = postsRepository.findById(id)
      .orElseThrow(()->new IllegalArgumentException("해당 게시글이 없습니다. id = " + id));

    //JpaRepository에서 지원하는 delete
    //Entity를 파라미터로 삭제하거나 deleteById 메소드를 이용, id로 삭제
    postsRepository.delete(posts);
  }

}
