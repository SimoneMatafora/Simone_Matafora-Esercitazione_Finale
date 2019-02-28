package it.tcgroup.vilear.coursemanager.service.impl;

import it.tcgroup.vilear.coursemanager.adapter.PartnerAdapter;
import it.tcgroup.vilear.coursemanager.common.exception.NotFoundException;
import it.tcgroup.vilear.coursemanager.controller.payload.request.PartnerRequestV1;
import it.tcgroup.vilear.coursemanager.controller.payload.response.IdResponseV1;
import it.tcgroup.vilear.coursemanager.controller.payload.response.PaginationResponseV1;
import it.tcgroup.vilear.coursemanager.controller.payload.response.PartnerResponseV1;
import it.tcgroup.vilear.coursemanager.entity.PartnerEntity;
import it.tcgroup.vilear.coursemanager.repository.PartnerRepository;
import it.tcgroup.vilear.coursemanager.service.PartnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

@Transactional
@Service
public class PartnerServiceImpl implements PartnerService {


    @Autowired
    private PartnerAdapter partnerAdapter;

    @Autowired
    private PartnerRepository partnerRepository;

    @Override
    public IdResponseV1 insertPartner(PartnerRequestV1 partnerInsertRequest) {

        PartnerEntity partner = partnerAdapter.adptPartnerRequestToPartner(partnerInsertRequest);
        partnerRepository.save(partner);

        return partnerAdapter.adptPartnerIdToPartnerIdResponse(partner);
    }

    @Override
    public PartnerResponseV1 updatePartner(PartnerRequestV1 partnerInsertRequest, UUID idPartner) {
        return null;
    }

    @Override
    public PartnerResponseV1 getPartner(UUID idPartner) {

        Optional<PartnerEntity> partner = partnerRepository.findById(idPartner);
        if(!partner.isPresent()){
            throw new NotFoundException("Partner with id " + idPartner + " not found");
        }

        return partnerAdapter.adptPartnerToPartnerResponse(partner.get());
    }

    @Override
    public PartnerResponseV1 patchPartner(PartnerRequestV1 partnerInsertRequest, UUID idPartner) {
        return null;
    }

    @Override
    public PaginationResponseV1<PartnerResponseV1> getPartnersPagination(int page, int pageSize, String username, String nome, String cognome, String telefono, String codiceFiscale, String dataDiNascita, String luogoDiNascita, String email, String areaProfessionale, Boolean dipendentePubblico, Boolean accreditatoFt, String codiceAccredidatoFt, Boolean autorizzato, Boolean iscrizioneOrdineProfessionale, String albo, Boolean titolarePiva, String settore, String citta, String comune, String cap) {
        return null;
    }

    @Override
    public void deletePartner(UUID idPartner) {

    }
}
