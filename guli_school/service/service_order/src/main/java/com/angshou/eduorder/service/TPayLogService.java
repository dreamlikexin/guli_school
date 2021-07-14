package com.angshou.eduorder.service;

import com.angshou.eduorder.entity.TPayLog;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 支付日志表 服务类
 * </p>
 *
 * @author angshou
 * @since 2021-07-12
 */
public interface TPayLogService extends IService<TPayLog> {

	Map createNative(String orderNo);

	Map queryPayStatus(String orderNo);

	void updateOrderStatus(Map<String, String> map);
}
