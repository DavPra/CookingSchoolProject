package Cooking.School.Project.cookingSchool.Services;

import Cooking.School.Project.cookingSchool.entities.Course;
import Cooking.School.Project.cookingSchool.entities.CourseTag;
import Cooking.School.Project.cookingSchool.entities.RecipeTag;
import Cooking.School.Project.cookingSchool.repository.CourseTagRepository;
import Cooking.School.Project.cookingSchool.repository.RecipeTagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagService {

    @Autowired
    CourseTagRepository courseTagRepository;

    @Autowired
    RecipeTagRepository recipeTagRepository;

    public TagService(){
    }

    //----------------------- Course Tags

    public CourseTag addCourseTag(CourseTag courseTag){
        courseTagRepository.save(courseTag);
        return courseTag;
    }

    public CourseTag getCourseTagById(Long id){
        return courseTagRepository.findById(id).get();
    }

    public void deleteCourseTagById(Long id){
        courseTagRepository.deleteById(id);
    }

    public CourseTag updateCourseTag(CourseTag courseTag){
        courseTagRepository.save(courseTag);
        return courseTag;
    }

    public List<CourseTag> getAllCourseTags() {
        return courseTagRepository.findAll();
    }


    //------------------------------------------ Recipe Tags
    public RecipeTag addRecipeTag(RecipeTag recipeTag){
        recipeTagRepository.save(recipeTag);
        return recipeTag;
    }

    public RecipeTag getRecipeTagById(Long id){
        return recipeTagRepository.findById(id).get();
    }

    public void deleteRecipeTagById(Long id){
        recipeTagRepository.deleteById(id);
    }

    public RecipeTag updateRecipeTag(RecipeTag recipeTag){
        recipeTagRepository.save(recipeTag);
        return recipeTag;
    }



}
