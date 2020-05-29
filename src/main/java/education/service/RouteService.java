package education.service;

import education.dao.RouteMapper;
import education.pojo.Route;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 路径Service层
 * @author xulibin
 */
@Service(value = "rs")
public class RouteService {

    @Resource
    private RouteMapper rm;


    /**
     * 查询所有路径信息
     * @return
     */
    public  List<Route> routeList(){
        return rm.routeList();
    }

    /**
     * 查询所有路径信息
     * @return
     */
    public List<Route> routePage(String routename,String routedesc){
        System.out.println("routename-->"+routename);
        System.out.println("routedesc-->"+routedesc);
        Map<String,String> map=new HashMap<String, String>();
        map.put("routename",routename);
        map.put("routedesc",routedesc);
        return rm.routePage(map);
    }

    /**
     * 根据ID删除路径
     * @param routeId
     * @return
     */
    public int deleteRouteById(Integer routeId){
        return rm.deleteRouteById(routeId);
    }

    /**
     * 添加路径
     * @param record
     * @return
     */
    public int insert(Route record){
        return rm.insert(record);
    }

    /**
     * 添加路径
     * @param record
     * @return
     */
    public int insertSelective(Route record){
        return rm.insertSelective(record);
    }

    /**
     * 根据ID查询路径
     * @param routeId
     * @return
     */
    public Route selectRouteById(Integer routeId){
        return rm.selectRouteById(routeId);
    }

    /**
     * 修改路径
     * @param record
     * @return
     */
    public int updateRouteByIdSelective(Route record){
        return rm.updateRouteByIdSelective(record);
    }

    /**
     * 修改路径
     * @param record
     * @return
     */
    public int updateRouteById(Route record){
        return rm.updateRouteById(record);
    }
}
