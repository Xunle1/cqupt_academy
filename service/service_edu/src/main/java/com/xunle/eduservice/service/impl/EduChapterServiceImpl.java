package com.xunle.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xunle.eduservice.entity.chapter.EduChapter;
import com.xunle.eduservice.entity.chapter.ChapterVo;
import com.xunle.eduservice.entity.chapter.EduVideo;
import com.xunle.eduservice.entity.chapter.VideoVo;
import com.xunle.eduservice.mapper.EduChapterMapper;
import com.xunle.eduservice.service.EduChapterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xunle.eduservice.service.EduVideoService;
import com.xunle.servicebase.handler.GuliException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author xunle
 * @since 2021-05-31
 */
@Service
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter> implements EduChapterService {

    @Autowired
    private EduVideoService eduVideoService;

    @Override
    public List<ChapterVo> getChapterVideoByCourseId(String courseId) {

        //根据课程id查询课程所有章节
        QueryWrapper chapterWrapper = new QueryWrapper();
        chapterWrapper.eq("course_id",courseId);
        List<EduChapter> chapterList = baseMapper.selectList(chapterWrapper);

        //根据课程id查询小节
        QueryWrapper videoWrapper = new QueryWrapper();
        videoWrapper.eq("course_id",courseId);
        List<EduVideo> videoList = eduVideoService.list(videoWrapper);

        //最后返回的集合
        List<ChapterVo> returnChapterList = new ArrayList<>();

        //遍历查询章节list集合，封装
        for (int i = 0; i < chapterList.size(); i++) {
            EduChapter chapter = chapterList.get(i);
            ChapterVo chapterVo = new ChapterVo();
            BeanUtils.copyProperties(chapter,chapterVo);
            returnChapterList.add(chapterVo);

            List<VideoVo> returnVideoList = new ArrayList<>();
            for (int j = 0; j < videoList.size(); j++) {
                EduVideo eduVideo = videoList.get(j);
                if (chapterVo.getId().equals(eduVideo.getChapterId())) {
                    VideoVo videoVo = new VideoVo();
                    BeanUtils.copyProperties(eduVideo, videoVo);

                    returnVideoList.add(videoVo);
                }
            }
            chapterVo.setChildren(returnVideoList);
        }

        return returnChapterList;
    }

    /**
     * 删除章节，如果章节下存在小节，则需要先删除小节，否则不让删除
     * @param chapterId 章节id
     * @return
     */
    @Override
    public boolean deleteChapter(String chapterId) {
        QueryWrapper<EduVideo> wrapper = new QueryWrapper<>();
        wrapper.eq("chapter_id",chapterId);

        int count = eduVideoService.count(wrapper);

        if (count > 0) {
            throw new GuliException(20001, "请先删除小节");
        }

        int result = baseMapper.deleteById(chapterId);
        return result > 0;
    }

    @Override
    public void removeChapterByCourseId(String courseId) {
        QueryWrapper<EduChapter> wrapper = new QueryWrapper<>();
        wrapper.eq("course_Id",courseId);
        baseMapper.delete(wrapper);
    }
}
