package com.primemobi.iaas.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author Liuzhanqiao
 * @since 2017-05-24
 */
@TableName("T_LOGINFO")
public class TLoginfo extends Model<TLoginfo> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
	@TableField("user_id")
	private String userId;
	@TableField("user_ip")
	private String userIp;
	@TableField("class_path")
	private String classPath;
	@TableField("class_method")
	private String classMethod;
	@TableField("create_time")
	private Date createTime;
	private String LogLevel;
	private String message;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserIp() {
		return userIp;
	}

	public void setUserIp(String userIp) {
		this.userIp = userIp;
	}

	public String getClassPath() {
		return classPath;
	}

	public void setClassPath(String classPath) {
		this.classPath = classPath;
	}

	public String getClassMethod() {
		return classMethod;
	}

	public void setClassMethod(String classMethod) {
		this.classMethod = classMethod;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getLogLevel() {
		return LogLevel;
	}

	public void setLogLevel(String LogLevel) {
		this.LogLevel = LogLevel;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
