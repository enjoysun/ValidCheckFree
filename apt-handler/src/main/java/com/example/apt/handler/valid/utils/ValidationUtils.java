package com.example.apt.handler.valid.utils;


import com.example.apt.handler.valid.models.ValidResult;
import org.hibernate.validator.HibernateValidator;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @Author myou
 * @Date 2020/6/28  4:00 下午
 */
public class ValidationUtils {
    private static Validator validator = Validation.byProvider(HibernateValidator.class).configure().failFast(false).buildValidatorFactory().getValidator();

    /**
     * 验证ValueObject
     *
     * @param object 验证对象类
     * @param groups hibernateValidator 验证组
     * @param <T>    泛型
     * @return
     */
    public static <T> ValidResult validObject(T object, Class<?>... groups) {
        ValidResult validResult = new ValidResult();
        Set<ConstraintViolation<T>> validate = validator.validate(object, groups);
        validResult.setError(null != validate && validate.size() > 0);
        validate.forEach(tConstraintViolation -> {
            validResult.addError(tConstraintViolation.getPropertyPath().toString(), tConstraintViolation.getMessage());
        });
        return validResult;
    }

    /**
     * 验证ValueObject的指定field
     *
     * @param object
     * @param <T>
     * @return
     */
    public static <T> ValidResult validField(T object, Map<String, Object> map) {
        ValidResult result = new ValidResult();
        Set<String> keySet = map.keySet();
        keySet.forEach(field -> {
            if (null != field) {
                Set<ConstraintViolation<T>> constraintViolations = validator.validateProperty(object, field);
                result.setError(null != constraintViolations && constraintViolations.size() > 0);
                constraintViolations.forEach(tConstraintViolation -> {
                    result.addError(tConstraintViolation.getPropertyPath().toString(), tConstraintViolation.getMessage());
                });
            }
        });
        return result;
    }

    public static Map<String, Object> objectToMap(Object obj) throws Exception {
        if (obj == null) {
            return null;
        }

        Map<String, Object> map = new HashMap<String, Object>();

        Field[] declaredFields = obj.getClass().getDeclaredFields();
        for (Field field : declaredFields) {
            field.setAccessible(true);
            map.put(field.getName(), field.get(obj));
        }

        return map;
    }
}
