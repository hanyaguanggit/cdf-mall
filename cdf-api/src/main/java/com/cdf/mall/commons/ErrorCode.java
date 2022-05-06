package com.cdf.mall.commons;

/**
 * @author ：es
 * @date ：Created in 2021/9/18 16:19
 * @description：错误码
 * @modified By：
 * @version: $
 */

public class ErrorCode {

     /*
  成功统一200 ，网络错误500
  模块编码 系统2位 模块两位 错误类型吗
  feign错误调用统一：2位模块编码 + 99 + 2位方法错误码,
        例如 1101001 后台系统用户权限模块
* */

    public static final long SUCCESS = 200;

    public static final long FAILED = 500;

    //请求头过期
    public static final long EXPIRED_TOKEN = 401;

    //请求头空
    public static final long EMPTY_TOKEN = 402;

    //离岛请求接口错误
    public static final long OFFSHORE_NET_ERROR = 601;

    //前端特殊不需要报错信息
    public static final long NOT_THROW_MESSAGE = 999;


    /*-----------------------------------------后台模块开始------------------------------------------*/

    //系统用户模块
    public static final long INVALID_DATA = 1001003;
    public static final long ADD_USER_ROLE_ERROR = 1101004;
    public static final long SELECT_SYS_USER_ERROR = 1101006;
    public static final long UPDATE_SYS_ROLE_ERROR = 1101007;
    public static final long SELECT_SYS_ROLE_LIST_ERROR = 1101008;
    public static final long DELETE_SYS_ROLE_ERROR = 1101009;
    public static final long DELETE_SYS_USER_ERROR = 11010010;
    public static final long DELETE_USER_ROLE_ERROR = 1101011;
    public static final long DELETE_ROLE_MENU_ERROR = 1101012;
    public static final long ADMIN_USER_FORBIDDEN_ERROR = 1101013;

    //网站结构模块
    public static final long SELECT_SITESTRUCTURE_ERROR = 1102002;
    public static final long SELECT_HOME_INDEX_ERROR = 1102003;
    public static final long SELECT_POSITION_TYPE_ERROR = 1002004;
    public static final long SAVE_POSITION_TYPE_ERROR = 1002005;
    public static final long DELETE_POSITION_TYPE_ERROR = 100206;
    public static final long SAVE_SITE_ERROR = 100207;
    public static final long UPDATE_SITE_ERROR = 100208;
    public static final long UPDATE_SITE_STATUS_ERROR = 100209;
    public static final long UNDEFINED_POSITION_TYPE = 100210;
    public static final long SELECT_HOME_INDEX_PRODUCT_ADS_ERROR = 100211;
    public static final long SELECT_REDIS_SEARCH_HOT_WORDS_ERROR = 100212;
    public static final long SELECT_REDIS_CATEGORY_AS_ERROR = 100213;
    public static final long SELECT_REDIS_BRAND_WALL_ERROR = 100214;
    public static final long SET_SITESTRUCTURE_CACHE_ERROR = 100215;
    public static final long SELECT_PAYMENT_ADS_ERROR = 100216;

    //文章模块
    public static final long  SAVE_ARTICLE_ERROR = 1103001;
    public static final long  SELECT_ARTICLE_ERROR = 1103002;
    public static final long  UPDATE_ARTICLE_ERROR = 1103003;
    public static final long  DELETE_ARTICLE_ERROR = 1103004;
    public static final long  SELECT_ARTICLE_NONE = 1103005;
    public static final long  SAVE_ARTICLE_CACHE_ERROR = 1103006;

    //文章栏目
    public static final long SAVE_CATEGORY_ERROR = 1103005;
    public static final long UPDATE_CATEGORY_ERROR = 110306;
    public static final long DELETE_CATEGORY_ERROR = 110307;
    public static final long SELECT_CATEGORY_ERROR = 110308;
    //图片验证码
    public static final long GET_VALID_CODE_ERROR = 1104001;

    //地区
    public static final long SELECT_REGION_ERROR = 1105001;

    //会员模块
    public static final long SELECT_MEMBER_LEVEL_ERROR = 1106001;
    public static final long SELECT_MEMBER_LIST_ERROR = 1106002;
    public static final long ADD_MEMBER_ERROR = 1106003;
    public static final long UPDATE_MEMBER_ERROR = 1106004;
    public static final long UPDATE_MEMBER_INFO_ERROR = 1106005;
    public static final long UPDATE_MEMBER_PASS_ERROR = 1106006;
    public static final long SELECT_MEMBER_ERROR = 1106007;


    //菜单
    public static final long SAVE_ROLE_MENU_ERROR = 1107001;
    public static final long SAVE_MENU_ERROR = 1107002;
    public static final long DELETE_MENU_ERROR = 1107003;
    public static final long SELECT_MENU_ERROR = 1107004;
    public static final long UPDATE_MENU_STATUS_ERROR = 11070005;

    //订单
    public static final long SELECT_ORDER_LIST_ERRPR = 11008001;
    public static final long ORDER_LOG_ERROR = 1108002;
    public static final long SELECT_ORDER_DETAIL_ERROR = 1108003;
    public static final long UPDATE_ORDER_ERROR = 1108004;
    public static final long USER_MEMBERI_ERROR = 1108005;
    public static final long SELECT_ORDER_FLIGHT_ERROR = 1108006;

    //商城页面管理相关
    public static final long SELECT_PAGE_LIST_ERROR = 1109001;
    public static final long UPDATE_PAGE_ERROR = 1109002;
    public static final long SAVE_PAGE_ERROR = 1109003;
    public static final long UPDATE_PAGE_STATUS_ERROR  = 1109004;


    //图片上传相关
    public static final long IMAGES_UPLOAD_ERROR = 1110001;
    public static final long IMAGES_SIZE_OUT_OF_RANGE = 1110002;
    public static final long FILE_FORMAT_ERROR = 1110003;
    public static final long ZIP_UPLOAD_ERROR = 1110004;
    public static final long UPLOAD_FILE_NONE = 1110005;

    //界面管理模块相关
    public static final long SAVE_SECTION_ERROR = 1111001;
    public static final long SELECT_SECTION_LIST_ERROR = 1111002;
    public static final long UPDATE_SECTION_ERROR = 1111003;
    public static final long UPDATE_SECTION_PICTURELINK_ERROR = 1111004;
    public static final long UPDATE_SECTION_TEXTLINK_ERROR = 1111005;
    public static final long UPDATE_SECTION_RICHTEXT_ERROR = 1111006;
    public static final long UPDATE_SECTION_BEST_SELLER_ERROR = 1111007;
    public static final long UPDATE_SECTION_RECOMMEND_PRODUCT_ERROR = 1111008;
    public static final long UPDATE_SECTION_RECOMMEND_BRAND_ERROR = 1111009;
    public static final long UPDATE_SECTION_HOTWORDS_ERROR = 1111010;
    public static final long ADD_SECTION_RECOMMEND_BRAND_ERROR = 1111011;
    public static final long ADD_SECTION_BEST_SELLER_ERROR = 1111012;
    public static final long ADD_SECTION_PICTURELINK_ERROR = 1111013;
    public static final long ADD_SECTION_TEXTLINK_ERROR = 1111014;
    public static final long ADD_SECTION_RICHTEXT_ERROR = 1111015;
    public static final long ADD_SECTION_RECOMMEND_PRODUCT_ERROR = 1111016;
    public static final long ADD_SECTION_HOTWORDS_ERROR = 1111017;
    public static final long QUERY_EDIT_DATA_ERROR = 1111018;
    public static final long ADD_SECTION_RECOMMEND_ARTICLE_ERROR = 1111019;
    public static final long UPDATE_SECTION_RECOMMEND_ARTICLE_ERROR = 1111020;
    public static final long ADD_SECTION_RECOMMEND_CATEGORY_ERROR = 1111021;
    public static final long UPDATE_SECTION_RECOMMEND_CATEGORY_ERROR = 1111022;
    public static final long ADD_SECTION_SHARE_ACTIVITY_ERROR = 1111023;
    public static final long UPDATE_SECTION_SHARE_ACTIVITY_ERROR  = 1111024;
    public static final long ADD_SECTION_INDEX_FLOOR_ERROR  = 1111025;
    public static final long UPDATE_SECTION_INDEX_FLOOR_ERROR  = 1111026;
    public static final long ADD_SECTION_CONTENT_ERROR = 1111027;
    public static final long ADD_SECTION_APP_LINK_ERROR = 1111028;
    public static final long UPDATE_SECTION_APP_LINK_ERROR = 1111029;
    public static final long UPDATE_SECOND_PAGE_CACHE_ERROR = 1111030;
    public static final long DATA_INCOMPLETE = 1111031;
    public static final long SELECT_NONE_SECTION_ERROR = 1111032;


    //请求离岛接口返回响应code
    public static final long SELECT_NON_DATA_RESERVE_ERROR =  1112001;
    public static final long VALID_GOODS_ADMIN_ERROR = 1112002;

