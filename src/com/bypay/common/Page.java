package com.bypay.common;


public class Page {

	public static final int DEFAULT_SIZEPERPAGE = 15; //默认的每页显示条数为15
	
	private int totalPage;  //分页时总页数
	
	private int sizePerPage; //每页显示行数
	
	private int allRowCount; //总记录的行数
	
	private int curPage; //当前页
	
	
	public int getTotalPage() {
		return totalPage;
	}

	public int getSizePerPage() {
		return sizePerPage;
	}


	public int getAllRowCount() {
		return allRowCount;
	}


	public int getCurPage() {
		return curPage;
	}

	

	/**
	 * 
	 * @param curPage       当前页
	 * @param sizePerPage   每页记录数
	 * @param allRowCount   总记录数
	 */
	public Page (int curPage, int sizePerPage , int allRowCount ){
		if(curPage < 0){
			throw new IllegalArgumentException();
		}
		
		if(sizePerPage <= 0){
			throw new IllegalArgumentException();
		}
		
		if(allRowCount <0){
			throw new IllegalArgumentException("记录总数(allRowCount)必须大于等于0的整数");
		}
		
		this.allRowCount = allRowCount;
		this.sizePerPage = sizePerPage;
		this.curPage = curPage;
		
		//计算总页数
		if(allRowCount % sizePerPage != 0){
			totalPage = allRowCount / sizePerPage + 1 ;
		}else{
			totalPage = allRowCount / sizePerPage ;
		}
		
		if(totalPage > 0) {
			if(curPage > totalPage){
				curPage = totalPage;
				this.curPage = curPage;
			}		
		}
	}
	
	public static void main(String[] args) {
		int x = 5;
		Page  p = new Page(20,10,200);
		System.out.println(p.getAllRowCount());
		System.out.println(p.getCurPage());
		System.out.println(p.getSizePerPage());
		System.out.println(p.getTotalPage());
	}
	
}
