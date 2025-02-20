package cl.gvidal.techtest.model;

import jakarta.persistence.*;
import lombok.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="phones")
public class Phones {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_phone")
    private Long idPhone;
    @Column(name = "telefono")
    private Long number;
    @Column(name = "city_code")
    private int citycode;
    @Column(name = "country_code")
    private int countrycode;
}
