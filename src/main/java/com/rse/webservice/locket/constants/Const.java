package com.rse.webservice.locket.constants;

public class Const {
    public interface GeneralStatus {
        int INACTIVE = 0;
        int ACTIVE = 1;
        int DELETED = 2;
    }

    public interface GeneralState {
        int PUBLIC = 0;
        int PRIVATE = 1;
        int PENDING = 2;
    }

    public interface MailTitle {
        String NEW_USER_ACCOUNT_VERIFICATION = "New User Account Verification";
    }
}
