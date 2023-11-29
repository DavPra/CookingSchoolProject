package Cooking.School.Project.cookingSchool.Services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import Cooking.School.Project.cookingSchool.entities.Rating;
import Cooking.School.Project.cookingSchool.repository.RatingRepository;

import java.util.List;


@Service
public class RatingService {

    @Autowired
    RatingRepository ratingRepository;

    public RatingService(){
    }

    @Transactional
    public Rating addRating(Rating rating){
        ratingRepository.save(rating);
        return rating;
    }

    public Rating getRatingById(Long id){
        return ratingRepository.findById(id).get();
    }

    public void deleteRatingById(Long id){
        ratingRepository.deleteById(id);
    }

    @Transactional
    public Rating updateRating(Rating rating){
        ratingRepository.save(rating);
        return rating;
    }

    public List<Rating> getAllRatings() {
        return ratingRepository.findAll();
    }
}
