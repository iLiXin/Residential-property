package com.tjpu.property.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @Description �ճ�������ݴ���
 * @version 0.02
 * @Created 2012-11-5
 */
public class DateOpt {

	/**
	 * @Description ��ȡ14λ��ǰʱ��
	 * @author ��ƽ
	 * @Created 2012-11-5
	 * @param ����Ҫ����
	 * @return 14λyyyyMMddHHmmss��ʽ�ĵ�ǰ����ʱ��
	 * @exception Exception
	 */
	public static String getNowTime() throws Exception {
		try {
			Date date = new Date();
			SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
			return sf.format(date);
		} catch (Exception e) {
			// log.error("getNowTime Exception", e);
			throw e;
		}
	}

	/**
	 * @Description ��ȡ8λ��ǰ����
	 * @author ��ƽ
	 * @Created 2012-11-5
	 * @param ����Ҫ����
	 * @return 8λyyyyMMdd��ʽ�ĵ�ǰ����
	 * @exception Exception
	 */
	public static String getNowDate() throws Exception {
		try {
			Date date = new Date();
			SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
			return sf.format(date);
		} catch (Exception e) {
			// log.error("getNowDate Exception", e);
			throw e;
		}
	}

	/**
	 * @Description ���ʱ���ʽ��ȡʱ�䣬��ʽ������yyyy-MM-dd H:m:s��yyyyMM��
	 * @author ��ƽ
	 * @Created 2012-11-5
	 * @param format
	 *            ��ʽ
	 * @return ���ʽ���������ڡ�ʱ���ַ�
	 * @exception Exception
	 */
	public static String getNowByFormat(String format) throws Exception {
		try {
			Date date = new Date();
			SimpleDateFormat sf = new SimpleDateFormat(format);
			return sf.format(date);
		} catch (Exception e) {
			// log.error("getNowByFormat Exception", e);
			throw e;
		}
	}

	/**
	 * @Description ��ȡһЩ����ǰ������
	 * @author ��ƽ
	 * @Created 2012-11-5
	 * @param delay
	 *            ��ǰ��Խ������
	 * @return 8λyyyyMMdd��ʽ������ʱ��
	 * @exception Exception
	 */
	public static String getOldDateByDate(int delay) throws Exception {
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
			Calendar c = Calendar.getInstance();
			c.add(Calendar.DAY_OF_MONTH, -delay);
			String oldtime = format.format(c.getTime());
			return oldtime;
		} catch (Exception e) {
			// log.error("getOldDateByDate Exception", e);
			throw e;
		}
	}

	/**
	 * @Description ��ȡһЩ����ǰ������ʱ��
	 * @author ��ƽ
	 * @Created 2012-11-5
	 * @param delay
	 *            ��ǰ��Խ������
	 * @return 14λyyyyMMddHHmmss��ʽ������ʱ��
	 * @exception Exception
	 */
	public static String getOldTimeByDate(int delay) throws Exception {
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
			Calendar c = Calendar.getInstance();
			c.add(Calendar.DAY_OF_MONTH, -delay);
			String oldtime = format.format(c.getTime());
			return oldtime;
		} catch (Exception e) {
			// log.error("getOldTimeByDate Exception", e);
			throw e;
		}
	}

	/**
	 * @Description ��ȡһЩ������ǰ������ʱ��
	 * @author ��ƽ
	 * @Created 2012-11-5
	 * @param delay
	 *            ��ǰ��Խ�ķ�����
	 * @return 14λyyyyMMddHHmmss��ʽ������ʱ��
	 * @exception Exception
	 */
	public static String getOldTimeByMinute(int delay) throws Exception {
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
			Calendar c = Calendar.getInstance();
			c.add(Calendar.MINUTE, -delay);
			String oldtime = format.format(c.getTime());
			return oldtime;
		} catch (Exception e) {
			// log.error("getOldTimeByMinute Exception", e);
			throw e;
		}
	}

	/**
	 * @Description ����������ڣ�������������������
	 * @author ��ƽ
	 * @Created 2012-11-7
	 * @param date1
	 *            ���ϵ�����yyyyMMdd
	 * @param date2
	 *            ���µ�����yyyyMMdd
	 * @return �������
	 * @exception Exception
	 */
	public static long getSubDateByDate(String date1, String date2)
			throws Exception {
		try {
			long subDate = 0;
			SimpleDateFormat ft = new SimpleDateFormat("yyyyMMdd");

			Date d2 = ft.parse(date2);
			Date d1 = ft.parse(date1);
			subDate = d2.getTime() - d1.getTime();
			subDate = subDate / 1000 / 60 / 60 / 24;

			return subDate;
		} catch (Exception e) {
			// log.error("getSubDateByDate Exception", e);
			throw e;
		}
	}

}
