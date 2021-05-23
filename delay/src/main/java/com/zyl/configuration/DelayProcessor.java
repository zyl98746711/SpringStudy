package com.zyl.configuration;

import com.zyl.tool.ThreadHolderUtil;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;

/**
 * @author zyl
 */
public class DelayProcessor implements ApplicationContextAware {

    public static final String EXECUTOR_NAME = "delayExecutor";

    public static final String THREAD_NAME = "delayThread";

    private ExecutorService executorService;

    private final Map<String, List<Runnable>> taskMap = new ConcurrentHashMap<>();

    public void execute() {
        Deque<String> deque = ThreadHolderUtil.getValue(THREAD_NAME, Deque.class);
        String key = deque.pollFirst();
        List<Runnable> runnables = taskMap.get(key);
        if (CollectionUtils.isEmpty(runnables)) {
            return;
        }
        runnables.forEach(runnable -> {
            System.out.println("execute……");
            executorService.execute(runnable);
        });
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.executorService = applicationContext.getBean(EXECUTOR_NAME, ExecutorService.class);
    }

    public void registerDelayEvent(Runnable delaySource) {
        Deque<String> deque = ThreadHolderUtil.getValue(THREAD_NAME, Deque.class);
        Assert.notNull(deque, "不能为空");
        String key = deque.peekFirst();
        taskMap.compute(key, ((k, taskList) -> {
            if (taskList == null) {
                taskList = new ArrayList<>();
            }
            taskList.add(delaySource);
            return taskList;
        }));
    }

    public void initDelay() {
        Deque<String> deque = ThreadHolderUtil.getValue(THREAD_NAME, Deque.class);
        if (deque == null) {
            deque = new ArrayDeque<>();
            ThreadHolderUtil.setValue(THREAD_NAME, deque);
        }
        String uuid = UUID.randomUUID().toString();
        deque.add(uuid);
    }

    public void clear() {
        taskMap.remove(ThreadHolderUtil.getValue(THREAD_NAME, Deque.class));
    }
}
