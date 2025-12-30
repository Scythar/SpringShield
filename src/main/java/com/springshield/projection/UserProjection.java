package com.springshield.projection;

import java.time.LocalDateTime;

public interface UserProjection {
    Long getId();
    String getUsername();
    String getEmail();
    String getFirstName();
    String getLastName();
    LocalDateTime getCreatedAt();
}