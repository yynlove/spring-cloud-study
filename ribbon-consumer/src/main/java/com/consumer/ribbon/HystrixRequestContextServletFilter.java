package com.consumer.ribbon;

import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.*;
import java.io.IOException;

@Configuration
public class HystrixRequestContextServletFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HystrixRequestContext context = HystrixRequestContext.initializeContext();
        try {
            filterChain.doFilter(servletRequest, servletResponse);
        } finally {
            context.shutdown();
        }
    }

    @Bean
    public FilterRegistrationBean indexFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean(new HystrixRequestContextServletFilter());
        registration.addUrlPatterns("/*");
        return registration;
    }

}
