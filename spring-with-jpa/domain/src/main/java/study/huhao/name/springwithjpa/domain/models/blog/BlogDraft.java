package study.huhao.name.springwithjpa.domain.models.blog;

import lombok.AllArgsConstructor;
import lombok.Value;
import study.huhao.name.springwithjpa.domain.models.base.ValueObject;

import java.time.Instant;

@AllArgsConstructor
@Value
public class BlogDraft implements ValueObject {
    private String title;
    private String body;
    private Instant savedAt;
}
