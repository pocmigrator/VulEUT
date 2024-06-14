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
package com.lion.demo.consumer.feign;

import com.lion.common.result.Result;
import com.lion.demo.consumer.feign.fallback.ProviderDemoFeignClientFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * ProviderDemoClient
 * 服务调用类
 *
 * @author Yanzheng (https://github.com/micyo202)
 * @date 2019/01/05
 */
@FeignClient(value = "lion-demo-provider", fallback = ProviderDemoFeignClientFallback.class)
public interface ProviderDemoFeignClient {

    @GetMapping(value = "/init")
    Result<String> initFromProvider();

    @GetMapping(value = "/hi")
    Result<String> hiFromProvider(@RequestParam(value = "name") String name);

    @RequestMapping("/temp/product/deduct")
    Result<String> deductFromProvider(@RequestParam("productCode") String productCode, @RequestParam("count") int count);
}
