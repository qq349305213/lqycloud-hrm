package cn.lqycloud.hrm.web.controller;

import cn.lqycloud.hrm.service.ISystemdictionaryitemService;
import cn.lqycloud.hrm.domain.Systemdictionaryitem;
import cn.lqycloud.hrm.query.SystemdictionaryitemQuery;
import cn.lqycloud.hrm.util.AjaxResult;
import cn.lqycloud.hrm.util.PageList;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/systemdictionaryitem")
public class SystemdictionaryitemController {
    @Autowired
    public ISystemdictionaryitemService systemdictionaryitemService;

    /**
    * 保存和修改公用的
    * @param systemdictionaryitem  传递的实体
    * @return Ajaxresult转换结果
    */
    @RequestMapping(method= RequestMethod.PUT)
    public AjaxResult save(@RequestBody Systemdictionaryitem systemdictionaryitem){
        try {
            if(systemdictionaryitem.getId()!=null){
                systemdictionaryitemService.updateById(systemdictionaryitem);
            }else{
                systemdictionaryitemService.insert(systemdictionaryitem);
            }
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setMessage("保存对象失败！"+e.getMessage());
        }
    }

    /**
    * 删除对象信息
    * @param id
    * @return
    */
    @RequestMapping(value="{id}",method=RequestMethod.DELETE)
    public AjaxResult delete(@PathVariable("id") Long id){
        try {
            systemdictionaryitemService.deleteById(id);
            return AjaxResult.me();
        } catch (Exception e) {
        e.printStackTrace();
            return AjaxResult.me().setMessage("删除对象失败！"+e.getMessage());
        }
    }

    //获取用户
    @RequestMapping(value = "{id}",method = RequestMethod.GET)
    public Systemdictionaryitem get(@PathVariable("id")Long id)
    {
        return systemdictionaryitemService.selectById(id);
    }


    /**
    * 查看所有的员工信息
    * @return
    */
    @RequestMapping(method = RequestMethod.PATCH)
    public List<Systemdictionaryitem> list(){

        return systemdictionaryitemService.selectList(null);
    }


    /**
    * 分页查询数据
    *
    * @param query 查询对象
    * @return PageList 分页对象
    */
    @RequestMapping(method = RequestMethod.POST)
    public PageList<Systemdictionaryitem> json(@RequestBody SystemdictionaryitemQuery query)
    {
        Page<Systemdictionaryitem> page = new Page<Systemdictionaryitem>(query.getPage(),query.getRows());
            page = systemdictionaryitemService.selectPage(page);
            return new PageList<Systemdictionaryitem>(page.getTotal(),page.getRecords());
    }
}
