package com.ycw.common.config;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.interceptor.NameMatchTransactionAttributeSource;
import org.springframework.transaction.interceptor.RollbackRuleAttribute;
import org.springframework.transaction.interceptor.RuleBasedTransactionAttribute;
import org.springframework.transaction.interceptor.TransactionAttribute;
import org.springframework.transaction.interceptor.TransactionInterceptor;

import lombok.extern.slf4j.Slf4j;

/**
 * 全局事务配置
 * @author yuminjun
 * @date 2020/04/14 14:43:57
 * @version 1.00
 *
 * @record
 * <pre>
 * version  author      date          desc
 * -------------------------------------------------
 * 1.00     yuminjun    2020/04/14    新建
 * -------------------------------------------------
 * </pre>
 */
@Slf4j
@Aspect
@Configuration
public class TransactionManagerConfig {

    private static final int AOP_TIME_OUT = 50000;

    private static final String AOP_POINTCUT_EXPRESSION = "execution(* com.ycw..*.api.impl.*Impl.*(..)))";

    @Autowired
    private PlatformTransactionManager transactionManager;

    @Bean
    public TransactionInterceptor txAdvice(){
        NameMatchTransactionAttributeSource source = new NameMatchTransactionAttributeSource();

        /* 只读事务，不做更新操作 */
        RuleBasedTransactionAttribute readOnlyTx = new RuleBasedTransactionAttribute();
        readOnlyTx.setReadOnly(true);
        readOnlyTx.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

        /** 当前存在事务就使用当前事务，当前不存在事务就创建一个新的事务 */
        RuleBasedTransactionAttribute requiredTx = new RuleBasedTransactionAttribute();
        requiredTx.setRollbackRules(Collections.singletonList(new RollbackRuleAttribute(Exception.class)));
        requiredTx.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        requiredTx.setTimeout(AOP_TIME_OUT);

        /* 独立事务, 每次新建一个事务 */
        RuleBasedTransactionAttribute requiredNewTx = new RuleBasedTransactionAttribute();
        requiredNewTx.setRollbackRules(Collections.singletonList(new RollbackRuleAttribute(Exception.class)));
        requiredNewTx.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
        requiredNewTx.setTimeout(AOP_TIME_OUT);

        Map<String, TransactionAttribute> methodMap = new HashMap<>();

        /* 可以提交事务或回滚事务的方法 */
        methodMap.put("add*", requiredTx);
        methodMap.put("save*", requiredTx);
        methodMap.put("update*", requiredTx);
        methodMap.put("modify*", requiredTx);
        methodMap.put("edit*", requiredTx);
        methodMap.put("insert*", requiredTx);
        methodMap.put("delete*", requiredTx);
        methodMap.put("remove*", requiredTx);
        methodMap.put("repair*", requiredTx);
        methodMap.put("binding*", requiredTx);

        /* 独立事务 */
        methodMap.put("*NewTrans", requiredNewTx);

        /* 其他方法无事务，只读 */
        methodMap.put("*", readOnlyTx);

        source.setNameMap(methodMap);
        TransactionInterceptor txAdvice = new TransactionInterceptor(transactionManager, source);
        return txAdvice;
    }

    @Bean(name = "txAdviceAdvisor")
    public Advisor txAdviceAdvisor() {
        log.info("===============================创建txAdviceAdvisor===================================");
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression(AOP_POINTCUT_EXPRESSION);
        return new DefaultPointcutAdvisor(pointcut, txAdvice());
    }
}