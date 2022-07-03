package com.example.api.Controller;


import com.example.api.Model.Feedback;
import com.example.api.Service.FeedBackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.util.List;

@RestController
@RequestMapping("/api")
public class FeedbackController {

    @Autowired
    private FeedBackService feedbackservice;



    //Save feedback data
    @PostMapping("save")
    public ResponseEntity<Feedback> saveFeedBack(@RequestBody Feedback feedback){
      return new ResponseEntity<Feedback>(feedbackservice.saveFeedback(feedback), HttpStatus.CREATED);
    }

    //Get all data from the database
    @GetMapping("getall")
    public List<Feedback> getAllFeedbackData(){
        return feedbackservice.getAllFeedback();
    }

    //Retrieving data by feedback id
    @GetMapping("data/{id}")
   public ResponseEntity<Feedback> getFeedbackById(@PathVariable("id") long id){
     return new ResponseEntity<Feedback>(feedbackservice.getFeedbackById(id), HttpStatus.OK);
   }

   //Retrieving data by user id
    @GetMapping("user/{id}")
    public List<Feedback> getFeedbackByUserId(@PathVariable("id") String id){
        return feedbackservice.getUserId(id);
    }

    //Get feedback data by type
    @GetMapping("type/{type}")
    public List<Feedback> getByFeedbackDataType(@PathVariable("type") String type){
        return feedbackservice.getByFeedbackType(type);
    }

    //Retrieve data between 2 dates
    @GetMapping("date")
    public List<Feedback> getBetweenDates(@RequestParam("from") String Date1,
                                          @RequestParam("to") String Date2){

            return feedbackservice.getBetweenDates(Date1,Date2);

    }

    //Update data by feedback id
    @PutMapping("update/{id}")
    public ResponseEntity<Feedback> UpdateFeedback(@PathVariable("id") long id,
                                                   @RequestBody Feedback feedback){

        return new ResponseEntity<Feedback>(feedbackservice.updateFeedback(feedback,id),HttpStatus.OK);
    }

    //Delete data by feedback id
    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteFeedback(@PathVariable("id") long id){

        feedbackservice.DeleteFeedback(id);
        return new ResponseEntity<String>("Data Deleted",HttpStatus.OK);
    }

    //Retrieve data as CSV
    @GetMapping("getcsv")
    public ResponseEntity<?> exportToCSV(HttpServletResponse response) throws IOException {
        feedbackservice.downloadFeedbackCsv(response);
       return ResponseEntity.ok().build();
    }


}
