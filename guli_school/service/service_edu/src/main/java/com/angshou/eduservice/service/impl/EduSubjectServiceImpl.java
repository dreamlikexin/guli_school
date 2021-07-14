package com.angshou.eduservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.angshou.eduservice.entity.EduSubject;
import com.angshou.eduservice.entity.excel.SubjectData;
import com.angshou.eduservice.listener.SubjectExcelListener;
import com.angshou.eduservice.mapper.EduSubjectMapper;
import com.angshou.eduservice.service.EduSubjectService;
import com.angshou.eduservice.entity.subject.OneSubject;
import com.angshou.eduservice.entity.subject.TwoSubject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author angshou
 * @since 2021-07-01
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {


	@Override
	public void saveSubject(MultipartFile file, EduSubjectService eduSubjectService) {
		try {
			InputStream in = file.getInputStream();
			EasyExcel.read(in, SubjectData.class, new SubjectExcelListener(eduSubjectService)).sheet().doRead();
		} catch (IOException e) {
			e.printStackTrace();
		}


	}

	@Override
	public List<OneSubject> getAllOneTwoSubject() {

		// 一级
		QueryWrapper<EduSubject> wrapperOne = new QueryWrapper<>();
		wrapperOne.eq("parent_id", "0");
		List<EduSubject> oneSubjects = baseMapper.selectList(wrapperOne);

		// 二级
		QueryWrapper<EduSubject> wrapperTwo = new QueryWrapper<>();
		wrapperOne.ne("parent_id", "0");
		List<EduSubject> twoSubjects = baseMapper.selectList(wrapperTwo);

		// 封装数据
		List<OneSubject> finallSubject = new ArrayList<>();
		// for (EduSubject one : oneSubjects) {
		// 	OneSubject oneSubject = new OneSubject();
		// 	oneSubject.setId(one.getId());
		// 	oneSubject.setTitle(one.getTitle());
		// 	List<TwoSubject> twoSubjectList = new ArrayList<>();
		// 	for (EduSubject two:twoSubjects){
		// 		TwoSubject twoSubject = new TwoSubject();
		// 		twoSubject.setId(two.getId());
		// 		twoSubject.setTitle(two.getTitle());
		// 		twoSubjectList.add(twoSubject);
		// 	}
		// 	oneSubject.setChildren(twoSubjectList);
		// }
		for (EduSubject one : oneSubjects) {
			OneSubject oneSubject = new OneSubject();
			BeanUtils.copyProperties(one, oneSubject);

			List<TwoSubject> twoSubjectList = new ArrayList<>();
			for (EduSubject two : twoSubjects) {
				if (two.getParentId().equals(one.getId())) {
					TwoSubject twoSubject = new TwoSubject();
					BeanUtils.copyProperties(two, twoSubject);
					twoSubjectList.add(twoSubject);
				}
			}

			oneSubject.setChildren(twoSubjectList);
			finallSubject.add(oneSubject);
		}

		return finallSubject;
	}
}