    //专题页相关
    public static final long SELECT_THEMATIC_ERROR = 1113001;
    public static final long ADD_THEMATIC_ERROR = 1113002;
    public static final long UPDATE_THEMATIC_ERROR = 1113003;
    public static final long UPDATE_THEMATIC_CACHE_ERROR = 1113004;
    public static final long THEMATIC_ID_NONE_ERROR = 1113005;

    //系统参数相关
    public static final long SET_SYS_PARAM_CACHE_ERROR = 1114001;


   /* ----------------------------------------后台模块结束--------------------------------------------*/

    /** ------------------------------------------订单模块------------------------------------------- */
    //查询订单失败
    public static final long ORDER_QERRY_ERROR = 2101002;

    // 下单失败
    public static final long ORDER_CART_CONFIRM_ERROR = 2001001;
    // 指定商品下单失败
    public static final long ORDER_GOODS_CONFIRM_ERROR = 2001002;
    // 取消订单失败
    public static final long ORDER_CANCEL_ERROR = 2001003;
    // 申请取消订单失败
    public static final long ORDER_CANCELING_CONFIRM_ERROR = 2001004;
    // 提交申请后失败
    public static final long ORDER_AFTER_SALE_ERROR = 2001008;
    // 获得购物车下单可用优惠券失败
    public static final long ORDER_COUPON_CART_ERROR = 2001009;
    // 获得指定商品下单可用优惠券失败
    public static final long ORDER_COUPON_GOODS_ERROR = 2001010;
    // 售后申请页面失败
    public static final long ORDER_LOAD_SUB_ERROR = 2001011;
    // 加载快递列表失败
    public static final long ORDER_LOAD_LOGISTICS_LIST_ERROR = 2001012;
    // 申请售后提交失败
    public static final long ORDER_REQUEST_AFTER_SALE_ERROR = 2001013;
    // 获取售寄回地址失败
    public static final long ORDER_FIND_ARTICLE_ERROR = 2001014;
    // 提交快递单号信息失败
    public static final long ORDER_AFTER_SALE_TRACKING_ERROR = 2001015;
    // 取消售后失败
    public static final long ORDER_REQUEST_CANCEL_AFTER_SALE_ERROR = 2001016;
    // 售后列表失败
    public static final long ORDER_GET_AFTER_SALE_LIST_ERROR = 2001017;





    //获取可使用优惠券列表接口失败
    public static final long USER_AVILIABLE_COUPON_LIST_FAIL = 2001019;
    //使用优惠券接口失败
    public static final long USER_USE_COUPON_FAIL = 2001020;
    // 用户更新memberInfo出错
    public static final long USER_UPDATE_MEMBERINFO_FAIL = 2001022;
    // 领取优惠券接口失败
    public static final long TAKE_COUPON_FAIL = 2001023;
    // 优惠券数量不足
    public static final long TAKE_COUPON_FAIL_25006 = 2001024;
    // 您已经领取过优惠券
    public static final long TAKE_COUPON_FAIL_25007 = 2001025;
    // 获取优惠券专题失败
    public static final long COUPON_INFO_FAIL = 2001026;







    //下单调用数据为空
    public static final long ORDER_CONFIRM_NULL_ERROR = 2002001;
    //指定商品下单调用数据为空
    public static final long ORDER_GOODS_NULL_ERROR = 2002002;
    //取消订单调用数据为空
    public static final long ORDER_CANCEL_NULL_ERROR = 2002003;
    //申请取消订单调用数据为空
    public static final long ORDER_CANCELING_NULL_ERROR = 2002004;
    //查询订单列表调用数据为空
    public static final long ORDER_LIST_NULL_ERROR = 2002005;
    //查询订单详情调用数据为空
    public static final long ORDER_DETAIL_NULL_ERROR = 2002006;
    //查询订单物流信息调用数据为空
    public static final long ORDER_TRACE_NULL_ERROR = 2002007;
    //提交申请后调用数据为空
    public static final long ORDER_AFTER_SALE_NULL_ERROR = 2002008;
    //获得购物车下单可用优惠券调用数据为空
    public static final long ORDER_COUPON_CART_NULL_ERROR = 2002009;
    //获得指定商品下单可用优惠券调用数据为空
    public static final long ORDER_COUPON_GOODS_NULL_ERROR = 2002010;
    //售后申请页面调用数据为空
    public static final long ORDER_LOAD_SUB_NULL_ERROR = 2002011;
    //加载快递列表调用数据为空
    public static final long ORDER_LOAD_LOGISTICS_NULL_ERROR = 2002012;
    // 插入订单日志失败
    public static final long INSET_ORDER_CODE = 2002013;
    // 申请售后提交调用数据为空
    public static final long ORDER_REQUEST_AFTER_SALE_NULL_ERROR = 2002014;
    // 获取售寄回地址调用数据为空
    public static final long ORDER_FIND_ARTICLE_NULL_ERROR = 2002015;
    // 提交快递单号信息调用数据为空
    public static final long ORDER_AFTER_SALE_TRACKING_NULL_ERROR = 2002016;
    // 取消售后调用数据为空
    public static final long ORDER_REQUEST_CANCEL_AFTER_SALE_NULL_ERROR = 2002017;
    // 售后列表调用数据为空
    public static final long ORDER_GET_AFTER_SALE_LIST_NULL_ERROR = 2002018;


    //会员购  认证错误码
    public static final long SECURITY_ORDER_FAIL_CODE = 209999;





    /** ------------------------------------------用户模块------------------------------------------- */
    //查询用户失败
    public static final long USER_QUERY_ERROR = 3301001;
    //用户注册失败
    public static final long USER_REGISTER_ERROR = 3301002;
    //用户认证失败
    public static final long USER_SECURITY_ERROR = 3301003;
    //用户code验证不通过
    public static final long USER_CODE_VALIDATE_ERROR = 3301004;
    //用户code不存在缓存
    public static final long USER_CODE_NOT_EXIT_ERROR = 3301005;
    //用户登录失败
    public static final long USER_LOGGING_ERROR = 3301006;
    //用户注册登录失败
    public static final long USER_REGISTER_LOGINERROR = 3301007;
    //用户注册获取token失败
    public static final long USER_REGISTER_TOKENERROR = 3301008;
    //用户注册获取注册且登录失败
    public static final long USER_REGISTER_LOGINANDREGESTERERROR = 3301009;
    // 会员购登录过期
    public static final long MEMBER_PURCHASE_LOGIN_OVERDUE = 3301010;
    // 会员购重试登录
    public static final long MEMBER_PURCHASE_RETRY_LOGIN_CODE = 3301011;
    // 验证码发送失败
    public static final long CODE_SEND_ERROR= 3301012;

    // 删除用户离岛信息失败
    public static final long CHECK_INFO_DELETE_ERROR = 3302001;
    // 获得用户离岛信息失败
    public static final long CHECK_INFO_GET_ERROR = 3302002;
    // 更新用户离岛信息失败
    public static final long CHECK_INFO_UPDATE_ERROR = 3302003;

    // 删除用户离岛信息调用数据为空
    public static final long CHECK_INFO_DELETE_NULL_ERROR = 3303001;
    // 获得用户离岛信息调用数据为空
    public static final long CHECK_INFO_GET_NULL_ERROR = 3303002;
    // 更新用户离岛信息调用数据为空
    public static final long CHECK_INFO_UPDATE_NULL_ERROR = 3303003;

    // 绑定大会员失败
    public static final long BIND_MEMBER_POINT_CODE = 3303020;
    // 发送绑定会员验证码失败
    public static final long BIND_MEMBER_POINT_CODE_CODE = 3303021;
    // 查询用户是否绑定大会员失败
    public static final long CHECK_MEMBER_CODE = 3303022;
    // 获得用户大会员可用积分数失败
    public static final long GET_MEMBER_POINT_CODE = 3303023;
    // 获得大会员信息失败
    public static final long GET_MEMBER_POINT_INFO_CODE = 3303024;
    // 获得大会员积分记录失败
    public static final long GET_MEMBER_POINT_LIST_CODE = 3303025;

    public static final long CHECK_INFO_UPDATE_FAIL = 3302003;
    //获取用户商品浏览记录失败
    public static final long USER_BROWSE_HISTORY_FAIL = 3302004;
    //获取用户商城优惠券列表失败
    public static final long USER_COUPON_LIST_FAIL = 3302005;
    //查询用户商品收藏列表失败
    public static final long USER_FAVORITE_LIST_FAIL=3302006;
    //获取帮助中心列表失败
    public static final long USER_HELP_CENTER_LIST_FAIL=3302007;
    //获取帮助中心下指定模块信息失败
    public static final long USER_HELP_CENTER_MODULE_FAIL=3302008;
    //查询我的中免会员卡信息失败
    public static final long USER_MEMBER_CARD_FAIL=3302009;
    //查询会员基本信息失败
    public static final long USER_MEMBER_INFO_FAIL=3302010;
    //额度查询失败
    public static final long USER_QUOTA_ACCOUNT_FAIL = 3302011;
    //查询我的中免会员页面指定模块的信息失败
    public static final long USER_MEMBER_INFO_MODULE_FAIL = 3302012;
    //获取用户收藏页面的商品分类接口失败
    public static final long USER_FAVORITE_LIST_CATEGORY_FAIL = 33020123;
    //点击收藏接口失败
    public static final long USER_FAVORITE_ADD_CATEGORY_FAIL = 33020124;
    //点击取消收藏商品接口失败
    public static final long USER_FAVORITE_DELETE_CATEGORY_FAIL = 33020125;
    //批量删除收藏商品接口失败
    public static final long USER_FAVORITE_BATCH_DELETE_CATEGORY_FAIL = 33020126;
    //获取用户大会员优惠券列表失败
    public static final long USER_CRM_COUPON_LIST_FAIL = 33020127;


