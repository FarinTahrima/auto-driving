package com.auto_driving.model;

import java.util.List;
import java.util.stream.Collectors;

public class CollisionIndicator {
    private boolean collided;
    private String reason;

    // getters and setters
    public boolean isCollided() {
        return collided;
    }

    public void setCollided(boolean collided) {
        this.collided = collided;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
