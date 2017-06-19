package com.primemobi.iaas.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;



import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;

import javax.net.ssl.SSLContext;  
  
import javax.net.ssl.TrustManager;  
import javax.net.ssl.X509TrustManager;  

import java.security.SecureRandom;
import java.security.cert.CertificateException;  
import java.security.cert.X509Certificate;  
import org.apache.http.client.ClientProtocolException;  
import org.apache.http.client.HttpClient;  
import org.apache.http.client.ResponseHandler;  
import org.apache.http.client.methods.HttpGet;  
import org.apache.http.conn.ClientConnectionManager;  
  
import org.apache.http.conn.scheme.Scheme;  
import org.apache.http.conn.scheme.SchemeRegistry;  
import org.apache.http.conn.scheme.SchemeSocketFactory;  
import org.apache.http.conn.ssl.SSLSocketFactory;  
import org.apache.http.impl.client.BasicResponseHandler;  
import org.apache.http.impl.client.ClientParamsStack;  
import org.apache.http.impl.client.DefaultHttpClient;  
import org.apache.http.params.DefaultedHttpParams;  
import org.apache.http.params.HttpParams;


/**
 * Created by Mr.Q on 2014/5/6.
 */
public class HttpUtil {
	public static String post(String url, Map<String, String> params) {
		HttpClient httpClient = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost(url);
		try {
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			Set<String> keySet = params.keySet();
			for (String key : keySet) {
				nvps.add(new BasicNameValuePair(key, params.get(key)));
			}
			httpPost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
			HttpResponse response = httpClient.execute(httpPost);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				String resJson = EntityUtils.toString(entity);
				System.out.println("返回:\n" + resJson);
				return resJson;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			httpClient.getConnectionManager().shutdown();
		}
		return null;
	}

	public static String get(String url) {
		HttpClient httpClient = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(url);
		try {
			HttpResponse response = httpClient.execute(httpGet);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				String resJson = EntityUtils.toString(entity);
				System.out.println("返回:\n" + resJson);
				return resJson;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			httpClient.getConnectionManager().shutdown();
		}
		return null;
	}
	
	private static DefaultHttpClient getSecuredHttpClient(HttpClient httpClient) {
		final X509Certificate[] _AcceptedIssuers = new X509Certificate[] {};
		try {
			SSLContext ctx = SSLContext.getInstance("TLS");
			X509TrustManager tm = new X509TrustManager() {
				public X509Certificate[] getAcceptedIssuers() {
					return _AcceptedIssuers;
				}

				public void checkServerTrusted(X509Certificate[] chain,
						String authType) throws CertificateException {
				}

				public void checkClientTrusted(X509Certificate[] chain,
						String authType) throws CertificateException {
				}
			};
			ctx.init(null, new TrustManager[] { tm }, new SecureRandom());
			SSLSocketFactory ssf = new SSLSocketFactory(ctx, SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
			ClientConnectionManager ccm = httpClient.getConnectionManager();
			SchemeRegistry sr = ccm.getSchemeRegistry();
			sr.register(new Scheme("https", 443, ssf));
			return new DefaultHttpClient(ccm, httpClient.getParams());
		} catch (Exception e) {
			System.out.println("=====:=====");
			e.printStackTrace();
		}
		return null;
	}

	public static String postJson(String url, String json, String auth_key) {
		HttpClient httpClient = getSecuredHttpClient(new DefaultHttpClient()); // 处理https协议
//		HttpClient httpClient = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost(url);
		try {
			httpPost.setHeader("Content-Type", "application/json");
			httpPost.setHeader("Authorization", "Basic " + new String(Base64.encodeBase64(auth_key.getBytes()), "UTF-8"));
			StringEntity myEntity = new StringEntity(json, "UTF-8");
			httpPost.setEntity(myEntity);
			HttpResponse response = httpClient.execute(httpPost);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				String resJson = EntityUtils.toString(entity);
				System.out.println("返回:\n" + resJson);
				return resJson;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			httpClient.getConnectionManager().shutdown();
		}
		return null;
	}

	public static void main(String[] args) {
		String auth_key = "758e97dc8dd9854cc01e3660:2bee243bd338847b90b499bb";
		Gson gson = new Gson();
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> map1 = new HashMap<String, Object>();
		// map1.put("alias",new String[]{"1"});
		map1.put("tag", new String[] { "15011316037" });
		map.put("audience", map1);
		map.put("platform", "all");
		Map<String, Object> map2 = new HashMap<String, Object>();
		Map<String, Object> map21 = new HashMap<String, Object>();
		map21.put("alert", "你好ya！");
		map2.put("android", map21);
		map.put("notification", map2);
		System.out.println(gson.toJson(map));
		postJson("https://api.jpush.cn/v3/push", gson.toJson(map), auth_key);
		// getJson("https://api.jpush.cn/v3/aliases/00?platform=android,ios");
	}
}
