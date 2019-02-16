package cn.tycoding.system.controller;

import cn.tycoding.common.controller.BaseController;
import cn.tycoding.common.dto.QueryPage;
import cn.tycoding.common.dto.ResponseCode;
import cn.tycoding.system.entity.Menu;
import cn.tycoding.common.enums.StatusEnums;
import cn.tycoding.system.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author tycoding
 * @date 2019-01-31
 */
@RestController
@RequestMapping("/menu")
public class MenuController extends BaseController {

    @Autowired
    private MenuService menuService;

    @PostMapping("/list")
    public ResponseCode queryList(QueryPage queryPage, Menu menu) {
        return ResponseCode.SUCCESS(super.selectByPageNumSize(queryPage, () -> menuService.queryList(menu)));
    }

    @GetMapping("/urlList")
    public ResponseCode getAllUrl() {
        return ResponseCode.SUCCESS(menuService.queryList(new Menu()));
    }

    @GetMapping("/menuButtonTree")
    public ResponseCode getMenuButtonTree() {
        try {
            return ResponseCode.SUCCESS(menuService.getMenuButtonTree());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseCode.ERROR();
        }
    }

    @GetMapping("/menuTree")
    public ResponseCode getMenuTree() {
        try {
            return ResponseCode.SUCCESS(menuService.getMenuTree());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseCode.ERROR();
        }
    }

    @GetMapping("/findById")
    public ResponseCode findById(Long id) {
        try {
            return new ResponseCode(StatusEnums.SUCCESS, menuService.findById(id));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseCode.ERROR();
        }
    }

    @PostMapping("/add")
    public ResponseCode add(@RequestBody Menu menu) {
        try {
            menuService.add(menu);
            return ResponseCode.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseCode.ERROR();
        }
    }

    @GetMapping("/checkName")
    public ResponseCode checkName(String name, String id) {
        if (name.isEmpty()) {
            return new ResponseCode(StatusEnums.PARAM_ERROR);
        }
        if (!menuService.checkName(name, id)) {
            return new ResponseCode(StatusEnums.PARAM_REPEAT);
        }
        return new ResponseCode(StatusEnums.SUCCESS);
    }

    @PostMapping("/update")
    public ResponseCode update(@RequestBody Menu menu) {
        try {
            menuService.update(menu);
            return ResponseCode.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseCode.ERROR();
        }
    }

    @PostMapping("/delete")
    public ResponseCode delete(@RequestBody List<Long> ids) {
        try {
            menuService.delete(ids);
            return ResponseCode.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseCode.ERROR();
        }
    }
}
