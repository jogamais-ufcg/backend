package com.jogamais.ufcg.models.pk;

import com.jogamais.ufcg.models.Court;
import com.jogamais.ufcg.models.User;
import lombok.*;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.io.Serializable;

@Embeddable
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentPK  implements Serializable {
    private static final long serialVersionUID = 1L;

    @OneToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @OneToOne
    @JoinColumn(name = "COURT_ID")
    private Court court;
}
