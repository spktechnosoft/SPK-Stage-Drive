package com.stagedriving.modules.commons.utils.common;

import com.stagedriving.commons.StgdrvData;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Stream;

/**
 * Project stage driving api
 * Author: man
 * Date: 17/10/2018
 */
public class CommonUtils {

    private static ArrayList<String> colors;

    public static <K, V extends Comparable<? super V>> Map<K, V>
    sortByValue( Map<K, V> map )
    {
        Map<K, V> result = new LinkedHashMap<>();
        Stream<Map.Entry<K, V>> st = map.entrySet().stream();

        st.sorted( Map.Entry.comparingByValue() )
                .forEachOrdered( e -> result.put(e.getKey(), e.getValue()) );

        return result;
    }

    public static String chooseColor(String search){

        if(colors==null){
            colors = new ArrayList<>();
            colors.add(StgdrvData.StgdrvColor.BLU);
            colors.add(StgdrvData.StgdrvColor.ROSSO);
            colors.add(StgdrvData.StgdrvColor.ARANCIONE);
            colors.add(StgdrvData.StgdrvColor.VERDE);
            colors.add(StgdrvData.StgdrvColor.VERDE_ACIDO);
            colors.add(StgdrvData.StgdrvColor.BEIGE);
            colors.add(StgdrvData.StgdrvColor.AZZURRO);
            colors.add(StgdrvData.StgdrvColor.GIALLO);
            colors.add(StgdrvData.StgdrvColor.VIOLETTO);
            colors.add(StgdrvData.StgdrvColor.LILLA);
            colors.add(StgdrvData.StgdrvColor.MULTICOLOR);
            colors.add(StgdrvData.StgdrvColor.ALTRO);
        }

        for (String color : colors) {
            if(color.contains(search)) return color;
        }

        return null;
    }
}
