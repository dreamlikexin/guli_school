package com.angshou.msm.service;

import java.util.Map;

/**
 * @author adminPC--昂首灬
 * @date 2021-07-06 16:58
 * @description
 */
public interface MsmService {
	boolean sendMsm(Map<String, Object> param, String phone);
}
