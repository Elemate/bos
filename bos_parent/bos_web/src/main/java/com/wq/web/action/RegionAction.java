package com.wq.web.action;

import com.wq.domain.Region;
import com.wq.service.RegionService;
import com.wq.utils.PinYin4jUtils;
import com.wq.web.action.base.BaseAction;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

@Controller
@Scope("prototype")
public class RegionAction extends BaseAction<Region> {

    @Autowired
    public RegionService regionService;

    public File myFile;
    public String q;    //combobox请求参数


    //导入xls到数据库
    public String importXls () throws Exception{

        List regions = new ArrayList<Region>();

        //获取xls中的数据,并将所有数据放入集合中
        HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(myFile));
        HSSFSheet sheet = workbook.getSheetAt(0);
        for (Row rows : sheet) {
            if (rows.getRowNum()==0){
                continue;
            }
            String id = rows.getCell(0).getStringCellValue();
            String province = rows.getCell(1).getStringCellValue();
            String city = rows.getCell(2).getStringCellValue();
            String district = rows.getCell(3).getStringCellValue();
            String postcode = rows.getCell(4).getStringCellValue();

            //简码以及城市编码的实现
            province = province.substring(0,province.length()-1);
            city = city.substring(0,city.length()-1);
            district = district.substring(0,district.length()-1);

            String[] py = PinYin4jUtils.getHeadByString(province+city+district);
            String shortcode = StringUtils.join(py);

            String citycode = PinYin4jUtils.hanziToPinyin(city,"");
            Region region = new Region(id,province,city,district,postcode,shortcode,citycode,null);
            regions.add(region);
        }

        //批量保存数据
        regionService.saveBatch(regions);
        return NONE;
    }

    //获取所有区域
    public String list() throws Exception{

        regionService.pageQuery(pageBean);

        //查询区域,并响应到客户端
        java2Json(pageBean,new String[]{"currentPage","pageSize","detachedCriteria","subareas"});

        return NONE;
    }

    //根据编码和城市简码模糊查询区域
    public String regionLists(){
        List<Region> regions = null;
        if (StringUtils.isNotBlank(q)){
            regions = regionService.findRegionByQ(q);
        } else {
            regions = regionService.findAll();
        }
        java2Json(regions, new String[]{"subareas"});
        return NONE;
    }
    public void setMyFile(File myFile) {
        this.myFile = myFile;
    }

    public String getQ() {
        return q;
    }

    public void setQ(String q) {
        this.q = q;
    }
}
