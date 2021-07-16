package com.xunle.vod.service.impl;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.req.UploadVideoRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.aliyun.vod.upload.resp.UploadVideoResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.aliyuncs.vod.model.v20170321.DeleteVideoResponse;
import com.xunle.servicebase.handler.GuliException;
import com.xunle.vod.service.VodService;
import com.xunle.vod.utils.ConstantPropertiesUtils;
import com.xunle.vod.utils.InitClient;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

/**
 * @author xunle
 */
@Service
public class VodServiceImpl implements VodService {
    @Override
    public String uploadVideo(MultipartFile file) {
        try {
            /* 上传文件绝对路径(必须包含扩展名) */
            String fileName = file.getOriginalFilename();

            /* 视频标题 */
            String title = fileName.substring(0,fileName.lastIndexOf("."));

            InputStream inputStream = file.getInputStream();

            UploadStreamRequest request = new UploadStreamRequest(ConstantPropertiesUtils.KEY_ID, ConstantPropertiesUtils.KEY_SECRET
                    , title, fileName, inputStream);
            UploadVideoImpl uploader = new UploadVideoImpl();
            UploadStreamResponse response = uploader.uploadStream(request);
            String videoId = null;
            if (response.isSuccess()) {
                videoId = response.getVideoId();
            } else { //如果设置回调URL无效，不影响视频上传，可以返回VideoId同时会返回错误码。其他情况上传失败时，VideoId为空，此时需要根据返回错误码分析具体错误原因
                videoId = response.getVideoId();
                System.out.print("ErrorCode=" + response.getCode() + "\n");
                System.out.print("ErrorMessage=" + response.getMessage() + "\n");
            }
            return videoId;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void removeVideo(String videoId) {
        try {
            DefaultAcsClient client = InitClient.initVodClient(ConstantPropertiesUtils.KEY_ID, ConstantPropertiesUtils.KEY_SECRET);
            DeleteVideoRequest request = new DeleteVideoRequest();
            request.setVideoIds(videoId);

            DeleteVideoResponse response = client.getAcsResponse(request);
            System.out.print("RequestId = " + response.getRequestId() + "\n");
        } catch (ClientException e) {
            System.out.print("ErrorMessage = " + e.getLocalizedMessage());
            throw new GuliException(20001, "删除视频失败");
        }
    }

    //删除多个视频
    @Override
    public void removeVideoList(List videoIdList) {
        try {
            DefaultAcsClient client = InitClient.initVodClient(ConstantPropertiesUtils.KEY_ID, ConstantPropertiesUtils.KEY_SECRET);
            DeleteVideoRequest request = new DeleteVideoRequest();

            //videoList的值转换成 id1,id2,id3
            String videoIds = StringUtils.join(videoIdList.toArray(), ",");
            request.setVideoIds(videoIds);

            DeleteVideoResponse response = client.getAcsResponse(request);
            System.out.print("RequestId = " + response.getRequestId() + "\n");
        } catch (ClientException e) {
            System.out.print("ErrorMessage = " + e.getLocalizedMessage());
            throw new GuliException(20001, "删除视频失败");
        }
    }
}
