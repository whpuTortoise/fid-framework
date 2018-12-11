package com.tortoise.quake.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tortoise.framework.service.BaseService;
import com.tortoise.quake.dao.DepartmentMapper;
import com.tortoise.quake.model.Department;
import com.tortoise.quake.vo.DepartmentVo;

@Service
public class DepartmentService extends BaseService<Department, DepartmentMapper> {

	@Autowired
	public void setMapper(DepartmentMapper mapper) {
		this.mapper = mapper;
	}
	
	/**
	 * 获取完整的机构树
	 * @return
	 */
	public List<DepartmentVo> getDepartmentTree(){
		List<Department> departments = queryAll();
		
		List<DepartmentVo> departmentVos = new ArrayList<DepartmentVo>();
		for(Department department : departments){
			if(department.getPid() == 0){
				DepartmentVo departmentVo = new DepartmentVo();
				BeanUtils.copyProperties(department, departmentVo);
				departmentVo.setChildren(getChildren(departments, departmentVo.getId()));
				departmentVos.add(departmentVo);
			}
		}
		
		return departmentVos;
	}
	
	/**
	 * 递归获取子节点
	 * @param menus
	 * @param pId
	 * @return
	 */
	private List<DepartmentVo> getChildren(List<Department> departments, Long pId){
		List<DepartmentVo> departmentVos = new ArrayList<DepartmentVo>();
		for(Department department : departments){
			if(department.getPid() == pId){
				DepartmentVo departmentVo = new DepartmentVo();
				BeanUtils.copyProperties(department, departmentVo);
				departmentVo.setChildren(getChildren(departments, departmentVo.getId()));
				departmentVos.add(departmentVo);
			}
		}
		return departmentVos;
	}
	
	/**
	 * 删除菜单及其子菜单（递归删除）
	 * @param id
	 */
	public void deleteDepartmentsById(Long id){
		
		List<Object> ids = getNodeIds(id);
		
		batchDelete(ids);
	}
	
	public List<Object> getNodeIds(Long id){
		List<Object> ids = new ArrayList<Object>();
		
		List<Department> departments = queryAll();
		for(Department department : departments){
			if(department.getId() == id){
				ids.add(id);
				List<DepartmentVo> departmentvoChilds = getChildren(departments, id);
				if(departmentvoChilds != null && departmentvoChilds.size() > 0){
					ids.addAll(getChildIds(departmentvoChilds));
				}
				break;
			}
		}
		
		return ids;
	}
	
	/**
	 * 递归获取子节点ID
	 * @param menus
	 * @return
	 */
	private List<Long> getChildIds(List<DepartmentVo> departments){
		List<Long> ids = new ArrayList<Long>();
		for(DepartmentVo department : departments){
			ids.add(department.getId());
			if(department.getChildren() != null && department.getChildren().size() > 0){
				ids.addAll(getChildIds(department.getChildren()));
			}
		}
		
		return ids;
	}

}
