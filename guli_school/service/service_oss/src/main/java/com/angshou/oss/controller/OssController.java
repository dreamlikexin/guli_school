package com.angshou.oss.controller;

import com.angshou.commonutils.Result;
import com.angshou.oss.service.OssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Stack;

/**
 * @author adminPC--昂首灬
 * @date 2021-07-01 10:44
 * @description
 */


@RestController
@RequestMapping("/eduoss/fileoss")
@CrossOrigin
public class OssController {

	@Autowired
	private OssService ossService;


	// 上传头像的方法
	@PostMapping("uploadfile")
	public Result uploadOssFile(MultipartFile file) {

		String str="";
		String[] st = str.split(" ");
		Stack<String> stack = new Stack<>();
		for (String s: st
		     ) {

		}
		String newStr ="";
		while(!stack.isEmpty()){
			newStr += stack.pop();
		}



		String url = ossService.uploadFileAvatar(file);
		return Result.ok().data("url", url);
	}

}
