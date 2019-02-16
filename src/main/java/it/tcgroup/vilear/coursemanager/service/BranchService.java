package it.tcgroup.vilear.coursemanager.service;

import it.tcgroup.vilear.coursemanager.controller.payload.request.BranchRequestV1;
import it.tcgroup.vilear.coursemanager.controller.payload.response.BranchResponseV1;
import it.tcgroup.vilear.coursemanager.controller.payload.response.IdResponseV1;
import it.tcgroup.vilear.coursemanager.controller.payload.response.PaginationResponseV1;

import java.util.UUID;

public interface BranchService {

    IdResponseV1 insertBranch(BranchRequestV1 filialeInsertRequest);
    BranchResponseV1 getBranch(UUID idBranch);
    BranchResponseV1 updateBranch(BranchRequestV1 branchUpdateRequest, UUID idBranch);
    BranchResponseV1 patchBranch(BranchRequestV1 branchUpdateRequest, UUID idBranch);
    PaginationResponseV1<BranchResponseV1> getBranchesPagination(int page, int pageSize, String username, String name, String email, String rightOfAccessToTheCourses, Boolean superBranch,
                                                                       String city, String region, String province);
    void deleteBranch(UUID idBranch);

}
