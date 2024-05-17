package com.ning.web.jotato.common.annotion;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.ConcurrentReferenceHashMap;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Method;
import java.util.Map;

public class AnnotationUtil extends AnnotationUtils {
    private static final Map<AnnotationCacheKey, Annotation> findAnnotationCache = new ConcurrentReferenceHashMap(256);

    public AnnotationUtil() {
    }

    public static <A extends Annotation> A getClassAnnotation(Class<?> c, Class<A> aClass) {
        if (c == null) {
            return null;
        } else {
            A a = c.getAnnotation(aClass);
            if (a != null) {
                return a;
            } else {
                a = getClassAnnotation(c.getSuperclass(), aClass);
                if (a != null) {
                    return a;
                } else {
                    Class[] var3 = c.getInterfaces();
                    int var4 = var3.length;

                    for(int var5 = 0; var5 < var4; ++var5) {
                        Class<?> i = var3[var5];
                        a = getClassAnnotation(i, aClass);
                        if (a != null) {
                            return a;
                        }
                    }

                    return null;
                }
            }
        }
    }

    public static <A extends Annotation> A getMethodAnnotation(Method m, Class<A> aClass) {
        Method annotatedMethod = getAnnotatedMethod(m, aClass);
        return annotatedMethod == null ? null : annotatedMethod.getAnnotation(aClass);
    }

    public static Method getAnnotatedMethod(Method m, Class<?> aClass) {
        if (m == null) {
            return null;
        } else {
            Annotation[] var2 = m.getAnnotations();
            int var3 = var2.length;

            int var4;
            Class c;
            for(var4 = 0; var4 < var3; ++var4) {
                Annotation a = var2[var4];
                c = a.annotationType();
                if (c == aClass) {
                    return m;
                }
            }

            Class<?> superC = m.getDeclaringClass().getSuperclass();
            if (superC != null && Object.class != superC) {
                try {
                    Method method = getAnnotatedMethod(superC.getMethod(m.getName(), m.getParameterTypes()), aClass);
                    if (method != null) {
                        return method;
                    }
                } catch (Exception var9) {
                }
            }

            Class[] var12 = m.getDeclaringClass().getInterfaces();
            var4 = var12.length;

            for(int var13 = 0; var13 < var4; ++var13) {
                c = var12[var13];

                try {
                    Method method = getAnnotatedMethod(c.getMethod(m.getName(), m.getParameterTypes()), aClass);
                    if (method != null) {
                        return method;
                    }
                } catch (Exception var8) {
                }
            }

            return null;
        }
    }

    public static boolean hasParameterAnnotation(Method m, Class<?> aClass) {
        if (m != null && aClass != null) {
            Annotation[][] paraAnnotations = m.getParameterAnnotations();

            for(int i = 0; i < paraAnnotations.length; ++i) {
                Annotation[] var4 = paraAnnotations[i];
                int var5 = var4.length;

                for(int var6 = 0; var6 < var5; ++var6) {
                    Annotation an = var4[var6];
                    if (an.annotationType() == aClass) {
                        return true;
                    }
                }
            }

            return false;
        } else {
            return false;
        }
    }

    public static <A extends Annotation> Annotation findClassAnnotation(Class<?> clazz, Class<A> annotationType) {
        if (clazz != null && annotationType != null) {
            AnnotationCacheKey cacheKey = new AnnotationCacheKey(clazz, annotationType);
            if (findAnnotationCache.containsKey(cacheKey)) {
                return (Annotation)findAnnotationCache.get(cacheKey);
            } else {
                A result = findAnnotation(clazz, annotationType);
                findAnnotationCache.put(cacheKey, result);
                return result;
            }
        } else {
            return null;
        }
    }

    public static <A extends Annotation> Annotation findMethodAnnotation(Method m, Class<A> annotationType) {
        if (m != null && annotationType != null) {
            AnnotationCacheKey cacheKey = new AnnotationCacheKey(m, annotationType);
            if (findAnnotationCache.containsKey(cacheKey)) {
                return (Annotation)findAnnotationCache.get(cacheKey);
            } else {
                A result = findAnnotation(m, annotationType);
                findAnnotationCache.put(cacheKey, result);
                return result;
            }
        } else {
            return null;
        }
    }

    private static final class AnnotationCacheKey implements Comparable<AnnotationCacheKey> {
        private final AnnotatedElement element;
        private final Class<? extends Annotation> annotationType;

        public AnnotationCacheKey(AnnotatedElement element, Class<? extends Annotation> annotationType) {
            this.element = element;
            this.annotationType = annotationType;
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            } else if (!(other instanceof AnnotationCacheKey)) {
                return false;
            } else {
                AnnotationCacheKey otherKey = (AnnotationCacheKey)other;
                return this.element.equals(otherKey.element) && this.annotationType.equals(otherKey.annotationType);
            }
        }

        public int hashCode() {
            return this.element.hashCode() * 29 + this.annotationType.hashCode();
        }

        public String toString() {
            return "@" + this.annotationType + " on " + this.element;
        }

        public int compareTo(AnnotationCacheKey other) {
            int result = this.element.toString().compareTo(other.element.toString());
            if (result == 0) {
                result = this.annotationType.getName().compareTo(other.annotationType.getName());
            }

            return result;
        }
    }
}

