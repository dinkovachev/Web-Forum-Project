package com.telerikacademy.web.sportforumgroup10.controllers;

import com.telerikacademy.web.sportforumgroup10.helpers.AuthenticationHelper;
import com.telerikacademy.web.sportforumgroup10.services.Contracts.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


    @Controller
    @RequestMapping("/api/likes")
    public class LikeController {

        public static final String PERMISSION_ERROR = "You don't have permission.";



        private final LikeService likeService;
        private final AuthenticationHelper authenticationHelper;


        @Autowired
        public LikeController(LikeService likeService, AuthenticationHelper authenticationHelper) {
            this.likeService = likeService;
            this.authenticationHelper = authenticationHelper;
        }
    }
