package com.ll;

import java.util.ArrayList;
import java.util.List;

public class Rq {
    String cmd;
    String action;
    String queryString;
    List<String> paramNames;
    List<String> paramValues;

    Rq(String cmd) {
        paramNames = new ArrayList<>();
        paramValues = new ArrayList<>();
        this.cmd = cmd;
        String[] cmdBits = cmd.split("//?", 2);
        action = cmdBits[0];
        queryString = cmdBits[1];
        String[] queryStringBits = queryString.split("&");
        for (int i = 0; i < queryStringBits.length; i++) {
            String queryParamStr = queryStringBits[i];
            String[] queryParamBits = queryParamStr.split("=", 2);

            String paramName = queryParamBits[0];
            String parmaValue = queryParamBits[1];

            paramNames.add(paramName);
            paramValues.add(parmaValue);
        }
    }

    String getAction() {
        return action;
    }

    public int getParamAsInt(String paramName, int defaultValue) {
        int index = paramNames.indexOf(paramName);
        if (index == -1) return defaultvalue;
        String paramValue = paramValues.get(index);

        try {
            return Integer.parseInt(paramValue);
        } catch (NumberFormatException e) {
            return defaultvalue;
        }
    }
}
