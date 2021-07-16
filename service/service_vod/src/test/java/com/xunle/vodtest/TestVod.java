package com.xunle.vodtest;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadVideoRequest;
import com.aliyun.vod.upload.resp.UploadVideoResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.vod.model.v20170321.GetPlayInfoRequest;
import com.aliyuncs.vod.model.v20170321.GetPlayInfoResponse;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;
import com.xunle.vod.utils.InitClient;
import org.junit.Test;

import java.util.List;

/**
 * @author xunle
 */
public class TestVod {
    @Test
    public void testGetAddress() throws Exception{
        //初始化客户端
        DefaultAcsClient client = InitClient.initVodClient("LTAI5tNAirpUwT2ZdKwuKQne",
                "c07hM0fMkvBIeyXOY3up4s89QR73tK");

        //创建获取视频地址request和response
        GetPlayInfoRequest request = new GetPlayInfoRequest();
        GetPlayInfoResponse response = new GetPlayInfoResponse();

        //设置视频ID
        request.setVideoId("3fdf87e70cd24111928adc29df074344");

        //传递request
        response = client.getAcsResponse(request);

        List<GetPlayInfoResponse.PlayInfo> playInfoList = response.getPlayInfoList();
        //播放地址
        for (GetPlayInfoResponse.PlayInfo playInfo : playInfoList) {
            System.out.print("PlayInfo.PlayURL = " + playInfo.getPlayURL() + "\n");
        }
        //Base信息
        System.out.print("VideoBase.Title = " + response.getVideoBase().getTitle() + "\n");
    }

    @Test
    public void testGetPlayAuth() throws Exception{
        //初始化客户端
        DefaultAcsClient client = InitClient.initVodClient("LTAI5tNAirpUwT2ZdKwuKQne",
                "c07hM0fMkvBIeyXOY3up4s89QR73tK");

        //创建获取视频地址request和response
        GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
        GetVideoPlayAuthResponse response = new GetVideoPlayAuthResponse();

        //设置视频ID
        request.setVideoId("e432709cee294543ba1a75cff14f2f45");

        //传递request
        response = client.getAcsResponse(request);

        //播放凭证
        System.out.print("PlayAuth = " + response.getPlayAuth() + "\n");
        //VideoMeta信息
        System.out.print("VideoMeta.Title = " + response.getVideoMeta().getTitle() + "\n");
    }

    @Test
    public void testUpload() {
        String accessKeyId = "LTAI5tNAirpUwT2ZdKwuKQne";
        String accessKeySecret = "c07hM0fMkvBIeyXOY3up4s89QR73tK";
        /* 视频标题 */
        String title = "testUpload";
        /* 上传文件绝对路径(必须包含扩展名) */
        String fileName = "D:\\File\\backend\\谷粒学院\\项目资料\\1-阿里云上传测试视频\\6 - What If I Want to Move Faster.mp4";

        //上传视频的方法
        UploadVideoRequest request = new UploadVideoRequest(accessKeyId, accessKeySecret, title, fileName);
        /* 可指定分片上传时每个分片的大小，默认为2M字节 */
        request.setPartSize(2 * 1024 * 1024L);
        /* 可指定分片上传时的并发线程数，默认为1 */
        request.setTaskNum(1);

        UploadVideoImpl uploader = new UploadVideoImpl();
        UploadVideoResponse response = uploader.uploadVideo(request);
        if (response.isSuccess()) {
            System.out.println("VideoId=" + response.getVideoId());
        } else {
            /* 如果设置回调URL无效，不影响视频上传，可以返回VideoId同时会返回错误码。其他情况上传失败时，VideoId为空，此时需要根据返回错误 */
            System.out.println("VideoId=" + response.getVideoId());
            System.out.println("ErrorCode=" + response.getCode());
            System.out.println("ErrorMessage=" + response.getMessage());
        }
    }
}
