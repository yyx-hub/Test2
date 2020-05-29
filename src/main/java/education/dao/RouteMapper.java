package education.dao;

import education.pojo.Route;

import java.util.List;
import java.util.Map;

/**
 * 路径dao层接口
 * @author xulibin
 */
public interface RouteMapper {

    /**
     * 查询所有路径信息
     * @return
     */
    List<Route> routeList();


    /**
     * 查询所有路径信息
     * @return
     */
    List<Route> routePage(Map map);

    /**
     * 根据ID删除路径
     * @param routeId
     * @return
     */
    int deleteRouteById(Integer routeId);

    /**
     * 添加路径
     * @param record
     * @return
     */
    int insert(Route record);

    /**
     * 添加路径
     * @param record
     * @return
     */
    int insertSelective(Route record);

    /**
     * 根据ID查询路径
     * @param routeId
     * @return
     */
    Route selectRouteById(Integer routeId);

    /**
     * 修改路径
     * @param record
     * @return
     */
    int updateRouteByIdSelective(Route record);

    /**
     * 修改路径
     * @param record
     * @return
     */
    int updateRouteById(Route record);
}