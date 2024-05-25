package com.ning.web.jotato.core.web.session;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Setter
@Getter
public class SessionUser implements Serializable {

    private static final long serialVersionUID = 834813777905874716L;

    /**
     * 用户号
     */
    private Long userId;

    /**
     * 用户名
     */
    private String userName;


    /**
     * 部门id
     */
    private Long departmentId;


    /**
     * 部门名称
     */
    private String departmentName;



    private Date signInTime;


    private Date loginTime;


    public static SessionUser newSessionUser() {
        return new SessionUser();
    }
}
