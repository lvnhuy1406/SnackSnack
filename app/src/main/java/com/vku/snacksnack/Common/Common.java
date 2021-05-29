package com.vku.snacksnack.Common;

import com.vku.snacksnack.Model.Users;

public class Common {
    public static Users currentUser;

    public static String convertCodeToStatus(String status) {
        if (status.equals("0")) {
            return "Đã đặt";
        } else if (status.equals("1")) {
            return "Đang chuyển";
        } else {
            return "Đã nhận";
        }
    }
}
