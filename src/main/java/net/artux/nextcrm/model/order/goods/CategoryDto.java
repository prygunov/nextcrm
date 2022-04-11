package net.artux.nextcrm.model.order.goods;

import lombok.Data;
import lombok.Getter;
import net.artux.nextcrm.model.CDto;

import javax.persistence.ManyToOne;

@Data
@Getter
public class CategoryDto extends CDto {

    private String parentName;
    private Long parentId;
    private String name;
    private String description;

}
