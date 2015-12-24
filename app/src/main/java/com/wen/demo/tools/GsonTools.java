package com.wen.demo.tools;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class GsonTools {

	public GsonTools() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 * 
	 * ��һ�ָ�ʽ��{"person":{"id":1001,"address":"beijing","name":"jack"}}
	 * 
	 * @param string
	 * @param cls
	 * @return
	 */
	public static <T> T getPerson(String string, Class<T> cls) {
		T t = null;
		try {
			Gson gson = new Gson();
			t = gson.fromJson(string, cls);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return t;
	}

	/**
	 * ʹ��Gson����list<Person> �ڶ��ָ�ʽ
	 * ��{"persons":[{"id":1001,"address":"beijing","name":"jack"}, {"id":1002,"address":"shanghai","name":"rose"}]}
	 * @param string
	 * @param cls
	 * @return
	 */
	public static <T> List<T> getPersons(String string, Class<T> cls) {
		List<T> list = new ArrayList<T>();
		try {
			Gson gson = new Gson();
			list = gson.fromJson(string, new TypeToken<List<T>>() {
			}.getType());
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}
/**
 * �����ָ�ʽ��{"liststring"��["beijing","shanghai"],"hunan"}
 * @param string
 * @return
 */
	public static List<String> getList(String string) {
		List<String> list = new ArrayList<String>();
		try {
			Gson gson = new Gson();
			gson.fromJson(string, new TypeToken<List<String>>() {
			}.getType());
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;

	}

	/**
	 * �����ָ�ʽ��{"listmap":[{"id":1001,"address":"beijing","name":"jack"},{"id":1002,"address":"shanghai,"name":"rose"}]}
	 * @param string
	 * @return
	 */
	public static List<Map<String, Object>> lMaps(String string) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		try {
			Gson gson = new Gson();
			list = gson.fromJson(string,
					new TypeToken<List<Map<String, Object>>>() {
					}.getType());
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;

	}
}