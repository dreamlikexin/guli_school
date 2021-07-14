package com.angshou.eduservice.entity.chapter;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author adminPC--昂首灬
 * @date 2021-07-02 11:05
 * @description
 */

@Data
public class ChapterVo {

	private String id;

	private String title;

	private List<VideoVo> children = new ArrayList<>();
}
