package com.example.api.Service.impl;

import com.example.api.Exception.ResourceNotFoundException;
import com.example.api.Model.Feedback;
import com.example.api.Repository.FeedbackRepository;
import com.example.api.Service.FeedBackService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class FeedBackServiceimpl implements FeedBackService {

    @Autowired
    private FeedbackRepository feedbackrepository;

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

    @Override
    public void downloadFeedbackCsv(HttpServletResponse response) throws IOException {

        response.setContentType("text/csv");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Feedbacks_" + currentDateTime + ".csv";
        response.setHeader(headerKey, headerValue);

        List<Feedback> feedbacks = getAllFeedback();
        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);

        String[] csvHeader = {"feedback_id", "content", "date", "subject", "type","user_id"};
        String[] nameMapping = {"feedbackId", "content", "date", "subject", "type","userId"};
        csvWriter.writeHeader(csvHeader);

        for (Feedback data : feedbacks) {
            csvWriter.write(data, nameMapping);
        }
        csvWriter.close();
    }

}
