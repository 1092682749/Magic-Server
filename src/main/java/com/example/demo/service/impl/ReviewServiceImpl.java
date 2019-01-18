package com.example.demo.service.impl;

import com.example.demo.dao.ReviewMapper;
import com.example.demo.model.Review;
import com.example.demo.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {
    @Autowired
    ReviewMapper reviewMapper;
    @Override
    public List<Review> findById(Integer aid) {
        return reviewMapper.findById(aid);
    }

    @Override
    public List<Review> findSecondLevel(Integer id) {
        return reviewMapper.findSecondLevel(id);
    }

    @Override
    public int save(Review review) {
        return reviewMapper.insert(review);
    }
}
