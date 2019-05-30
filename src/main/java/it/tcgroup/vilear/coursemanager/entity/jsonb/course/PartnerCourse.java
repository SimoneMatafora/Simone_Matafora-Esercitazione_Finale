package it.tcgroup.vilear.coursemanager.entity.jsonb.course;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.tcgroup.vilear.coursemanager.entity.PartnerEntity;
import it.tcgroup.vilear.coursemanager.entity.dto.PartnerDto;
import it.tcgroup.vilear.coursemanager.entity.enumerated.SupplyServicePartnerCourseEnum;

import java.io.Serializable;
import java.util.List;
import java.util.*;

public class PartnerCourse implements Serializable {

    @JsonProperty("supplier")
    private PartnerDto supplier;

    @JsonProperty("supply_services")
    private List<SupplierService> supplyServices;

    @JsonProperty("first_payment_date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date firstPaymentDate;

    @JsonProperty("amount_first_paymen")
    private Double amountFirstPaymen;

    @JsonProperty("second_payment_date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date secondPaymentDate;

    @JsonProperty("amount_second_paymen")
    private Double amountSecondPaymen;

    @JsonProperty("third_payment_date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date thirdPaymentDate;

    @JsonProperty("amount_third_paymen")
    private Double amountThirdPaymen;

    @JsonProperty("sub_suppliers_list")
    private List<SubSupplier> subSupplierList;

    public PartnerCourse() {
    }

    public PartnerCourse(PartnerDto supplier, List<SupplierService> supplyServices, Date firstPaymentDate, Double amountFirstPaymen, Date secondPaymentDate, Double amountSecondPaymen, Date thirdPaymentDate, Double amountThirdPaymen, List<SubSupplier> subSupplierList) {
        this.supplier = supplier;
        this.supplyServices = supplyServices;
        this.firstPaymentDate = firstPaymentDate;
        this.amountFirstPaymen = amountFirstPaymen;
        this.secondPaymentDate = secondPaymentDate;
        this.amountSecondPaymen = amountSecondPaymen;
        this.thirdPaymentDate = thirdPaymentDate;
        this.amountThirdPaymen = amountThirdPaymen;
        this.subSupplierList = subSupplierList;
    }

    public PartnerDto getSupplier() {
        return supplier;
    }

    public void setSupplier(PartnerDto supplier) {
        this.supplier = supplier;
    }

    public List<SupplierService> getSupplyServices() {
        return supplyServices;
    }

    public void setSupplyServices(List<SupplierService> supplyServices) {
        this.supplyServices = supplyServices;
    }

    public Date getFirstPaymentDate() {
        return firstPaymentDate;
    }

    public void setFirstPaymentDate(Date firstPaymentDate) {
        this.firstPaymentDate = firstPaymentDate;
    }

    public Double getAmountFirstPaymen() {
        return amountFirstPaymen;
    }

    public void setAmountFirstPaymen(Double amountFirstPaymen) {
        this.amountFirstPaymen = amountFirstPaymen;
    }

    public Date getSecondPaymentDate() {
        return secondPaymentDate;
    }

    public void setSecondPaymentDate(Date secondPaymentDate) {
        this.secondPaymentDate = secondPaymentDate;
    }

    public Double getAmountSecondPaymen() {
        return amountSecondPaymen;
    }

    public void setAmountSecondPaymen(Double amountSecondPaymen) {
        this.amountSecondPaymen = amountSecondPaymen;
    }

    public Date getThirdPaymentDate() {
        return thirdPaymentDate;
    }

    public void setThirdPaymentDate(Date thirdPaymentDate) {
        this.thirdPaymentDate = thirdPaymentDate;
    }

    public Double getAmountThirdPaymen() {
        return amountThirdPaymen;
    }

    public void setAmountThirdPaymen(Double amountThirdPaymen) {
        this.amountThirdPaymen = amountThirdPaymen;
    }

    public List<SubSupplier> getSubSupplierList() {
        return subSupplierList;
    }

    public void setSubSupplierList(List<SubSupplier> subSupplierList) {
        this.subSupplierList = subSupplierList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PartnerCourse)) return false;
        PartnerCourse that = (PartnerCourse) o;
        return Objects.equals(supplier, that.supplier) &&
                Objects.equals(supplyServices, that.supplyServices) &&
                Objects.equals(firstPaymentDate, that.firstPaymentDate) &&
                Objects.equals(amountFirstPaymen, that.amountFirstPaymen) &&
                Objects.equals(secondPaymentDate, that.secondPaymentDate) &&
                Objects.equals(amountSecondPaymen, that.amountSecondPaymen) &&
                Objects.equals(thirdPaymentDate, that.thirdPaymentDate) &&
                Objects.equals(amountThirdPaymen, that.amountThirdPaymen) &&
                Objects.equals(subSupplierList, that.subSupplierList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(supplier, supplyServices, firstPaymentDate, amountFirstPaymen, secondPaymentDate, amountSecondPaymen, thirdPaymentDate, amountThirdPaymen, subSupplierList);
    }

    public static class SupplierService implements Serializable {

        @JsonProperty("supplier_service")
        private SupplyServicePartnerCourseEnum supplierService;

        @JsonProperty("service_cost")
        private Double serviceCost;

        public SupplierService() {
        }

        public SupplierService(SupplyServicePartnerCourseEnum supplierService, Double serviceCost) {
            this.supplierService = supplierService;
            this.serviceCost = serviceCost;
        }

        public SupplyServicePartnerCourseEnum getSupplierService() {
            return supplierService;
        }

        public void setSupplierService(SupplyServicePartnerCourseEnum supplierService) {
            this.supplierService = supplierService;
        }

        public Double getServiceCost() {
            return serviceCost;
        }

        public void setServiceCost(Double serviceCost) {
            this.serviceCost = serviceCost;
        }

        @Override
        public String toString() {
            return "SupplierService{" +
                    "supplierService=" + supplierService +
                    ", serviceCost=" + serviceCost +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof SupplierService)) return false;
            SupplierService that = (SupplierService) o;
            return supplierService == that.supplierService &&
                    Objects.equals(serviceCost, that.serviceCost);
        }

        @Override
        public int hashCode() {
            return Objects.hash(supplierService, serviceCost);
        }
    }

    public static class SubSupplier implements Serializable {

        @JsonProperty("sub_supplier")
        private PartnerDto subSupplier;

        @JsonProperty("sub_suplly_service")
        private List<SupplyServicePartnerCourseEnum> subSupplierService;

        public SubSupplier() {
        }

        public SubSupplier(PartnerDto subSupplier, List<SupplyServicePartnerCourseEnum> subSupplierService) {
            this.subSupplier = subSupplier;
            this.subSupplierService = subSupplierService;
        }

        public PartnerDto getSubSupplier() {
            return subSupplier;
        }

        public void setSubSupplier(PartnerDto subSupplier) {
            this.subSupplier = subSupplier;
        }

        public List<SupplyServicePartnerCourseEnum> getSubSupplierService() {
            return subSupplierService;
        }

        public void setSubSupplierService(List<SupplyServicePartnerCourseEnum> subSupplierService) {
            this.subSupplierService = subSupplierService;
        }

        @Override
        public String toString() {
            return "SubSupplier{" +
                    "subSupplier=" + subSupplier +
                    ", subSupplierService=" + subSupplierService +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            SubSupplier that = (SubSupplier) o;
            return Objects.equals(subSupplier, that.subSupplier) &&
                    Objects.equals(subSupplierService, that.subSupplierService);
        }

        @Override
        public int hashCode() {
            return Objects.hash(subSupplier, subSupplierService);
        }
    }

}
