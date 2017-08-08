package top.yuyufeng.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import top.yuyufeng.constants.StatusesCommonUse;
import top.yuyufeng.constants.UserAccountTypeEnum;
import top.yuyufeng.constants.UserSatusEnum;
import top.yuyufeng.dto.CataLogDto;
import top.yuyufeng.entity.User;
import top.yuyufeng.exception.AuthorityException;
import top.yuyufeng.service.CatalogService;
import top.yuyufeng.utils.SessionUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 博客拦截器
 *
 * @author yyf
 */
public class AdminInterceptor implements HandlerInterceptor {


    @Autowired
    private CatalogService catalogService;

    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    // 执行Handler完成执行此方法
    // 应用场景：统一异常处理，统一日志处理
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object object,
                                Exception exception) throws Exception {
    }

    // 进入Handler方法之后，返回modelAndView之前执行
    // 应用场景从模型出发 公用model数据（菜单导航）在这里传到视图，也可以在这里统一指定视图
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object object,
                           ModelAndView modelAndView) throws Exception {
        //装载分类

    }

    // 进入Handler方法之前
    // 用于身份认真、身份授权
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
        String uri = request.getRequestURI();
        if ("/admin/login".equals(uri) || "/admin/doLogin".equals(uri) || "/admin/quit".equals(uri)) {
            return true;
        }
        User sessionUser = SessionUtil.getSessionUser(request);
        if (sessionUser == null) {
            request.getRequestDispatcher("/WEB-INF/jsp/admin/login.jsp").forward(request, response);
            return false;
        }

        boolean isManagerEnter = false;
        for (String accountType : StatusesCommonUse.accountTypesManager) {
            if(accountType.equals(sessionUser.getAccountType())){
                isManagerEnter = true;
                break;
            }
        }

        if (!isManagerEnter) {
            throw new AuthorityException("无权访问！");
        }
        if (!UserSatusEnum.NORMAL.getKey().equals(sessionUser.getUserStatus())) {
            throw new AuthorityException("账号异常：" + UserSatusEnum.getValue(sessionUser.getUserStatus()));
        }
        return true;
    }

}
