package com.maybe.live.service;

import com.maybe.live.kit.qiniu.QiNiuPicResp;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author: Tate
 * @date: 2016/5/26 11:53
 */
public interface IFileService {

    List<QiNiuPicResp> uploadPicToQiNiu(MultipartFile[] files);
}
