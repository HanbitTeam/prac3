package board.control;

import javax.servlet.http.HttpServletRequest;

public class ActionForward {

	int nowPage = 1 , start, end, total ,startPage,endPage;
	
	int limit=3;
	
	int pageLimit = 4;
	
	String service ,url ,goUrl ,msg;
	
	boolean redirect = false;
	
	Object mainData;
	
	HttpServletRequest request;
	
	public void pageSetting()
	{
		startPage=(nowPage-1)/pageLimit*pageLimit+1;
		
		endPage=(startPage+pageLimit-1);
		
		if(endPage>total)
			
			endPage=total;
	}
	
	

	public ActionForward(HttpServletRequest request) {
		super();
		this.request = request;
	}

	
	public int getNowPage() {
		return nowPage;
	}

	public void setNowPage(int nowPage) {
		this.nowPage = nowPage;
	}
	
	
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public boolean isRedirect() {
		return redirect;
	}

	public void setRedirect(boolean redirect) {
		this.redirect = redirect;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
		this.url = service;
	}

	public Object getMainData() {
		return mainData;
	}

	public void setMainData(Object mainData) {
		this.mainData = mainData;
	}
	
	public void setAttr()
	{
		request.setAttribute("forward", this);
	}
	
	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public String getGoUrl() {
		return goUrl;
	}

	public void setGoUrl(String goUrl) {
		this.goUrl = goUrl;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}


	public int getLimit() {
		return limit;
	}


	public void setLimit(int limit) {
		this.limit = limit;
	}


	public int getPageLimit() {
		return pageLimit;
	}


	public void setPageLimit(int pageLimit) {
		this.pageLimit = pageLimit;
	}


	public int getStart() {
		return start;
	}


	public void setStart(int start) {
		this.start = start;
	}


	public int getEnd() {
		return end;
	}


	public void setEnd(int end) {
		this.end = end;
	}


	public int getTotal() {
		return total;
	}


	public void setTotal(int total) {
		this.total = total/limit;
		
		if(total%limit!=0)
			this.total++;
		
		pageSetting();
	}


	public int getStartPage() {
		return startPage;
	}


	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}


	public int getEndPage() {
		return endPage;
	}


	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	
}
