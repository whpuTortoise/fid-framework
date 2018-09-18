package com.tortoise.quake;

import java.io.IOException;

import com.gexin.rp.sdk.base.IPushResult;
import com.gexin.rp.sdk.base.impl.SingleMessage;
import com.gexin.rp.sdk.base.impl.Target;
import com.gexin.rp.sdk.exceptions.RequestException;
import com.gexin.rp.sdk.http.IGtPush;
import com.gexin.rp.sdk.template.LinkTemplate;
import com.gexin.rp.sdk.template.NotificationTemplate;
import com.gexin.rp.sdk.template.style.Style0;

/**  
 * @Project: quake
 * @Title: GeTuiPush.java
 * @Package com.tortoise.quake
 * @Description: TODO
 * @author WangZhi
 * @date 2018年6月15日 下午4:41:10
 * @Copyright: 2018 
 * @version V1.0  
 */
public class GeTuiPush {

    //定义常量, appId、appKey、masterSecret 采用本文档 "第二步 获取访问凭证 "中获得的应用配置
    private static String appId = "ULlV4nc3fY52I06eUsDsd7";
    private static String appKey = "FLHdeZ4pqrA8D34dCWbMV1";
    private static String masterSecret = "hmarXW5hrZ8gi6pxTDJWC2";
    private static String host = "http://sdk.open.api.igexin.com/apiex.htm";


    public static void sendMessage(String cid, String title, String text) {
        IGtPush push = new IGtPush(host, appKey, masterSecret);
        // 消息模板
        NotificationTemplate template = getNotificationTemplate(title, text, "");
        SingleMessage message = new SingleMessage();
        message.setOffline(true);
        // 离线有效时间，单位为毫秒，可选
        message.setOfflineExpireTime(24 * 3600 * 1000);
        message.setData(template);
        // 可选，1为wifi，0为不限制网络环境。根据手机处于的网络情况，决定是否下发
        message.setPushNetWorkType(0);
        Target target = new Target();
        target.setAppId(appId);
        target.setClientId(cid);
        //target.setAlias(Alias);
        IPushResult ret = null;
        try {
            ret = push.pushMessageToSingle(message, target);
        } catch (RequestException e) {
            e.printStackTrace();
            ret = push.pushMessageToSingle(message, target, e.getRequestId());
        }
        if (ret != null) {
            System.out.println(ret.getResponse().toString());
        } else {
            System.out.println("服务器响应异常");
        }
    }
    
    private static LinkTemplate getLinkTemplate(String title, String text, String url) {
        LinkTemplate template = new LinkTemplate();
        // 设置APPID与APPKEY
        template.setAppId(appId);
        template.setAppkey(appKey);

        Style0 style = new Style0();
        // 设置通知栏标题与内容
        style.setTitle(title);
        style.setText(text);
        // 配置通知栏图标
        style.setLogo("icon.png");
        // 配置通知栏网络图标
        style.setLogoUrl("");
        // 设置通知是否响铃，震动，或者可清除
        style.setRing(true);
        style.setVibrate(true);
        style.setClearable(true);
        template.setStyle(style);

        // 设置打开的网址地址
        template.setUrl(url);
        return template;
    }
    
    /**
     * 获取消息模板
    * @param  title 标题
    * @param  text 内容
    * @return NotificationTemplate     
    * @throws
     */
    private static NotificationTemplate getNotificationTemplate(String title, String text, String transmissionContent) {
        NotificationTemplate template = new NotificationTemplate();
        // 设置APPID与APPKEY
        template.setAppId(appId);
        template.setAppkey(appKey);

        Style0 style = new Style0();
        // 设置通知栏标题与内容
        style.setTitle(title);
        style.setText(text);
        // 配置通知栏图标
        style.setLogo("icon.png");
        // 配置通知栏网络图标
        style.setLogoUrl("");
        // 设置通知是否响铃，震动，或者可清除
        style.setRing(true);
        style.setVibrate(true);
        style.setClearable(true);
        template.setStyle(style);

        // 透传消息设置，1为强制启动应用，客户端接收到消息后就会立即启动应用；2为等待应用启动
        template.setTransmissionType(1);
        template.setTransmissionContent(transmissionContent); //请输入您要透传的内容
        return template;
    }
    
    public static void main(String[] args) throws IOException {
    	sendMessage("b1a5d7bdd2a4bbb457cd1c9290413ce4", "标题", "内容");
    }
    
}
