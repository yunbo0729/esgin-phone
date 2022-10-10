package com.example.demo.test;

import com.example.demo.common.DataValidationException;
import com.example.demo.util.AesUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class LianJieTest {
    private static final String PARAMKEY="3$%#$7sfdsS67dSgf53";

    public static void main(String[] args) throws JSONException {

        /*try {
            File file = ResourceUtils.getFile(ResourceUtils.CLASSPATH_URL_PREFIX + "static/images/user.png");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }*/
        //e店分享连接
        String str ="https://esign.lumes.cn/maintain/#/home/oldInterview?params=856DFB6302B634824EA163530703A35BE96CE04819651330C4C2D8563D840C4083298EFE1C7EF7BC1816848ABCC8BF53939018122F487F6F177FFE3AE13701A84C211F127BB6B884D3B9AF0EB8476026F434CE022962DE395146447EF785467D1C9133F554B560DA2D1F2CD4A511100C404D1BFEF176567FBE456F597B57F550838D32A6F9019B0D5CDC742A4ACB8AABF0D39C3A4B074B27AA62321B48AD82E09715646386095C0E80C7BB263464B1BD&channelType=AMIS";
        //e店补签连接地址 pid 工号 bid 省级id
         log.info("EDUrl={}",LianJieTest.getEDBQUrl("120000", "12011281000150"));
        //获取微信登入时的param值
        //log.info("param={}",LianJieTest.getParam("AMIS202103034209101182", "","",""));
        //解密param，显示json
        //String param = "ADE93D2760FB69082941E78D56CB20F26B97B9131B0ED0B58370F55DD23DEFB484FFD2B0CF71019D018670F1E2F7E3CE258E8EBD2753630C4DE91227079EF50F";
        String param = "F02BAF7CD995D42FE964188DC47F1FC413CCBD672DAC6A261CD1E3C0906C359C6EA69A41968D251F67E216B856DE9CB0A886F891D83BE6E6D4A4CA911B02E5BFA196C1531BCEF239063D333D02ED65A9F1E6B0DFBAA11A8B8FF4D11017CAC72F560071BCA9CBD469D8D1A9909DFB7DA35D751B42C8755164E493CEF7E09DD72EFB7EE9F31685DFC0CE55DA79520340F45D1C1CC8F993D9E23A3625A0AFE6FFB334452BF797FCC0453B71ABE115A5822A";
        //log.info("getJsonByParam={}",LianJieTest.getParamJson(param));
        //获取从e点分享连接跳转到移动端
        log.info(LianJieTest.getMaintainToPhone("37020600000944","370200","AMIS"));//42110300004413
        //log.info(LianJieTest.getMaintainToPhone1("111","420000","AMIS"));//42110300004413
    }

    public static com.alibaba.fastjson.JSONObject getParamJson(String param) throws JSONException {
        String dencryptJson = new String (AesUtil.decrypt(AesUtil.parseHexStr2Byte(param),PARAMKEY));
        String deParams = null;
        try{
            deParams = URLDecoder.decode(dencryptJson,"utf-8");
        }catch (UnsupportedEncodingException e){
            e.printStackTrace();
        }
        com.alibaba.fastjson.JSONObject jsonObject = com.alibaba.fastjson.JSONObject.parseObject(deParams);
        return jsonObject;
    }

    public static String getParam(String qrCodeId,String pid,String bigChannel,String pBid){
        Map<String ,String> map = new HashMap <String,String>();
        if(qrCodeId!=null&&qrCodeId!=""){
            map.put("qrCodeId",qrCodeId);
        }else{
            map.put("qrCodeId",qrCodeId);
            map.put("pid",pid);
            map.put("bigChannel",bigChannel);
            map.put("pBid",pBid);
        }
        String encryptParam = createEncParamStr(map);
        return encryptParam;
    }

    public static String getEDBQUrl(String bid,String pid){
        String urlStr ="https://esign.lumes.cn/maintain/#/home/oldContract?params=";
        Map<String,String> map = new HashMap<String,String>();
        map.put("UserGende","M");
        map.put("PhoneNum","220000");
        map.put("Branch",bid);
        map.put("Id",pid);
        map.put("Name","张三");
        String encryptParam = createEncParamStr(map);
        String url = urlStr+encryptParam+"&channelType=AMIS";
        return url;
    }

    public static String createEncParamStr(Map<String,String> paramMap){
        StringBuffer sb = new StringBuffer();
        for(String key : paramMap.keySet()){
            sb.append(key);
            sb.append("\":\"");
            sb.append(paramMap.get(key));
            sb.append("\",\"");
        }
        String str =sb.toString();
        String jsonStr = "{\""+str.substring(0,str.length()-3)+"\"}";
        return getParamStr(jsonStr);
    }

    public static String getParamStr(String jsonStr){
        try {
            String encodeJson = URLEncoder.encode(jsonStr,"UTF-8");
            String encryptJson = new String(AesUtil.parseByte2HexStr(AesUtil.encrypt(encodeJson,PARAMKEY)));
            String encodeStr = URLEncoder.encode(encryptJson,"utf-8");
            return encodeStr;
        } catch (Exception e) {
            throw new DataValidationException(e.getMessage());
        }
    }

    public static  String  getMaintainToPhone(String pid,String bid,String bogChannel){
        StringBuilder url = new StringBuilder();
        StringBuilder linkServer = new StringBuilder();
        try {
        linkServer.append("https://esign.lumes.cn");
        linkServer.append("/KeyServiceForEsignRecruit/esignrecruit/wsp/getUrl");
        linkServer.append("?param=");
        StringBuilder sb = new StringBuilder();
        sb.append("{\"");
        sb.append("pid");
        sb.append("\":\"");
        sb.append(pid);
        sb.append("\",\"");
        sb.append("bid");
        sb.append("\":\"");
        sb.append(bid);
        sb.append("\",\"");
        sb.append("bogChannel");
        sb.append("\":\"");
        sb.append(bogChannel);
        sb.append("\"}");
        String encodeJson = null;
        encodeJson = URLEncoder.encode(sb.toString(),"UTF-8");
        String encryptJson = new String(AesUtil.parseByte2HexStr(AesUtil.encrypt(encodeJson,"PARAMKEY")));
        log.info(encryptJson);
        linkServer.append(encodeJson);
        url.append("https://wx.weixinclic.cn/mic/oauth");
        url.append("?sub=");
        url.append("false");
        url.append("&officialid=");
        url.append("gh_8d38f0166412");
        url.append("&secret=");
        url.append("esign");
        url.append("&wsp_param=openid");
        url.append("@linkUrl=");
        url.append(URLEncoder.encode(linkServer.toString(),"utf-8"));
        } catch (Exception e) {
            throw new DataValidationException(e.getMessage());
        }
        return url.toString();
    }

    public static  String  getMaintainToPhone1(String pid,String bid,String bogChannel){
        StringBuilder url = new StringBuilder();
        StringBuilder linkServer = new StringBuilder();
        try {
            // 读取配置文件 中的信息进行组装url
            linkServer.append("https://esign.lumes.cn");
            linkServer.append("/keyServiceForEsignRecruit/esignrecruit/wsp/getUrl");//E加盟跳转的linkUrl的服务地址
            linkServer.append("?param=");
            StringBuilder sb = new StringBuilder();
            sb.append("{\"");
            sb.append("pid");
            sb.append("\":\"");
            sb.append(pid);
            sb.append("\",\"");
            sb.append("bid");
            sb.append("\":\"");
            sb.append(bid);
            sb.append("\",\"");
            sb.append("bigChannel");
            sb.append("\":\"");
            sb.append(bogChannel);
            sb.append("\"}");
            String encodeJson = URLEncoder.encode(sb.toString(),"UTF-8");
            String encryptJson = new String(AesUtil.parseByte2HexStr(AesUtil.encrypt(encodeJson,"3$%#$7sfdsS67dSgf53")));
            log.info(encryptJson);
            linkServer.append(encryptJson);
            url.append("https://wxtest.e-chinalife.com/mic/oauth");//  https://wx.weixinclic.cn/mic/oauth
            url.append("?sub=");
            url.append("false");
            url.append("&officialid=");
            url.append("gh_8d38f0166412");
            url.append("&secret=");
            url.append("esign");
            url.append("&wsp_param=openid");
            url.append("&linkUrl=");
            url.append(URLEncoder.encode(linkServer.toString(), "utf-8"));
        } catch (Exception e) {
            throw new DataValidationException(e.getMessage());
        }
        return url.toString();
    }
}



