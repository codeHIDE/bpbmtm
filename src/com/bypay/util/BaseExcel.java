package com.bypay.util;

import java.io.Serializable;
import java.text.DecimalFormat;

public abstract class BaseExcel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8027963323112398994L;

	public abstract String getCellValue(int i);

	/**
	 * 金额保留2两小数的换算 amount 100 rate0.01 那么结果为1.00
	 * 
	 * @param amount
	 * @param rate
	 * @return
	 * 
	 */
	public String amtTransform(String amount, Double rate) {
		if (amount == null || rate == null) {
			return "0.00";
		} else {
			DecimalFormat df = new DecimalFormat("####0.00");
			Double amt = 0.0D;
			try {
				amt = Double.valueOf(amount);
			} catch (Exception e) {
				e.printStackTrace();
				amt = 0.0D;
			}
			return df.format(amt * rate);
		}
	}

}
