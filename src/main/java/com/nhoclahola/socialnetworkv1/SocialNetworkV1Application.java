package com.nhoclahola.socialnetworkv1;

import com.nhoclahola.socialnetworkv1.security.JwtProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SocialNetworkV1Application
{

    public static void main(String[] args)
    {
        SpringApplication.run(SocialNetworkV1Application.class, args);
    }

}
