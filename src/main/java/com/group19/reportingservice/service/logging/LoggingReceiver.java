package com.group19.reportingservice.service.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;

import java.sql.Timestamp;
import java.util.Date;


public class LoggingReceiver implements MessageListener {

    Logger logger = LoggerFactory.getLogger(LoggingReceiver.class);

    @Override
    public void onMessage(Message message, byte[] pattern) {
        logger.info("{} :: "+ new Timestamp(new Date().getTime()) , message);
    }
}
