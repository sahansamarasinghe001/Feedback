package com.example.api.Repository;

import com.example.api.Model.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface FeedbackRepository extends JpaRepository<Feedback,Long> {

    @Query(value = "SELECT * FROM feedback WHERE user_id = ?1", nativeQuery = true)
    List<Feedback> findByUserId(String userid);
    @Query(value = "SELECT * FROM feedback WHERE type = ?1", nativeQuery = true)
    List<Feedback> getAllFeedbackType(String userType);
    @Query(value = "SELECT * FROM feedback WHERE date >= ?1 AND date<=?2", nativeQuery = true)
    List<Feedback> getBetweenDates(String Date1,String Date2);


}
