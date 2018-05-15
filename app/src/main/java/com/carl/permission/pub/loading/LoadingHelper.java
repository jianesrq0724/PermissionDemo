package com.carl.permission.pub.loading;

/**
 * @author Carl
 * @version 1.0
 * @since 2018/5/10
 */
public class LoadingHelper {

    public int count;

    public void addCount() {
        count++;
    }

    public void subCount() {
        count--;
    }

    public boolean isZero() {
        return count <= 0;
    }

}