    // 会员购 激活地址失败
    public static final long ACTIVE_ADDRESS_ERROR = 3302017;
    // 会员购 添加地址
    public static final long ADD_ADDRESS_ERROR = 3302018;
    // 会员购 删除地址
    public static final long DELETE_ADDRESS_ERROR = 3302019;
    // 会员购 会员购获得用户收货地址失败
    public static final long GET_USER_ADDRESS_LIST_ERROR = 3302020;
    // 会员购 更新地址
    public static final long UPDATE_ADDRESS_ERROR = 3302021;
    // 会员购 更新地址
    public static final long SELECT_ADDRESS_ERROR = 3302040;

    //帮助中心搜索框接口失败
    public static final long USER_HELP_CENTER_SEARCH_FAIL = 33020132;

    //查询会员卡适用门店信息失败
    public static final long GET_MEMBER_CARD_STORES_FAIL = 33020133;
    //查询注册中免会员卡的个人资料信息失败
    public static final long GET_MEMBER_CARD_WITH_INFO_FAIL = 33020134;
    //查询会员卡积分流水信息失败
    public static final long GET_MEMBER_CARD_FLOW_POINT_FAIL = 33020135;
    //查询会员二维码失败
    public static final long GET_MEMBER_CARD_QRCODE_FAIL = 33020136;
    //中免会员卡中心-我的服务-快速导航接口失败
    public static final long GET_MEMBER_CARD_NAVIGATION_FAIL = 33020137;
    //注册开通中免会员
    public static final long MEMBER_CARD_REGISTER_FAIL = 33020138;
    //会员折扣类型查询
    public static final long GET_DISCOUNT_TYPE_FAIL = 33020139;
    //可使用积分查询接口失败
    public static final long GET_AVILIABLE_POINTS_FAIL = 33020140;
    //我的中免页面信息封装接口失败
    public static final long GET_CDF_MEMBER_INFO_FAIL = 33020141;
    //统一用户信息查询接口失败
    public static final long QUERY_MEMBER_INFO_FAIL = 33020142;
    //总积分积分查询接口失败
    public static final long GET_TOTAL_POINTS_FAIL = 33020143;
    //会员系统升级中，暂停使用
    public static final long MEMBER_CARD_REGISTER_FAIL_995 = 33020144;
    //未绑定中免大会员系统
    public static final long MEMBER_CARD_REGISTER_FAIL_996 = 33020145;
    //当前用户已绑定大会员
    public static final long MEMBER_CARD_REGISTER_FAIL_997 = 33020146;
    //用户未注册
    public static final long MEMBER_CARD_REGISTER_FAIL_998 = 33020147;




    //我的中免 收藏、优惠券、浏览数量失败
    public static final long COLLECT_COUPON_COUNT_ERROR = 3302033;
    //我的中免 订单数量失败
    public static final long ORDER_COUNT_ERROR = 3302039;
    //我的中免 收藏、优惠券、浏览数量集合不为空
    public static final long COLLECT_COUPON_COUNT_NULL_ERROR = 3303004;
    //我的中免 订单数量集合不为空
    public static final long ORDER_COUNT_NULL_ERROR = 3303004;


    //会员购获得券列表失败
    public static final long USER_COUPON_LIST_ERROR = 3302034;
    //会员购获得领券列表失败
    public static final long USER_RECEIVE_COUPON_ERROR = 3302035;
    //会员购领券失败
    public static final long USER_REQUEST_RECEIVE_COUPON_ERROR = 3302036;
    //购物车模式获得大会员可用券列表失败
    public static final long USER_MEMBER_CART_COUPON_ERROR = 3302037;
    //获得大会员可用券列表失败
    public static final long USER_MEMBER_GOODS_COUPON_ERROR = 3302038;
    // 查询浏览记录失败
    public static final long BROWSE_RECORDS_LIST_CODE = 3302039;


    // 会员购 激活地址调用数据为空
    public static final long ACTIVE_ADDRESS_NULL_ERROR = 3303005;
    // 会员购 添加地址调用数据为空
    public static final long ADD_ADDRESS_NULL_ERROR = 3303006;
    // 会员购 删除地址调用数据为空
    public static final long DELETE_ADDRESS_NULL_ERROR = 3303007;
    // 会员购 获得用户收货地址调用数据为空
    public static final long GET_USER_ADDRESS_LIST_NULL_ERROR = 3303008;
    // 会员购 更新地址调用数据为空
    public static final long UPDATE_ADDRESS_NULL_ERROR = 3303009;
    // 获取微信手机号错误
    public static final long WECHAT_PHONE_ERROR = 3303010;
    // 会员购获得券列表调用数据为空
    public static final long USER_COUPON_LIST_NULL_ERROR = 3303011;
    // 会员购获得领券列表调用数据为空
    public static final long USER_RECEIVE_COUPON_NULL_ERROR = 3303012;
    // 会员购领券调用数据为空
    public static final long USER_REQUEST_RECEIVE_COUPON_NULL_ERROR = 3303013;
    // 购物车模式获得大会员可用券列表调用数据为空
    public static final long USER_MEMBER_CART_COUPON_NULL_ERROR = 3303015;
    // 获得大会员可用券列表调用数据为空
    public static final long USER_MEMBER_GOODS_COUPON_NULL_ERROR = 3303016;

    // 获取微code错误
    public static final long WECHAT_CODE_ERROR = 3303014;

    public static final long USER_FORBIDDEN = 3303017;

    //会员购  认证错误码
    public static final long SECURITY_USER_FAIL_CODE = 309999;

    //会员购 激活地址 认证错误码
    public static final long SECURITY_ACTIVE_ADDRESS_CODE = 309901;
    //会员购 添加地址 认证错误码
    public static final long SECURITY_ADD_ADDRESS_CODE = 309902;
    //会员购 删除地址 认证错误码
    public static final long SECURITY_DELETE_ADDRESS_CODE = 309903;
    //会员购 获得用户收货地址 认证错误码
    public static final long SECURITY_GET_USER_ADDRESS_LIST_CODE = 309904;
    //会员购  更新地址 认证错误码
    public static final long SECURITY_UPDATE_ADDRESS_CODE = 309905;

    //会员购 删除用户离岛信息 认证错误码
    public static final long SECURITY_CHECK_INFO_DELETE_CODE = 309906;
    //会员购 获得用户离岛信息 认证错误码
    public static final long SECURITY_CHECK_INFO_GET_CODE = 309907;
    //会员购 更新用户离岛信息 认证错误码
    public static final long SECURITY_CHECK_INFO_UPDATE_CODE = 309908;

    // 会员购 登录 认证错误码
    public static final long SECURITY_MEMBER_PURCHASE_LOGIN_CODE = 309909;

    // 会员购 收藏、优惠券、浏览数量 认证错误码
    public static final long SECURITY_COLLECT_COUPON_CODE = 309910;
    // 会员购 订单数量 认证错误码
    public static final long SECURITY_ORDER_CODE = 309911;




    /** ------------------------------------------购物车模块------------------------------------------- */
    // 加入购物车失败
    public static final long CART_ADD_FAIL = 5101001;

    // 清空购物车失败
    public static final long CART_CLEAR_FAIL = 5101002;
    // 删除购物车失败
    public static final long CART_DELETE_FAIL = 5101003;
    // 更新购物车失败
    public static final long CART_UPDATE_FAIL = 5101004;
    // 加载购物车失败
    public static final long CART_LOAD_FAIL = 5101005;
    // 选择购物车失败
    public static final long CART_SELECT_FAIL = 5101006;
    // 全选购物车失败
    public static final long CART_SELECT_ALL_FAIL = 5101007;
    // 预览购物车订单失败
    public static final long CART_PREPARE_CART_ORDER_FAIL = 5101008;
    // 预览商品订单失败
    public static final long CART_PREPARE_GOODS_ORDER_FAIL = 5101009;
    //查询购物车列表失败
    public static final long SELECT_CART_LIST_FAIL = 5101010;
    //获取购物车商品数目失败
    public static final long SELECT_CART_COUNT_FAIL = 5101011;
    //取消选中购物车的所有商品失败
    public static final long CART_UNSELECT_ALL_FAIL = 5101012;
    //取消选择购物车商品失败
    public static final long CART_UNSELECT_FAIL = 5101013;
    //修改购物车商品数量失败
    public static final long UPDATE_CART_COUNT_FAIL = 5101014;
    //删除组合商品失败
    public static final long DELETE_CART_COMBINATION_FAIL = 5101015;
    //修改组合商品数目失败
    public static final long UPDATE_CART_COMBINATION_COUNT_FAIL = 5101016;
    //添加促销商品至购物车失败
    public static final long ADD_CART_COMBINATION_FAIL = 5101017;
    //取消选中促销商品失败
    public static final long UNSELECT_CART_COMBINATION_FAIL = 5101018;
    //选中促销商品失败
    public static final long SELECT_CART_COMBINATION_FAIL = 5101019;
    //购物车数目失败
    public static final long CART_COUNT_FAIL = 5101020;

