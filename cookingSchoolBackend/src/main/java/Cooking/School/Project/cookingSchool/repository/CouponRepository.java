package Cooking.School.Project.cookingSchool.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import Cooking.School.Project.cookingSchool.entities.CouponCodes;

@Repository
public interface CouponRepository extends JpaRepository<CouponCodes, Long>{
}
