package com.example.demo.blog;

import com.example.demo.user.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BlogService {

    private final BlogRepository blogRepository;
    private final PostRepository postRepository;

    public BlogResponseDto findOrSaveBlog(Account account){
        Blog blog = blogRepository.findBlogByAccount(account).orElseGet(() -> {
            Blog sample = new Blog();
            sample.setAccount(account);
            return blogRepository.save(sample);
        });
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

        return PostDto.of(post);
    }
}
