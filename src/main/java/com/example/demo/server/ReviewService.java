package com.example.demo.server;

import com.example.demo.model.Review;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ReviewService {
    List<Review> findById(Integer aid);
    List<Review> findSecondLevel(Integer id);
    int save(Review review);
}
