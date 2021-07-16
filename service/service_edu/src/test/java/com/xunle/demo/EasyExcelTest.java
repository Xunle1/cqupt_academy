package com.xunle.demo;

import com.alibaba.excel.EasyExcel;
import com.xunle.eduservice.entity.excel.DemoData;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xunle
 */
public class EasyExcelTest {
    @Test
    public void testWrite() {
        //设置写入excel文件的地址和文件名
        String fileName = "D:\\File\\backend\\guli_parent\\subject.xlsx";

        //调用写方法
        EasyExcel.write(fileName, DemoData.class).sheet("学生列表").doWrite(getList());
    }

    @Test
    public void testRead() {
        //设置读取文件的路径
        String fileName = "D:\\File\\backend\\guli_parent\\subject.xlsx";

        EasyExcel.read(fileName, DemoData.class, new ExcelListener()).sheet().doRead();
    }

    public List<DemoData> getList() {
        List<DemoData> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            String sname = "lucy";
            String sno = i + "";
            DemoData stu = new DemoData(sno, sname);
            list.add(stu);
        }
        return list;
    }
}
