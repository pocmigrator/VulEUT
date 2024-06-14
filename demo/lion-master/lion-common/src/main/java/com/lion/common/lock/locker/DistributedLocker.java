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
package com.lion.common.lock.locker;

import org.redisson.api.RLock;

import java.util.concurrent.TimeUnit;

/**
 * DistributedLocker
 * 分布式锁接口方法
 *
 * @author Yanzheng (https://github.com/micyo202)
 * @date 2019/05/08
 */
public interface DistributedLocker {

    /**
     * 强制加锁
     *
     * 拿不到lock就不罢休，不然线程就一直block
     *
     * @param lockKey 锁的key值
     * @return 锁信息
     */
    RLock lock(String lockKey);

    /**
     * 强制加锁
     *
     * @param lockKey   锁的key值
     * @param leaseTime 加锁时间（默认单位毫秒）
     * @return 锁信息
     */
    RLock lock(String lockKey, long leaseTime);

    /**
     * 强制加锁
     *
     * @param lockKey   锁的key值
     * @param unit      时间单位
     * @param leaseTime 加锁时间
     * @return 锁信息
     */
    RLock lock(String lockKey, TimeUnit unit, long leaseTime);

    /**
     * 尝试加锁
     *
     * 马上返回，拿到lock就返回true，不然返回false
     * 带时间限制的tryLock()，拿不到lock，就等一段时间，超时返回false
     *
     * @param lockKey 锁的key值
     * @return 是否拿到
     */
    boolean tryLock(String lockKey);

    /**
     * 尝试加锁
     *
     * @param lockKey   锁的key值
     * @param waitTime  等待时间
     * @param leaseTime 加锁时间
     * @return 是否拿到标志
     */
    boolean tryLock(String lockKey, long waitTime, long leaseTime);

    /**
     * 尝试加锁
     *
     * @param lockKey   锁的key值
     * @param unit      时间单位
     * @param waitTime  等待时间
     * @param leaseTime 加锁时间
     * @return 是否拿到标志
     */
    boolean tryLock(String lockKey, TimeUnit unit, long waitTime, long leaseTime);

    /**
     * 解锁
     *
     * @param lockKey 锁的key值
     */
    void unlock(String lockKey);

    /**
     * 解锁
     *
     * @param lock 锁信息
     */
    void unlock(RLock lock);
}