package net.artux.nextcrm.model.order.goods;

import lombok.Data;
import lombok.Getter;
import net.artux.nextcrm.model.CDto;

@Data
@Getter
public class GoodDto extends CDto {

    private Long categoryId;
    private String categoryName;
    private String name;
    private String code;
    private float price;
    private String photo;

}
