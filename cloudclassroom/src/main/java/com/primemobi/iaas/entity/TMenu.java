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
@TableName("T_MENU")
public class TMenu extends Model<TMenu> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
	private Integer pid;
	private String name;
	private Integer leaf;
	private String url;
	private String iconskin;
	private String target;
	@TableField("create_datetime")
	private Date createDatetime;
	@TableField("update_datetime")
	private Date updateDatetime;
	private Integer sn;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getLeaf() {
		return leaf;
	}

	public void setLeaf(Integer leaf) {
		this.leaf = leaf;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getIconskin() {
		return iconskin;
	}

	public void setIconskin(String iconskin) {
		this.iconskin = iconskin;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
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

	public Integer getSn() {
		return sn;
	}

	public void setSn(Integer sn) {
		this.sn = sn;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
