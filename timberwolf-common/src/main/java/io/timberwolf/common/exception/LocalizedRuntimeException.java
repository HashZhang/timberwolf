package io.timberwolf.common.exception;

import java.util.ResourceBundle;

/**
 * The base localized Runtime exception class
 *
 * @author Hash Zhang
 * @version 0.0.0
 * @see @http://blog.sina.com.cn/s/blog_a910da270101eko7.html
 */
public abstract class LocalizedRuntimeException extends RuntimeException {
    public static final String DEFAULT_BASE_NAME = "locale/timberwolf";
    private String base = DEFAULT_BASE_NAME;
    protected ResourceBundle resourceBundle;
    protected String messageKey;

    public LocalizedRuntimeException(String messageKey) {
        this.messageKey = messageKey;
        resourceBundle = ResourceBundle.getBundle(base);
    }

    @Override
    public String getMessage() {
        return getLocalizedMessage();
    }

    @Override
    public abstract String getLocalizedMessage();
}
