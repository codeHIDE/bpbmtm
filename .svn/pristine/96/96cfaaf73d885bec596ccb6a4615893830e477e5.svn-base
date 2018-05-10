package com.bypay.action;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.bypay.common.BaseAction;
import com.bypay.domain.OrderRisk;
import com.bypay.service.OrderRiskService;
import com.bypay.util.ImportExcelFile;
import com.bypay.util.PageUtil;

public class AbnormalOrderAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	@Inject
	private OrderRiskService orderRiskService;
	private OrderRisk orderRisk;

	private int page = 1;
	private int pageSize = 15;

	/**
	 * 异常交易查询
	 */
	public void selecrAbnormalOrder() {
		String startTime = getRequest().getParameter("startTime");
		String endTime = getRequest().getParameter("endTime");
		Map map = PageUtil.getPageMap(page, pageSize);
		Integer page = (Integer) map.get("page");
		Integer pageSize = (Integer) map.get("pageSize");
		if(null == orderRisk) {
			orderRisk = new OrderRisk();
		}
		orderRisk.setPage(page);
		orderRisk.setPageSize(pageSize);
		List<OrderRisk> orderRiskList = orderRiskService.getOrderRiskList(orderRisk, startTime, endTime);
		int count = orderRiskService.getOrderRiskListCount(orderRisk, startTime, endTime);
		JSONArray array = JSONArray.fromObject(orderRiskList);
		JSONObject object = new JSONObject();
		object.put("Rows", array.toString());
		object.put("Total", count);
		String orderList = object.toString();
		System.out.println(orderList);
		try {
			getResponse().getWriter().write(orderList.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 异常订单下载
	 */
	public void download_excel() {
		String startTime = this.getRequest().getParameter("startTime");
		String endTime = this.getRequest().getParameter("endTime");
		if(null == orderRisk) {
			orderRisk = new OrderRisk();
		}
		orderRisk.setIsDownload("1");
		List<OrderRisk> orderRiskList = orderRiskService.getOrderRiskList(orderRisk, startTime, endTime);
		HttpServletResponse response = getResponse();
		
		if ( null != orderRiskList && orderRiskList.size() > 2000) {
			try {
				response.setHeader("Content-type", "text/html;charset=UTF-8");
				response.getWriter().write("<script>alert('弹出，超2000');window.close()</script>");
			} catch (IOException e) {
				e.printStackTrace();
			}
			return;
		}
		//设置下载类型
		response.setContentType("application/x-download");
		String sheetName = "异常订单";//文件名
		
		Map<String, String> mapfile = new LinkedHashMap<String, String>();
		mapfile.put("orderId", "订单号");
		mapfile.put("subMerId", "商户号");
		mapfile.put("orderRiskTypes", "异常类型");
		mapfile.put("orderRiskProcTypes", "异常处理方式");
		mapfile.put("orderRiskProcTime", "处理时间");
		try {
			response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(sheetName, "UTF-8") + ".xls");
			ImportExcelFile.ImportExcel(orderRiskList, response.getOutputStream(), mapfile, sheetName, null);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}


	public OrderRisk getOrderRisk() {
		return orderRisk;
	}

	public void setOrderRisk(OrderRisk orderRisk) {
		this.orderRisk = orderRisk;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

}
