package it.tcgroup.vilear.coursemanager.service.impl;

import it.tcgroup.vilear.coursemanager.adapter.BranchAdapter;
import it.tcgroup.vilear.coursemanager.common.exception.NotFoundException;
import it.tcgroup.vilear.coursemanager.controller.payload.request.BranchRequestV1;
import it.tcgroup.vilear.coursemanager.controller.payload.request.UploadRequestV1;
import it.tcgroup.vilear.coursemanager.controller.payload.response.BranchResponseV1;
import it.tcgroup.vilear.coursemanager.controller.payload.response.IdResponseV1;
import it.tcgroup.vilear.coursemanager.controller.payload.response.PaginationResponseV1;
import it.tcgroup.vilear.coursemanager.entity.BranchEntity;
import it.tcgroup.vilear.coursemanager.entity.Pagination;
import it.tcgroup.vilear.coursemanager.repository.BranchEMRepository;
import it.tcgroup.vilear.coursemanager.repository.BranchRepository;
import it.tcgroup.vilear.coursemanager.service.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Transactional
@Service
public class BranchServiceImpl implements BranchService {

    @Autowired
    private BranchAdapter branchAdapter;

    @Autowired
    private BranchRepository branchRepository;

    @Autowired
    private BranchEMRepository branchEMRepository;

    @Override
    public IdResponseV1 insertBranch(BranchRequestV1 branchInsertRequest){

        BranchEntity branch = branchAdapter.adptBranchRequestToBranch(branchInsertRequest);

        branchRepository.save(branch);

        return branchAdapter.adptBranchIdToBranchIdResponse(branch);

    }

    @Override
    public BranchResponseV1 getBranch(UUID idBranch){

        Optional<BranchEntity> branch = branchRepository.findById(idBranch);
        if(!branch.isPresent()){
            throw new NotFoundException("Branch with id " + idBranch+ " not found");
        }

        return branchAdapter.adptBranchToBranchResponse(branch.get());
    }

    @Modifying
    @Override
    public BranchResponseV1 updateBranch(BranchRequestV1 branchUpdateRequest, UUID idBranch){

        Optional<BranchEntity> optBranch = branchRepository.findById(idBranch);
        if(!optBranch.isPresent()){
            throw new NotFoundException("Branch with id " + idBranch + " not found");
        }
        BranchEntity branch = optBranch.get();

        BranchEntity branchUpdate = branchAdapter.adptBranchRequestToBranch(branchUpdateRequest);

        branch.setEmail(branchUpdate.getEmail());
        branch.setName(branchUpdate.getName());
        branch.setUsername(branchUpdate.getUsername());
        branch.setSuperBranch(branchUpdate.getSuperBranch());
        branch.setRightOfAccessToTheCourses(branchUpdate.getRightOfAccessToTheCourses());
        branch.setAddress(branchUpdate.getAddress());

        branchRepository.save(branch);

        return  branchAdapter.adptBranchToBranchResponse(branch);
    }

    @Modifying
    @Override
    public BranchResponseV1 patchBranch(BranchRequestV1 branchUpdateRequest, UUID idBranch){

        Optional<BranchEntity> optBranch = branchRepository.findById(idBranch);
        if(!optBranch.isPresent()){
            throw new NotFoundException("Branch with id " + idBranch+ " not found");
        }
        BranchEntity branch = optBranch.get();

        BranchEntity branchPatch = branchAdapter.adptBranchRequestToBranch(branchUpdateRequest);

        if( branchPatch.getRightOfAccessToTheCourses() != null)
            branch.setRightOfAccessToTheCourses(branchPatch.getRightOfAccessToTheCourses());

        if( branchPatch.getSuperBranch() != null)
            branch.setSuperBranch(branchPatch.getSuperBranch());

        if( branchPatch.getEmail() != null)
            branch.setEmail(branchPatch.getEmail());

        if( branchPatch.getName() != null)
            branch.setName(branchPatch.getName());

        if( branchPatch.getUsername() != null)
            branch.setUsername(branchPatch.getUsername());

        if( branchPatch.getAddress() != null)
            branch.setAddress(branchPatch.getAddress());

        branchRepository.save(branch);

        return branchAdapter.adptBranchToBranchResponse(branch);
    }

    @Override
    public PaginationResponseV1<BranchResponseV1> getBranchesPagination(int page, int pageSize, String username, String name, String email, String rightOfAccessToTheCourses, Boolean superBranch,
                                                                       String city, String region, String province){

        Pagination<BranchEntity> branchesPagination = new Pagination<>();

        List<BranchEntity> branchesList = branchEMRepository.getFilialiForPagination(username, name, email, rightOfAccessToTheCourses, superBranch, city, region, province);

        branchesPagination.setStats(new PaginationResponseV1.InfoPagination(branchesList.size(), page, pageSize));

        int start = branchesPagination.getStats().getStartPage();
        int count = 0;
        List<BranchEntity> filialiForPagination = new LinkedList<>();

        while (count < branchesPagination.getStats().getPageSize() && ((start - 1) + count) < branchesList.size()) {
            filialiForPagination.add((branchesList.get((start - 1) + count)));
            count++;
        }

        branchesPagination.setItems(filialiForPagination);

        return branchAdapter.adpBranchPaginationToBranchPaginationResposne(branchesPagination);
    }

    @Override
    public void deleteBranch(UUID idBranch){
        branchRepository.deleteById(idBranch);
    }


}
