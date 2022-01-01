package com.example.api.Controller;


import com.example.api.Model.Feedback;
import com.example.api.Service.FeedBackService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
public class FeedbackController {

    private FeedBackService feedbackservice;
    public FeedbackController(FeedBackService feedbackservice) {
        this.feedbackservice = feedbackservice;
    }


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
    public void exportToCSV(HttpServletResponse response) throws IOException {

        response.setContentType("text/csv");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Feedbacks_" + currentDateTime + ".csv";
        response.setHeader(headerKey, headerValue);

        List<Feedback> feedbacks = feedbackservice.getAllFeedback();
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
