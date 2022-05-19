package net.artux.nextcrm.controller.util;

public abstract class Tools {

    public static String getQueryForSimilar(String q){
        String[] arr = q.toUpperCase().split(" ");
        StringBuilder qBuilder = new StringBuilder(arr[0]);
        for (int i = 1; i < arr.length; i++) {
            qBuilder.append("|");
            qBuilder.append(arr[i]);
        }
        return "%(" + qBuilder + ")%";
    }

}
