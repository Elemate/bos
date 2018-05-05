package com.wq;

import com.wq.utils.PinYin4jUtils;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;

public class Pinyin4JTest {
    @Test
    public void test1(){
        String province = "湖北省";
        String city = "黄冈市";
        String district = "罗田县";

        province = province.substring(0,province.length()-1);
        city = city.substring(0,city.length()-1);
        district = district.substring(0,district.length()-1);

        String[] py = PinYin4jUtils.getHeadByString(province+city+district);
        String shortcode = StringUtils.join(py);

        String citycode = PinYin4jUtils.hanziToPinyin(city,"");
    }
}
