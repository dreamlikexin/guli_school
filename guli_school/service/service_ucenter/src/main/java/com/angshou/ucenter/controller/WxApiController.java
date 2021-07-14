package com.angshou.ucenter.controller;

import com.angshou.commonutils.JwtUtils;
import com.angshou.servicebase.exceptionhandler.GuliException;
import com.angshou.ucenter.entity.UcenterMember;
import com.angshou.ucenter.service.UcenterMemberService;
import com.angshou.ucenter.utils.ConstantWxUtils;
import com.angshou.ucenter.utils.HttpClientUtils;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;

/**
 * @author adminPC--昂首灬
 * @date 2021-07-07 10:38
 * @description
 */


// @RestController 此注解会直接返回JSON，而不是二维码
@Controller//只是请求地址不需要返回数据，所以不需要@Responsebody
@CrossOrigin
@RequestMapping("/api/ucenter/wx")
public class WxApiController {

	@Autowired
	private UcenterMemberService ucenterMemberService;


	//获取扫描人的信息添加数据
	@GetMapping("callback")
	public String callback(String code, String state) {
		try {
			//1.获取code值，临时票据，类似于验证码

			//2.拿着code请求微信固定的地址，得到两个值access_token和openid
			String baseAccessTokenUrl = "https://api.weixin.qq.com/sns/oauth2/access_token" +
					"?appid=%s" +
					"&secret=%s" +
					"&code=%s" +
					"&grant_type=authorization_code";
			//拼接三个参数
			String accessTokenUrl = String.format(
					baseAccessTokenUrl,
					ConstantWxUtils.WX_OPEN_APP_ID,
					ConstantWxUtils.WX_OPEN_APP_SECRET,
					code
			);

			//使用httpClient请求上面这个拼接好的地址，返回的字符串里面包含access_token和openid
			String accessTokenInfo = HttpClientUtils.get(accessTokenUrl);
			//从accessTokenInfo字符串获取access_token和openid两个值
			//使用Gson讲字符串转换为map集合，根据map里面key获取对应值
			Gson gson = new Gson();
			HashMap map = gson.fromJson(accessTokenInfo, HashMap.class);
			String access_token = (String) map.get("access_token");
			String openid = (String) map.get("openid");


			//将扫描人用户的信息添加在数据库中
			//先判断这个数据是否已经在数据库中
			UcenterMember member = ucenterMemberService.getOpenIdMember(openid);

			if (member == null) {
				//3.拿着得到的access_token和openid两个值，再去请求固定的地址，获取扫码人的信息
				String baseUserInfoUrl = "https://api.weixin.qq.com/sns/userinfo"
						+ "?access_token=%s"
						+ "&openid=%s";
				//拼接两个参数
				String userInfoUrl = String.format(baseUserInfoUrl, access_token, openid);
				//发送请求获取扫码用户的信息
				String useInfo = HttpClientUtils.get(userInfoUrl);
				//获取返回useInfo里面扫描人的信息
				HashMap UserInfoMap = gson.fromJson(useInfo, HashMap.class);
				String nickname = (String) UserInfoMap.get("nickname");
				String headimgurl = (String) UserInfoMap.get("headimgurl");

				member = new UcenterMember();
				member.setOpenid(openid);
				member.setNickname(nickname);
				member.setAvatar(headimgurl);
				ucenterMemberService.save(member);
			}

			// 原：cookie 方法无法实现跨域，只能在一个域内
			// 改：使用 jwt 根据member 对象生成 token字符串
			String jwtToken = JwtUtils.getJwtToken(member.getId(), member.getNickname());
			// 最终：返回首页面，通过路劲传递 token 字符串
			return "redirect:http://localhost:3000?token=" + jwtToken;
			// return "redirect:http://localhost:3000";

		} catch (Exception e) {
			e.printStackTrace();
			throw new GuliException(20001, "微信登录失败");
		}
	}

	@GetMapping("/login")
	public String getWxCode() {
		//固定地址，后面拼接参数，%s相当于？代表占位符
		String baseUrl = "https://open.weixin.qq.com/connect/qrconnect" +
				"?appid=%s" +
				"&redirect_uri=%s" +
				"&response_type=code" +
				"&scope=snsapi_login" +
				"&state=%s" +
				"#wechat_redirect";

		String encode = null;
		try {
			encode = URLEncoder.encode(ConstantWxUtils.WX_OPEN_REDIRECT_URL, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String url = String.format(
				baseUrl,
				ConstantWxUtils.WX_OPEN_APP_ID,
				encode,
				"atguigu"
		);
		return "redirect:" + url;
	}
}
