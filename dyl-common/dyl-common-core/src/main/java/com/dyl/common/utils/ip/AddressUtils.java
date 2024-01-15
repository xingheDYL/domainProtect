//package com.dyl.common.utils.ip;
//
//import org.apache.poi.ss.util.RegionUtil;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import com.dyl.common.config.BaseConfig;
//import com.dyl.common.utils.StringUtils;
//
///**
// * 获取地址类
// *
// * @author dyl
// */
//public class AddressUtils {
//    private static final Logger log = LoggerFactory.getLogger(AddressUtils.class);
//
//    // 未知地址
//    public static final String UNKNOWN = "XX XX";
//
//    public static String getRealAddressByIP(String ip) {
//        String address = UNKNOWN;
//        // 内网不查询
//        if (IpUtils.internalIp(ip)) {
//            return "内网IP";
//        }
//        if (BaseConfig.isAddressEnabled()) {
//            try {
//                String rspStr = RegionUtil.getRegion(ip);
//                if (StringUtils.isEmpty(rspStr)) {
//                    log.error("获取地理位置异常 {}", ip);
//                    return UNKNOWN;
//                }
//                String[] obj = rspStr.split("\\|");
//                String region = obj[2];
//                String city = obj[3];
//
//                return String.format("%s %s", region, city);
//            } catch (Exception e) {
//                log.error("获取地理位置异常 {}", e);
//            }
//        }
//        return address;
//    }
//}
