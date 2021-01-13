package com.example.demo.blog;

import com.example.demo.exception.UnAuthorizedException;
import com.example.demo.user.Account;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@Transactional
@RequiredArgsConstructor
@Service
public class BlogService {

    private final BlogRepository blogRepository;
    private final PostRepository postRepository;

    public BlogResponseDto findOrSaveBlog(Account account){
        Blog blog = blogRepository.findBlogByAccount(account).orElseGet(() -> {
            Blog sample = new Blog();
            sample.setUserImageUrl(account.getImageUrl());
            sample.setAccount(account);
            sample.setHasProfileImage(false);
            return blogRepository.save(sample);
        });
        List<Post> postList = postRepository.findAllByBlog(blog);

        return BlogResponseDto.of(blog, postList);
    }

    public BlogResponseDto getBlogByEmail(Account account) {
        Blog blog = blogRepository.findBlogByAccount(account).orElseThrow(IllegalArgumentException::new);
        List<Post> postList = postRepository.findAllByBlog(blog);
        return BlogResponseDto.of(blog, postList);
    }

    public BlogResponseDto getBlogById(Long id) {
        Blog blog = blogRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        List<Post> postList = postRepository.findAllByBlog(blog);
        return BlogResponseDto.of(blog, postList);
    }

    public PostDto createPost(Account account, PostDto dto){
        Post post = new Post();
        Blog blog = blogRepository.findBlogByAccount(account).orElseThrow(RuntimeException::new);

        post.setBlog(blog);
        post.setTitle(dto.getTitle());
        post.setContent(dto.getContent());
        return PostDto.of(postRepository.save(post));
    }

    public void updateBlog(Account account, BlogRequestDto requestDto) {
        Blog blog = blogRepository.findBlogByAccount(account).orElseThrow(IllegalArgumentException::new);
        blog.setTitle(requestDto.getBlogTitle());
        blog.setDescription(requestDto.getDescription());
        log.info("블로그 업데이트");
        if(requestDto.getImage() != null) {
            log.info("이미지 업데이트");
            blog.setProfileImage(requestDto.getImage());
            blog.setHasProfileImage(true);
        }
        blogRepository.save(blog);
    }

    public void deletePost(Account account, Long id) {
        Post post = postRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        Blog blog = blogRepository.findBlogByAccount(account).orElseThrow(IllegalArgumentException::new);
        if(!post.getBlog().equals(blog)){
            throw new UnAuthorizedException();
        }
        postRepository.delete(post);
    }

    public void updatePost(Account account, PostDto requestDto, Long id) {
        Post post = postRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        Blog blog = blogRepository.findBlogByAccount(account).orElseThrow(IllegalArgumentException::new);
        if(!post.getBlog().equals(blog)){
            throw new UnAuthorizedException();
        }
        post.setTitle(requestDto.getTitle());
        post.setContent(requestDto.getContent());
        postRepository.save(post);
    }
}
