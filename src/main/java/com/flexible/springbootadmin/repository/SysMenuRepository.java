package com.flexible.springbootadmin.repository;

import com.flexible.springbootadmin.entity.SysMenu;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SysMenuRepository extends JpaRepository<SysMenu, Integer> {
    List<SysMenu> getByMenuParent(Integer parentId);

    List<SysMenu> findByMenuParentOrderByMenuSortAsc(Integer parentId);


}
