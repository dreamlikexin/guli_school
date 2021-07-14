package com.angshou.eduorder.service;

import com.angshou.eduorder.entity.TOrder;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 订单 服务类
 * </p>
 *
 * @author angshou
 * @since 2021-07-12
 */
public interface TOrderService extends IService<TOrder> {

	String createOrder(String courseId, String memberId);
}
