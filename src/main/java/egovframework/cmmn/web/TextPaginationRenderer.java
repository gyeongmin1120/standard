package egovframework.cmmn.web;

import javax.servlet.ServletContext;

import org.springframework.web.context.ServletContextAware;

import egovframework.rte.ptl.mvc.tags.ui.pagination.AbstractPaginationRenderer;

public class TextPaginationRenderer extends AbstractPaginationRenderer implements ServletContextAware {
	private ServletContext servletContext;
	
	public TextPaginationRenderer() {
		// TODO Auto-generated constructor stub
	}
	
	public void initVariables() {
		firstPageLabel    = "<li class=\"page-item\"><a class=\"page-link\" href=\"?pageIndex={1}\" onclick=\"{0}({1}); return false;\">처음</a></li>";
		previousPageLabel = "<li class=\"page-item\"><a class=\"page-link\" href=\"?pageIndex={1}\" onclick=\"{0}({1}); return false;\">이전</a></li>";
		currentPageLabel  = "<li class=\"page-item active\"><a class=\"page-link\">{0}</a></li>";
		otherPageLabel    = "<li class=\"page-item\"><a class=\"page-link\" href=\"?pageIndex={1}\" onclick=\"{0}({1}); return false;\">{2}</a></li>";
		nextPageLabel     = "<li class=\"page-item\"><a class=\"page-link\" href=\"?pageIndex={1}\" onclick=\"{0}({1}); return false;\">다음</a></li>";
		lastPageLabel     = "<li class=\"page-item\"><a class=\"page-link\" href=\"?pageIndex={1}\" onclick=\"{0}({1}); return false;\">끝</a></li>";
	}
	
	@Override
	public void setServletContext(ServletContext servletContext) {
		// TODO Auto-generated method stub
		this.servletContext = servletContext;
		initVariables();
	}
}
