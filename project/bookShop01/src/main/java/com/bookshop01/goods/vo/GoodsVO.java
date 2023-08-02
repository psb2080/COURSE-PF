package com.bookshop01.goods.vo;

import java.sql.Date;
import java.util.ArrayList;

public class GoodsVO {
	private int goods_id;
	private String goods_title;
	
	private int    goods_price;
	private String goods_sort;
	private int    goods_sales_price;
	private int    goods_point;
	private String goods_delivery_price;
	private Date goods_delivery_date;
	private String goods_fileName;
	private String goods_status;
	
	private String goods_contents_order;
	private String goods_recommendation;
	private Date   goods_credate;
	private String goods_del_yn;
	
	public GoodsVO() {
	}

	



	public int getGoods_id() {
		return goods_id;
	}





	public void setGoods_id(int goods_id) {
		this.goods_id = goods_id;
	}





	public String getGoods_title() {
		return goods_title;
	}



	public void setGoods_title(String goods_title) {
		this.goods_title = goods_title;
	}


	public int getGoods_price() {
		return goods_price;
	}



	public void setGoods_price(int goods_price) {
		this.goods_price = goods_price;
	}


	public String getGoods_sort() {
		return goods_sort;
	}

	public void setGoods_sort(String goods_sort) {
		this.goods_sort = goods_sort;
	}
	public int getGoods_sales_price() {
		return goods_sales_price;
	}

	public void setGoods_sales_price(int goods_sales_price) {
		this.goods_sales_price = goods_sales_price;
	}

	public int getGoods_point() {
		return goods_point;
	}



	public void setGoods_point(int goods_point) {
		this.goods_point = goods_point;
	}





	public String getGoods_delivery_price() {
		return goods_delivery_price;
	}



	public void setGoods_delivery_price(String goods_delivery_price) {
		this.goods_delivery_price = goods_delivery_price;
	}



	public Date getGoods_delivery_date() {
		return goods_delivery_date;
	}



	public void setGoods_delivery_date(Date goods_delivery_date) {
		this.goods_delivery_date = goods_delivery_date;
	}





	public String getGoods_fileName() {
		return goods_fileName;
	}



	public void setGoods_fileName(String goods_fileName) {
		this.goods_fileName = goods_fileName;
	}



	
	public String getGoods_status() {
		return goods_status;
	}





	public void setGoods_status(String goods_status) {
		this.goods_status = goods_status;
	}


	public String getGoods_contents_order() {
		return goods_contents_order;
	}
	public void setGoods_contents_order(String goods_contents_order) {
		this.goods_contents_order = goods_contents_order;
	}
	

	public String getGoods_recommendation() {
		return goods_recommendation;
	}

	public void setGoods_recommendation(String goods_recommendation) {
		this.goods_recommendation = goods_recommendation;
	}

	public Date getGoods_credate() {
		return goods_credate;
	}

	public void setGoods_credate(Date goods_credate) {
		this.goods_credate = goods_credate;
	}
	
	public String getGoods_del_yn() {
		return goods_del_yn;
	}
	
	public void setGoods_del_yn(String goods_del_yn) {
		this.goods_del_yn = goods_del_yn;
	}
	

}
