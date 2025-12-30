package com.springshield.projection;

public interface UserSummaryProjection {
    Long getId();
    String getUsername();
    String getEmail();
    boolean isEnabled();
}