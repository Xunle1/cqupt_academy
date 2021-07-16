package com.xunle.eduservice.service;

import com.xunle.eduservice.entity.subject.EduSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xunle.eduservice.entity.subject.OneSubject;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author testjava
 * @since 2021-05-25
 */
public interface EduSubjectService extends IService<EduSubject> {

    void saveSubject(MultipartFile file, EduSubjectService eduSubjectService);

    List<OneSubject> getAllSubject();
}
