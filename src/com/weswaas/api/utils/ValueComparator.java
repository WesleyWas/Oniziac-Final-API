package com.weswaas.api.utils;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Weswas on 10/01/2017.
 */
public class ValueComparator implements Comparator<String>{

        Map<String, Integer> base;

        public ValueComparator(HashMap<String, Integer> map) {
            this.base = map;
        }

        @Override
        public int compare(String a, String b) {
            if(base.get(a) >= base.get(b)){
                return -1;
            }else{
                return 1;
            }
        }

}
