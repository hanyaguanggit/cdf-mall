package com.cdf.mall.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
@Slf4j
public class TraceService {
   /* @Autowired
    private GoodsFacade goodsFacade;
    @Autowired
    private TraceService self;
    @Autowired
    private BrandFacade brandFacade;
    @Autowired
    private CategoryFacade categoryFacade;
    @Autowired
    private EventTraceLogDao eventTraceLogDao;
    @Autowired
    private InfrastructureFacade infrastructureFacade;
    public void trace(String phone,String memberId,String code){
        log.info("code:{}",code);
        String codeValue = EncryptUtil.decrypt(code);
        log.info("codeValue:{}",codeValue);
        String [] codeArray = codeValue.split("\\|");
        String productId = codeArray[0];
        int workerId = Integer.parseInt(codeArray[1]);
        int time = Integer.parseInt(codeArray[2]);

        Date now = new Date();
        Date endTime = new Date(now.getTime()+time*60*1000);
        EventTraceLog eventTraceLog = new EventTraceLog();
        eventTraceLog.setCompany("");
        eventTraceLog.setEmployeeId(workerId);
        eventTraceLog.setEnterTime(now);
        eventTraceLog.setStartTime(now);
        eventTraceLog.setEndTime(endTime);
        eventTraceLog.setPhone(phone);
        eventTraceLog.setMemberId(memberId);
        eventTraceLog.setProductId(productId);
        eventTraceLog.setValidBindTime(time);
        eventTraceLog.setName("");
        eventTraceLogDao.insert(eventTraceLog);
    }
    public List<PromotionResult> pricePromotionInfo(String codes) {
        List<PromotionResult> promotionResultList = new ArrayList<>();
        String[] codeArr  = codes.split(",");
        for(String code:codeArr){
            GoodsDetailResult goodsDetailResult = goodsFacade.findGoodsDetailByIdAlways(null,null,null,"01"+code);
            if(goodsDetailResult.getActivityData()!=null&&goodsDetailResult.getActivityData().size()>0){
                GoodsDetailResult.ActivityDataCla activityDataCla = goodsDetailResult.getActivityData().get(0);
                PromotionResult promotionData = new PromotionResult();
                promotionData.setCode(code);
                promotionData.setSalesPrice(goodsDetailResult.getSalesPrice());
                promotionData.setEstimatePrice(goodsDetailResult.getSalesPrice());
                if(goodsDetailResult.getEstimatePrice().compareTo(BigDecimal.ZERO)>0){
                    promotionData.setEstimatePrice(goodsDetailResult.getEstimatePrice());
                }
                PromotionResult.RuleVO vo = new PromotionResult.RuleVO();
                vo.setName(activityDataCla.getLongName());
                vo.setEstimatePrice(goodsDetailResult.getSalesPrice());
                if(goodsDetailResult.getEstimatePrice().compareTo(BigDecimal.ZERO)>0){
                    vo.setEstimatePrice(goodsDetailResult.getEstimatePrice());
                }
                vo.setLabel(activityDataCla.getName());
                vo.setType(activityDataCla.getType());
                promotionData.getRules().add(vo);
                promotionResultList.add(promotionData);
            }else{
                PromotionResult promotionData = new PromotionResult();
                promotionData.setSalesPrice(goodsDetailResult.getSalesPrice());
                promotionData.setCode(code);
                promotionData.setEstimatePrice(goodsDetailResult.getSalesPrice());
                promotionResultList.add(promotionData);
            }
        }
        return promotionResultList;
    }
    public List<BrandResult> brands(){
        List<Brand> brandList = brandFacade.findBrandList();
        List<BrandResult> list = new ArrayList<>();
        for(Brand brand:brandList){
            BrandResult brandRequest = new BrandResult();
            brandRequest.setCountry(brand.getCountry());
            brandRequest.setId(brand.getBrandId());
            brandRequest.setLogo(brand.getSmallLogo());
            brandRequest.setName(brand.getCnName());
            brandRequest.setNameEN(brand.getEnName());
            if(brand.getIsClose()==1)
                brandRequest.setStatus(0);
            brandRequest.setStatus(1);
            list.add(brandRequest);
        }
        return list;
    }
    public List<CategoryResult> categoryList() {
        List<Category> categoryList = categoryFacade.findAll();
        Map<String, CategoryResult> hashMap = new HashMap<>();
        Map<String, Category> oldMap = new HashMap<>();
        List<CategoryResult> firstList = new ArrayList<>();
        List<CategoryResult> list = new ArrayList<>();
        for(Category category:categoryList){
            //如果是0的话就加入到data中
            oldMap.put(category.getCategoryId(),category);
            if(category.getLevel()==null){
               continue;
            }

            CategoryResult object = new CategoryResult();
            object.setId(category.getCategoryId());
            object.setLevel(category.getLevel());
            object.setName(category.getCnName());
            object.setIcon(category.getSmallImg());
            object.setStatus(1);
            if(category.getClosed()!=null&&category.getClosed()==1)
             object.setStatus(0);
            object.setNameEN(category.getEnName());
            object.setParentId(category.getParentCategoryId());
            object.setSequence(category.getIndex());
            hashMap.put(object.getId(),object);
            list.add(object);
        }
        for(CategoryResult category:list){
            if(category.getLevel()==3){
                //找2级
                CategoryResult selfNode = hashMap.get(category.getId());
                CategoryResult parentNode = hashMap.get(category.getParentId());
                if(parentNode!=null){
                    parentNode.getChildrens().add(selfNode);
                    CategoryResult parentNode2 = hashMap.get(parentNode.getParentId());
                    if(parentNode2!=null)
                        selfNode.setPath(parentNode2.getId()+"/"+parentNode.getId()+"/"+selfNode.getId());
                }

            }
            if(category.getLevel()==2){
                //找3级
                CategoryResult selfNode = hashMap.get(category.getId());
                CategoryResult parentNode = hashMap.get(category.getParentId());
                if(parentNode!=null){
                    parentNode.getChildrens().add(selfNode);
                    selfNode.setPath(parentNode.getId()+"/"+selfNode.getId());
                }
            }
            if(category.getLevel()==1){
                //找3级
                CategoryResult selfNode = hashMap.get(category.getId());
                selfNode.setPath(selfNode.getId());
                firstList.add(selfNode);
            }
        }
        return firstList;
    }
    public boolean checkTrace(OrderDetailResult orderDetailResult){
        if(!orderDetailResult.getPlatformId().equals("08")){
            return false;
        }
        List<EventTraceLog> list =  eventTraceLogDao.findByPhoneAndTime(orderDetailResult.getPhone(),orderDetailResult.getCreateTime());
        if(list.size()==0)
            return false;
        return true;
    }*/
}
