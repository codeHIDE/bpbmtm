package com.bypay.util;

import com.bypay.domain.clientTool.T01002;

public class YinLianUtil {
        //加签密钥(商户RSA私钥)
        private static String merchantPrivateKey = "MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBAOTYIqkjyCrIHdIeOAvTwaggG6mAhXU6byrW5SIqAXE3znaiBeOeDVNWJzs/pQtXuTn6fB1LoU3Q93hPcLkh7kdoH3+BJDzoPWZ5tPyzgua2nad9xMNNphfRYDVTiEoAxOnFc3aNI22gse+wPS0Ll29/LGp+z3e/p+e1cRP/ibFJAgMBAAECgYEA3pVbISisiPAcEUNTQC23LtAMF9Hp/RvZBNIADDrPLFAbgUgWck5Ip8YkYnyFC4NHphz8m4H0Yrvd+CdMfMWD/BkPRf3eafhnJlHGKyGqsAXLmGh/mvJbleE3NH9LS1N/0+pPam58mAjvkujxoPQ0v5BxHyS7r14lBMkvxiXN9AECQQD8B2zTpvsXDWJFwjKYmKRkWCs3JOaOJmWX6MTY3qPSE6mFW/93blDAs1kEioB01ZsbKiE3fIubZVcFEzI90nCXAkEA6HMxd+GYWA7+UdeOklhz/XhBdtlsOeHZDG8glOFhsHJguURcnov2TG4G5L1t+qdnpZzTeNKVrSyT2ECE4gVJHwJAVwiZZF39x/AvR7fQkTHlU2G/SsPLert3ygXwNJRuLlXr7MngZvYJnQJSc2cBBVfewHrEDc1MyNUuP+ppJ0BM8QJBALdi6gwiNwaCDbKT1S8wCZJXZY5WSkQAIjTlF1dd2KxUEGsZu9h5o3747wdXS4UMvYCzEUOpH9zX5mwdurh2YxECQQDuPsVpoJlevwbIuRymGzvYvVZvDP2N+O4rN0lrJnlhTXkYdsRLSw92QcBX0jRqjwl/LwEMPt8EaK25xJ6rEc07";
        //加密密钥
        private static String aesKey = "xbDy7BIi4rgG+Bp0m4JpQA==";
        //请求地址
        private static String submitUrl = "http://w.joinxx.cn/rdPay/payProcess";
        //验签密钥(支付平台公钥)
        private static String channelPublicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCfRRqiTyiDRvgPwAnHm+odB6kEY1O51Zh5rlr3iSYEgDKfO00yD6ZCAh6MlKfYT0DD+WKN91lt6t9g/u0Cw2WJwGeUiOEWUDso/MiOGmdGYrfsarEzGCTSRmu1tIdwFKNi9HThcMTs7aU99lBtoGIYu2mxsXoWnLbdExZ9TaOBgwIDAQAB";
        //商户号
//        private static String merId = "2016101910232196";
        private static String merId = "0000000000000136";
        
        public static void main(String[] args) throws Exception {
            //快捷支付（网页跳转，不限卡号）
            /*T01001 t01001 = new T01001(merchantPrivateKey, aesKey, merId, submitUrl);
            t01001.payment();*/
            //快捷支付（网页跳转，限制卡号）
            T01002 t01002 = new T01002(merchantPrivateKey, aesKey, merId, submitUrl);
            t01002.payment();
            //快捷支付（API直联，短信认证）
            /*T01011 t01011 = new T01011(merchantPrivateKey, aesKey, merId, submitUrl, channelPublicKey);
            t01011.quickPayReq();*/
            //快捷支付（API直联，直接支付）
//            T01013 t01013 = new T01013(merchantPrivateKey, aesKey, merId, submitUrl, channelPublicKey);
//            t01013.quickPayReq();
            
            //订单查询
            /*Q01001 q01001 = new Q01001(merchantPrivateKey, aesKey, merId, submitUrl, channelPublicKey);
            q01001.query("ZF20170208173508");*/
            
            //扫码支付（持卡人主扫）
            /*T02002 t02002 = new T02002(merchantPrivateKey, aesKey, merId, submitUrl, channelPublicKey);
            t02002.payment();*/

            //扫码支付（持卡人被扫）
            /*T02001 t02001 = new T02001(merchantPrivateKey, aesKey, merId, submitUrl, channelPublicKey);
            t02001.payment();*/
            
            //扫码支付查询
            /*Q02001 q02001  = new Q02001(merchantPrivateKey, aesKey, merId, submitUrl, channelPublicKey);
            q02001.query("ZF20170208171038");*/
            
            //退款
            /*T01021 t01021 = new T01021(merchantPrivateKey, aesKey, merId, submitUrl, channelPublicKey);
            t01021.refund("ZF20170208171038");*/
            
            //退款结果查询
            /*Q01021 q01021  = new Q01021(merchantPrivateKey, aesKey, merId, submitUrl, channelPublicKey);
            q01021.query("TK20170208172335");*/
            
            //公众号支付
            /*T02021 t02021 = new T02021(merchantPrivateKey, aesKey, merId, submitUrl, channelPublicKey);
            t02021.payment();*/
            //密钥生成方法
    /*      Map<String, Object> keyMap = RSA.genKeyPair();
            System.out.println("RSA私钥："+RSA.getPrivateKey(keyMap));
            System.out.println("RSA公钥："+RSA.getPublicKey(keyMap));*/
        }
}
