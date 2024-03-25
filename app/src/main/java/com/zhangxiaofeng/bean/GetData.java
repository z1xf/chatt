package com.zhangxiaofeng.bean;

import java.util.List;


public class GetData {

    public int code;
    public String message;
    public List<DataDTO> data;

    public static class DataDTO {
        public DataDTO(String sender, String recipients, String message) {
            this.sender = sender;
            this.recipients = recipients;
            this.message = message;
        }

        public String sender;
        public String recipients;
        public String message;
    }
}
