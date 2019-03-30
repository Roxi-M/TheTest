package com.roxi.demo.Util;

public class Intparse {
    public static String defalutString(String what){
        if(what==null){
            what="";
        }
        return what;
    }

    public static int defalutInt(String what){
        if(what!=null) {
            try {
                return Integer.parseInt(what);
            } catch (Exception e) {
                return -1;
            }
        }else {
            return -1;
        }
    }
    public static boolean defalutBoolean(String what){
        if(what!=null){
            try {
          /*      boolean w=Boolean.parseBoolean(what);
                if(w){
                    return 1;
                }else {
                    return 0;
                }*/
          return Boolean.parseBoolean(what);
            }catch (Exception e){
                return false;
            }
        }else {
            return false;
        }
    }
}
