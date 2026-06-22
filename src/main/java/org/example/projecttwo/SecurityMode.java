package org.example.projecttwo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SecurityMode {
    @Value("${spring.profiles.active:prod}")
    private String profile;

    public boolean isDev(){
        return profile.contains("dev");
    }
}
