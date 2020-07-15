package cn.com.eju.deal.dto.op;

import java.util.List;
import cn.com.eju.deal.dto.company.CompanyADto;
import cn.com.eju.deal.dto.store.StoreADto;

/**   
* Model类
* @author (qy)
* @date 2017年7月20日 下午13:51:09
*/
public class OpOperatorDto
{
        
    private Integer userIdUpdate;//操作人ID
    
    private CompanyADto companyADto;//公司DTO
    
    private List<StoreADto> storeADtoList;//门店DTOlist
 
	public List<StoreADto> getStoreADtoList() {
		return storeADtoList;
	}

	public void setStoreADtoList(List<StoreADto> storeADtoList) {
		this.storeADtoList = storeADtoList;
	}

	public Integer getUserIdUpdate() {
		return userIdUpdate;
	}

	public void setUserIdUpdate(Integer userIdUpdate) {
		this.userIdUpdate = userIdUpdate;
	}

	public CompanyADto getCompanyADto() {
		return companyADto;
	}

	public void setCompanyADto(CompanyADto companyADto) {
		this.companyADto = companyADto;
	}
    
}