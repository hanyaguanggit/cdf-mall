package com.cdf.mall.aspect;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cdf.mall.annontaion.SystemLog;
import com.cdf.mall.dto.SystemLogDto;
import com.cdf.mall.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Description 系统日志切面
 * @Author hanyaguang
 * @Date 2022/4/19 14:51
 * @Version 1.0
 */
@Aspect
@Component("SystemLogAopAspect")
@Slf4j
public class SystemLogAopAspect {

    //注入service,用来将日志信息保存在数据库
  /*  @Autowired
    private LogServiceImpl logservice;*/

    @Pointcut("execution(public * com.cdf.mall.controller.*.*(..))")
    public void controllerAspect() {
        System.out.println("----------------------------point cut start");
    }

    @SuppressWarnings({ "rawtypes", "unused" })
    @Around("controllerAspect()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {

        SystemLogDto systemLogDto = new SystemLogDto();
        //获取登录用户账户
        HttpServletRequest httpRequest = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        //方法通知前获取时间,为什么要记录这个时间呢？当然是用来计算模块执行时间的
        //获取系统时间
        String time = DateFormatUtils.format(new Date(),DateUtil.format1);
        systemLogDto.setStartTime(time);

        // 拦截的实体类，就是当前正在执行的controller
        Object target = pjp.getTarget();
        // 拦截的方法名称。当前正在执行的方法
        String methodName = pjp.getSignature().getName();
        // 拦截的方法参数
        Object[] args = pjp.getArgs();

        // 拦截的放参数类型
        Signature sig = pjp.getSignature();
        MethodSignature msig = null;
        if (!(sig instanceof MethodSignature)) {
            throw new IllegalArgumentException("该注解只能用于方法");
        }
        msig = (MethodSignature) sig;
        Class[] parameterTypes = msig.getMethod().getParameterTypes();

        Object object = null;
        // 获得被拦截的方法
        Method method = null;
        method = target.getClass().getMethod(methodName, parameterTypes);

        if (null != method) {
            // 判断是否包含自定义的注解，说明一下这里的SystemLog就是我自己自定义的注解
            if (method.isAnnotationPresent(SystemLog.class)) {
                SystemLog systemlog = method.getAnnotation(SystemLog.class);
                systemLogDto.setModule(systemlog.module());
                systemLogDto.setOperateType(systemlog.methods());//操作类型


                //请求查询操作前数据的spring bean
                String serviceClass = systemlog.serviceClass();
                //请求查询数据的方法
                String queryMethod = systemlog.queryMethod();

                //参数类型
                String paramType = systemlog.parameterType();
                String key = systemlog.parameterKey();

                //操作前的对象信息
                Object data = getOperateBeforeData(paramType, serviceClass, queryMethod, "1");
                JSONObject json = (JSONObject) JSON.toJSON(data);
                log.info("操作前的对象信息：{}",json);
            }else {
                //没有包含注解
                object = pjp.proceed();
            }
        }else {
            //不需要拦截直接执行
            object = pjp.proceed();
        }
        return object;
    }


    /**
     *
     * 功能描述: <br>
     * 〈功能详细描述〉
     *
     * @param paramType:参数类型
     * @param serviceClass：bean名称
     * @param queryMethod：查询method
     * @param value：查询id的value
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public Object getOperateBeforeData(String paramType, String serviceClass, String queryMethod, String value) {
        Object obj = new Object();
        //在此处解析请求的参数类型，根据id查询数据，id类型有四种：int，Integer,long,Long
        if (paramType.equals("int")) {
            int id = Integer.parseInt(value);
            Method mh = ReflectionUtils.findMethod(SpringContextUtil.getBean(serviceClass).getClass(), queryMethod, Long.class);
            //用spring bean获取操作前的参数,此处需要注意：传入的id类型与bean里面的参数类型需要保持一致
            obj = ReflectionUtils.invokeMethod(mh, SpringContextUtil.getBean(serviceClass), id);

        } else if (paramType.equals("Integer")) {
            Integer id = Integer.valueOf(value);
            Method mh = ReflectionUtils.findMethod(SpringContextUtil.getBean(serviceClass).getClass(), queryMethod, Long.class);
            //用spring bean获取操作前的参数,此处需要注意：传入的id类型与bean里面的参数类型需要保持一致
            obj = ReflectionUtils.invokeMethod(mh, SpringContextUtil.getBean(serviceClass), id);

        } else if (paramType.equals("long")) {
            long id = Long.parseLong(value);
            Method mh = ReflectionUtils.findMethod(SpringContextUtil.getBean(serviceClass).getClass(), queryMethod, Long.class);
            //用spring bean获取操作前的参数,此处需要注意：传入的id类型与bean里面的参数类型需要保持一致
            obj = ReflectionUtils.invokeMethod(mh, SpringContextUtil.getBean(serviceClass), id);

        } else if (paramType.equals("Long")) {
            Long id = Long.valueOf(value);
            Method mh = ReflectionUtils.findMethod(SpringContextUtil.getBean(serviceClass).getClass(), queryMethod, Long.class);
            //用spring bean获取操作前的参数,此处需要注意：传入的id类型与bean里面的参数类型需要保持一致
            obj = ReflectionUtils.invokeMethod(mh, SpringContextUtil.getBean(serviceClass), id);
        }
        return obj;
    }





}
