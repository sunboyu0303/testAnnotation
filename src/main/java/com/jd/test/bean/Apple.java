package com.jd.test.bean;

import com.jd.test.annotation.FruitColor;
import com.jd.test.annotation.FruitName;
import com.jd.test.annotation.FruitProvider;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by sunboyu on 2017/9/25.
 */
@Data
@Slf4j
public class Apple {

    @FruitName("Apple")
    private String appleName;

    @FruitColor(fruitColor = FruitColor.Color.RED)
    private String appleColor;

    @FruitProvider(id = 1, name = "陕西红富士集团", address = "陕西省西安市延安路89号红富士大厦")
    private String appleProvider;

    public void displayName() {
        log.info("水果的名字是：苹果");
    }
}
