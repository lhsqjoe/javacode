package com.primemobi.iaas.util;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Const {

  public static String BASE_PATH;

  public static String PROGECT_NAME;

  public static String COPY_RIGHT;

  public static int PAGE_SIZE;
  public static String REAL_BASE_PATH;

  public static String TOTAL_ROW_KEY = "FTUO_TOTAL_ROW";

  public static String LOGIN_SESSION_KEY = "FTUOLoginUser";

  public static String LOGIN_MENU_TREE_IDS_KEY = "FTUOLoginMenuTreeId";

  public static String SUCCESS = "1";

  public static String ERROR = "0";

  public static class DATABASE_TYPE {
    public static String MYSQL = "MYSQL";

    public static String ORACLE = "ORACLE";
  }

  public static JdbcTemplate jdbcTemplate;

  public static SqlSessionTemplate sqlSessionTemplate;


  public static volatile RabbitTemplate amqpTemplate;


  // public static Session hibernateSession;
  public static String CONFIG = "CONFIG_SERVLET";

  public static String ADMIN_FLAG = "ADMIN_FLAG";

  public static int PROJECT_PORT = 80;

  public static final String USER_ROLE_SESSION_KEY = "USERROLEKEY";

  public static String USER_PORTAL_FLAG = "USER_PORTAL_FLAG";

  public static String ADMIN = "admin";

  public static String localDiskSet = "vdd,vde,vdf,vdg,vdh,vdi,vdj,vdk,vdl,vdm,vdn";

  public static String remoteDiskSet = "vdd,vde,vdf,vdg,vdh,vdi,vdj,vdk,vdl,vdm,vdn,vdo,vdp,vdq,vdr,vds,vdt,vdu,vdv,vdw,vdx,vdy,vdz";

  public static String QUEUE_CLOUD_HEALTH = "queue.cloud.health";

  public static Map<String, String> ncMap = new HashMap<String, String>();

  public static String USER_SYSC_CTOB = "queue.user.ctob.sync";

  public static final byte g[] = new byte[] { 99,99,99,95,49,49,48,55,64,115,105,110,97,46,99,111,109 };

  public static final String ISWARN_FLAG = new String(g);

  public static String BACKUP_PATH;

  public static String SYS_BACKUP_ACTUATORS;
  
//  public static boolean DEV_MODE = true;
  public static boolean DEV_MODE = false;

  public static int RESOURCE_TIME_OUT = 5;

  public static final String BACKUP_PREFIX = "bk";

  public static String portal_img_path="images_backup";

    //虚机备份的三种状态
  public static String on_progress_backup="backuping";
  public static String on_finish_backup="complete";
  public static String on_fail_backup="fail";

  /** 备份规则与实例的关系 k/y 为 规则ID/实例编号集合 */
  public static Map<Integer,List<String>> ruleInstMap = new ConcurrentHashMap<Integer,List<String>>();

  public static String sg_operation_allow="normal";

  public static String sg_operation_reject="drop";

  //用户自定义安全组规则的初始优先级
  public static int initPriority=10001;

  //队列名称
  public static String QUEUE_SG_LOADRULES ="queue.sg.loadrules";
  public static String QUEUE_RESTORE_START ="queue.restore.start";


  //下发安全组ws时，对应flag参数的值
  public static String sg_ws_tag ="FGZ";

  //网络节点管理动作
  public static String ACTION_CREATE = "create";
  public static String ACTION_DESTORY = "destory";

  //安全组文件的路径
  public static String sgPath="/opt/eucalyptus/sg";
  //截屏的存放路径
  public static String screenshotPath="/opt/screenshot";
  public static Map<String,Integer> backStatusMap=new ConcurrentHashMap<String, Integer>();

  public static final String win_port="10089";
  public static final String linux_port="22";

  public static final String resourcePrefix="/services/Eucalyptus";
  public static final String resourceAccountPrefix="/services/Accounts";

  public static String root="root";
  public static String tomcat6="tomcat6";
  public static String tomcat7="tomcat7";



  public static Map<String, String> smsAuthCode = new HashMap<String, String>();
  //是否加载默认安全组，管理员可配置
  public static String defaultSgFlag="0";
  //是否发送短信
  public static boolean sendSmsFlag=false;
  //是否加载默认的数据盘
  public static boolean defaultDatadisk=false;
  //虚机agent的修改标记
  public static String agentFlag="2,3";

  public static String vmHostnamePrefix="vm_";
  public static String vmOwnerPrefix="prime";

  public static int curCreatingVmCount=0;

  public static String userDefaultPwd="111111";

  //上课前重启VM的等待时间，如果到此时间agent没有签到，则重启此VM，默认为5分钟
  public static Long waitTime=300L;

  public static Map<String, String> actionLog = new HashMap<String, String>();

  //VNC的端口文件
  public static String vncfilePath="/opt/vcloudm/vnc_tokens";

  //备份路径
  public static String backupPath="/var/lib/eucalyptus/backups/admin/";
  //课程文件路径
  public static String coursePath="/var/lib/courses/";

  //版本号
  public static String vicloudVer="2.3";

  public static String sysStoreLocal="/var/lib/eucalyptus/instances";
  public static String dataStoreLocal="/var/lib/eucalyptus/volumes";
  public static String backStoreLocal="/var/lib/eucalyptus/backups";

  public static String domainPort="389";
  public static String PROJECTNAME="DiCloud";

  public static String clcHost="";
  public static String clcPassword="";
  public static String clcPort="";
  public static String clcUser="root";
}
