package com.urise.webapp;

import com.urise.webapp.model.Resume;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MainReflection {
    public static void main(String[] args) throws IllegalAccessException, InvocationTargetException {
        Resume r = new Resume("uuid");
        Field field = r.getClass().getDeclaredFields()[0];
        field.setAccessible(true);
        System.out.println(field.getName());
        System.out.println(field.get(r));
        field.set(r, "new_uuid");
        // TODO : invoke r.toString via reflection
        Method[] methods = r.getClass().getMethods();
        for (Method method : methods) {
            if (!method.getName().equals("toString")) {
                continue;
            }
            System.out.println("Invoked toString method via reflection: " + method.invoke(r));
        }

        System.out.println(r);
    }
}
