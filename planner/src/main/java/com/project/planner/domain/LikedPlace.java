package com.project.planner.domain;

import lombok.*;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "likedPlace")
@Data
public class LikedPlace {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    private String placeName;

    @Column(nullable = false)
    private String roadAddress;

    @Column(nullable = false)
    private String x;

    @Column(nullable = false)
    private String y;

    @ManyToOne
    private User user;
}
