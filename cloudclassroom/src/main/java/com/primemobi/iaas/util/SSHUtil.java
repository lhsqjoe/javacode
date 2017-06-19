package com.primemobi.iaas.util;

import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.List;

import ch.ethz.ssh2.ChannelCondition;
import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SSHUtil {
	private static Logger logger = LoggerFactory.getLogger(SSHUtil.class);
	static Connection conn;
	public static final String spliString = "@@";
	static String ip;
	static String usr;
	static String psword;
	static String charset = Charset.defaultCharset().toString();
	static int TIME_OUT = 1000 * 60 * 60; // 超时时长设定为1小时
	private static Object session;

	// ssh远程链接
	public static String remoteSshExecutor(String ip1, String user,
			String password, List<String> execComands) throws Exception {
		return remoteExecValue(ip1, user, password, execComands,
				SSHUtil.TIME_OUT);
	}

	public static  String remoteExecValue(String ip1, String user,
			String password, List<String> execComands, int timeout)
			throws Exception {
		SSHUtil.ip = ip1;
		SSHUtil.usr = user;
		SSHUtil.psword = password;
		StringBuffer execComand1 = new StringBuffer();
		for (int i = 0; i < execComands.size(); i++) {
			if (i == execComands.size() - 1) {
				execComand1.append(execComands.get(i));
			} else {
				execComand1.append(execComands.get(i) + "&&");
			}
		}
		SSHUtil.logger.debug("执行命令：" + execComand1.toString());
		System.out.println("执行命令：" + execComand1.toString());
		SSHUtil.conn = new Connection(SSHUtil.ip);
		SSHUtil.conn.connect();
		InputStream stdOut = null;
		InputStream stdErr = null;
		String outStr = "";
		String outErr = "";
		int ret = -1;
		try {
			if (conn.authenticateWithPassword(SSHUtil.usr, SSHUtil.psword)) {
				Session session = conn.openSession();
				session.execCommand(execComand1.toString());
				stdOut = new StreamGobbler(session.getStdout());
				byte[] buf = new byte[1024];
				StringBuilder sb = new StringBuilder();
				while (stdOut.read(buf) != -1) {
					sb.append(new String(buf, SSHUtil.charset));
				}
				outStr = sb.toString();
				stdErr = new StreamGobbler(session.getStderr());
				byte[] buf1 = new byte[1024];
				StringBuilder sb1 = new StringBuilder();
				while (stdErr.read(buf1) != -1) {
					sb1.append(new String(buf1, SSHUtil.charset));
				}
				outErr = sb1.toString();
				session.waitForCondition(ChannelCondition.EXIT_STATUS, timeout);
				ret = session.getExitStatus();
			} else {
				SSHUtil.logger.error("登录远程机器失败" + SSHUtil.ip);
				throw new Exception("登录远程机器失败" + SSHUtil.ip); // 自定义异常类 实现略
			}
		} finally {
			if (SSHUtil.conn != null) {
				SSHUtil.conn.close();
			}
			if (stdOut != null) {
				stdOut.close();
			}
			if (stdErr != null) {
				stdErr.close();
			}
		}
	/*	SSHUtil.logger.info("return message:" + outStr + SSHUtil.spliString
				+ outErr);*/
		return outStr;
	}
}
