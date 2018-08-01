package springcloudredssion.redislock.impl;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import springcloudredssion.redislock.DistributeLocker;

import java.util.concurrent.TimeUnit;

public class RedissonDistributeLoker implements DistributeLocker {

    private RedissonClient redissonClient;

    public RedissonClient getRedissonClient() {
        return redissonClient;
    }

    public void setRedissonClient(RedissonClient redissonClient) {
        this.redissonClient = redissonClient;
    }

    @Override
    public RLock lock(String lockKey) {
        RLock lock = redissonClient.getLock(lockKey);
        lock.lock();
        return lock;
    }

    @Override
    public RLock lock(String lockKey, int timeout) {
        RLock lock = redissonClient.getLock(lockKey);
        lock.lock(timeout,TimeUnit.SECONDS);
        return lock;
    }

    @Override
    public RLock lock(String lockKey, TimeUnit timeUnit, int timeout) {
        RLock lock = redissonClient.getLock(lockKey);
        lock.lock(timeout,timeUnit);
        return lock;
    }

    @Override
    public boolean tryLock(String lockKey, TimeUnit timeUnit, int waitTime, int leaseTime) {
        RLock lock = redissonClient.getLock(lockKey);
        try {
            return lock.tryLock(waitTime,leaseTime,timeUnit);
        }catch (InterruptedException e){
            return false;
        }
    }

    @Override
    public void unlock(String lockKey) {
        RLock lock = redissonClient.getLock(lockKey);
        lock.unlock();
    }

    @Override
    public void unlock(RLock lock) {
        lock.unlock();
    }
}
