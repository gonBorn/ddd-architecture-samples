package study.huhao.name.springwithjpa.adapters.persistence.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import study.huhao.name.springwithjpa.domain.models.blog.Blog;
import study.huhao.name.springwithjpa.domain.models.blog.BlogId;
import study.huhao.name.springwithjpa.domain.models.user.UserId;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import java.time.Instant;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BlogDto {

    @Id
    private String id;
    private String title;
    private String body;
    private String author;
    @Enumerated(EnumType.STRING)
    private Blog.PublishStatus status;
    private Instant createdAt;
    private Instant publishedAt;
    private Instant lastModifiedAt;
    private BlogDraftDto draft;

    public Blog toEntity() {

        return new Blog(
                BlogId.of(id),
                title,
                body,
                UserId.of(author),
                status,
                createdAt,
                publishedAt,
                lastModifiedAt,
                new BlogDraftDto(draft.getDraftTitle(), draft.getDraftBody(), draft.getDraftSavedAt()).toEntity()
        );
    }

    public static BlogDto of(Blog blog) {

        return BlogDto.builder()
                .id(blog.getId().toString())
                .title(blog.getTitle())
                .body(blog.getBody())
                .author(blog.getAuthor().toString())
                .status(blog.getStatus())
                .createdAt(blog.getCreatedAt())
                .publishedAt(blog.getPublishedAt())
                .lastModifiedAt(blog.getLastModifiedAt())
                .draft(BlogDraftDto.of(blog.getDraft()))
                .build();
    }
}
