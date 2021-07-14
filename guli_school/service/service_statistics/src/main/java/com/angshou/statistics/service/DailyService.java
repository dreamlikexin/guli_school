package com.angshou.statistics.service;

import com.angshou.statistics.entity.Daily;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 网站统计日数据 服务类
 * </p>
 *
 * @author angshou
 * @since 2021-07-12
 */
public interface DailyService extends IService<Daily> {

	void registerCount(String day);

	Map<String, Object> getShowData(String type, String begin, String end);
}
