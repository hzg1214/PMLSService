package cn.com.eju.deal.user.api.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.com.eju.deal.core.support.Constant;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.support.ReturnCode;
import cn.com.eju.deal.core.support.SystemCfg;
import cn.com.eju.deal.user.api.IAuthAPI;
import cn.com.eju.deal.user.dao.AuthMapper;
import cn.com.eju.deal.user.model.Auth;

@Service("authAPI")
public class AuthAPIImpl implements IAuthAPI
{
    @Resource
    private AuthMapper authDao;
    
    @Override
    public ResultData<List<Auth>> getListByPostId(int operateUserId, int postId)
    {
        ResultData<List<Auth>> authResult = new ResultData<List<Auth>>();
        try
        {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("postId", postId);
            map.put("systemCode", SystemCfg.getString("systemCode"));
            map.put(Constant.SQL_UN_CONTROL, false);
            List<Auth> list = this.authDao.getListByPostId(map);
            if (list != null)
            {
                authResult.setReturnCode(ReturnCode.SUCCESS);
                authResult.setReturnMsg("调用成功");
                authResult.setReturnData(list);
            }
            else
            {
                authResult.setReturnCode(ReturnCode.FAILURE);
                authResult.setReturnMsg("未找到数据");
            }
        }
        catch (Exception e)
        {
            authResult.setReturnCode(ReturnCode.FAILURE);
            authResult.setReturnMsg("调用出错");
        }
        return authResult;
    }
    
    public ResultData<List<Auth>> getMentListByPostId(int operateUserId, int postId)
    {
        ResultData<List<Auth>> authResult = new ResultData<List<Auth>>();
        try
        {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("postId", postId);
            map.put("systemCode", SystemCfg.getString("systemCode"));
            map.put(Constant.SQL_UN_CONTROL, false);
            List<Auth> list = this.authDao.getListByPostId(map);
            if (list != null)
            {
                List<Auth> mList = new ArrayList<Auth>();
                List<Auth> pList = new ArrayList<Auth>();
                List<Auth> bList = new ArrayList<Auth>();
                for (Auth a : list)
                {
                    if (a.getParentId() == 0)
                    {
                        mList.add(a);
                    }
                    else if ("P".equals(a.getType()) || "M".equals(a.getType()))
                    {
                        pList.add(a);
                    }
                    else
                    {
                        bList.add(a);
                    }
                }
                
                for (Auth m : mList)
                {
                    if (!"/workbench/init".equals(m.getUrl()))
                    {
                        int urlsearchcount = 0;
                        for (int i = 0; i < pList.size(); i++)
                        {
                            Auth p = pList.get(i);
                            if (m.getUrl().equals(p.getUrl()))
                            {
                                break;
                            }
                            urlsearchcount++;
                        }
                        //没有找到
                        if (urlsearchcount == pList.size())
                        {
                            map.put("treeStr", m.getTreeIdStr());
                            Auth newAuth = this.authDao.getAllowAuthByHeader(map);
                            if (newAuth != null)
                            {
                                m.setUrl(newAuth.getUrl());
                            }
                        }
                        
                    }
                }
                
                mList.addAll(pList);
                mList.addAll(bList);
                authResult.setReturnCode(ReturnCode.SUCCESS);
                authResult.setReturnMsg("调用成功");
                authResult.setReturnData(mList);
            }
            else
            {
                authResult.setReturnCode(ReturnCode.FAILURE);
                authResult.setReturnMsg("未找到数据");
            }
        }
        catch (Exception e)
        {
            authResult.setReturnCode(ReturnCode.FAILURE);
            authResult.setReturnMsg("调用出错");
        }
        return authResult;
    }
    
    /*  
      @Override
      public ResultData<List<AuthDto>> getAuthTreeByuserId(int operateUserId, int userId)
      {
          ResultData<List<AuthDto>> authResult = this.getAuthTreeByuserId(operateUserId,userId,false);
          return authResult;
      }
      
      
      @Override
      public ResultData<List<AuthDto>> getAuthTreeByuserId(int operateUserId, int userId, boolean hasBtn)
      {
          ResultData<List<AuthDto>> authResult = new ResultData<List<AuthDto>>();
          try
          {
              Map<String, Object> queryParam = new HashMap<String, Object>();
              queryParam.put("userId", userId);
              if(hasBtn){
                  queryParam.put("paramsql",  " and ua.type in('P','M','B')");
              }else{
                  queryParam.put("paramsql",  " and ua.type in('P','M')");
              }
              List<Auth> list = this.authDao.getListByUserId(queryParam);
              
              List<AuthDto> treeList= getTreelist(list);
              if (treeList != null)
              {
                  authResult.setReturnCode("200");
                  authResult.setReturnMsg("调用成功");
                  authResult.setReturnData(treeList);
              }
              else
              {
                  authResult.setReturnCode("-1");
                  authResult.setReturnMsg("未找到数据");
              }
          }
          catch (Exception e)
          {
              authResult.setReturnCode("0");
              authResult.setReturnMsg("调用出错");
          }
          return authResult;
      }
      
      
      
      private List<AuthDto> getTreelist(List<Auth> authes) {        
          List<AuthDto> authDtos = new ArrayList<AuthDto>();
          createTree(0,authes,authDtos);
          return authDtos;
          
      }
      
      private List<AuthDto> createTree(int parentId,List<Auth> authes,List<AuthDto> authDtos){
          List<AuthDto> list2 = getPrivateSubItem(parentId,authes);
          if(list2 != null && !list2.isEmpty())
          {
              int n = list2.size();
              for(int i = 0; i < n;i++)
              {
                  AuthDto o = list2.get(i);               
                  //不包含子菜单
                  if(!isHaveSubItem(o.getId(),authes))
                  {
                  }else{
                       List<AuthDto> list333 = new ArrayList<AuthDto>();
                       o.setChildren(createTree(o.getId(),authes,list333));
                  }               
                  authDtos.add(o);            
              }
          }
          
          return list2;
      }
      
      private boolean isHaveSubItem(int id,List<Auth> authes) {
          List<AuthDto> list = getPrivateSubItem(id,authes);
          if(list != null && !list.isEmpty()){
              return true;
          }
          return false;
      }

      private List<AuthDto> getPrivateSubItem(int parentId,List<Auth> authes) {
          
          List<AuthDto> mds = new ArrayList<AuthDto>();
          for(Auth auth : authes){
              if(auth.getParentId() == parentId){
                  AuthDto md = new AuthDto();
                  md.setId(auth.getAuthId());
                  md.setText(auth.getAuthName());
                  md.setAuthCode(auth.getAuthCode());
                  md.setAuthDesc(auth.getAuthDesc());
                  mds.add(md);
              }
          }
          return mds;
      }*/
    
    /**
     * 根据url和type查询权限
     * @param params
     * @return
     */
    public ResultData<Auth> getAuthByUrlAndType(Map<String, Object> params)
    {
        ResultData<Auth> authResult = new ResultData<Auth>();
        try
        {
            Auth auth = this.authDao.getAuthByUrlAndType(params);
            if (null != auth)
            {
                authResult.setReturnCode(ReturnCode.SUCCESS);
                authResult.setReturnMsg("调用成功");
                authResult.setReturnData(auth);
            }
            else
            {
                authResult.setReturnCode(ReturnCode.FAILURE);
                authResult.setReturnMsg("未找到数据");
            }
        }
        catch (Exception e)
        {
            authResult.setReturnCode(ReturnCode.FAILURE);
            authResult.setReturnMsg("调用出错");
        }
        return authResult;
    }
}
