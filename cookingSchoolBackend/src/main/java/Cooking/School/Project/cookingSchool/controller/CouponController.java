package Cooking.School.Project.cookingSchool.controller;

import Cooking.School.Project.cookingSchool.services.CouponCodeService; // Corrected the package name
import Cooking.School.Project.cookingSchool.entities.CouponCodes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController // Adjust the mapping based on your application
public class CouponController {

    private final CouponCodeService couponCodeService;

    @Autowired
    public CouponController(CouponCodeService couponCodeService) {
        this.couponCodeService = couponCodeService;
    }

    @GetMapping("/generate/coupon")
    public @ResponseBody CouponCodes generateCouponCode() {
        return couponCodeService.couponCode();
    }
}
