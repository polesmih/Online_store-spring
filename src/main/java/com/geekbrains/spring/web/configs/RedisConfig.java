package com.geekbrains.spring.web.configs;

//@Configuration
//@EnableRedisRepositories
//public class RedisConfig {
//
//    @Bean
//    public JedisConnectionFactory jedisConnectionFactory() {
//        return new JedisConnectionFactory();
//    }
//
//    @Bean
//    public RedisTemplate<String, Object> redisTemplate() {
//        RedisTemplate<String, Object> template = new RedisTemplate<>();
//        template.setKeySerializer(new StringRedisSerializer());
//        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
//        template.setConnectionFactory(jedisConnectionFactory());
//        return template;
//    }
