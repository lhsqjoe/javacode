package com.primemobi.iaas.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.primemobi.iaas.common.ServiceException;
import com.primemobi.iaas.entity.TUser;
import com.primemobi.iaas.service.ITUserService;
import com.primemobi.iaas.util.Const;
import com.xerox.amazonws.ec2.EC2Exception;
import com.xerox.amazonws.ec2.ImageDescription;
import com.xerox.amazonws.ec2.InstanceStateChangeDescription;
import com.xerox.amazonws.ec2.Jec2;
import com.xerox.amazonws.ec2.LaunchConfiguration;
import com.xerox.amazonws.ec2.ReservationDescription;
import com.xerox.amazonws.ec2.SnapshotInfo;
import com.xerox.amazonws.ec2.VmTypeWebDescription;

/**
 * Created with IntelliJ IDEA. User: George Date: 12-8-27 Time: 下午5:34 To change
 * this template use File | Settings | File Templates.
 */
public class BaseFunction {
	
	@Autowired
    ITUserService userService;
	protected Jec2 ec2;
	//private AwsCredentials credentials;
	private static Logger logger = Logger.getLogger(BaseFunction.class.getName());
    private BaseFunction(){}
    private static BaseFunction instance =  new BaseFunction();
    public static BaseFunction getInstance (){
        return instance;
    }

    /*public TInstance[] listInstances(String... instanceIds){
		List<String> params = new ArrayList<String>();
		for(String instanceId:instanceIds){
			params.add(instanceId);
		}
		try {
			Jec2 ec2 = this.initConnection();
			List<ReservationDescription> instances = ec2
					.describeInstances(params);
			List<TInstance> result = new ArrayList<TInstance>();
			if (instances != null) {
				for (ReservationDescription d : instances) {
					for (int index = 0; index < d.getInstances().size(); index++) {
						result.add(new TInstance(d, index));
					}
				}
				return result.toArray(new TInstance[0]);
			}
		} catch (Exception ex) {
			logger.log(Level.SEVERE, "Listing instances failed: "
					+ ex.getMessage());

		}
		return null;

	}*/

    public ReservationDescription describeInstances(String instanceId) throws Exception{
    	
    	try {
    	Jec2 ec2 = this.initConnection();
    	List<ReservationDescription> list =  ec2.describeInstances(new String[]{instanceId});
    	if(list!=null && list.size()>0){
    		return list.get(0);
    	}else{
    		return null;
    	}
		} catch (EC2Exception e) {
			logger.log(Level.SEVERE, e.getMessage());
			return null;
		}
    }
    
	public ImageDescription[] listImages(boolean all)  throws Exception{

		// initialize the interface

		List<ImageDescription> result = new ArrayList<ImageDescription>();
		List<SnapshotInfo> snapshotResult = new ArrayList<SnapshotInfo>();
		try {
			Jec2 ec2 = this.initConnection();
			// snapshotResult = ec2.describeSnapshots(new String[] {});
			if (all) {
				result = ec2.describeImages(new String[] {});// .toArray(new
				// ImageDescription[0]);
			} else {
				ArrayList<String> list = new ArrayList<String>();
				list.add("342202941070");

				result = ec2.describeImagesByOwner(list);

			}

			return result.toArray(new ImageDescription[0]);

		} catch (Exception ex) {
			logger.log(Level.SEVERE, "Listing images failed: "
					+ ex.getMessage());

			// ex.printStackTrace();

			return null;
		}

	}
	/**
	 * 支持NC位置的创建虚拟机
	 * @param imageId
	 * @param groups
	 * @param keyName
	 * @param userData
	 * @param instanceType
	 * @param count
	 * @param availabilityZone
	 * @param kernelId
	 * @param ramdiskId
	 * @param realUser
	 * @param targetNode 扩展了targetNode的内容，支持制定nc
	 * @return
	 * @throws Exception
	 */
	public ReservationDescription runInstance(String imageId, String[] groups,
			String keyName, byte[] userData, String instanceType, int count,
			String availabilityZone, String kernelId, String ramdiskId,
			TUser realUser, String targetNode) throws Exception {
		try {
			if (StringUtils.isNotBlank(targetNode)) {
				return runInstance(imageId, groups, keyName, userData,
						instanceType, count, availabilityZone, kernelId,
						ramdiskId, realUser);
			} else {
				List<String> groupList = Arrays.asList(groups);
				Jec2 ec2 = this.initConnection(realUser);
				// ReservationDescription d = ec2.runInstances(imageId,count, count,
				// groupList, userData, keyName,instanceType);

				LaunchConfiguration l = new LaunchConfiguration(imageId);
				l.setAvailabilityZone(availabilityZone);
				l.setInstanceType(instanceType);
				l.setKernelId(kernelId);
				l.setKeyName(keyName);
				l.setMaxCount(count);
				l.setMinCount(count);
				l.setRamdiskId(ramdiskId);
				l.setSecurityGroup(groupList);
				l.setUserData(userData);
                if(targetNode!=null){
                    l.setTargetNode(targetNode);//add by ftuo
                }
				ReservationDescription d = ec2.runInstances(l);
				return d;
			}

		} catch (Exception ex) {
			logger.log(Level.SEVERE,
					"Starting instance failed: " + ex.getMessage());

			throw ex;
		}
	}

