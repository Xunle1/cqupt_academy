package com.xunle.eduservice.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xunle.eduservice.entity.subject.EduSubject;
import com.xunle.eduservice.entity.excel.SubjectData;
import com.xunle.eduservice.service.EduSubjectService;
import com.xunle.servicebase.handler.GuliException;

import java.util.Map;

/**
 * @author xunle
 */
public class ExcelListener extends AnalysisEventListener<SubjectData> {

    private EduSubjectService eduSubjectService;

    public ExcelListener() {}

    public ExcelListener(EduSubjectService eduSubjectService) {
        this.eduSubjectService = eduSubjectService;
    }

    @Override
    public void invoke(SubjectData data, AnalysisContext context) {
        if (null == data) {
            throw new GuliException(20001, "文件数据为空或文件不存在");
        }

        //一行一行读取
        //添加一级分类
        EduSubject oneSubject = existOneSubject(data.getOneSubjectName());
        if (null == oneSubject) {
            oneSubject = new EduSubject();
            oneSubject.setParentId("0");
            oneSubject.setTitle(data.getOneSubjectName());
            eduSubjectService.save(oneSubject);
        }

        String pid = oneSubject.getId();
        //添加二级分类
        EduSubject twoSubject = existTwoSubject(data.getTwoSubjectName(), pid);
        if (null == twoSubject) {
            twoSubject = new EduSubject();
            twoSubject.setTitle(data.getTwoSubjectName());
            twoSubject.setParentId(pid);
            eduSubjectService.save(twoSubject);
        }
    }

    @Override
    public void invokeHeadMap(Map headMap, AnalysisContext context) {

    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {

    }

    //判断一级分类是否存在
    private EduSubject existOneSubject(String name) {
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title",name);
        wrapper.eq("parent_id",0);
        EduSubject oneSubject = eduSubjectService.getOne(wrapper);
        return oneSubject;
    }

    //判断二级分类是否存在
    private EduSubject existTwoSubject(String name, String parentId) {
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title",name);
        wrapper.eq("parent_id",parentId);
        EduSubject twoSubject = eduSubjectService.getOne(wrapper);
        return twoSubject;
    }
}
