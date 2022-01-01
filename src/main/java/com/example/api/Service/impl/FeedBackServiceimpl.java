package com.example.api.Service.impl;

import com.example.api.Exception.ResourceNotFoundException;
import com.example.api.Model.Feedback;
import com.example.api.Repository.FeedbackRepository;
import com.example.api.Service.FeedBackService;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FeedBackServiceimpl implements FeedBackService {

    private FeedbackRepository feedbackrepository;
    public FeedBackServiceimpl(FeedbackRepository feedbackrepository) {
        super();
        this.feedbackrepository = feedbackrepository;
    }


    @Override
    public Feedback saveFeedback(Feedback feedback) {
        return feedbackrepository.save(feedback);
    }

    @Override
    public List<Feedback> getAllFeedback() {
        return feedbackrepository.findAll();
    }

    @Override
    public Feedback getFeedbackById(long id) {
        Optional<Feedback> feedback = feedbackrepository.findById(id);
        if(feedback.isPresent()){
            return feedback.get();
        }
        else{
            throw new ResourceNotFoundException("Feedback Data","Id",id);
        }

    }

    @Override
    public List<Feedback> getUserId(String userid) {

        List<Feedback> feedback = feedbackrepository.findByUserId(userid);
        if(feedback.isEmpty()){
            throw new ResourceNotFoundException("Feedback UserData","UserId",userid);

        }
        else{
            return feedback;
        }


    }

    @Override
    public List<Feedback> getByFeedbackType(String userType) {
        List<Feedback> data = feedbackrepository.getAllFeedbackType(userType);
        if(data.isEmpty()){
            throw new ResourceNotFoundException("Feedback Type","UserType",userType);
        }
        else{
            return feedbackrepository.getAllFeedbackType(userType);
        }

    }

    @Override
    public List<Feedback> getBetweenDates(String Date1, String Date2) {

        List<Feedback> data = feedbackrepository.getBetweenDates(Date1,Date2);
        if(data.isEmpty()){
            throw new ResourceNotFoundException("Feedback Date","Date","Date Range");
        }
        else{
            return data;
        }

    }

    @Override
    public Feedback updateFeedback(Feedback feedback, long id) {

        Feedback existingFeedback = feedbackrepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Feedback","Id", id)
        );

        existingFeedback.setUserId(feedback.getUserId());
        existingFeedback.setContent(feedback.getContent());
        existingFeedback.setSubject(feedback.getSubject());
        existingFeedback.setType(feedback.getType());
        existingFeedback.setDate(feedback.getDate());

        feedbackrepository.save(existingFeedback);

        return existingFeedback;

    }

    @Override
    public void DeleteFeedback(long id) {
        feedbackrepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Feedback","Id", id));
        feedbackrepository.deleteById(id);
    }

}
