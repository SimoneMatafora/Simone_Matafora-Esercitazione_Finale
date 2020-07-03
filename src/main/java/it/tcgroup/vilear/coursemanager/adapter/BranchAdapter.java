package it.tcgroup.vilear.coursemanager.adapter;

import it.tcgroup.vilear.coursemanager.controller.payload.request.BranchRequestV1;
import it.tcgroup.vilear.coursemanager.controller.payload.response.BranchResponseV1;
import it.tcgroup.vilear.coursemanager.controller.payload.response.IdResponseV1;
import it.tcgroup.vilear.coursemanager.controller.payload.response.PaginationResponseV1;
import it.tcgroup.vilear.coursemanager.entity.BranchEntity;
import it.tcgroup.vilear.coursemanager.pagination.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
public class BranchAdapter {

    @Autowired
    private AddressAdapter addressAdapter;

    public BranchEntity adptBranchRequestToBranch(BranchRequestV1 filialeRequest){

        if(filialeRequest == null)
            return null;

        BranchEntity branch = new BranchEntity();

        branch.setRightOfAccessToTheCourses(filialeRequest.getRightOfAccessToTheCourses());
        branch.setEmail(filialeRequest.getEmail());
        branch.setAddress(addressAdapter.adptAddressRequestToAddress(filialeRequest.getAddress()));
        branch.setName(filialeRequest.getName());
        branch.setSuperBranch(filialeRequest.getSuperBranch());

        return branch;
    }

    public IdResponseV1 adptBranchIdToBranchIdResponse(BranchEntity branch){

        if(branch == null)
            return null;

        IdResponseV1 filialeIdResponse = new IdResponseV1();
        filialeIdResponse.setId(branch.getId());

        return filialeIdResponse;
    }

    public BranchResponseV1 adptBranchToBranchResponse(BranchEntity branch){

        if(branch == null)
            return null;

        BranchResponseV1 brancheResponse = new BranchResponseV1();

        brancheResponse.setId(branch.getId().toString());
        brancheResponse.setRightOfAccessToTheCourses(branch.getRightOfAccessToTheCourses());
        brancheResponse.setEmail(branch.getEmail());
        brancheResponse.setAddress(addressAdapter.adptAddressToAddressResponse(branch.getAddress()));
        brancheResponse.setName(branch.getName());
        brancheResponse.setSuperBranch(branch.getSuperBranch());

        return brancheResponse;
    }

    public List<BranchResponseV1> adptBranchToBranchResponse(List<BranchEntity> branchList){

        if(branchList == null)
            return null;

        List<BranchResponseV1> branchResponseList = new LinkedList<>();
        for (BranchEntity att : branchList){
            branchResponseList.add(this.adptBranchToBranchResponse(att));
        }
        return branchResponseList;
    }

    public PaginationResponseV1<BranchResponseV1> adpBranchPaginationToBranchPaginationResposne(Pagination<BranchEntity> branchPagination){

        if(branchPagination == null)
            return null;

        PaginationResponseV1<BranchResponseV1> branchPaginationResponse = new PaginationResponseV1<>();

        branchPaginationResponse.setItems(this.adptBranchToBranchResponse(branchPagination.getItems()));
        branchPaginationResponse.setStats(branchPagination.getStats());

        return branchPaginationResponse;
    }


}
