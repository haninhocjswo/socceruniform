package shop.soccerUniform.entity.dto;

import lombok.Data;
import shop.soccerUniform.entity.enumtype.ItemState;
import shop.soccerUniform.entity.enumtype.OptionType;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class ItemSaveForm {

    @NotNull
    public Long itemId;

    @NotBlank(message = "상품명은 필수값입니다.")
    public String name;

    @NotNull(message = "가격은 필수값입니다.")
    public Integer price;

    @NotNull(message = "입점업체를 선택해주세요.")
    public Long managerId;

    @NotNull(message = "카테고리는 필수값입니다.")
    @Min(value = 1, message = "카테고리를 선택해주세요.")
    public Long categoryId;

    @NotBlank(message = "제조사는 필수값입니다.")
    public String manufacturer;

    @NotBlank(message = "원산지는 필수값입니다.")
    public String origin;

    @NotNull(message = "상품옵션 유형을 선택해주세요.")
    public OptionType optionType;

    @NotNull(message = "상품상태를 선택해주세요.")
    public ItemState state;

    public String description;

    public Integer itemOption1ValueSize;

    public Integer itemOption2ValueSize;

    @NotBlank(message = "첫번째 옵션명은 필수값입니다.")
    public String firstOptionName;

    public String secondOptionName;

    //첫번재 옵션 값들
    public String valueName1_1;
    public String valueName1_2;
    public String valueName1_3;
    public String valueName1_4;
    public String valueName1_5;
    public String valueName1_6;
    public String valueName1_7;
    public String valueName1_8;
    public String valueName1_9;
    public String valueName1_10;

    //두번째 옵션 값들
    public String valueName2_1;
    public String valueName2_2;
    public String valueName2_3;
    public String valueName2_4;
    public String valueName2_5;
    public String valueName2_6;
    public String valueName2_7;
    public String valueName2_8;
    public String valueName2_9;
    public String valueName2_10;
    
    //첫번째 옵션 값 : 1 | 두번째 옵션 값 : 1~10
    public Integer stock_1_0;
    public Integer stock_1_1;
    public Integer stock_1_2;
    public Integer stock_1_3;
    public Integer stock_1_4;
    public Integer stock_1_5;
    public Integer stock_1_6;
    public Integer stock_1_7;
    public Integer stock_1_8;
    public Integer stock_1_9;
    public Integer stock_1_10;
    
    //첫번째 옵션 값 : 2 | 두번째 옵션 값 : 1~10
    public Integer stock_2_0;
    public Integer stock_2_1;
    public Integer stock_2_2;
    public Integer stock_2_3;
    public Integer stock_2_4;
    public Integer stock_2_5;
    public Integer stock_2_6;
    public Integer stock_2_7;
    public Integer stock_2_8;
    public Integer stock_2_9;
    public Integer stock_2_10;

    //첫번째 옵션 값 : 3 | 두번째 옵션 값 : 1~10
    public Integer stock_3_0;
    public Integer stock_3_1;
    public Integer stock_3_2;
    public Integer stock_3_3;
    public Integer stock_3_4;
    public Integer stock_3_5;
    public Integer stock_3_6;
    public Integer stock_3_7;
    public Integer stock_3_8;
    public Integer stock_3_9;
    public Integer stock_3_10;

    //첫번째 옵션 값 : 4 | 두번째 옵션 값 : 1~10
    public Integer stock_4_0;
    public Integer stock_4_1;
    public Integer stock_4_2;
    public Integer stock_4_3;
    public Integer stock_4_4;
    public Integer stock_4_5;
    public Integer stock_4_6;
    public Integer stock_4_7;
    public Integer stock_4_8;
    public Integer stock_4_9;
    public Integer stock_4_10;

    //첫번째 옵션 값 : 5 | 두번째 옵션 값 : 1~10
    public Integer stock_5_0;
    public Integer stock_5_1;
    public Integer stock_5_2;
    public Integer stock_5_3;
    public Integer stock_5_4;
    public Integer stock_5_5;
    public Integer stock_5_6;
    public Integer stock_5_7;
    public Integer stock_5_8;
    public Integer stock_5_9;
    public Integer stock_5_10;

    //첫번째 옵션 값 : 6 | 두번째 옵션 값 : 1~10
    public Integer stock_6_0;
    public Integer stock_6_1;
    public Integer stock_6_2;
    public Integer stock_6_3;
    public Integer stock_6_4;
    public Integer stock_6_5;
    public Integer stock_6_6;
    public Integer stock_6_7;
    public Integer stock_6_8;
    public Integer stock_6_9;
    public Integer stock_6_10;

    //첫번째 옵션 값 : 7 | 두번째 옵션 값 : 1~10
    public Integer stock_7_0;
    public Integer stock_7_1;
    public Integer stock_7_2;
    public Integer stock_7_3;
    public Integer stock_7_4;
    public Integer stock_7_5;
    public Integer stock_7_6;
    public Integer stock_7_7;
    public Integer stock_7_8;
    public Integer stock_7_9;
    public Integer stock_7_10;

    //첫번째 옵션 값 : 8 | 두번째 옵션 값 : 1~10
    public Integer stock_8_0;
    public Integer stock_8_1;
    public Integer stock_8_2;
    public Integer stock_8_3;
    public Integer stock_8_4;
    public Integer stock_8_5;
    public Integer stock_8_6;
    public Integer stock_8_7;
    public Integer stock_8_8;
    public Integer stock_8_9;
    public Integer stock_8_10;

    //첫번째 옵션 값 : 9 | 두번째 옵션 값 : 1~10
    public Integer stock_9_0;
    public Integer stock_9_1;
    public Integer stock_9_2;
    public Integer stock_9_3;
    public Integer stock_9_4;
    public Integer stock_9_5;
    public Integer stock_9_6;
    public Integer stock_9_7;
    public Integer stock_9_8;
    public Integer stock_9_9;
    public Integer stock_9_10;

    //첫번째 옵션 값 : 10 | 두번째 옵션 값 : 1~10
    public Integer stock_10_0;
    public Integer stock_10_1;
    public Integer stock_10_2;
    public Integer stock_10_3;
    public Integer stock_10_4;
    public Integer stock_10_5;
    public Integer stock_10_6;
    public Integer stock_10_7;
    public Integer stock_10_8;
    public Integer stock_10_9;
    public Integer stock_10_10;
}
