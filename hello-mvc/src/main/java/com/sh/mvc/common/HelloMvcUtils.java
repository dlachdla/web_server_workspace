package com.sh.mvc.common;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class HelloMvcUtils {
    /**
     * <pre>
     * 암호화
     * 1. MessageDigest (암호화)
     *  - 단방향 암호화 알고리즘 sha512
     *  - salt값을 사용해서 보안성 높히기
     * 2. Encoding (이진데이터를 텍스트로 변환)
     *
     * @param password
     * @return
     */
    public static String getEncryptedPassword(String password, String salt) {
        String encryptedPassword = null;
        try {
            // 1. 암호화 (hashing)
            //  SHA-512 방식의 해시 함수를 수행할 수 있는 객체를 얻어옴
            MessageDigest md = MessageDigest.getInstance("SHA-512");

            // md를 이용해 암호화를 진행할 수 있도록 pw를 byte[] 형태로 변환
            byte[] input = password.getBytes("UTF-8");
            // 평문에 salt를 더하여 보안성 높이기
            byte[] saltInput = salt.getBytes("UTF-8");
            // 암호화 수행 -> 암호화 결과가 md 내부에 저장
            md.update(saltInput);
            // update()된 값을 바이트 배열로 해시를 반환한다. (digest)
            byte[] output = md.digest(input); // 이진데이터

            // 2. 인코딩 (64개문자 - 영대소문자(52), 숫자(10), +, /) + Padding(=)
            // -> Base64: byte 코드를 문자열로 변환하는 객체
            // -> byte[]를 String 변환해야함
            Base64.Encoder encoder = Base64.getEncoder();
            encryptedPassword = encoder.encodeToString(output);

        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        return encryptedPassword;
    }
}
