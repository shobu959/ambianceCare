package com.stackroute.feedbackservice.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document("feedback")
public class Feedback {

    @Id
    String feedbackId;

    @NotNull("bookingId cannot be null")
    String bookingId;

    @NotNull("customerEmail cannot be null")
    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}")
    String customerEmail;

    @Min(value = 1,message = "Rating should be greater than 0")
    @Max(value = 5,message = "Rating should be less than 5")
    Integer customerRating;

    @NotNull("customerComment cannot be null")
    String customerComment;
}
