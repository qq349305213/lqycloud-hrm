package cn.lqycloud.hrm.client;

import cn.lqycloud.hrm.domain.Systemdictionary;
import cn.lqycloud.hrm.query.SystemdictionaryQuery;
import cn.lqycloud.hrm.util.AjaxResult;
import cn.lqycloud.hrm.util.PageList;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.FeignClientsConfiguration;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value = "ZUUL-GATEWAY",configuration = FeignClientsConfiguration.class,
        fallbackFactory = SystemdictionaryClientHystrixFallbackFactory.class)
@RequestMapping("/systemdictionary")
public interface SystemdictionaryClient {
    /**
     * 保存和修改公用的
     * @param systemdictionary  传递的实体
     * @return Ajaxresult转换结果
     */
    @RequestMapping(method= RequestMethod.PUT)
    AjaxResult save(Systemdictionary systemdictionary);

    /**
     * 删除对象信息
     * @param id
     * @return
     */
    @RequestMapping(value="{id}",method=RequestMethod.DELETE)
    AjaxResult delete(@PathVariable("id") Integer id);

    //获取用户
    @RequestMapping(value = "{id}",method = RequestMethod.GET)
    Systemdictionary get(@RequestParam(value="id",required=true) Long id);


    /**
     * 查看所有的员工信息
     * @return
     */
    @RequestMapping(method = RequestMethod.PATCH)
    public List<Systemdictionary> list();

    /**
     * 分页查询数据
     *
     * @param query 查询对象
     * @return PageList 分页对象
     */
    @RequestMapping(method = RequestMethod.POST)
    PageList<Systemdictionary> json(@RequestBody SystemdictionaryQuery query);
}
