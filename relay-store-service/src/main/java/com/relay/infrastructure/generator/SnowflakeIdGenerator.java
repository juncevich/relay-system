package com.relay.infrastructure.generator;

import org.springframework.stereotype.Component;

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
    private static long workerId;
    private static long datacenterId;
    private static long sequence = 0L;
    private static long lastTimestamp = -1L;

    private Long value;

    public static synchronized void initDataCenterAndWorker(int datacenterId, int workerId) {
        if ((long) workerId <= 31L && workerId >= 0) {
            if ((long) datacenterId <= 31L && datacenterId >= 0) {
                SnowflakeIdGenerator.workerId = (long) workerId;
                SnowflakeIdGenerator.datacenterId = (long) datacenterId;
            } else {
                throw new IllegalArgumentException(String.format("datacenter Id can't be greater than %d or less than 0", 31L));
            }
        } else {
            throw new IllegalArgumentException(String.format("worker Id can't be greater than %d or less than 0", 31L));
        }
    }

    public static synchronized Long getId() {
        long timestamp = timeGen();
        if (timestamp < lastTimestamp) {
            throw new RuntimeException(String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds", lastTimestamp - timestamp));
        } else {
            if (lastTimestamp == timestamp) {
                sequence = sequence + 1L & 4095L;
                if (sequence == 0L) {
                    timestamp = tilNextMillis(lastTimestamp);
                }
            } else {
                sequence = 0L;
            }

            lastTimestamp = timestamp;
            return timestamp - 1514764800000L << 22 | datacenterId << 17 | workerId << 12 | sequence;
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
