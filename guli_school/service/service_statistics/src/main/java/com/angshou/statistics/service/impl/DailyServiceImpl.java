package com.angshou.statistics.service.impl;

import com.angshou.commonutils.Result;
import com.angshou.statistics.client.UcenterClient;
import com.angshou.statistics.entity.Daily;
import com.angshou.statistics.mapper.DailyMapper;
import com.angshou.statistics.service.DailyService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 网站统计日数据 服务实现类
 * </p>
 *
 * @author angshou
 * @since 2021-07-12
 */
@Service
public class DailyServiceImpl extends ServiceImpl<DailyMapper, Daily> implements DailyService {

	@Autowired
	private UcenterClient ucenterClient;


	@Override
	public void registerCount(String day) {

		Result result = ucenterClient.countRegister(day);
		Integer countRegister = (Integer) result.getData().get("countRegister");

		QueryWrapper<Daily> wrapper = new QueryWrapper<>();
		wrapper.eq("date_calculated", day);
		Daily daily1 = baseMapper.selectOne(wrapper);

		if (daily1 == null) {
			daily1 = new Daily();
			daily1.setRegisterNum(countRegister);
			daily1.setDateCalculated(day);
			daily1.setCourseNum(RandomUtils.nextInt(100, 200));
			daily1.setLoginNum(RandomUtils.nextInt(100, 200));
			daily1.setVideoViewNum(RandomUtils.nextInt(100, 200));
			baseMapper.insert(daily1);
		} else {
			daily1.setRegisterNum(countRegister);
			baseMapper.updateById(daily1);
		}

	}

	@Override
	public Map<String, Object> getShowData(String type, String begin, String end) {
		QueryWrapper<Daily> wrapper = new QueryWrapper<>();
		wrapper.between("date_calculated", begin, end);
		wrapper.select(type, "date_calculated");
		wrapper.orderByAsc("date_calculated");
		List<Daily> dailyList = baseMapper.selectList(wrapper);

		List<String> listDay = new ArrayList<>();
		List<Integer> listNum = new ArrayList<>();

		for (int i = 0; i < dailyList.size(); i++) {
			Daily daily = dailyList.get(i);
			listDay.add(daily.getDateCalculated());

			switch (type) {
				case "login_num":
					listNum.add(daily.getLoginNum());
					break;
				case "register_num":
					listNum.add(daily.getRegisterNum());
					break;
				case "video_view_num":
					listNum.add(daily.getVideoViewNum());
					break;
				case "course_num":
					listNum.add(daily.getCourseNum());
					break;
				default:
					break;
			}
		}

		Map<String, Object> map = new HashMap<>();
		map.put("listDay", listDay);
		map.put("listNum", listNum);

		return map;
	}


}