    //会员购 购物车 认证错误码
     public static final long SECURITY_CART_FAIL_CODE = 509999;











    // 加入购物车调用数据为空
    public static final long CART_ADD_NUll_FAIL = 5101010;
    // 清空购物车调用数据为空
    public static final long CART_CLEAR_NUll_FAIL = 5101011;
    // 删除购物车调用数据为空
    public static final long CART_DELETE_NUll_FAIL = 5101012;
    // 更新购物车调用数据为空
    public static final long CART_UPDATE_NUll_FAIL = 5101013;
    // 加载购物车调用数据为空
    public static final long CART_LOAD_NUll_FAIL = 5101014;
    // 选择购物车调用数据为空
    public static final long CART_SELECT_NUll_FAIL = 5101015;
    // 全选购物车调用数据为空
    public static final long CART_SELECT_ALL_NUll_FAIL = 5101016;
    // 预览购物车订单调用数据为空
    public static final long CART_PREPARE_CART_ORDER_NUll_FAIL = 5101017;
    // 预览商品订单调用数据为空
    public static final long CART_PREPARE_GOODS_ORDER_NUll_FAIL = 5101018;
    // 购物车数目调用数据为空
    public static final long COUNT_CART_NUll_FAIL = 5101019;
    // 商品库存不足，无法添加至购物车
    public static final long CART_PRODUCT_UNDERSTOCK_FAIL = 5101020;
    // 商品库存不足，或是已下架
    public static final long CART_PRODUCT_SOLDOUT = 5101021;
    // 购物车商品添加达到上限
    public static final long CART_LIMIT = 5101022;
    // 购物车已加入该活动商品，不能重复添加
    public static final long CART_ACTIVITY_ADDED = 5101023;
    // 已经超过购买上限
    public static final long BUY_LIMIT = 5101024;
    // 无效操作
    public static final long INVALID_OPERATION = 5101025;
    // 商品已经停止经营
    public static final long CART_PRODUCT_NOT_SELL = 5101026;
    // 传入参数异常
    public static final long INVALID_PARAM = 5101027;
    /** ------------------------------------------产品分类------------------------------------------- */
    public static final long CATEGORY_LIST_FAIL = 6001001;
    //获取品牌种类
    public static final long BRAND_LIST_FAIL = 6001002;
    //获取品牌详情
    public static final long BRAND_DETAIL_FAIL = 6001003;

    //产品分类 认证错误码
    public static final long SECURITY_CATEGORY_FAIL_CODE = 609999;

    /** ------------------------------------------商品分类------------------------------------------- */
    //查询商品详情失败
    public static final long GOODS_DETAIL_ERROR = 7001001;
    //根据条件查询商品列表(商品ID查询分页)失败
    public static final long GOODS_LIST_ERROR = 7001002;
    //根据条件查询商品列表分分类品牌统计信息(商品ID查询分页)失败
    public static final long GOODS_LIST_SEARCH_ERROR = 7001003;
    //查询一级分类列表失败
    public static final long GOODS_CATEGORY_LIST_ERROR = 7001004;
    //根据一级分类查询下级分类列表失败
    public static final long GOODS_CATEGORY_ID_ERROR = 7001005;
    //查询品牌列表失败
    public static final long GOODS_BRAND_LIST_ERROR = 7001006;
    //查询品牌详情失败
    public static final long GOODS_BRAND_ID_ERROR = 7001007;


    //获取商品列表失败
    public static final long SELECT_PRODUCT_LIST_FAIL = 7001008;
    //获取商品筛选条件失败
    public static final long GET_PRODUCT_FILTERS_FAIL = 7001009;
    //获取商品数量失败
    public static final long SELECT_PRODUCT_COUNT_FAIL = 7001010;
    //获取商品款式失败
    public static final long GET_PRODUCT_STYLE_FAIL = 7001011;
    //获取帮助咨询界面失败
    public static final long GET_PRODUCT_ARTICLE_FAIL = 7001012;
    //查询航班号信息失败
    public static final long GET_FLIGHT_INFO_FAIL = 7001013;
    //验证航班信息失败
    public static final long VALIDATE_FLIGHT_INFO_FAIL = 7001014;
    //获取会员行程信息失败
    public static final long MEMBER_JOURNEY_INFO_FAIL = 7001015;
    //查询轮渡信息失败
    public static final long GET_SHIP_INFO_FAIL = 7001016;
    //获取离岛轮渡港口列表信息失败
    public static final long GET_SHIP_BOARD_INFO_FAIL = 7001017;
    //验证轮渡信息失败
    public static final long VALIDATE_SHIP_INFO_FAIL = 7001018;
    //查询火车信息失败
    public static final long GET_TRAIN_INFO_FAIL = 7001019;
    //获取火车离岛信息失败
    public static final long GET_TRAIN_BOARD_INFO_FAIL = 7001020;
    //验证火车信息失败
    public static final long VALIDATE_TRAIN_INFO_FAIL = 7001021;
    //获取系统参数查看离岛三方校验开启状态接口
    public static final long GET_AUTO_CHECK_INFO_FAIL = 7001022;
    //货到通知接口失败
    public static final long PRODUCT_ARRIVAL_NOTICE_ERROR = 7001023;
    //获取排行榜热销商品接口失败
    public static final long PRODUCT_RANKINGS_BEST_ERROR = 7001024;
    //获取排行榜热门品牌接口失败
    public static final long PRODUCT_RANKINGS_BRANDS_ERROR = 7001025;
    //获取排行榜热门搜索接口失败
    public static final long PRODUCT_RANKINGS_KEYWORDS_ERROR = 7001026;


    //查询商品是否已收藏失败
    public static final long FIND_GOODS_COLLECT_ERROR = 7001027;
    //获得商品收藏列表失败
    public static final long GET_GOODS_COLLECT_LIST_ERROR = 7001028;

    //获取'验证离岛信'页面相关数据接口失败
    public static final long VALIDATE_OVERSEA_INFO_ERROR = 7001029;

    //删除商品收藏失败
    public static final long DELETE_GOODS_COLLECT_ERROR = 7001030;

    //验证用户行程信息接口失败
    public static final long CHECKOUT_JOURNEY_VALIDATION_ERROR = 700102930;

    //添加商品收藏商品失败
    public static final long ADD_GOODS_COLLECT_ERROR = 7001031;

    //获取唯一提货人信息失败
    public static final long GET_SINGLE_ERROR = 7001032;
    //保存唯一提货人信息失败
    public static final long POST_SINGLE_ERROR = 7001033;
    //修改唯一提货人信息失败
    public static final long UPDATE_SINGLE_ERROR = 7001034;

    //多商品id查询失败
    public static final long GOODS_IDS_LIST_ERROR = 7001035;

    //立即购买
    public static final long CHECKOUTNOW_ERROR = 7001036;
    public static final long GET_PRODUCTLIST = 7001037;

    //组合商品列表
    public static final long SELECT_COMBINATION_PRODUCT_ERROR = 7001038;
    //校验商品信息
    public static final long SELECT_PRODUCT_VALID_FAIL = 7001039;

    //校验商品请求校验接口失败
    public static final long REQUEST_PRODUCT_VALID_FAIL = 7001040;
    // 多商品id查询-查询活动商品列表
    public static final long FIND_GOODS_IDS_LIST_TITLE_ERROR = 7001041;
    // 获取售寄回地址失败
    public static final long GOODS_FIND_ARTICLE_ERROR = 7001042;

    //查询商品详情调用数据为空
    public static final long GOODS_DETAIL_NULL_ERROR = 7002001;
    //根据条件查询商品列表(商品ID查询分页)调用数据为空
    public static final long GOODS_LIST_NULL_ERROR = 7002002;
    //根据条件查询商品列表分分类品牌统计信息(商品ID查询分页)调用数据为空
    public static final long GOODS_LIST_SEARCH_NULL_ERROR = 7002003;
    //查询一级分类列表调用数据为空
    public static final long GOODS_CATEGORY_LIST_NULL_ERROR = 7002004;
    //根据一级分类查询下级分类列表调用数据为空
    public static final long GOODS_CATEGORY_ID_NULL_ERROR = 7002005;
    //查询品牌列表调用数据为空
    public static final long GOODS_BRAND_LIST_NULL_ERROR = 7002006;
    //查询品牌详情调用数据为空
    public static final long GOODS_BRAND_ID_NULL_ERROR = 7002007;


    //查询商品是否已收藏调用数据为空
    public static final long FIND_GOODS_COLLECT_NULL_ERROR = 7002008;
    //获得商品收藏列表调用数据为空
    public static final long GET_GOODS_COLLECT_LIST_NULL_ERROR = 7002009;
    //添加商品收藏商品调用数据为空
    public static final long ADD_GOODS_COLLECT_NULL_ERROR = 7002010;
    //删除商品收藏调用数据为空
    public static final long DELETE_GOODS_COLLECT_LIST_NULL_ERROR = 7002011;

