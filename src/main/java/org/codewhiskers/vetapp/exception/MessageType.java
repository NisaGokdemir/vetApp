package org.codewhiskers.vetapp.exception;

import lombok.Getter;

@Getter
public enum MessageType {

    NO_RECORD_EXIST("1001","Kayıt bulunamadı"),
    RECORD_CREATE_UNSUCCESS("1002","Kayıt oluşturulamadı"),
    RECORDS_NOT_FOUND("1003","Kayıtlar bulunamadı"),
    RECORD_UPDATE_UNSUCCESS("1004","Kayıt güncellenemedi"),
    RECORD_DELETE_UNSUCCESS("1005","Kayıt silinemedi"),
    INVALID_INPUT("1006", "Geçersiz giriş"),



    GENERAL_EXCEPTION("9999","Genel bir hata oluştu"),
    REFRESH_TOKEN_EXPIRED("2001", "Refresh token süresi dolmuştur"),
    REFRESH_TOKEN_NOT_FOUND("2002", "Refresh token bulunamadı");
    // USERNAME_OR_PASSWORD_INVALID("1004","kullanıcı adı veya şifre hatalı"),;

    private String code;

    private String message;

    private MessageType(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