	/**
	 * 运行实例
	 * 
	 * @param imageId
	 * @param groups
	 * @param keyName
	 * @param userData
	 * @param instanceType
	 * @param count
	 * @param availabilityZone
	 * @param kernelId
	 * @param ramdiskId
	 * @param realUser TODO
	 * @return
	 */
	public ReservationDescription runInstance(String imageId, String[] groups,
			String keyName, byte[] userData, String instanceType,
			int count, String availabilityZone, String kernelId,
			String ramdiskId, TUser realUser)  throws Exception{


		List<String> groupList = Arrays.asList(groups);

		try {
			Jec2 ec2 = this.initConnection(realUser);
			// ReservationDescription d = ec2.runInstances(imageId,count, count,
			// groupList, userData, keyName,instanceType);

			LaunchConfiguration l = new LaunchConfiguration(imageId);
			l.setAvailabilityZone(availabilityZone);
			l.setInstanceType(instanceType);
			l.setKernelId(kernelId);
			l.setKeyName(keyName);
			l.setMaxCount(count);
			l.setMinCount(count);
			l.setRamdiskId(ramdiskId);
			l.setSecurityGroup(groupList);
			//l.setTargetNode("10.10.12.191"); // 设置需要部署的ncIP地址
			l.setUserData(userData);

			ReservationDescription d = ec2.runInstances(l);
			return d;

		} catch (Exception ex) {
			logger.log(Level.SEVERE, "Starting instance failed: "
					+ ex.getMessage());

			throw ex;
		}

	}

