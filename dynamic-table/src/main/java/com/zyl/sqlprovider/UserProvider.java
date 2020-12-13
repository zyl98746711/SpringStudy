package com.zyl.sqlprovider;

import com.zyl.domain.User;
import org.apache.ibatis.jdbc.SQL;

/**
 * 用户SQL
 *
 * @author zyl
 * @create 2020/12/13 12:46 下午
 */
public class UserProvider {

    public String saveSql(User user) {
        return new SQL()
                .INSERT_INTO("t_user")
                .VALUES("user_name", "#{userName}")
                .VALUES("age", "#{age}")
                .toString();
    }
}
