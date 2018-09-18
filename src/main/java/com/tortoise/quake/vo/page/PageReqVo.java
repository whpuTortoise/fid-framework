package com.tortoise.quake.vo.page;

/**
 * datatable分页查询入参
 * @author wangzhi
 *
 */
public class PageReqVo {
	
		// 每页多少条数据
		private Integer limit;
		//起始位置
		private Integer offset; 
		
		public Integer getLimit() {
			return limit;
		}
		public void setLimit(Integer limit) {
			this.limit = limit;
		}
		
		public Integer getOffset() {
			return offset;
		}
		public void setOffset(Integer offset) {
			this.offset = offset;
		}
	
}