	/**
	 * 终止实例
	 * 
	 * @param instanceId
	 * @return
	 */
	public InstanceStateChangeDescription terminateInstance(String instanceId,TUser adminUser)  throws Exception{
		try {
            Jec2 ec2=null;
            if(adminUser!=null){
                ec2= this.initConnection(adminUser);
            }else{
                ec2=this.initConnection();
            }
			List<InstanceStateChangeDescription> insdesc = ec2.terminateInstances(new String[] { instanceId });
			if(insdesc!=null && insdesc.size()>0){
				return ec2.terminateInstances(new String[] { instanceId }).get(0);
			}else{
				// 实例不存在，或者其他异常，action处理
				return null;
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			logger.log(Level.SEVERE, "Terminating instance failed: "
					+ ex.getMessage());

			throw ex;
		}

	}

	/**
	 * 停止实例
	 * 
	 * @param instanceId
	 * @return
	 */
	public List<InstanceStateChangeDescription> stopInstance(String instanceId,TUser adminUser)  throws Exception{
		try {
            Jec2 ec2=null;
            if(adminUser!=null){
                ec2= this.initConnection(adminUser);
            }else{
                ec2=this.initConnection();
            }
			return  ec2.stopInstances((new String[] { instanceId }), true);
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.log(Level.SEVERE, "stopping instance failed: "
					+ ex.getMessage());

			throw ex;
		}

	}

    /**
     * 关机
     * @param instanceId
     * @return
     * @throws Exception 
     */
    public List<InstanceStateChangeDescription> poweroffInstance(String instanceId,TUser adminUser) throws Exception{
        try {
            Jec2 ec2=null;
            if(adminUser!=null){
                ec2= this.initConnection(adminUser);
            }else{
                ec2=this.initConnection();
            }

            return  ec2.poweroffInstances((new String[] { instanceId }), true);

        } catch (Exception ex) {
            ex.printStackTrace();
            logger.log(Level.SEVERE, "poweroff instance failed: "
                    + ex.getMessage());
            throw ex;
        }

    }

	/**
	 * 启动实例
	 * 
	 * @param instanceId
	 * @return
	 */
	public List<String> startInstance(String instanceId,TUser adminUser) throws Exception{
		try {
            Jec2 ec2=null;
            if(adminUser!=null){
                ec2= this.initConnection(adminUser);
            }else{
                ec2=this.initConnection();
            }
			return  ec2.startInstances((new String[] { instanceId }));

		} catch (Exception ex) {
			logger.log(Level.SEVERE, "start instance failed: "
					+ ex.getMessage());

			throw ex;
		}

	}

	/**
	 * reboot实例
	 * 
	 * @param instanceId
	 */
	public boolean rebootInstance(String instanceId,TUser adminUser) throws Exception{

		try {
            Jec2 ec2=null;
            if(adminUser!=null){
                ec2= this.initConnection(adminUser);
            }else{
                ec2=this.initConnection();
            }
			ec2.rebootInstances(new String[] { instanceId });
			return true;

		} catch (Exception ex) {
			logger.log(Level.SEVERE, "Rebooting instance failed: "
					+ ex.getMessage());
			throw ex;
		}

	}

	/**
	 * Get All vmware type list if vmType name is null
	 * @param vmTypeName
	 * @return
	 */
	public List<VmTypeWebDescription> getVmTypeList(String vmTypeName,TUser... tusers){
		try {
			Jec2 ec2 = this.initConnection(tusers);
			return  ec2.getVmTypes(vmTypeName);

		} catch (Exception ex) {
			ex.printStackTrace();
			logger.log(Level.SEVERE, "getVmTYpes operation failed: " + ex.fillInStackTrace());
			return new ArrayList<VmTypeWebDescription>();
		}
	}
	/**
	 * 删除vmtyupe
	 * @param vmTypeNameSet
	 * @param tusers
	 * @return
	 */
	public boolean deleteVmTypeList(Set<String> vmTypeNameSet,TUser... tusers){
		try {
			Jec2 ec2 = this.initConnection(tusers);
			ec2.deleteVMTypes(vmTypeNameSet);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.log(Level.SEVERE, "deleteVmTypeList operation failed: " + ex.fillInStackTrace());
			return false;
		}
	}
	
	/**
	 * update VmType,generlly from zero value to realltily value
	 * @param vmSet
	 * @throws ServiceException 
	 */
	public void updateVMTypes(Set<VmTypeWebDescription> vmSet,TUser... tusers) throws ServiceException,Exception{
		try {
			Jec2 ec2 = this.initConnection(tusers);
			ec2.updateVMTypes(vmSet);

		} catch (EC2Exception ex) {
			logger.log(Level.SEVERE, "updateVMTypes failed: " + ex.getMessage());
			throw new ServiceException(this.getClass(),ex.getMessage());
		}
	}
    public void modifyInstanceAttribute(String instanceId, String attribute, String value) throws Exception{

        try {
        	Jec2 ec2 = this.initConnection();
            ec2.modifyInstanceAttribute(instanceId,attribute,value,"");

        } catch (Exception ex) {
            logger.log(Level.SEVERE, "Rebooting instance failed: "
                    + ex.getMessage());
        }
    }

	/**
	 * 初始化
	 *
	 * @return
	 */
	public Jec2 initConnection(TUser... tusers){
		//增加指定用户的链接
		try {
			boolean isSecure = false;
			String accessKeyId=null;
			String secretAccessKey=null;
			String server=null;
			int port=-1;
            server =Const.clcHost;
            port = Integer.valueOf(Const.clcPort);
            System.out.println("port ;;;;;;;;"+Integer.valueOf(Const.clcPort));
            if(tusers == null || tusers.length != 1){
                //取admin的授权
				TUser adminUser = userService.selectOne(new EntityWrapper<TUser>().eq("USER_NAME", Const.ADMIN));
            	accessKeyId = adminUser.getQueryKey();
            	secretAccessKey = adminUser.getSecuryKey();
            }else{
            	TUser user = tusers[0];
            	accessKeyId = user.getQueryKey();
            	secretAccessKey = user.getSecuryKey();
            }
			String resourcePrefix = Const.resourcePrefix;
			int signatureVersion = 1;
			// initialize the interface
			Jec2 ec2 = new Jec2(accessKeyId, secretAccessKey, isSecure, server,
					port);

			ec2.setResourcePrefix(resourcePrefix);
			ec2.setSignatureVersion(signatureVersion);

			return ec2;

		} catch (Exception ex) {
			Logger.getAnonymousLogger().severe(
					"Initializing configuration failed.");

			ex.printStackTrace();

			return null;
		}
	}


    public Jec2 initConnectionForThread(TUser... tusers){
        //增加指定用户的链接
        try {
            boolean isSecure = false;
            String accessKeyId=null;
            String secretAccessKey=null;
            String server=null;
            int port=-1;
            server =Const.clcHost;
            port = Integer.valueOf(Const.clcPort);
            TUser user = tusers[0];
            accessKeyId = user.getQueryKey();
            secretAccessKey = user.getSecuryKey();
            String resourcePrefix = Const.resourcePrefix;
            int signatureVersion = 1;
            // initialize the interface
            Jec2 ec2 = new Jec2(accessKeyId, secretAccessKey, isSecure, server,
                    port);
            ec2.setResourcePrefix(resourcePrefix);
            ec2.setSignatureVersion(signatureVersion);
            return ec2;
        } catch (Exception ex) {
            Logger.getAnonymousLogger().severe(
                    "Initializing configuration failed.");
            ex.printStackTrace();

            return null;
        }
    }
}