    //多商品id查询调用数据为空
    public static final long GOODS_IDS_LIST_NULL_ERROR = 7002012;

    //获取售寄回地址调用数据为空
    public static final long GOODS_FIND_ARTICLE_NULL_ERROR = 7002013;

    //提货人信息不存在
    public static final long UPDATE_SINGLE_ERROR_19006 = 7002014;
    //户籍验证失败，请稍后再试！
    public static final long UPDATE_SINGLE_ERROR_19007 = 7002015;
    //您好！经系统验证，您的身份证户籍为%s，请核对后选择正确的户籍，祝您旅途愉快
    public static final long UPDATE_SINGLE_ERROR_19008 = 7002016;
    //发证机关不存在
    public static final long UPDATE_SINGLE_ERROR_19009 = 7002017;
    //唯一提货人已存在
    public static final long UPDATE_SINGLE_ERROR_19015 = 7002018;
    //参数错误
    public static final long UPDATE_SINGLE_ERROR_400 = 7002019;



    //获得商品收藏列表调用数据为空
    public static final long GET_GOODS_ERROR = 702999;

    //会员购商品 认证错误码
    public static final long SECURITY_GOODS_FAIL_CODE = 709999;


    /** ------------------------------------------首页前置操作错误------------------------------------------- */

    //获取首页搜出错
    public static final long HOTKEY_ERROR = 7003001;
    //首页搜索自动匹配搜索条件
    public static final long AUTO_COMPLETE_ERROR = 7003002;
    //首页广告
    public static final long ADVERTISE_ERROR= 7003003;


    /** ------------------------------------------离岛检查------------------------------------------- */


    /** ------------------------------------------认证模块------------------------------------------- */

    //前台认证错误码
    public static final long SECURITYERROR = 309999;
    //后台认证错误码
    public static final long SECURITYERRORAdmin = 109999;



    /** ------------------------------------------文章模块------------------------------------------- */


    /** ------------------------------------------处理第三方错误编码------------------------------------------- */
    public static final long WEBAPI_FIND_GOODS_IDS_LIST_CODE = 608801;

    public static final long WEBAPI_FIND_ADDRESS_IDS_LIST_CODE = 608802;

    /** ------------------------------------------处理第三方错误编码------------------------------------------- */
    // 会员购购物车服务第三方错误统一
    public static final long COUNT_CART_MEMBER_PURCHASE_FAIL = 5005101;
    // 会员购订单服务第三方错误统一
    public static final long ORDER_MEMBER_PURCHASE_FAIL = 5002001;
    // 会员购商品服务第三方错误统一
    public static final long PRODUCT_COLLECT_GOODS_MEMBER_PURCHASE_FAIL = 5007001;
    public static final long PRODUCT_INFO_MEMBER_PURCHASE_FAIL = 5007002;
    public static final long MEMBER_PURCHASE_PAYMENT_INFO_FAIL = 5007003;
    public static final long SECURITY_MEMBER_PURCHASE_PAYMENT_INFO_CODE = 5007004;
    // 会员购用户服务第三方错误统一
    public static final long USER_ADDRESS_MEMBER_PURCHASE_FAIL = 5003301;
    public static final long USER_CHECK_INFO_MEMBER_PURCHASE_FAIL = 5003302;
    public static final long USER_MY_DUTY_FREE_MEMBER_PURCHASE_FAIL = 5003303;
    public static final long USER_COUPON_MEMBER_PURCHASE_FAIL = 5003304;
    public static final long USER_MEMBER_POINT_FAIL = 5003305;


    /** ------------------------------------------Fallback订单模块(20)开始------------------------------------------- */
    //根据订单类型获取订单列表网络请求超时
    public static final long SECURITY_OVERSEA_ORDER_LIST_ERROR = 309901;
    //根据订单id获取订单详情网络请求超时
    public static final long SECURITY_OVERSEA_ORDER_DETAIL_ERROR = 309902;
    //根据订单id获取物流信息网络请求超时
    public static final long SECURITY_OVERSEA_ORDER_TRACE_ERROR = 309903;
    //根据订单id重新购买网络请求超时
    public static final long SECURITY_ORDER_REBUY_ERROR = 309904;
    //开具发票网络请求超时
    public static final long SECURITY_ORDER_APPLY_INVOICE_ERROR = 309905;
    //创建结算中心网络请求超时
    public static final long SECURITY_ORDER_CLEARING_CENTER_ERROR = 309906;
    //获取邮寄地址网络请求超时
    public static final long SECURITY_ORDER_GET_POST_ADDRESS_ERROR = 309907;
    //创建订单支付中心网络请求超时
    public static final long SECURITY_ORDER_CREATE_ERROR = 309908;
    //获取订单支付方式网络请求超时
    public static final long SECURITY_ORDER_PAYMENT_MODE_ERROR = 309909;
    //获取地区列表接口网络请求超时
    public static final long SECURITY_GET_REGIONS_LIST_ERROR = 309910;
    //结算中心网络请求超时
    public static final long SECURITY_SETTLEMENT_CENTER_ERROR = 309911;
    //离岛人脸识别接口网络请求超时
    public static final long SECURITY_GET_EID_TOKEN_ERROR = 309912;
    //同步通知Eid人脸验证结果接口网络请求超时
    public static final long SECURITY_EID_RESULT_ERROR = 309913;
    //会员购 下单 认证错误码
    public static final long SECURITY_ORDER_CONFIRM_FAIL_CODE = 309914;
    //会员购 指定商品下单 认证错误码
    public static final long SECURITY_ORDER_GOODS_FAIL_CODE = 309915;
    //会员购 取消订单 认证错误码
    public static final long SECURITY_ORDER_CANCEL_FAIL_CODE = 309916;
    //会员购 申请取消订单 认证错误码
    public static final long SECURITY_ORDER_CANCELING_FAIL_CODE = 309917;
    //会员购 查询订单列表 认证错误码
    public static final long SECURITY_ORDER_LIST_FAIL_CODE = 309918;
    //会员购 查询订单详情 认证错误码
    public static final long SECURITY_ORDER_DETAIL_FAIL_CODE = 309919;
    //会员购 查询订单物流信息 认证错误码
    public static final long SECURITY_ORDER_TRACE_FAIL_CODE = 309920;
    //会员购 设置售后单快递单号 认证错误码
    public static final long SECURITY_ORDER_AFTER_SALE_FAIL_CODE = 309921;
    //会员购 获得购物车下单可用优惠券 认证错误码
    public static final long SECURITY_ORDER_COUPON_CART_FAIL_CODE = 309922;
    //会员购 获得指定商品下单可用优惠券 认证错误码
    public static final long SECURITY_ORDER_COUPON_GOODS_FAIL_CODE = 309923;
    //会员购 可以申请售后的子订单 认证错误码
    public static final long SECURITY_ORDER_LOAD_SUB_FAIL_CODE = 309924;
    //会员购 加载快递列表 认证错误码
    public static final long SECURITY_ORDER_LOAD_LOGISTICS_FAIL_CODE = 309925;

    //取消订单接口失败
    public static final long SECURITY_CANCEL_ORDER_ERROR = 309926;
    //获取滚动物流信息失败
    public static final long SECURITY_SCROLL_LOGISTICS_ERROR = 309927;
    //申请退款接口
    public static final long SECURITY_RETURN_MONEY_ERROR = 309928;

    //获取申请退款提示信息接口
    public static final long SECURITY_RETURN_MONEY_TIPS_ERROR = 309929;
    //退款原因查询接口网络请求超时
    public static final long SECURITY_AFTER_SALE_REASONS_ERROR = 309930;
    //申请退款校验接口网络请求超时
    public static final long SECURITY_RETURN_MONEY_VALIDATE_ERROR = 309931;
    //获取修改邮寄地址是否存在多个订单提示接口网络请求超时
    public static final long SECURITY_POST_ADDRESS_APPLY_MULTI_ERROR = 309932;
    //创建修改邮寄地址订单接口网络请求超时
    public static final long SECURITY_POST_ADDRESS_APPLY_ERROR = 309933;
    //获取邮寄订单地址接口网络请求超时
    public static final long SECURITY_GET_ORDER_ADDRESS_ERROR = 309934;
    //使用积分接口网络请求超时
    public static final long SECURITY_USE_POINT_ERROR = 309935;
    //获取所有启用的返岛自提点网络请求超时
    public static final long SECURITY_DEPOSIT_ADDRESS_ERROR = 309936;
    //获取同行程有效返岛自提信息网络请求超时
    public static final long SECURITY_DEPOSIT_SAMEJOURNEY_ERROR = 309937;
    //获取订单对应返岛自提信息网络请求超时
    public static final long SECURITY_ORDER_DEPOSIT_ERROR = 309938;
    //修改返岛自提时间网络请求超时
    public static final long SECURITY_UPDATE_ORDER_DEPOSIT_ERROR = 309939;


    //会员购

