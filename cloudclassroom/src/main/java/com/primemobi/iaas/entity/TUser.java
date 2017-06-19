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
 * 用户
 * </p>
 *
 * @author Liuzhanqiao
 * @since 2017-05-24
 */
@TableName("T_USER")
public class TUser extends Model<TUser> {

    private static final long serialVersionUID = 1L;

	@TableId(value="ID", type= IdType.AUTO)
	private Integer id;
    /**
     * 昵称
     */
	@TableField("USER_NAME")
	private String userName;
    /**
     * 0-否 1-是；只在注册时写入，禁止使用此字段进行业务逻辑处理
     */
	@TableField("IS_AGENT")
	private Integer isAgent;
    /**
     * 0-否 1-是
     */
	@TableField("IS_ADMIN")
	private Integer isAdmin;
	@TableField("PASSWORD")
	private String password;
    /**
     * 真实姓名
     */
	@TableField("NAME")
	private String name;
	@TableField("CERTIFICATE_TYPE")
	private Integer certificateType;
	@TableField("CERTIFICATE_NO")
	private Integer certificateNo;
    /**
     * 手机号
     */
	@TableField("MOBILE")
	private String mobile;
    /**
     * email
     */
	@TableField("EMAIL")
	private String email;
    /**
     * 0-invalid 1-valid 2-forbidden
     */
	@TableField("STATUS")
	private Integer status;
	@TableField("CREATE_TIME")
	private Date createTime;
	@TableField("QUERY_KEY")
	private String queryKey;
	@TableField("SECURY_KEY")
	private String securyKey;
	@TableField("IS_VAILDUSB")
	private Integer isVaildusb;
    /**
     * 通信地址
     */
	@TableField("ADDRESS")
	private String address;
    /**
     * 验证码
     */
	private String validkey;
    /**
     * 邮政编码
     */
	private String postcode;
    /**
     * 公司名称
     */
	private String company;
    /**
     * 工商注册号码
     */
	private String regcode;
    /**
     * 该用户所属的代理商，如果是管理员创建，则默认为来自电商
     */
	@TableField("USER_CREATER")
	private String userCreater;
    /**
     * 0 普通用户 1企业用户； 只在注册时写入，禁止使用此字段进行业务逻辑处理
     */
	private Integer usertype;
    /**
     * 部门ID
     */
	@TableField("DEPID")
	private Integer depid;
    /**
     * 集团ID
     */
	@TableField("ORGID")
	private Integer orgid;
    /**
     * 分公司ID
     */
	@TableField("COMPANYID")
	private Integer companyid;
    /**
     * 二级部门ID
     */
	@TableField("SECDEPID")
	private Integer secdepid;
    /**
     * 数据盘标识：1.有
     */
	@TableField("SFLAG")
	private Integer sflag;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getIsAgent() {
		return isAgent;
	}

	public void setIsAgent(Integer isAgent) {
		this.isAgent = isAgent;
	}

	public Integer getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(Integer isAdmin) {
		this.isAdmin = isAdmin;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getCertificateType() {
		return certificateType;
	}

	public void setCertificateType(Integer certificateType) {
		this.certificateType = certificateType;
	}

	public Integer getCertificateNo() {
		return certificateNo;
	}

	public void setCertificateNo(Integer certificateNo) {
		this.certificateNo = certificateNo;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getQueryKey() {
		return queryKey;
	}

	public void setQueryKey(String queryKey) {
		this.queryKey = queryKey;
	}

	public String getSecuryKey() {
		return securyKey;
	}

	public void setSecuryKey(String securyKey) {
		this.securyKey = securyKey;
	}

	public Integer getIsVaildusb() {
		return isVaildusb;
	}

	public void setIsVaildusb(Integer isVaildusb) {
		this.isVaildusb = isVaildusb;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getValidkey() {
		return validkey;
	}

	public void setValidkey(String validkey) {
		this.validkey = validkey;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getRegcode() {
		return regcode;
	}

	public void setRegcode(String regcode) {
		this.regcode = regcode;
	}

	public String getUserCreater() {
		return userCreater;
	}

	public void setUserCreater(String userCreater) {
		this.userCreater = userCreater;
	}

	public Integer getUsertype() {
		return usertype;
	}

	public void setUsertype(Integer usertype) {
		this.usertype = usertype;
	}

	public Integer getDepid() {
		return depid;
	}

	public void setDepid(Integer depid) {
		this.depid = depid;
	}

	public Integer getOrgid() {
		return orgid;
	}

	public void setOrgid(Integer orgid) {
		this.orgid = orgid;
	}

	public Integer getCompanyid() {
		return companyid;
	}

	public void setCompanyid(Integer companyid) {
		this.companyid = companyid;
	}

	public Integer getSecdepid() {
		return secdepid;
	}

	public void setSecdepid(Integer secdepid) {
		this.secdepid = secdepid;
	}

	public Integer getSflag() {
		return sflag;
	}

	public void setSflag(Integer sflag) {
		this.sflag = sflag;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
