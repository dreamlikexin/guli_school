package com.angshou.eduorder.service.impl;

import com.angshou.commonutils.ordervo.CourseWebVoOrder;
import com.angshou.commonutils.ordervo.UcenterMemberOrder;
import com.angshou.eduorder.client.EduClient;
import com.angshou.eduorder.client.UcenterClient;
import com.angshou.eduorder.entity.TOrder;
import com.angshou.eduorder.mapper.TOrderMapper;
import com.angshou.eduorder.service.TOrderService;
import com.angshou.eduorder.utils.OrderNoUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单 服务实现类
 * </p>
 *
 * @author angshou
 * @since 2021-07-12
 */
@Service
public class TOrderServiceImpl extends ServiceImpl<TOrderMapper, TOrder> implements TOrderService {

	@Autowired
	private EduClient eduClient;

	@Autowired
	private UcenterClient ucenterClient;


	@Override
	public String createOrder(String courseId, String memberId) {

		// 远程调用————课程信息
		CourseWebVoOrder courseDto = eduClient.getCourseInfoInfo(courseId);


		// 远程调用————会员信息
		UcenterMemberOrder ucenterMember = ucenterClient.getInfo(memberId);


		TOrder order = new TOrder();
		order.setOrderNo(OrderNoUtil.getOrderNo());
		order.setCourseId(courseId);
		order.setCourseTitle(courseDto.getTitle());
		order.setCourseCover(courseDto.getCover());
		order.setTeacherName(courseDto.getTeacherName());
		order.setTotalFee(courseDto.getPrice());
		order.setMemberId(memberId);
		order.setMobile(ucenterMember.getMobile());
		order.setNickname(ucenterMember.getNickname());
		order.setStatus(0);
		order.setPayType(1);
		baseMapper.insert(order);

		return order.getOrderNo();
	}
}
