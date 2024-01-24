package cydeo.dto;


import com.fasterxml.jackson.annotation.*;
import cydeo.enums.EducationLevel;
import cydeo.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)// ignoring unknown fields
@JsonInclude(JsonInclude.Include.NON_NULL) // hiding null value fields
public class TeacherDTO {

    @JsonIgnore // hiding id ignoring this field
    private Long id;

    private String firstName;
    private String lastName;
    private String phoneNumber;

    private String email;
    private String username;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)//hiding password for get Requests
    private String password;

    private LocalDate birthday;

    private Status status;

    private EducationLevel educationLevel;
    @JsonManagedReference (value = "teacher-address-reference")
    private AddressDTO address;

}