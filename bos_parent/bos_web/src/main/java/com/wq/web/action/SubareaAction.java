package com.wq.web.action;

import com.wq.domain.Region;
import com.wq.domain.Subarea;
import com.wq.service.SubareaService;
import com.wq.utils.FileUtils;
import com.wq.web.action.base.BaseAction;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.servlet.ServletOutputStream;
import java.util.List;

@Controller
@Scope("prototype")
public class SubareaAction extends BaseAction<Subarea> {

    @Autowired
    public SubareaService subareaService;

    //查询所有分区, 以及根据组合条件查询分区
    public String pageQuery(){
        DetachedCriteria criteria = pageBean.getDetachedCriteria();
        //动态添加过滤条件
        String addresskey = model.getAddresskey();
        if (StringUtils.isNotBlank(addresskey)){
            criteria.add(Restrictions.like("addresskey","%"+addresskey+"%"));
        }

        Region region = model.getRegion();

        if (region!=null){
            String province = region.getProvince();
            String city = region.getCity();
            String district = region.getDistrict();

            //多表关联查询,使用别名的方式
            criteria.createAlias("region","r");

            if (StringUtils.isNotBlank(province)){
                criteria.add(Restrictions.like("r.province","%"+province+"%"));
            }
            if (StringUtils.isNotBlank(city)){
                criteria.add(Restrictions.like("r.city","%"+city+"%"));
            }
            if (StringUtils.isNotBlank(district)){
                criteria.add(Restrictions.like("r.district","%"+district+"%"));
            }
        }


        subareaService.pageQuery(pageBean);
        java2Json(pageBean, new String[]{"currentPage","pageSize","decidedzone","detachedCriteria","subareas"});
        return NONE;
    }

    //保存分区
    public String save(){
        subareaService.save(model);
        return LIST;
    }

    //导出数据
    public String exportXls() throws  Exception{
        //查找出所有分区数据
        List<Subarea> subareas = subareaService.findAll();

        //创建Excel文档
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("分区数据");        //创建标签页
        Row row = sheet.createRow(0);     //创建标题行
        //给标题名称赋予数据
        row.createCell(0).setCellValue("分区编号");
        row.createCell(1).setCellValue("开始编号");
        row.createCell(2).setCellValue("结束编号");
        row.createCell(3).setCellValue("位置信息");
        row.createCell(4).setCellValue("省市区");

        //填充数据
        for (Subarea sb: subareas){
            Row row1 = sheet.createRow(sheet.getLastRowNum()+1);
            row1.createCell(0).setCellValue(sb.getId());
            row1.createCell(1).setCellValue(sb.getStartnum());
            row1.createCell(2).setCellValue(sb.getEndnum());
            row1.createCell(3).setCellValue(sb.getPosition());
            row1.createCell(4).setCellValue(sb.getRegion().getProvinceCityRegion());
        }

        //使用输出流进行下载,一个流两个头信息

        //下载显示文件名
        String fileName = "分区数据.xls";
        //动态获取mimeType
        String mimeType = ServletActionContext.getServletContext().getMimeType(fileName);
        //获取浏览器类型
        String agent = ServletActionContext.getRequest().getHeader("User-Agent");
        fileName = FileUtils.encodeDownloadFilename(fileName,agent);

        ServletOutputStream outputStream = ServletActionContext.getResponse().getOutputStream();
        ServletActionContext.getResponse().setContentType(mimeType);
        ServletActionContext.getResponse().setHeader("content-disposition","attachment;filename="+fileName);
        workbook.write(outputStream);
        return NONE;
    }

    //查找未关联定区的分区
    public String listAjax(){

        List<Subarea> subareas = subareaService.findListNotAssociation();
        java2Json(subareas, new String[]{"decidedzone","region"});
        return NONE;
    }

    //查寻区域分布，分组的方式
    public String findProvinceByGroup(){
        List<Object> list = subareaService.findProvinceByGroup();
        java2Json(list, new String[]{});
        return NONE;
    }
}
