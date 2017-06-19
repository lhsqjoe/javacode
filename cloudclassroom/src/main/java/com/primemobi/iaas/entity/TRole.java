package com.primemobi.iaas.entity;

import java.util.Date;
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
@TableName("T_ROLE")
public class TRole extends Model<TRole> {

    private static final long serialVersionUID = 1L;

	private Integer id;
	private String name;
	@TableField("create_datetime")
	private Date createDatetime;
	@TableField("update_datetime")
	private Date updateDatetime;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreateDatetime() {
		return createDatetime;
	}

	public void setCreateDatetime(Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	public Date getUpdateDatetime() {
		return updateDatetime;
	}

	public void setUpdateDatetime(Date updateDatetime) {
		this.updateDatetime = updateDatetime;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