    //申请售后提交接口网络请求超时
    public static final long SECURITY_ORDER_REQUEST_AFTER_SALE_FAIL_CODE = 309936;
    //获取售寄回地址接口网络请求超时
    public static final long SECURITY_ORDER_FIND_ARTICLE_FAIL_CODE = 309937;
    //提交快递单号信息接口网络请求超时
    public static final long SECURITY_ORDER_SET_AFTER_SALE_TRACKING_FAIL_CODE = 309938;
    //取消售后接口网络请求超时
    public static final long SECURITY_ORDER_REQUEST_CANCEL_AFTER_SALE_FAIL_CODE = 309939;
    //售后列表接口网络请求超时
    public static final long SECURITY_ORDER_GET_AFTER_SALE_LIST_FAIL_CODE = 309940;






    /** ------------------------------------------Fallback订单模块(20)结束------------------------------------------- */



    /** ------------------------------------------Fallback用户模块(30)开始------------------------------------------- */
    //浏览记录接口请求超时
    public static final long SECURITY_USER_BROWSE_HISTORY_FAIL = 309901;
    //查询优惠券列表请求超时
    public static final long SECURITY_USER_COUPON_LIST_FAIL = 309902;
    //获取可使用优惠券列表接口请求超时
    public static final long SECURITY_USER_AVILIABLE_COUPON_LIST_FAIL = 309903;
    //浏览记录接口请求超时
    public static final long SECURITY_USER_USE_COUPON_FAIL = 309904;
    //查询商品收藏列表请求超时
    public static final long SECURITY_USER_FAVORITE_LIST_FAIL = 309905;
    //查询商品收藏页面的商品分类接口请求超时
    public static final long SECURITY_USER_FAVORITE_LIST_CATEGORY_FAIL = 309906;
    //点击收藏商品接口请求超时
    public static final long SECURITY_USER_FAVORITE_ADD_CATEGORY_FAIL = 309907;
    //点击取消收藏商品接口请求超时
    public static final long SECURITY_USER_FAVORITE_DELETE_CATEGORY_FAIL = 309908;
    //批量删除收藏商品接口请求超时
    public static final long SECURITY_USER_FAVORITE_BATCH_DELETE_CATEGORY_FAIL = 309909;
    //帮助中心列表接口请求超时
    public static final long SECURITY_USER_HELP_CENTER_LIST_FAIL = 309910;
    //帮助中心列表下指定模块的信息请求超时
    public static final long SECURITY_USER_HELP_CENTER_MODULE_FAIL = 309911;
    //帮助中心搜索框接口请求超时
    public static final long SECURITY_USER_HELP_CENTER_SEARCH_FAIL = 309912;
    //微信兑换openid请求超时
    public static final long SECURITY_WX_LOGIN_FAIL = 309913;
    //通过第一步返回的code，解析手机号信息接口请求超时
    public static final long SECURITY_WX_USER_CODE_FAIL = 309914;
    //微信授权登录接口请求超时
    public static final long SECURITY_WX_USER_DOLOGIN_FAIL = 309915;
    //验证码登录接口请求超时
    public static final long SECURITY_CODE_LOGIN_FAIL = 309916;
    //获取验证码接口请求超时
    public static final long SECURITY_GET_AUTH_CODE_FAIL = 309917;
    //查询我的中免会员卡信息接口请求超时
    public static final long SECURITY_USER_MEMBER_CARD_FAIL = 309918;
    //查询会员卡适用门店信息接口请求超时
    public static final long SECURITY_GET_MEMBER_CARD_STORES_FAIL = 309919;
    //查询注册中免会员卡的个人资料信息接口请求超时
    public static final long SECURITY_GET_MEMBER_CARD_WITH_INFO_FAIL = 309920;
    //查询会员卡积分流水信息接口请求超时
    public static final long SECURITY_GET_MEMBER_CARD_FLOW_POINT_FAIL = 309921;
    //查询会员二维码接口请求超时
    public static final long SECURITY_GET_MEMBER_CARD_QRCODE_FAIL = 309922;
    //中免会员卡中心-我的服务-快速导航接口请求超时
    public static final long SECURITY_GET_MEMBER_CARD_NAVIGATION_FAIL = 309923;
    //注册开通中免会员接口请求超时
    public static final long SECURITY_MEMBER_CARD_REGISTER_FAIL = 309924;
    //查询我的会员基础信息接口请求超时
    public static final long SECURITY_USER_MEMBER_INFO_FAIL = 309925;
    //额度查询接口请求超时
    public static final long SECURITY_USER_QUOTA_ACCOUNT = 309926;
    //会员折扣类型查询接口请求超时
    public static final long SECURITY_GET_DISCOUNT_TYPE_FAIL = 309927;
    //可使用积分查询接口请求超时
    public static final long SECURITY_GET_AVILIABLE_POINTS_FAIL = 309928;
    //我的中免会员页面指定模块的信息接口请求超时
    public static final long SECURITY_USER_MEMBER_INFO_MODULE_FAIL = 309929;
    //我的中免页面信息封装接口请求超时
    public static final long SECURITY_GET_CDF_MEMBER_INFO_FAIL = 309930;
    //统一用户信息查询请求超时
    public static final long SECURITY_QUERY_MEMBER_INFO_FAIL = 309931;
    //会员购激活地址网络请求超时
    public static final long SECURITY_ACTIVE_ADDRESS_ERROR = 309932;
    //会员购添加地址网络请求超时
    public static final long SECURITY_ADD_ADDRESS_ERROR = 309933;
    //会员购删除地址网络请求超时
    public static final long SECURITY_DELETE_ADDRESS_ERROR = 309934;
    //会员购获得用户收货地址网络请求超时
    public static final long SECURITY_GET_USER_ADDRESS_LIST_ERROR = 309935;
    //会员购更新地址网络请求超时
    public static final long SECURITY_UPDATE_ADDRESS_ERROR = 309936;
    //会员购选择地址网络请求超时
    public static final long SECURITY_SELECT_ADDRESS_ERROR = 309951;
    //删除用户离岛信息网络请求超时
    public static final long SECURITY_DELETE_CHECK_INFO_ERROR = 309937;
    //获得用户离岛信息网络请求超时
    public static final long SECURITY_GET_CHECK_INFO_ERROR = 309938;
    //更新用户离岛信息网络请求超时
    public static final long SECURITY_UPDATE_CHECK_INFO_ERROR = 309939;
    //会员购 我的中免数量集合 认证错误码
    public static final long SECURITY_MY_DUTY_FREE_COUNT_CODE = 309940;
    //会员购 绑定大会员 认证错误码
    public static final long SECURITY_BIND_MEMBER_POINT_CODE = 309941;
    //会员购 发送绑定会员验证码 认证错误码
    public static final long SECURITY_BIND_MEMBER_POINT_CODE_CODE = 309942;
    //会员购 查询用户是否绑定大会员 认证错误码
    public static final long SECURITY_CHECK_MEMBER_CODE = 309943;
    //会员购 获得用户大会员可用积分数 认证错误码
    public static final long SECURITY_GET_MEMBER_POINT_CODE = 309944;
    //会员购 获得大会员信息 认证错误码
    public static final long SECURITY_GET_MEMBER_POINT_INFO_CODE = 309945;
    //会员购 获得大会员积分记录 认证错误码
    public static final long SECURITY_GET_MEMBER_POINT_LIST_CODE = 309946;
    //总积分接口查询
    public static final long SECURITY_GET_TOTAL_POINT_FAIL = 309946;
    //领取优惠券接口请求超时
    public static final long SECURITY_TAKE_COUPON_FAIL = 309947;
    //查询大会员优惠券列表
    public static final long SECURITY_USER_CRM_COUPON_LIST_FAIL = 309948;
    //查询优惠券数量
    public static final long SECURITY_COUPON_NUM_FAIL = 309949;
    //获取优惠券专题接口
    public static final long SECURITY_COUPON_INFO_FAIL = 309950;









    /** ------------------------------------------Fallback用户模块(30)结束------------------------------------------- */








