package com.angshou.eduservice.service;

import com.angshou.eduservice.entity.EduSubject;
import com.angshou.eduservice.entity.subject.OneSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author angshou
 * @since 2021-07-01
 */
public interface EduSubjectService extends IService<EduSubject> {

	void saveSubject(MultipartFile file, EduSubjectService eduSubjectService);

	List<OneSubject> getAllOneTwoSubject();
}
