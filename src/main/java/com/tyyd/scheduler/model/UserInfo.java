package com.tyyd.scheduler.model;

import org.apache.commons.lang3.StringEscapeUtils;

public class UserInfo extends BaseDO{
	/**
     * 
     */
    private static final long serialVersionUID = -3692968356210869513L;
    private int id;
	private String userName;
	private String userPwd;
	private String status;

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    /**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer().append("{\"id\":").append(id)
											.append(",\"password\":\"").append(StringEscapeUtils.escapeEcmaScript(userPwd))
											.append("\",\"status\":\"").append(status)
											.append("\",\"username\":\"").append(StringEscapeUtils.escapeEcmaScript(userName)).append("\"}");
		return sb.toString();
	}

}
