package com.huawei.petsstore01.entity;



import com.huawei.petsstore01.R;

import java.util.ArrayList;

public class GoodsInfo {

    public int id;
    /**
     * 名称
     */
    public String name;
    /**
     * 描述
     */
    public String description;
    /**
     * 价格
     */
    public float price;
    /**
     * 大图的保存路径
     */
    public String picPath;
    /**
     * 大图的资源编号
     */
    public int pic;


    private static String[] mNameArray = {
            "xxxx牌 猫粮", "xxxx牌 猫粮", "xxxx牌 猫粮", "xxxx牌 猫粮", "xxxx牌 猫粮", "xxxx牌 猫粮"
    };

    private static String[] mDescArray = {
            "xxxx牌 猫粮",
            "xxxx牌 猫粮",
            "xxxx牌 猫粮",
            "xxxx牌 猫粮",
            "xxxx牌 猫粮",
            "xxxx牌 猫粮"
    };
    /**
     * 声明一个手机商品的价格数组
     */
    private static float[] mPriceArray = {199, 99, 399, 299, 298, 239};
    /**
     * 声明一个手机商品的大图数组
     */
    private static int[] mPicArray = {
            R.drawable.pic001, R.drawable.pic001, R.drawable.pic001,
            R.drawable.pic001, R.drawable.pic001, R.drawable.pic001
    };

    /**
     * 获取默认的手机信息列表
     */
    public static ArrayList<GoodsInfo> getDefaultList() {
        ArrayList<GoodsInfo> goodsList = new ArrayList<GoodsInfo>();
        for (int i = 0; i < mNameArray.length; i++) {
            GoodsInfo info = new GoodsInfo();
            info.id = i;
            info.name = mNameArray[i];
            info.description = mDescArray[i];
            info.price = mPriceArray[i];
            info.pic = mPicArray[i];
            goodsList.add(info);
        }
        return goodsList;
    }
}
