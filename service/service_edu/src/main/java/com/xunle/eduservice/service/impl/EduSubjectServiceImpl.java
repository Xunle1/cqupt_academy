package com.xunle.eduservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xunle.eduservice.entity.subject.EduSubject;
import com.xunle.eduservice.entity.subject.OneSubject;
import com.xunle.eduservice.entity.subject.TwoSubject;
import com.xunle.eduservice.entity.excel.SubjectData;
import com.xunle.eduservice.listener.ExcelListener;
import com.xunle.eduservice.mapper.EduSubjectMapper;
import com.xunle.eduservice.service.EduSubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2021-05-25
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {

    //添加课程分类
    @Override
    public void saveSubject(MultipartFile file, EduSubjectService eduSubjectService) {
        try{
            //文件输入流
            InputStream is = file.getInputStream();
            //调用方法进行读取
            EasyExcel.read(is, SubjectData.class, new ExcelListener(eduSubjectService) ).sheet().doRead();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<OneSubject> getAllSubject() {
        //查询所有一级分类
        QueryWrapper<EduSubject> wrapper1 = new QueryWrapper<>();
        wrapper1.eq("parent_id",0);
        List<EduSubject> oneSubjectList = baseMapper.selectList(wrapper1);

        //查询所有二级分类
        QueryWrapper<EduSubject> wrapper2 = new QueryWrapper<>();
        wrapper2.ne("parent_id",0);
        List<EduSubject> twoSubjectList = baseMapper.selectList(wrapper2);


        //最后返回的集合
        List<OneSubject> returnSubjects = new ArrayList<>();

        //封装一级分类
        for (int i = 0; i < oneSubjectList.size(); i++) {
            EduSubject oneSubject = oneSubjectList.get(i);
            OneSubject returnOneSubject = new OneSubject();
//            oneSubject.setId(subject.getId());
//            oneSubject.setTitle(subject.getTitle());
            BeanUtils.copyProperties(oneSubject,returnOneSubject);
            returnSubjects.add(returnOneSubject);

            //一级分类的children集合
            List<TwoSubject> returnTwoSubjects = new ArrayList<>();
            //封装二级分类
            for (int j = 0; j < twoSubjectList.size(); j++) {
                EduSubject twoSubject = twoSubjectList.get(j);
                if (twoSubject.getParentId().equals(oneSubject.getId())) {
                    TwoSubject returnTwoSubject = new TwoSubject();
                    BeanUtils.copyProperties(twoSubject,returnTwoSubject);
                    returnTwoSubjects.add(returnTwoSubject);
                } 
            }
            //将二级分类装入一级分类
            returnOneSubject.setChildren(returnTwoSubjects);
        }
        return returnSubjects;
    }
}
