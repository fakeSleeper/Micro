package com.weichuang.sensor.base.view;

/**
 * desc : View （Activity,Fragment） 超类 ，UI显示
 *
 * @author Forrest
 * @date 2018/8/13.
 */

public interface AbstractView {

    void showLoginView();

    void showLogoutView();

    void showToast(String message);

    void showSnackBar(String message);
}
