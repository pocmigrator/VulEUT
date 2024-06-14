/**
 *   Copyright 2019 Yanzheng (https://github.com/micyo202). All rights reserved.
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */
package com.lion.common.util.secure;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

/**
 * SHAUtil
 * SHA哈希散列工具类
 *
 * @author Yanzheng (https://github.com/micyo202)
 * @date 2019/10/21
 */
@Slf4j
public class SHAUtil {

    private SHAUtil() {
    }

    /**
     * 加密、解密方式：SHA-1
     */
    private static final String SHA_1 = "SHA-1";

    /**
     * 加密、解密方式：SHA-224
     */
    private static final String SHA_224 = "SHA-224";

    /**
     * 加密、解密方式：SHA-256
     */
    private static final String SHA_256 = "SHA-256";

    /**
     * 加密、解密方式：SHA-384
     */
    private static final String SHA_384 = "SHA-384";

    /**
     * 加密、解密方式：SHA-512
     */
    private static final String SHA_512 = "SHA-512";

    /**
     * SHA1 加密
     *
     * @param data 明文
     * @return 密文
     */
    public static String encrypt(String data) {
        return encrypt(data, SHA_1);
    }

    /**
     * SHA224 加密
     *
     * @param data 明文
     * @return 密文
     */
    public static String encrypt224(String data) {
        return encrypt(data, SHA_224);
    }

    /**
     * SHA256 加密
     *
     * @param data 明文
     * @return 密文
     */
    public static String encrypt256(String data) {
        return encrypt(data, SHA_256);
    }

    /**
     * SHA384 加密
     *
     * @param data 明文
     * @return 密文
     */
    public static String encrypt384(String data) {
        return encrypt(data, SHA_384);
    }

    /**
     * SHA512 加密
     *
     * @param data 明文
     * @return 密文
     */
    public static String encrypt512(String data) {
        return encrypt(data, SHA_512);
    }


    /**
     * 私有方法 - 加密
     *
     * @param data      密文
     * @param algorithm 加密方式
     * @return 密文
     */
    private static String encrypt(String data, String algorithm) {
        if (StringUtils.isEmpty(data)) {
            return null;
        }
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
            messageDigest.update(data.getBytes(StandardCharsets.UTF_8));
            byte[] bytes = messageDigest.digest();
            return HexUtil.bytes2Hex(bytes);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }
}
