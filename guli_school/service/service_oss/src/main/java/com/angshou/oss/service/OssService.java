package com.angshou.oss.service;


import org.springframework.web.multipart.MultipartFile;

/**
 * @author adminPC--昂首灬
 * @date 2021-07-01 10:43
 * @description
 */

public interface OssService {


	public String uploadFileAvatar(MultipartFile file);


}
