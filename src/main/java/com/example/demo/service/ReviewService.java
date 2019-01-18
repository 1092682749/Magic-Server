package com.example.demo.service;

import com.example.demo.model.Review;

import java.util.List;

public interface ReviewService {
    List<Review> findById(Integer aid);
    List<Review> findSecondLevel(Integer id);
    int save(Review review);
}
