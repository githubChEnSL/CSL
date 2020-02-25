package com.domain;/**
 * Description QueryVo
 * Created by 隔山海 .
 * date on 2020/2/21.
 */

import java.util.List;

/**
 * @ClassName QueryVo
 * @Description TODO
 * @Author 隔山海
 * @date 2020/2/21 17:22
 * @Version 1.0
 */
public class QueryVo {

    private User user;

    private List<Integer> ids;

    public List<Integer> getIds() {
        return ids;
    }

    public void setIds(List<Integer> ids) {
        this.ids = ids;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
