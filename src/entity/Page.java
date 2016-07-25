package entity;

public class Page {
	/**
	 * ������
	 */
	private int totalNumber;// ����
	/**
	 * �ڼ�ҳ
	 */
	private int currentPage;// ����
	/**
	 * ��ҳ��
	 */
	private int totalPage;
	/**
	 * ÿҳ��ʾ����
	 */
	private int pageNumber = 5;
	/**
	 * ���ݿ���limit�Ĳ������ӵڼ�����ʼȡ
	 */
	private int dbIndex;
	/**
	 * ���ݿ���limit�Ĳ�����һ��ȡ������
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
	 * ���ݵ�ǰ�Ķ�������ֵ���������������ֵ�����иö��������totalNumber��currentPage��pageNumber�����ԣ������޷�����
	 */
	public void count() {
		int totalPageTemp = totalNumber / pageNumber;
		if (totalNumber % pageNumber > 0) {
			setTotalPage(totalPageTemp + 1);// ����totalPage
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
