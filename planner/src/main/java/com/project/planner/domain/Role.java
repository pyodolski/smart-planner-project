package com.project.planner.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@RequiredArgsConstructor
public enum Role {
    ADMIN("관리자"), MANAGER("매니저"), USER("일반사용자");

    private String description;

    Role(String description) {this.description = description;}

}
