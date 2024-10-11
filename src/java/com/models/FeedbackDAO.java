package com.models;

import java.util.List;
import java.util.Map;

public interface FeedbackDAO {
    public Map<String, Object> getAverageRatingAndCount(String productId);
    public List<Feedback> findAllFeedback();
    public Products findProductByID(String productID);
    public EmployeeResponse getResponseByFeedbackId(int feedbackID);
    public List<Feedback> getFeedbackWithoutResponse();
    public List<Feedback> getFeedbackWithResponse();
    public void add(Feedback feedback);
    public List<Feedback> getFeedbackByProductId(int fbID);
    public Feedback get(int id);
    public List<Feedback> getFeedbacksForProduct(String productId);
    public List<Feedback> getFeedbacksByRating(String productId, int rating);
}
