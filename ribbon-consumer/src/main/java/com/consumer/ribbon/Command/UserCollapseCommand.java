package com.consumer.ribbon.Command;

import com.consumer.ribbon.bean.User;
import com.consumer.ribbon.service.UserService;
import com.netflix.hystrix.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 继承实现请求合并器
 */
public class UserCollapseCommand extends HystrixCollapser<List<User>,User,Long> {

    private UserService userService;
    private Long userId;

    /**
     * 设置时间延迟属性，合并器会在该时间内搜集单个User的请求，
     * 并在时间窗结束之后进行合并并组装单个批量请求
     */
    public UserCollapseCommand(UserService userService, Long userId) {
        super(Setter.withCollapserKey(HystrixCollapserKey.Factory.asKey("userCollapseCommand"))
                .andCollapserPropertiesDefaults(HystrixCollapserProperties.Setter().withTimerDelayInMilliseconds(100)));
        this.userService = userService;
        this.userId = userId;
    }
    @Override
    public Long getRequestArgument() {
        return userId;
    }

    /**
     *
     * @param collapsedRequests 保存了延迟时间窗中收集到的所有获取单个User的请求
     * @return
     */
    @Override
    protected HystrixCommand<List<User>> createCommand(Collection<CollapsedRequest<User, Long>> collapsedRequests) {

        List<Long> userIds = new ArrayList<>(collapsedRequests.size());
        userIds.addAll(collapsedRequests.stream().map(CollapsedRequest::getArgument).collect(Collectors.toList()));
        return new UserBatchCommand(userService,userIds);
    }

    /**
     * 在批量请求命令 UserBatchCommand实例被触发执行完成之后 该方法开始执行
     * @param batchResponse 保存了createCommand中组织的批量请求命令的返回结果
     * @param collapsedRequests 代表了每个被合并的请求
     */
    @Override
    protected void mapResponseToRequests(List<User> batchResponse,
        Collection<CollapsedRequest<User, Long>> collapsedRequests) {
        int count =0;
        for (CollapsedRequest<User,Long>collapsedRequest:collapsedRequests){
            User user = batchResponse.get(count++);
            collapsedRequest.setResponse(user);
        }
    }
}
