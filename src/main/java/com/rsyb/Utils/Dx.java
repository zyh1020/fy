package com.rsyb.Utils;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.yunpian.sdk.YunpianClient;
import com.yunpian.sdk.model.Result;
import com.yunpian.sdk.model.SmsSingleSend;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Dx {
    /* 手机验证码 */
    private static final String APIKEY ="b9ee7e463d38120faa62a1b2b81a7642";
    private static final String  QM ="您的验证码是";
    /* 用于生成字符 */
    private static final String SYMBOLS = "0123456789"; // 数字
    private static final Random RANDOM = new SecureRandom();

    /* 行为的二次验证 */
    private static final String AUTH_URL = "https://captcha.yunpian.com/v1/api/authenticate";
    private static final String APPID = "a8bef13adacb466680c390c30b27397e";
    private static final String secretId = "9f845d232a834a72b772a7da0ff9832e";
    private static final String secretKey = "eeaf8e84de894f42a2056a7ac85117fa";



    /* 判断二次行为验证 */
    public static Boolean isOk(String token,String authenticate) throws IOException {
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("captchaId", APPID);
        paramMap.put("secretId", secretId);
        paramMap.put("token", token);
        paramMap.put("authenticate", authenticate);
        paramMap.put("version", "1.0");
        paramMap.put("timestamp", String.valueOf(System.currentTimeMillis()));
        paramMap.put("nonce", String.valueOf(new Random().nextInt(99999)));
        //paramMap.put("user", "user"); // user is optional

        /* 计算签名 */
        String signature = genSignature(secretKey, paramMap);
        paramMap.put("signature", signature);

        StringBuilder sb = new StringBuilder();
        PostMethod postMethod = new PostMethod(AUTH_URL);
        postMethod.addRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        paramMap.forEach((k, v) -> {
            postMethod.addParameter(k, v);
        });

        HttpClient httpClient = new HttpClient();
        int status = httpClient.executeMethod(postMethod);
        String responseBodyAsString = postMethod.getResponseBodyAsString();
        System.out.println(responseBodyAsString);//{"code":0,"msg":"ok"}
        /*  判断结果 */
        JsonObject result = new JsonParser().parse(responseBodyAsString).getAsJsonObject();
        Integer code = result.get("code").getAsInt();
        if(code != null && code == 0){
            return true;
        }else{
            return false;
        }
    }

    //加密
    private static String genSignature(String secretKey, Map<String, String> params) {
        String[] keys = params.keySet().toArray(new String[0]);
        Arrays.sort(keys);
        StringBuilder sb = new StringBuilder();
        for (String key : keys) {
            sb.append(key).append(params.get(key));
        }
        sb.append(secretKey);
        return DigestUtils.md5Hex(sb.toString());
    }


    /**
     * 使用SDK发送单条短信,智能匹配短信模板
     * @param mobile 接收的手机号,仅支持单号码发送
     * @param Context 是随机生成的字符串
     */
    public static Boolean sendCode(String mobile,String Context) {
        //初始化client,apikey作为所有请求的默认值(可以为空)
        YunpianClient clnt = new YunpianClient(APIKEY).init();

        Map<String, String> param = clnt.newParam(2);
        param.put(YunpianClient.MOBILE, mobile);
        param.put(YunpianClient.TEXT, QM + Context);
        Result<SmsSingleSend> r = clnt.sms().single_send(param);
        //获取返回结果，返回码:r.getCode(),返回码描述:r.getMsg(),API结果:r.getData(),其他说明:r.getDetail(),调用异常:r.getThrowable()
        Integer code = r.getCode();
        System.out.println(code);
        //账户:clnt.user().* 签名:clnt.sign().* 模版:clnt.tpl().* 短信:clnt.sms().* 语音:clnt.voice().* 流量:clnt.flow().* 隐私通话:clnt.call().*

        //最后释放client
        clnt.close();

        if(code != null && code == 0){
            return true;
        }else {
            return false;
        }
    }

    /**
     * 获取长度为 6 的随机数字
     * @return 随机数字
     */
    public static String getNonce_str() {

        // 如果需要4位，那 new char[4] 即可，其他位数同理可得
        char[] nonceChars = new char[6];
        for (int index = 0; index < nonceChars.length; ++index) {
            nonceChars[index] = SYMBOLS.charAt(RANDOM.nextInt(SYMBOLS.length()));
        }
        return new String(nonceChars);
    }

}