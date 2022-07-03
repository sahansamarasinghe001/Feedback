package com.example.api.Service;

import com.example.api.Model.Feedback;


import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public interface FeedBackService {
    Feedback saveFeedback(Feedback feedback);
    List<Feedback> getAllFeedback();
    Feedback getFeedbackById(long id);
    List<Feedback> getUserId(String userid);
    List<Feedback> getByFeedbackType(String userType);
    List<Feedback> getBetweenDates(String Date1,String Date2);
    Feedback updateFeedback(Feedback feedback, long id);
    void DeleteFeedback(long id);
    void downloadFeedbackCsv(HttpServletResponse response) throws IOException;
}
