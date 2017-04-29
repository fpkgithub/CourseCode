package com.course.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.course.entity.Building;;

public interface BuildingDao
{
	 /**
     * 
     * @param id
     * @return
     */
	int insertBuilding(@Param("bnum") String bnum, @Param("canum") String canum,@Param("bname") String bname);
	List<Building> queryAll(@Param("canum") String canum);
}