    /** ------------------------------------------Fallback购物车模块(50)开始------------------------------------------- */
    //购物车列表
    public static final long SECURITY_GET_SHOPPING_CART_LIST_ERROR = 509901;
    //清空购物车
    public static final long SECURITY_CLEAR_SHOPPING_CART_ERROR = 509902;
    //添加商品至购物车
    public static final long SECURITY_ADD_GOODS_TO_CART_ERROR = 509903;
    //删除失效商品
    public static final long SECURITY_DELETE_INVALIDATE_ITEM_ERROR = 509904;
    //获取购物车商品数目
    public static final long SECURITY_GET_CART_ITEM_COUNT_ERROR = 509905;
    //取消选中购物车的所有商品
    public static final long SECURITY_CLEAR_ALL_SELECT_ITEM_ERROR = 509906;
    //选中购物车所有商品
    public static final long SECURITY_SELECT_ALL_ITEM_ERROR = 509907;
    //选中单个商品
    public static final long SECURITY_SELECT_ONE_ITEM_ERROR = 509908;
    //取消选中单个商品
    public static final long SECURITY_DELETE_ONE_SELECTED_ITEM_ERROR = 509909;
    //删除购物车单个商品
    public static final long SECURITY_DELETE_ONE_ITEM_ERROR = 509910;
    //修改商品数量
    public static final long SECURITY_SAVE_ITEM_QUANTITY_ERROR = 509911;
    //删除组合商品
    public static final long SECURITY_DELETE_COMBINATION_ERROR = 509912;
    //修改组合商品数目
    public static final long SECURITY_UPDATE_COMBINATION_ERROR = 509913;
    //添加促销商品至购物车
    public static final long SECURITY_ADD_COMBINATION_TO_CART_ERROR = 509914;
    //取消选中促销商品
    public static final long SECURITY_CLEAR_SELECTED_COMBINATION_ITEM_ERROR = 509915;
    //选中促销商品
    public static final long SECURITY_SELECTED_COMBINATION_ITEM_ERROR = 509916;
    //会员购 加入购物车 认证错误码
    public static final long SECURITY_ADD_CART_FAIL_CODE = 509917;
    //会员购 清空购物车 认证错误码
    public static final long SECURITY_CLEAR_CART_FAIL_CODE = 509918;
    //会员购 删除购物车 认证错误码
    public static final long SECURITY_DELETE_CART_FAIL_CODE = 509919;
    //会员购 更新购物车 认证错误码
    public static final long SECURITY_UPDATE_CART_FAIL_CODE = 509920;
    //会员购 加载购物车 认证错误码
    public static final long SECURITY_LOAD_CART_FAIL_CODE = 509921;
    //会员购 选择购物车 认证错误码
    public static final long SECURITY_SELECT_CART_FAIL_CODE = 509922;
    //会员购 全选购物车 认证错误码
    public static final long SECURITY_SELECT_ALL_CART_FAIL_CODE = 509923;
    //会员购 预览购物车 认证错误码
    public static final long SECURITY_PREPARE_ORDER_CART_FAIL_CODE = 509924;
    //会员购 预览商品 认证错误码
    public static final long SECURITY_PREPARE_ORDER_GOODS_FAIL_CODE = 509925;
    //会员购 购物车数目 认证错误码
    public static final long SECURITY_COUNT_CART_FAIL_CODE = 509926;
    //获取组合商品列表错误
    public static final long SECURITY_GET_COMBINATION_ERROR = 509927;
    //修改购物车商品样式失败
    public static final long SECURITY_SAVE_ITEM_PROPERTY_ERROR = 509928;
    /** ------------------------------------------Fallback购物车模块(50)结束------------------------------------------- */







    /** ------------------------------------------Fallback产品模块(60)开始------------------------------------------- */
    //小程序端查询文章网络请求超时
    public static final long SELECT_ARTICLE_FRONT_ERROR = 609901;
    //获取分类信息网络请求超时
    public static final long SECURITY_CATEGORY_LIST_FAIL = 609902;
    //获取品牌种类网络请求超时
    public static final long SECURITY_BRAND_LIST_FAIL = 609903;
    //获取品牌详情网络请求超时
    public static final long SECURITY_BRAND_DETAIL_FAIL = 609904;
    //搜索热词网络请求超时
    public static final long SECURITY_HOTKEY_ERROR = 609905;
    //自动搜索关键词网络请求超时
    public static final long SECURITY_AUTO_COMPLETE_ERROR = 609906;
    //根据关键字查询航班号信息网络请求超时
    public static final long SECURITY_GET_FLIGHT_INFO_ERROR = 609907;
    //航班信息验证网络请求超时
    public static final long SECURITY_VALIDATE_FLIGHT_INFO_ERROR = 609908;
    //获取会员行程信息网络请求超时
    public static final long SECURITY_MEMBER_JOURNEY_INFO_ERROR = 609909;
    //查询轮渡网络请求超时
    public static final long SECURITY_GET_SHIP_INFO_ERROR = 609910;
    //获取离岛轮渡港口列表信息网络请求超时
    public static final long SECURITY_GET_SHIP_BOARD_INFO_ERROR = 609911;
    //验证轮渡信息网络请求超时
    public static final long SECURITY_VALIDATE_SHIP_INFO_ERROR = 609912;
    //查询火车信息网络请求超时
    public static final long SECURITY_GET_TRAIN_INFO_ERROR = 609913;
    //获取火车离岛信息网络请求超时
    public static final long SECURITY_GET_TRAIN_BOARD_INFO_ERROR = 609914;
    //验证火车信息网络请求超时
    public static final long SECURITY_VALIDATE_TRAIN_INFO_ERROR = 609915;
    //获取系统参数查看离岛三方校验开启状态接口网络请求超时
    public static final long SECURITY_GET_AUTO_CHECK_INFO_ERROR = 609916;
    //获取'验证离岛信'页面相关数据网络请求超时
    public static final long SECURITY_VALIDATE_OVERSEA_INFO_ERROR = 609917;
    //验证用户行程信息网络请求超时
    public static final long SECURITY_CHECKOUT_JOURNEY_VALIDATION_ERROR = 609918;

    //获取商品列表
    public static final long SECURITY_GET_PRODUCTION_LIST_ERROR = 609919;
    //获取商品筛选条件
    public static final long SECURITY_GET_PRODUCT_FILTERS_ERROR = 609920;
    //获取商品数量
    public static final long SECURITY_GET_PRODUCT_COUNT_ERROR = 609921;
    //获取商品详情
    public static final long SECURITY_GET_PRODUCT_DETAIL_ERROR = 609922;
    //获取商品款式
    public static final long SECURITY_GET_PRODUCTION_STYLE_ERROR = 609923;
    //获取帮助咨询页面
    public static final long SECURITY_GET_PRODUCTION_ARTICLE_ERROR = 609924;
    //到货通知接口
    public static final long SECURITY_ARRIVAL_NOTICE_ERROR = 609925;
    //获取排行榜热销商品接口
    public static final long SECURITY_RANKINGS_BEST_ERROR = 609926;
    //获取排行榜热门品牌接口
    public static final long SECURITY_RANKINGS_BRANDS_ERROR = 609927;
    //查询商品错误
    public static final long SECURITY_RANKINGS_KEYWORDS_ERROR = 609928;
    //获取排行榜热门搜索接口
    public static final long SECURITY_GET_GOODS_ERROR = 609929;
    //通过商品id获取货品信息
    public static final long SECURITY_GET_GOODS_INFO_ERROR = 609930;
    //通过ids获取货品信息
    public static final long SECURITY_GET_HOME_PG_PRODUCTION_LIST_ERROR = 609931;
    //通过id查询文章
    public static final long SECURITY_SELECT_ARTICLE_ERROR = 609932;
    //会员购  添加商品收藏 认证错误码
    public static final long SECURITY_ADD_GOODS_FAIL_CODE = 609933;
    //会员购  查询商品是否已收藏 认证错误码
    public static final long SECURITY_FIND_GOODS_FAIL_CODE = 609934;
    //会员购  删除商品收藏 认证错误码
    public static final long SECURITY_DELETE_GOODS_FAIL_CODE = 609935;
    //会员购  获得商品收藏列表 认证错误码
    public static final long SECURITY_GET_GOODS_FAIL_CODE = 609936;
    //会员购 会员购获得券列表 认证错误码
    public static final long SECURITY_USER_COUPON_LIST_CODE = 609937;
    //会员购 获得领券列表 认证错误码
    public static final long SECURITY_USER_RECEIVE_COUPON_CODE = 609938;
    //会员购 领券 认证错误码
    public static final long SECURITY_USER_REQUEST_RECEIVE_COUPON_CODE = 609939;
    //会员购  查询商品详情 认证错误码
    public static final long SECURITY_GOODS_DETAIL_FAIL_CODE = 609940;
    //会员购  根据条件查询商品列表(商品ID查询分页) 认证错误码
    public static final long SECURITY_GOODS_LIST_FAIL_CODE = 609941;
    //会员购  根据条件查询商品列表分分类品牌统计信息(商品ID查询分页) 认证错误码
    public static final long SECURITY_GOODS_LIST_SEARCH_FAIL_CODE = 609942;
    //会员购  查询一级分类列表 认证错误码
    public static final long SECURITY_GOODS_CATEGORY_LIST_FAIL_CODE = 609943;
    //会员购  根据一级分类查询下级分类列表 认证错误码
    public static final long SECURITY_GOODS_CATEGORY_ID_FAIL_CODE = 609944;
    //会员购  查询品牌列表 认证错误码
    public static final long SECURITY_GOODS_BRAND_LIST_FAIL_CODE = 609945;
    //会员购  查询品牌详情 认证错误码
    public static final long SECURITY_GOODS_BRAND_ID_FAIL_CODE = 609946;
    //会员购  多商品id查询 认证错误码
    public static final long SECURITY_GOODS_IDS_LIST_FAIL_CODE = 609947;
    //根据条形码查询商品网络请求超时
    public static final long SECURITY_GET_PRODUCT_BY_BARCODE_ERROR = 609948;

    //通过ids校验商品信息失败
    public static final long SECURITY_GET_PRODUCT_VALID_ERROR = 609949;



    //查询文章栏目失败
    public static final long SELECT_ARTICLE_CATEGORY_FRONT_ERROR = 609950;

    //获取提货人列表失败
    public static final long SECURITY_CONSIGNEES_FAIL_CODE = 609951;


