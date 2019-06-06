package cn.temptation.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.temptation.dao.UserDao;
import cn.temptation.domain.User;

/**
 * 用户
 * <p>ClassName: UserController</p>
 * <p>Description: </p>
 * @author zhangwan
 * @date 2019年5月23日 上午10:14:11
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {
	@Resource
    private UserDao userDao;
 
    @RequestMapping("/view")
    public String view() {
        return "main/login";
    }
 
    @RequestMapping("/indexview")
    public String index() {
        return "main/index";
    }
 
    @PostMapping(value = "/login")
    public ModelAndView login(User model, HttpSession session) {
        User user = userDao.findByUsername(model.getUsername());
 
        if (user == null || !user.getPassword().equals(model.getPassword())) {
            return new ModelAndView("redirect:/login.jsp");
        } else {
            session.setAttribute("user", user);
            ModelAndView mav = new ModelAndView();
            mav.setViewName("index");
            return mav;
        }
    }
}
