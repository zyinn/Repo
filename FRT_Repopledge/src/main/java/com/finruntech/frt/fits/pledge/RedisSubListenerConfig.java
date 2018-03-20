package com.finruntech.frt.fits.pledge;

import com.finruntech.frt.fits.pledge.dispatcher.RedisDispatcher;
import com.finruntech.frt.fits.pledge.service.FitsRepoPldgInstService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

/**
 * listenerAdapter patternTopic+x of INST
 * Created by yinan.zhang on 2018/1/25.
 */
@Configuration
public class RedisSubListenerConfig {
    @Value("${redis.subscribe.listenerAdapter.PatternTopic}")
    private String patternTopic;
    @Autowired
    private RedisDispatcher redisDispatcher;

    @Bean
    RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory,
                                            MessageListenerAdapter listenerAdapter) {

        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        //订阅了一个叫patternTopic+x 的通道
        int x=1+((int)(Math.random()*100));
        redisDispatcher.setPatternTopic(patternTopic+x);
        container.addMessageListener(listenerAdapter, new PatternTopic(patternTopic+x));
        //这个container 可以添加多个 messageListener
        return container;
    }

    @Bean
    MessageListenerAdapter listenerAdapter(FitsRepoPldgInstService receiver) {
        return new MessageListenerAdapter(receiver, "receiveMessage");
    }


    @Bean
    StringRedisTemplate template(RedisConnectionFactory connectionFactory) {
        return new StringRedisTemplate(connectionFactory);
    }
}