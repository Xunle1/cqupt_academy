package com.xunle.eduservice.service;

import com.xunle.eduservice.entity.chapter.EduChapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xunle.eduservice.entity.chapter.ChapterVo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author xunle
 * @since 2021-05-31
 */
public interface EduChapterService extends IService<EduChapter> {

    List<ChapterVo> getChapterVideoByCourseId(String courseId);

    boolean deleteChapter(String chapterId);

    void removeChapterByCourseId(String courseId);
}
