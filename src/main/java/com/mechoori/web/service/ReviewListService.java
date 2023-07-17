package com.mechoori.web.service;

import java.util.List;

import com.mechoori.web.entity.ReviewListView;

public interface ReviewListService {

    public List<ReviewListView> getDate(int restaurantId);
}
