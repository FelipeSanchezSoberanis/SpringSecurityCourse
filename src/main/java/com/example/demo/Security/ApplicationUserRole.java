package com.example.demo.Security;

import com.google.common.collect.Sets;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;

import static com.example.demo.Security.ApplicationUserPermission.*;

@Getter
@AllArgsConstructor
public enum ApplicationUserRole {

    STUDENT(Sets.newHashSet()),
    ADMIN(Sets.newHashSet(COURSE_READ, COURSE_WRITE, STUDENT_READ, STUDENT_WRITE));

    private final Set<ApplicationUserPermission> permissions;

}

