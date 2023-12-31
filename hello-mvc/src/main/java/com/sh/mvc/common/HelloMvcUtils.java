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

    // svg : scalable graphic태그. 도형등을 수학적으로 그려서 스케일에 관계 없이 선명한 그림작성.
    static String previous = """
        <li>
            <a href="%s" class="flex items-center justify-center px-3 h-8 ms-0 leading-tight text-gray-500 bg-white border border-e-0 border-gray-300 rounded-s-lg hover:bg-gray-100 hover:text-gray-700 dark:bg-gray-800 dark:border-gray-700 dark:text-gray-400 dark:hover:bg-gray-700 dark:hover:text-white">
                <span class="sr-only">Previous</span>
                <svg class="w-2.5 h-2.5 rtl:rotate-180" aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 6 10">
                    <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M5 1 1 5l4 4"/>
                </svg>
            </a>
        </li>""";

    static String previousDisabled = """
              <li>
            <a href="#" class="flex items-center justify-center px-3 h-8 ms-0 leading-tight text-gray-500 bg-white border border-e-0 border-gray-300 rounded-s-lg cursor-not-allowed dark:bg-gray-800 dark:border-gray-700 dark:text-gray-400 dark:hover:bg-gray-700 dark:hover:text-white">
                <span class="sr-only">Previous</span>
                <svg class="w-2.5 h-2.5 rtl:rotate-180" aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 6 10">
                    <path stroke="currentColor" style="stroke:#9095a0ab"  stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M5 1 1 5l4 4"/>
                </svg>
            </a>
        </li>""";

    static String currentPageNo = """
            <li>
                <a href="%s" aria-current="page" class="z-10 flex items-center justify-center px-3 h-8 leading-tight text-blue-600 border border-blue-300 bg-blue-50 hover:bg-blue-100 hover:text-blue-700 dark:border-gray-700 dark:bg-gray-700 dark:text-white">%d</a>
            </li>""";

    static String notCurrentPageNo = """
             <li>
                <a href="%s" class="flex items-center justify-center px-3 h-8 leading-tight text-gray-500 bg-white border border-gray-300 hover:bg-gray-100 hover:text-gray-700 dark:bg-gray-800 dark:border-gray-700 dark:text-gray-400 dark:hover:bg-gray-700 dark:hover:text-white">%d</a>
             </li>""";

    static String next = """
            <li>
                <a href="%s" class="flex items-center justify-center px-3 h-8 leading-tight text-gray-500 bg-white border border-gray-300 rounded-e-lg hover:bg-gray-100 hover:text-gray-700 dark:bg-gray-800 dark:border-gray-700 dark:text-gray-400 dark:hover:bg-gray-700 dark:hover:text-white">
                    <span class="sr-only">Next</span>
                    <svg class="w-2.5 h-2.5 rtl:rotate-180" aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 6 10">
                        <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="m1 9 4-4-4-4"/>
                    </svg>
                </a>
           </li>""";

    static String nextDisabled = """
         <li>
                <a href="#" class="flex items-center justify-center px-3 h-8 leading-tight text-gray-500 bg-white border border-gray-300 rounded-e-lg cursor-not-allowed dark:bg-gray-800 dark:border-gray-700 dark:text-gray-400 dark:hover:bg-gray-700 dark:hover:text-white">
                    <span class="sr-only">Next</span>
                    <svg class="w-2.5 h-2.5 rtl:rotate-180" aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 6 10">
                        <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="m1 9 4-4-4-4"/>
                    </svg>
                </a>
           </li>""";


    /**
      *  - page 현재페이지
      *  - limit 한페이지당 표시할 개체수
      *  - totalCount 전체 개체수
      *  - totalPage 전체 페이지수
      *  - pagebarSize 페이지바의 숫자개수
      *  - pageNo 페이지 증감변수
      *  - pagebarStart | pagebarEnd 페이지 증감변수의 범위
      *  - url 요청 url
     *
     * @param page
     * @param limit
     * @param totalCount
     * @param url
     * @return
     */
    public static String getPagebar(int page, int limit, int totalCount, String url) {
        StringBuffer pagebar = new StringBuffer();
        // /mvc/admin/memberList
        // /mvc/admin/searchMember?search-type=xxx&search-keyword=yyyy
        url += (url.contains("?")) ? "&page=" : "?page=";

        // 전체페이지수
        int totalPage = (int)Math.ceil((double) totalCount / limit);
        // 페이지바의 숫자개수
        int pagebarSize = 5;

        // 페이지 증감변수 범위
        // 1 2 3 4 5
        // 6 7 8 9 10
        // 11 12 13 14 15
        int pagebarStart  = (page - 1) / pagebarSize * pagebarSize + 1;
        int pagebarEnd = pagebarStart  + pagebarSize - 1;
        // 페이지 증감변수 pagebarStart 초기화값으로 두기
        int pageNo = pagebarStart ;

        // 1. 이전
        if(pageNo == 1) {
            // 비활성화 이전
            pagebar.append(previousDisabled);
        }
        else {
            // 활성화 이전
            pagebar.append(previous.formatted(url + (pageNo - 1))); // /mvc/admin/memberList?page=5
        }

        // 2. 페이지 넘버
        while (pageNo <= pagebarEnd && pageNo <= totalPage){
            if(pageNo == page) {
                // 현재페이지
                pagebar.append(currentPageNo.formatted(url + pageNo, pageNo));
            }
            else {
                // 현재페이지가 아닌 페이지
                pagebar.append(notCurrentPageNo.formatted(url + pageNo, pageNo));
            }
            pageNo++;
        }
        // 3. 다음
        if(pageNo > totalPage) {
            // 비활성화 다음
            pagebar.append(nextDisabled);
        }
        else {
            // 활성화 다음
            pagebar.append(next.formatted(url + pageNo));
        }

        return pagebar.toString();
    }

    public static String convertLineFeedToBr(String string) {
        return string.replaceAll("\n", "<br/>");
    }

    public static String escapeHtml(String str) {
        return str.replaceAll("<", "&lt;").replaceAll(">", "&gt");
    }
}
