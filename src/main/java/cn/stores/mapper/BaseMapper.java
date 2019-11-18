package cn.stores.mapper;

import java.io.Serializable;
import java.util.List;
/**
 * 
 *  
 * @Description:DAO层基本的CRUD抽取   
 * @author: maotao 
 * @param <E>泛型
 * @param <PK> 主键  
 *
 */
public interface BaseMapper<E,PK extends Serializable> {
	/**
	 * 
	 * @Title: addObject   
	 * @Description: 添加   
	 * @param: @param entity
	 * @param: @return      
	 * @return: boolean      
	 * @throws
	 */
	int addObject(E entity);
	
	/**
	 * 
	 * @Title: deleteObject   
	 * @Description: 删除   
	 * @param: @param id
	 * @param: @return      
	 * @return: boolean      
	 * @throws
	 */
	int deleteObject(Serializable id);
	
	/**
	 * 
	 * @Title: updateObject   
	 * @Description: 修改  
	 * @param: @param entity
	 * @param: @return      
	 * @return: boolean      
	 * @throws
	 */
	int  updateObject(E entity);
	
	/**
	 * 
	 * @Title: getObject   
	 * @Description: 获取符合条件的单条数据  
	 * @param: @param id
	 * @param: @return      
	 * @return: E      
	 * @throws
	 */
	E getObject(Serializable id);
	
	/**
	 * 
	 * @Title: listAllObject   
	 * @Description: 获取所有数据      
	 * @param: @return      
	 * @return: List<E>      
	 * @throws
	 */
	List<E> listAllObject(Object ...param);
	

    
}
