package com.example.dell.midterm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by roger on 2017/11/25.
 */

public class filtersData {
    private Map<String, ArrayList<String>> filters = new HashMap<>();
    private static filtersData instance;
    private HashMap<filtersData.filterType, String> filters_selected = new HashMap<>();

    private filtersData() {
        this.init();
    }

    public enum filterType {first_char_filter, native_place_modern_filter, native_place_ancient_filter,
        camp_filter, age_filter, sex_filter, name_filter}

    public static filtersData getInstance() {
        if (filtersData.instance == null) {
            filtersData.instance = new filtersData();
        }
        return filtersData.instance;
    }

    private void init() {
        ArrayList<String> first_char_filter = new ArrayList<>();
        first_char_filter.add("不限");
        for (int i = 0; i < 23; i++) {
            String item = Character.toString((char)((int)'A' + i));
            first_char_filter.add(item);
        }
        filters.put("first_char_filter", first_char_filter);

        String[] modern_cities = new String[] {"不限", "北京", "天津", "上海", "重庆", "河北", "河南",
                "云南", "辽宁", "黑龙江", "湖南", "安徽", "山东", "新疆", "江苏", "浙江", "江西", "湖北",
                "广西", "甘肃", "山西", "内蒙古", "陕西", "吉林", "福建", "贵州", "广东", "青海", "西藏",
                "四川", "宁夏", "海南", "台湾", "香港"};
        ArrayList<String> native_place_modern_filter = new ArrayList<String>();
        for (int i = 0; i < modern_cities.length; i++) {
            native_place_modern_filter.add(modern_cities[i]);
        }
        filters.put("native_place_modern_filter", native_place_modern_filter);

        String[] ancient_cities = new String[] {"不限","并州", "冀州", "交州", "荆州", "凉州", "青州",
                "司隶", "徐州", "兖州", "扬州", "益州", "幽州", "豫州"};

        ArrayList<String> native_place_ancient_filter = new ArrayList<String>();
        for (int i = 0; i < ancient_cities.length; i++) {
            native_place_ancient_filter.add(ancient_cities[i]);
        }
        filters.put("native_place_ancient_filter", native_place_ancient_filter);

        String[] camps = new String[] {"不限", "东汉", "魏蜀", "吴", "袁绍", "袁术", "刘表", "起义军", "董卓",
                "刘璋", "西晋", "少数民族", "在野", "其他"};
        ArrayList<String> camp_filter = new ArrayList<String>();
        for (int i = 0; i < camps.length; i++) {
            camp_filter.add(camps[i]);
        }
        filters.put("camp_filter", camp_filter);

        String[] ages = new String[] {
                "不限", "小于10岁", "10-19岁", "20-29岁", "30-39岁", "40-49岁", "50-59岁", "60-69岁",
                "70-79岁", "80-89岁", "90-99岁", "大于100岁"};
        ArrayList<String> age_filter = new ArrayList<String>();
        for (int i = 0; i < ages.length; i++) {
            age_filter.add(ages[i]);
        }
        filters.put("age_filter", age_filter);

        String[] sexs = new String[] {"不限", "男", "女"};
        ArrayList<String> sex_filter = new ArrayList<String>();
        for (int i = 0; i < sexs.length; i++) {
            sex_filter.add(sexs[i]);
        }
        filters.put("sex_filter", sex_filter);
    }

    public ArrayList<String> getFilter(filterType type) {
        switch (type) {
            case first_char_filter:
                return filters.get("first_char_filter");
            case native_place_modern_filter:
                return filters.get("native_place_modern_filter");
            case native_place_ancient_filter:
                return filters.get("native_place_ancient_filter");
            case age_filter:
                return filters.get("age_filter");
            case sex_filter:
                return filters.get("sex_filter");
            case camp_filter:
                return filters.get("camp_filter");
        }
        return null;
    }

    public HashMap<filtersData.filterType, String> getFiltersSelected() {
        return this.filters_selected;
    }

    public void setFiltersSelected(HashMap<filtersData.filterType, String> filtersSelected) {
        this.filters_selected = filtersSelected;
    }
}
