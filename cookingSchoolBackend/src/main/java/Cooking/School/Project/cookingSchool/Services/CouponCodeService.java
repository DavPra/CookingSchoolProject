package Cooking.School.Project.cookingSchool.services; // Corrected the package name

import Cooking.School.Project.cookingSchool.entities.CouponCodes;
import Cooking.School.Project.cookingSchool.repository.CouponRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class CouponCodeService {

    @Autowired
    CouponRepository couponRepository;

    public CouponCodeService() {
    }

    public CouponCodes couponCode() {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        System.out.println(generatedString);

        CouponCodes couponCodes = new CouponCodes(); // Create a new instance of CouponCodes
        couponCodes.setCouponCode(generatedString); // Set the generated string as the coupon code
        return couponRepository.save(couponCodes);
    }
}
