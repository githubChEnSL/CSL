package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import entity.store;
import jdbc.DatabaseRegulator;
import jdbc.DatabaseStore;

public class StoresController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public StoresController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		String limit = request.getParameter("limit");
		String offset = request.getParameter("offset");
		System.out.println("limit:" + limit + " offset:" + offset);
		DatabaseStore dataStore = new DatabaseStore();
		DatabaseRegulator dataregulator=new DatabaseRegulator();
		// 查询店铺
		List<Map<String, Object>> storeData = new ArrayList<Map<String, Object>>();
		List<store> listStores = dataStore.ListStore();
		for (int i = 0; i < listStores.size(); i++) {
			Map<String, Object> row = new HashMap<>();
			store entity = listStores.get(i);
			row.put("userCount", entity.getStoreId());
			row.put("userName", entity.getStoreName());
			row.put("userRole", dataregulator.GetRegulatorRoleName(entity.getRegulatorId()));
			storeData.add(row);
		}

		Map<String, Object> preparedata = new HashMap<String, Object>();
		preparedata.put("rows", storeData);
		// 将map转为json
		JSONObject rows = new JSONObject(preparedata);
		PrintWriter out = response.getWriter();
		out.print(rows);
		out.flush();
		out.close();
		dataregulator.CloseDatabase();
		dataStore.CloseDatabase();

		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		doGet(request, response);
	}
}
