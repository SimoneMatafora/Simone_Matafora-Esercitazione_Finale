package it.tcgroup.vilear.dummy.adapter;

import it.tcgroup.vilear.dummy.controller.payload.request.BranchRequestV1;
import it.tcgroup.vilear.dummy.controller.payload.response.BranchResponseV1;
import it.tcgroup.vilear.dummy.controller.payload.response.IdResponseV1;
import it.tcgroup.vilear.dummy.controller.payload.response.PaginationResponseV1;
import it.tcgroup.vilear.dummy.entity.BranchEntity;
import it.tcgroup.vilear.dummy.entity.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
public class BranchAdapter {

    @Autowired
    private AddressAdapter addressAdapter;

    public BranchEntity adptBranchRequestToBranch(BranchRequestV1 filialeRequest){

        BranchEntity branch = new BranchEntity();

        branch.setRightOfAccessToTheCourses(filialeRequest.getRightOfAccessToTheCourses());
        branch.setEmail(filialeRequest.getEmail());
        branch.setAddress(addressAdapter.adptAddressRequestToAddress(filialeRequest.getAddress()));
        branch.setName(filialeRequest.getName());
        branch.setSuperBranch(filialeRequest.getSuperBranch());
        branch.setUsername(filialeRequest.getUsername());

        return branch;
    }

    public IdResponseV1 adptBranchIdToBranchIdResponse(BranchEntity branch){

        IdResponseV1 filialeIdResponse = new IdResponseV1();
        filialeIdResponse.setId(branch.getId());

        return filialeIdResponse;
    }

    public BranchResponseV1 adptBranchToBranchResponse(BranchEntity branch){

        BranchResponseV1 brancheResponse = new BranchResponseV1();

        brancheResponse.setRightOfAccessToTheCourses(branch.getRightOfAccessToTheCourses());
        brancheResponse.setEmail(branch.getEmail());
        brancheResponse.setAddress(addressAdapter.adptAddressToAddressResponse(branch.getAddress()));
        brancheResponse.setName(branch.getName());
        brancheResponse.setSuperBranch(branch.getSuperBranch());
        brancheResponse.setUsername(branch.getUsername());

        return brancheResponse;
    }

    public List<BranchResponseV1> adptBranchToBranchResponse(List<BranchEntity> branchList){

        List<BranchResponseV1> branchResponseList = new LinkedList<>();
        for (BranchEntity att : branchList){
            branchResponseList.add(this.adptBranchToBranchResponse(att));
        }

        return branchResponseList;
    }

    public PaginationResponseV1<BranchResponseV1> adpBranchPaginationToBranchPaginationResposne(Pagination<BranchEntity> branchPagination){

        PaginationResponseV1<BranchResponseV1> branchPaginationResponse = new PaginationResponseV1<>();

        branchPaginationResponse.setItems(this.adptBranchToBranchResponse(branchPagination.getItems()));
        branchPaginationResponse.setStats(branchPagination.getStats());

        return branchPaginationResponse;
    }


}
