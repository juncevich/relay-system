package com.relay.infrastructure.generator;

import org.springframework.stereotype.Component;

import java.util.concurrent.locks.ReentrantLock;

@Component
public class SnowflakeIdGenerator {
    public static final long MAX_WORKER_ID = 31L;
    public static final long MAX_DATA_CENTER_ID = 31L;
    private static final long TWEPOCH = 1514764800000L;
    private static final long WORKER_ID_BITS = 5L;
    private static final long DATA_CENTER_ID_BITS = 5L;
    private static final long SEQUENCE_BITS = 12L;
    private static final long WORKER_ID_SHIFT = 12L;
    private static final long DATA_CENTER_ID_SHIFT = 17L;
    private static final long TIMESTAMP_LEFT_SHIFT = 22L;
    private static final long SEQUENCE_MASK = 4095L;

    private static final ReentrantLock lock = new ReentrantLock();

    private static long workerId;
    private static long datacenterId;
    private static long sequence = 0L;
    private static long lastTimestamp = -1L;

    public static void initDataCenterAndWorker(int datacenterId, int workerId) {
        lock.lock();
        try {
            if ((long) workerId > MAX_WORKER_ID || workerId < 0) {
                throw new IllegalArgumentException(
                        String.format("worker Id can't be greater than %d or less than 0", MAX_WORKER_ID));
            }
            if ((long) datacenterId > MAX_DATA_CENTER_ID || datacenterId < 0) {
                throw new IllegalArgumentException(
                        String.format("datacenter Id can't be greater than %d or less than 0", MAX_DATA_CENTER_ID));
            }
            SnowflakeIdGenerator.workerId = (long) workerId;
            SnowflakeIdGenerator.datacenterId = (long) datacenterId;
        } finally {
            lock.unlock();
        }
    }

    public static Long getId() {
        lock.lock();
        try {
            long timestamp = timeGen();
            if (timestamp < lastTimestamp) {
                throw new RuntimeException(
                        String.format("Clock moved backwards. Refusing to generate id for %d milliseconds",
                                lastTimestamp - timestamp));
            }

            if (lastTimestamp == timestamp) {
                sequence = (sequence + 1L) & SEQUENCE_MASK;
                if (sequence == 0L) {
                    timestamp = tilNextMillis(lastTimestamp);
                }
            } else {
                sequence = 0L;
            }

            lastTimestamp = timestamp;
            return (timestamp - TWEPOCH) << TIMESTAMP_LEFT_SHIFT
                    | datacenterId << DATA_CENTER_ID_SHIFT
                    | workerId << WORKER_ID_SHIFT
                    | sequence;
        } finally {
            lock.unlock();
        }
    }

    protected static long tilNextMillis(long lastTimestamp) {
        long timestamp;
        for (timestamp = timeGen(); timestamp <= lastTimestamp; timestamp = timeGen()) {
        }
        return timestamp;
    }

    protected static long timeGen() {
        return System.currentTimeMillis();
    }
}
