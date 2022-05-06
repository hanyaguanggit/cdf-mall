package com.cdf.mall.service.master;
import com.cdf.mall.common.TokenInfo;
import com.cdf.mall.model.master.CsSecurityUser;
import com.cdf.mall.model.master.CsSecurityUserExample;

public interface CsSecurityUserService {
    /**
     * hyg
     * 登录
     * @param example
     * @return
     */
    //List<CsSecurityUser> selectByLoginNameAndPassword(CsSecurityUserExample example);

    /**
     * 修改密码
     * @param example
     * @return
     */
  //  List<CsSecurityUser> selectByUserIdAndPassword(CsSecurityUserExample example);

    //查询角色下的用户
    //List<CsSecurityUserExtension> selectUserListByRoleNameOrUserId(Integer shopId, String roleName, Integer userId);

   // int insertUser(CsSecurityUser csSecurityUser);

   // CsSecurityUser selectUserById(int userId);

    /**
     * 按条件更新用户字段
     * @param csSecurityUser
     * @param //csSecurityUserExample
     * @return
     */
  //  int updateByExampleSelective(CsSecurityUser csSecurityUser, CsSecurityUserExample csSecurityUserExample);

    //int updateByPrimaryKeySelective(CsSecurityUser csSecurityUser);
    //条件查询系统会员列表
  //  List<CsSecurityUser> selectByCondition(CsSecurityUserExample csSecurityUserExample, Integer pageNum, Integer pageSize);

    /**
     * ygl
     * 根据用户名获取会员
     */
  //  List<CsSecurityUser> getByUsername(CsSecurityUserExample example);

    /**
     * ygl
     * 根据会员编号获取会员
     */
   // CsSecurityUser getById(Integer id);

    /**
     * ygl
     * 生成验证码
     */
   // CommonResult generateAuthCode(String telephone);


    public CsSecurityUser loadUserByUsername(CsSecurityUserExample csSecurityUserExample);

    /**
     * ygl
     * 登录成功获取token
     * 登录后获取token
     */
    TokenInfo login(String username, String password);

    /**
     * ygl
     * 刷新token
     * @param token
     * @return
     */
    String refreshToken(String token);

    /**
     * 通过用户名名称，新密码，验证码更细用户密码
     * @param telephone
     * @param password
     * @param authCode
     * @return
     */
   // public CommonResult updatePassword(String telephone, String password, String authCode) ;


    //List<CsSecurityUser> selectByExample(CsSecurityUserExample csSecurityUserExample);
}