    //会员购 购物车模式获得大会员可用券列表 认证错误码
    public static final long SECURITY_USER_MEMBER_CART_COUPON_CODE = 609952;


    //会员购 获得大会员可用券列表 认证错误码
    public static final long SECURITY_USER_MEMBER_GOODS_COUPON_CODE = 609953;

    //会员购 获得大会员券列表 认证错误码
    public static final long SECURITY_USER_MEMBER_COUPON_LIST_ERROR = 609954;
    //会员购 获取售寄回地址 认证错误码
    public static final long SECURITY_GOODS_FIND_ARTICLE_FAIL_CODE = 609955;

    //定时任务更新数据库文章阅读数 认证错误码
    public static final long SECURITY_UPDATE_ARTICLE_READ_COUNT_ERROR = 609956;

    //定时任务更新数据库文章阅读数
    public static final long UPDATE_ARTICLE_READ_COUNT_ERROR = 609957;

    /** ------------------------------------------Fallback产品模块(60)结束------------------------------------------- */

    /** ------------------------------------------Web前置层产品模块(60)开始------------------------------------------- */
    //获取商品搜索列表错误
    public static final long WEB_PRODUCT_LIST_FAIL = 608801;

    //获取首页商品ids列表错误
    public static final long WEB_PRODUCT_LIST_BY_IDS_FAIL = 608802;

    //根据条形码获取商品失败
    public static final long GET_PRODUCT_BY_BARCODE_ERROR = 608803;

    //获取品牌详情失败
    public static final long WEB_BRAND_DETAIL_FAIL = 608804;

    //获取商品详情失败
    public static final long WEB_PRODUCT_DETAIL_FAIL = 608805;

    /** ------------------------------------------Web前置层产品模块(60)结束------------------------------------------- */


    /** ------------------------------------------业务层后台模块(10)开始------------------------------------------- */
    public static final long ADMIN_DADA_NONE = 1001001;
    public static final long CONVERT_DATA_ERROR = 1001002;

    //系统用户模块
   public static final long  SAVE_SYS_USER_ERROR = 1101001;
   public static final long  UPDATE_SYS_USER_ERROR = 1101002;
   public static final long  RESET_SYS_USER_PASS_ERROR =1101003;




    /** ------------------------------------------业务层后台模块(10)结束------------------------------------------- */

    /** ------------------------------------------业务层订单模块(20)开始------------------------------------------- */
    //系统两位20+模块两位01:CsOrderInfoController+具体接口两位xx
    // 查询订单列表失败
    public static final long ORDER_LIST_ERROR = 200101;
    // 查询订单详情失败
    public static final long ORDER_DETAIL_ERROR = 200102;
    // 查询订单物流信息失败
    public static final long ORDER_TRACE_ERROR = 200103;
    //根据订单id再次购买失败
    public static final long ORDER_REBUY_ERROR = 200104;
    //开具发票失败
    public static final long ORDER_APPLY_INVOICE_ERROR = 200105;
    //创建结算中心失败
    public static final long ORDER_CLEARING_CENTER_ERROR = 200106;
    //获取邮寄地址失败
    public static final long ORDER_GET_POST_ADDRESS_ERROR = 200107;
    //创建订单中心失败
    public static final long ORDER_CREATE_ERROR = 200108;
    //获取订单支付方式失败
    public static final long ORDER_PAYMENT_MODE_ERROR = 200109;
    //获取地区列表接口失败
    public static final long GET_REGIONS_LIST_ERROR = 200110;
    // 请求结算中心接口失败
    public static final long SETTLEMENT_CENTER_ERROR = 200111;
    //离岛人脸识别接口失败
    public static final long GET_EID_TOKEN_ERROR = 200112;
    //同步通知Eid人脸验证结果接口失败
    public static final long EID_RESULT_ERROR = 200113;
    //取消订单
    public static final long ORDER_CANCELED_ERROR = 200114;
    //获取滚动物流信息失败。
    public static final long SCROLL_LOGISTICS_ERROR = 200115;
    //部分商品已失效，请返回购物车重新操作。
    public static final long SERVICE_ORDER_CLEARING_CENTER_PRODUCT_ERROR = 200116;
    //当前行程时间不满足购买条件，建议您更换行程时间重新提交
    public static final long SERVICE_ORDER_CLEARING_CENTER_TRIP_ERROR = 200117;
    //您好，为了确保订单正常配送，您当前离岛车次日期无法下单
    public static final long SERVICE_ORDER_CLEARING_CENTER_TRIP_AIR_ERROR = 200118;
    //您选择的航班行程暂时不支持配送，请尝试选择其它的站点下单。
    public static final long SERVICE_ORDER_CLEARING_CENTER_AIR_DIST_ERROR = 200119;
    //离岛日期格式不对
    public static final long SERVICE_ORDER_CLEARING_CENTER_ERROR_19102 = 200120;
    //获取自提点信息异常
    public static final long SERVICE_ORDER_CLEARING_CENTER_ERROR_320000 = 200121;
    //当前系统时间大于轮渡离岛时间，无法下单
    public static final long SERVICE_ORDER_CLEARING_CENTER_ERROR_19331 = 200122;
    //火车信息不存在
    public static final long SERVICE_ORDER_CLEARING_CENTER_ERROR_19104 = 200123;
    //默认配送方式获取失败
    public static final long SERVICE_ORDER_CLEARING_CENTER_ERROR_420000 = 200124;
    //购物车为空
    public static final long SERVICE_ORDER_CLEARING_CENTER_ERROR_13002 = 200125;
    //申请退款接口失败
    public static final long RETURN_MONEY_ERROR = 200126;
    //未找到可申请退款子订单
    public static final long RETURN_MONEY_ERROR_12041 = 200127;
    //获取申请退款提示信息接口失败
    public static final long RETURN_MONEY_TIPS_ERROR = 200128;
    //获取退款原因接口失败
    public static final long AFTER_SALE_REASONS_ERROR = 200129;
    //获取退款校验接口失败
    public static final long RETURN_MONEY_VALIDATE_ERROR = 200130;
    //人脸核验失败，该用户已经核验成功，无需再次核验
    public static final long GET_EID_TOKEN_ERROR_38001 = 200131;
    //获取修改邮寄地址是否存在多个订单提示接口失败
    public static final long POST_ADDRESS_APPLY_MULTI_ERROR = 200132;
    //创建修改邮寄地址订单接口失败
    public static final long POST_ADDRESS_APPLY_ERROR = 200133;
    //获取邮寄订单地址接口失败
    public static final long GET_ORDER_ADDRESS_ERROR = 200134;
    //使用积分抵扣接口失败
    public static final long USE_POINT_ERROR = 200135;
    //库存不足（再次购买接口）
    public static final long ORDER_REBUY_ERROR_12006 = 200136;

    //离岛时间已过，不可以修改
    public static final long POST_ADDRESS_APPLY_MULTI_ERROR_12045 = 200137;
    //超出最早下单时间范围
    public static final long ORDER_CLEARING_CENTER_ERROR_19112 = 200138;

    //获取邮寄地址接口行程信息异常
    public static final long ORDER_GET_POST_ADDRESS_ERROR_19110 = 200139;

    public static final long ORDER_CLEARING_CENTER_ERROR_19113 = 200140;
    // 下单接口库存不足
    public static final long ORDER_CLEARING_CENTER_ERROR_12006 = 200141;
    // 商品上下架状态异常
    public static final long ORDER_CLEARING_CENTER_ERROR_12008 = 200142;
    // 触发限购
    public static final long ORDER_CLEARING_CENTER_ERROR_400 = 200143;
    // 结算金额错误
    public static final long ORDER_CLEARING_CENTER_ERROR_500 = 200144;
    // 商品已下架
    public static final long ORDER_REBUY_ERROR_12010 = 200145;
    // 获取所有启用的返岛自提点
    public static final long DEPOSIT_ADDRESS_ERROR = 200146;
    // 获取同行程有效返岛自提信息
    public static final long DEPOSIT_SAMEJOURNEY_ERROR = 200147;
    // 获取订单对应返岛自提信息
    public static final long ORDER_DEPOSIT_ERROR = 200148;
    // 修改返岛自提时间
    public static final long UPDATE_ORDER_DEPOSIT_ERROR = 200149;





    /** ------------------------------------------业务层订单模块(20)结束------------------------------------------- */

    /** ------------------------------------------业务层支付模块(70)开始------------------------------------------- */

    public static final long GET_PAY_MODE_FAIL = 700101;
    public static final long PREPAY_FAIL = 700102;

    /** ------------------------------------------业务层支付模块(70)结束------------------------------------------- */
    /** ------------------------------------------业务层支付模块fallback(70)开始------------------------------------------- */
    public static final long WEB_PAYMENT_LIST_FAIL = 709901;
    public static final long WEB_PREPAY_FAIL = 709902;
    // 会员购支付
    public static final long MEMBER_PURCHASE_PAYMENT_CODE = 709903;
    public static final long MEMBER_PURCHASE_PAYMENT_EXCEPTION_CODE = 709904;
    /** ------------------------------------------业务层支付模块fallback(70)结束------------------------------------------- */
}
