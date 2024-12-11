package org.problem2.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(length = 36)
    private String id;

    @Column(unique = true, length = 31)
    private String name;

    @Column(length = 255)
    private String email;

    @JsonIgnore
    @OneToMany
    private List<Post> postList;

    @JsonIgnore
    @OneToMany
    private List<Comment> commentList;
}
