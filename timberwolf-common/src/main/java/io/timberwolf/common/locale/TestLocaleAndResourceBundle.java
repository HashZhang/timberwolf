package io.timberwolf.common.locale;

import java.io.UnsupportedEncodingException;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * A class to show how to localize
 *
 * @author Hash Zhang
 * @version 0.0.0
 * @see @http://blog.sina.com.cn/s/blog_a910da270101eko7.html
 */
public class TestLocaleAndResourceBundle {
    public static void main(String[] args) throws UnsupportedEncodingException {
        Locale locale1 = new Locale("zh", "CN");
        ResourceBundle resb1 = ResourceBundle.getBundle("locale/timberwolf", locale1);
        System.out.println(new String(resb1.getString("FrameName").getBytes("ISO-8859-1"), "utf-8"));

        ResourceBundle resb2 = ResourceBundle.getBundle("locale/timberwolf", Locale.getDefault());
        System.out.println(new String(resb2.getString("FrameName").getBytes("ISO-8859-1"), "utf-8"));

        Locale locale3 = new Locale("en", "US");
        ResourceBundle resb3 = ResourceBundle.getBundle("locale/timberwolf", locale3);
        System.out.println(new String(resb3.getString("FrameName").getBytes("ISO-8859-1"), "utf-8"));
    }
}
