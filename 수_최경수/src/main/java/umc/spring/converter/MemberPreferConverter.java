package umc.spring.converter;

import umc.spring.domain.FoodCategory;
import umc.spring.domain.Member;
import umc.spring.domain.mapping.MemberPrefer;

import java.util.List;
import java.util.stream.Collectors;

public class MemberPreferConverter {
  public static List<MemberPrefer> toMemberPreferList(List<FoodCategory> foodCategoryList) {
    return foodCategoryList.stream()
            .map(foodCategory ->
                    MemberPrefer.builder()
                            .foodcategory(foodCategory)
                            .build()
            ).collect(Collectors.toList());
  }
}
