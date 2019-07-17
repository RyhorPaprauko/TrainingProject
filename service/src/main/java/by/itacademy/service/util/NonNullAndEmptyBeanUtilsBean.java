package by.itacademy.service.util;

import org.apache.commons.beanutils.BeanUtilsBean;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;

public class NonNullAndEmptyBeanUtilsBean extends BeanUtilsBean {

    @Override
    public void copyProperty(Object dest, String name, Object value)
            throws IllegalAccessException, InvocationTargetException {
        if (value == null || isEmptyIfString(value)) {
            return;
        }
        if (value instanceof Collection) {
            if (((Collection) value).size() == 0) {
                return;
            }
        }
        super.copyProperty(dest, name, value);
    }

    private boolean isEmptyIfString(Object value) {
        return value instanceof String && ((String) value).isEmpty();
    }
}
