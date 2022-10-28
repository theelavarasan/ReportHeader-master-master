package com.ZenPack.ReportHeader.utills;

import com.ZenPack.ReportHeader.model.ZKConstants;
import com.ZenPack.ReportHeader.model.ZKModel;

public class CommonUtils {
    public static String checkPortNumberForWildCardCertificate(String url) {
        try {

            if(ZKModel.getProperty(ZKConstants.WILD_CARD_CERTIFICATE).equalsIgnoreCase("true")) {
                url = url.replaceFirst(":\\d+", "");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return url;
    }

}
