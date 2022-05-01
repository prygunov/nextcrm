package net.artux.nextcrm.model.address;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.artux.nextcrm.model.BaseEntity;
import org.springframework.util.StringUtils;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Data
@Getter
@RequiredArgsConstructor
@Table(name = "address")
@Entity
public class  AddressEntity extends BaseEntity {

    private Integer index;
    @NotBlank
    private String region;
    @NotBlank
    private String city;
    @NotBlank
    private String street;
    @NotBlank
    private Integer house;
    private Integer entrance;
    private Integer building;
    private Integer flat;

    public String toString(){
        String base = "г. " + city + " ул. " + street + " д." + house;

        if(building != null)
            base += " строение " + building;
        if(entrance != null)
            base += " подъезд " + entrance;
        if(flat != null)
            base += " квартира " + flat;

        return base;
    }
}
