package com.jogamais.ufcg.dto;

import com.jogamais.ufcg.models.Court;
import com.jogamais.ufcg.models.Email;
import com.jogamais.ufcg.models.enumeration.StatusEmail;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import javax.validation.constraints.NotBlank;


@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class EmailDTO {

    @NotBlank
    private String ownerRef;

    @NotBlank
    @javax.validation.constraints.Email
    private String emailFrom;

    @NotBlank
    @javax.validation.constraints.Email
    private String emailTo;

    @NotBlank
    private String subject;

    @NotBlank
    private String text;

    public Email getModel() {
        return new Email(
                null,
                this.ownerRef,
                this.emailFrom,
                this.emailTo,
                this.subject,
                this.text,
                null,
                StatusEmail.SENT);
    }
}
