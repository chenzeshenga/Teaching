package com.gs.job;

import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2017/10/27.
 */
@Component
public class CacheClearJob {

    public void run() {
        System.out.println("清空缓存");
    }

}
