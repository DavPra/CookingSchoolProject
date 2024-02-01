package Cooking.School.Project.cookingSchool.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "couponcodes")
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CouponCodes {

    @Id
    @GeneratedValue(generator = "couponSequence")
    @GenericGenerator(
            name = "tagSequence",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "couponSequence"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "allocation_size", value = "1")
            }

    )

    private Long couponCodeId;

    @Setter
    private String couponCode;
}
