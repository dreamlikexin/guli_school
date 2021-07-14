package com.angshou.eduservice.entity.subject;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author adminPC--昂首灬
 * @date 2021-07-01 19:58
 * @description
 */

@Data
public class OneSubject {

	private String id;


	private String title;


	private List<TwoSubject> children = new ArrayList<>();

}
