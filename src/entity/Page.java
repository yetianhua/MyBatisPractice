package entity;

public class Page {
	/**
	 * 总条数
	 */
	private int totalNumber;// 必须
	/**
	 * 第几页
	 */
	private int currentPage;// 必须
	/**
	 * 总页数
	 */
	private int totalPage;
	/**
	 * 每页显示条数
	 */
	private int pageNumber = 5;
	/**
	 * 数据库中limit的参数，从第几条开始取
	 */
	private int dbIndex;
	/**
	 * 数据库中limit的参数，一共取多少条
	 */
	private int dbNumber;

	public Page(int totalNumber, int currentPage) {
		super();
		this.totalNumber = totalNumber;
		this.currentPage = currentPage;
	}

	public Page() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 根据当前的对象属性值，填充其他的属性值，其中该对象必须有totalNumber、currentPage和pageNumber的属性，否则无法计算
	 */
	public void count() {
		int totalPageTemp = totalNumber / pageNumber;
		if (totalNumber % pageNumber > 0) {
			setTotalPage(totalPageTemp + 1);// 设置totalPage
		} else {
			setTotalPage(totalPageTemp);
		}
		if (this.currentPage > this.totalPage) {
			setCurrentPage(this.totalPage);
		}
		if (this.currentPage < 1) {
			setCurrentPage(1);
		}

		setDbIndex((this.currentPage - 1) * pageNumber);
		setDbNumber(pageNumber);
	}

	public int getTotalNumber() {
		return totalNumber;
	}

	public void setTotalNumber(int totalNumber) {
		this.totalNumber = totalNumber;
		this.count();
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
		this.count();
	}

	public int getDbIndex() {
		return dbIndex;
	}

	public void setDbIndex(int dbIndex) {
		this.dbIndex = dbIndex;
	}

	public int getDbNumber() {
		return dbNumber;
	}

	public void setDbNumber(int dbNumber) {
		this.dbNumber = dbNumber;
	}

}
