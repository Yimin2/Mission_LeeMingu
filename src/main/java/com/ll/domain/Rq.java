package com.ll.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Rq {
    String cmd;
    String action;
    String queryString;
    Map<String, String> paramsMap;

    Rq(String cmd) {
        paramsMap = new HashMap<>();
        this.cmd = cmd;
        String[] cmdBits = cmd.split("//?", 2);
        action = cmdBits[0].trim();
        if (cmdBits.length == 1) {
            return;
        }
        queryString = cmdBits[1].trim();
        String[] queryStringBits = queryString.split("&");
        for (int i = 0; i < queryStringBits.length; i++) {
            String queryParamStr = queryStringBits[i];
            String[] queryParamBits = queryParamStr.split("=", 2);

            String paramName = queryParamBits[0];
            String paramValue = queryParamBits[1];

            paramsMap.put(paramName, paramValue);
        }
    }

    public String getAction() {
        return action;
    }
    public int getParamAsInt(String paramName, int defaultValue) {
        String paramValue = paramsMap.get(paramName);
        if (paramValue != null) {
            try {
                return Integer.parseInt(paramValue);
            } catch (NumberFormatException e) {
            }
        }
        return defaultValue;
    }
}