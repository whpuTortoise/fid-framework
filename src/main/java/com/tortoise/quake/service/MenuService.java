package com.tortoise.quake.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tortoise.framework.service.BaseService;
import com.tortoise.quake.dao.MenuMapper;
import com.tortoise.quake.model.Menu;
import com.tortoise.quake.model.User;
import com.tortoise.quake.model.UserRoleEntity;
import com.tortoise.quake.vo.MenuVo;

@Service
public class MenuService extends BaseService<Menu, MenuMapper> {

	@Autowired
	private UserRoleService userRoleService;
	
	@Autowired
	private AuthorityService authorityService;
	
	
	
	@Autowired
	public void setMapper(MenuMapper mapper) {
		this.mapper = mapper;
	}
	
	/**
	 * 获取完整的菜单树
	 * @return
	 */
	public List<MenuVo> getMyMenuTree(User currentUser){
		UserRoleEntity userRole = new UserRoleEntity();
		userRole.setUserId(currentUser.getId());
		List<UserRoleEntity> userRoles = userRoleService.queryList(userRole);
		if(userRoles != null && userRoles.size() > 0) {
			List<Long> roleIds = new ArrayList<>();
			for(UserRoleEntity entity : userRoles) {
				roleIds.add(entity.getRoleId());
			}
			List<Menu> menus = authorityService.getMenusByRoles(roleIds);
			
			if(menus != null && menus.size() > 0) {
				List<MenuVo> menuVos = new ArrayList<MenuVo>();
				for(Menu menu : menus){
					if(menu.getPid() == 0){
						MenuVo menuVo = new MenuVo();
						BeanUtils.copyProperties(menu, menuVo);
						menuVo.setChildren(getChildren(menus, menuVo.getId()));
						menuVos.add(menuVo);
					}
				}
				
				return menuVos;
			}
			
		}
		
		return null;
		
	}
	
	/**
	 * 获取完整的菜单树
	 * @return
	 */
	public List<MenuVo> getMenuTree(){
		List<Menu> menus = queryAll();
		
		List<MenuVo> menuVos = new ArrayList<MenuVo>();
		for(Menu menu : menus){
			if(menu.getPid() == 0){
				MenuVo menuVo = new MenuVo();
				BeanUtils.copyProperties(menu, menuVo);
				menuVo.setChildren(getChildren(menus, menuVo.getId()));
				menuVos.add(menuVo);
			}
		}
		
		return menuVos;
	}
	
	/**
	 * 递归获取子节点
	 * @param menus
	 * @param pId
	 * @return
	 */
	private List<MenuVo> getChildren(List<Menu> menus, Long pId){
		List<MenuVo> menuVos = new ArrayList<MenuVo>();
		for(Menu menu : menus){
			if(menu.getPid() == pId){
				MenuVo menuVo = new MenuVo();
				BeanUtils.copyProperties(menu, menuVo);
				menuVo.setChildren(getChildren(menus, menuVo.getId()));
				menuVos.add(menuVo);
			}
		}
		return menuVos;
	}
	
	/**
	 * 删除菜单及其子菜单（递归删除）
	 * @param id
	 */
	public void deleteMenusById(Long id){
		List<Object> ids = new ArrayList<Object>();
		
		List<Menu> menus = queryAll();
		for(Menu menu : menus){
			if(menu.getId() == id){
				ids.add(id);
				List<MenuVo> menuvoChilds = getChildren(menus, id);
				if(menuvoChilds != null && menuvoChilds.size() > 0){
					ids.addAll(getChildIds(menuvoChilds));
				}
				break;
			}
		}
		
		batchDelete(ids);
	}
	
	/**
	 * 递归获取子节点ID
	 * @param menus
	 * @return
	 */
	private List<Long> getChildIds(List<MenuVo> menus){
		List<Long> ids = new ArrayList<Long>();
		for(MenuVo menu : menus){
			ids.add(menu.getId());
			if(menu.getChildren() != null && menu.getChildren().size() > 0){
				ids.addAll(getChildIds(menu.getChildren()));
			}
		}
		
		return ids;
	}

}
