package com.sfu.cmpt470.pojo;

import com.google.gson.annotations.SerializedName;

public enum  OrderDetailStatusTypeCode {
    @SerializedName(value = "new")
    NEW("new"),
    @SerializedName(value = "in_progress")
    IN_PROGRESS("in_progress"),
    @SerializedName(value = "done")
    DONE("done");

    private String _status;

    OrderDetailStatusTypeCode(String status) {
        _status = status;
    }

    @Override
    public String toString() {
        return _status;
    }

    public static OrderDetailStatusTypeCode of(String str){
        switch (str){
            case "new":
                return NEW;
            case "in_progress":
                return IN_PROGRESS;
            case "done":
                return DONE;
        }
        return NEW;
    }
}
