package com.ktds.lizzy.common.web.pager;

public abstract class Pager {
	
	public static final boolean ORACLE = true;
	public static final boolean OTHER = false;
	
	/**
	 * 전체 게시물의 수
	 * 검색을 할 경우, 검색된 게시글의 수
	 */
	private int totalArticleCount;
	/**
	 * 한 페이지에 노출할 게시글의 수
	 * 기본 값으로 10을 가짐.
	 */
	protected int printArticle;
	
	/**
	 * 한 페이지 그룹에 노출할 페이지의 수
	 * 기본 값으로 10을 가짐.
	 */
	int printPage;

	/**
	 * 어떤 페이지 번호에서 노출할 게시물의 시작 번호
	 * 예 > 	1페이지의 시작 번호는 1번
	 * 		2페이지의 시작 번호는 11번
	 */
	protected int startArticleNumber;
	/**
	 * 어떤 페이지 번호에서 노출할 게시글의 마지막 번호
	 */
	protected int endArticleNumber;

	/**
	 * 전체 페이지의 수
	 */
	int totalPage;
	/**
	 * 전체 페이지 그룹의 수
	 */
	int totalGroup;

	/**
	 * 현재 노출 중인 페이지 그룹의 번호
	 */
	int nowGroupNumber;

	/**
	 * 현재 노출 중인 페이지 그룹의 시작 페이지 번호
	 */
	int groupStartPage;

	/**
	 * 현재 노출 중인 페이지 그룹의 다음 페이지 그룹 번호
	 */
	int nextGroupPageNumber;
	/**
	 * 현재 노출 중인 페이지 그룹의 이전 페이지 그룹 번호
	 */
	int prevGroupPageNumber;

	/**
	 * 현재 노출 중인 페이지 번호
	 */
	protected int pageNo;
	
	/**
	 * Paging 媛앹껜瑜� �뼸�뼱�삩�떎.
	 * �븳 �럹�씠吏��떦 蹂댁뿬吏��뒗 寃뚯떆湲� �닔 10媛�
	 * �븳 �럹�씠吏��떦 蹂댁뿬吏��뒗 �럹�씠吏� �닔 10媛�
	 * 濡� 湲곕낯 �꽕�젙�맖.
	 */
	public Pager() {
		this.printArticle = 10;
		this.printPage = 10;
	}
	
	public Pager(int printArticle, int printPage) {
		this.printArticle = printArticle;
		this.printPage = printPage;
	}
	
	public void setPageNumber(int pageNumber) {
		setPageNumber(pageNumber + "");
	}
	
	/**
	 * �슂泥��맂 �럹�씠吏��쓽 踰덊샇瑜� �뼸�뼱�삩�떎.
	 * 1 �럹�씠吏��쓽 寃쎌슦 0�씠 �엯�젰�맂�떎.
	 * �븘臾닿쾬�룄 �엯�젰�븯吏� �븡�븯�떎硫� 0�쑝濡� 湲곕낯 �꽕�젙�맂�떎.
	 * @param pageNumber
	 */
	public void setPageNumber(String pageNumber) {
		this.pageNo = 0;
		try {
			this.pageNo = Integer.parseInt(pageNumber);
		} catch (NumberFormatException nfe) {
			this.pageNo = 0;
		}

		computeArticleNumbers();
		
		this.nowGroupNumber = this.pageNo / this.printPage;
		this.groupStartPage = (this.nowGroupNumber * this.printPage) + 1;

		this.nextGroupPageNumber = this.groupStartPage + this.printPage - 1;
		this.prevGroupPageNumber = this.groupStartPage - this.printPage - 1;
	}
	
	protected abstract void computeArticleNumbers();
	
	/**
	 * 조회하는 조건(Query)의 총 게시물 수를 정의한다.
	 * @param count
	 */
	public void setTotalArticleCount(int count) {
		this.totalArticleCount = count;

		this.totalPage = (int) Math.ceil((double) this.totalArticleCount // ceil = 올림
				/ this.printArticle);
		this.totalGroup = (int) Math.ceil((double) this.totalPage
				/ this.printPage);
	}
	
	/**
	 * 議고쉶�븯�젮�뒗 議곌굔(Query)�쓽 珥� 寃뚯떆臾� �닔瑜� 媛��졇�삩�떎.
	 * @return
	 */
	public int getTotalArticleCount() {
		return this.totalArticleCount;
	}

	/**
	 * Query�뿉�꽌 �궗�슜�맆 �깘�깋 �떆�옉 踰덊샇 
	 * Oracle�쓽 寃쎌슦 rownum�쓽 �떆�옉 踰덊샇
	 * @return
	 */
	public int getStartArticleNumber() {
		return this.startArticleNumber;
	}
	
	/**
	 * Query�뿉�꽌 �궗�슜�맆 �깘�깋 �떆�옉 踰덊샇瑜� �젙�쓽�븳�떎.
	 * @param startArticleNumber
	 */
	public void setStartArticleNumber(int startArticleNumber) {
		this.startArticleNumber = startArticleNumber;
	}
	
	/**
	 * Query�뿉�꽌 �궗�슜�맆 �깘�깋 �걹 踰덊샇瑜� �젙�쓽�븳�떎.
	 * @param endArticleNumber
	 */
	public abstract void setEndArticleNumber(int endArticleNumber);

	/**
	 * Query�뿉�꽌 �궗�슜�맆 �깘�깋 留덉�留� 踰덊샇
	 * Oracle�쓽 寃쎌슦 rownum�쓽 留덉�留� 踰덊샇
	 * @return
	 */
	public abstract int getEndArticleNumber();
	
}
