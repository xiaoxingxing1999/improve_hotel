package com.example.hotel.enums;

/**
 * @Author stormbroken
 * Create by 2020/05/21
 * @Version 1.0
 **/

public enum VIPType {
    NOT_VIP("不是会员"),
    NORMAL_LEVEL1("一级普通会员"),
    NORMAL_LEVEL2("二级普通会员"),
    NORMAL_LEVEL3("三级普通会员"),
    NORMAL_LEVEL4("四级普通会员"),
    NORMAL_LEVEL5("五级普通会员"),
    ENTERPRISE("企业会员");

    private String value;

    VIPType(String value) {
        this.value = value;
    }
    @Override
    public String toString() {
        return value;
    }

    public static boolean isVip(VIPType vipType){
        if(vipType.equals(NORMAL_LEVEL1) || vipType.equals(NORMAL_LEVEL2)
                ||vipType.equals(NORMAL_LEVEL3)||vipType.equals(NORMAL_LEVEL4)
                ||vipType.equals(NORMAL_LEVEL5)||vipType.equals(ENTERPRISE)){
            return true;
        }
        return false;
    }

    public static VIPType getByIndex(int level){
        VIPType vipType = null;
        switch (level){
            case 0:
                vipType = VIPType.NOT_VIP;
                break;
            case 1:
                vipType = VIPType.NORMAL_LEVEL1;
                break;
            case 2:
                vipType = VIPType.NORMAL_LEVEL2;
                break;
            case 3:
                vipType = VIPType.NORMAL_LEVEL3;
                break;
            case 4:
                vipType = VIPType.NORMAL_LEVEL4;
                break;
            case 5:
                vipType = VIPType.NORMAL_LEVEL5;
                break;
            case 6:
                vipType = VIPType.ENTERPRISE;
                break;
            default:
                System.out.println("VIP Level Error");
                break;
        }
        return vipType;
    }
}
