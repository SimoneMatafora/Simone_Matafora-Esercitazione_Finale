package it.tcgroup.vilear.coursemanager.entity.jsonb.course;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.tcgroup.vilear.coursemanager.entity.PartnerEntity;
import it.tcgroup.vilear.coursemanager.entity.enumerated.SupplyServicePartnerCourseEnum;

import java.io.Serializable;
import java.util.List;
import java.util.*;

public class PartnerCourse implements Serializable {

    @JsonProperty("supplier")
    private PartnerEntity supplier;

    @JsonProperty("suplly_service")
    private List<SupplyServicePartnerCourseEnum> supplierService;

    @JsonProperty("services_costs")
    private Double servicesCosts;

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

    public PartnerCourse(PartnerEntity supplier, List<SupplyServicePartnerCourseEnum> supplierService, Double servicesCosts, Date firstPaymentDate, Double amountFirstPaymen, Date secondPaymentDate, Double amountSecondPaymen, Date thirdPaymentDate, Double amountThirdPaymen, List<SubSupplier> subSupplierList) {
        this.supplier = supplier;
        this.supplierService = supplierService;
        this.servicesCosts = servicesCosts;
        this.firstPaymentDate = firstPaymentDate;
        this.amountFirstPaymen = amountFirstPaymen;
        this.secondPaymentDate = secondPaymentDate;
        this.amountSecondPaymen = amountSecondPaymen;
        this.thirdPaymentDate = thirdPaymentDate;
        this.amountThirdPaymen = amountThirdPaymen;
        this.subSupplierList = subSupplierList;
    }

    public PartnerEntity getSupplier() {
        return supplier;
    }

    public void setSupplier(PartnerEntity supplier) {
        this.supplier = supplier;
    }

    public List<SupplyServicePartnerCourseEnum> getSupplierService() {
        return supplierService;
    }

    public void setSupplierService(List<SupplyServicePartnerCourseEnum> supplierService) {
        this.supplierService = supplierService;
    }

    public Double getServicesCosts() {
        return servicesCosts;
    }

    public void setServicesCosts(Double servicesCosts) {
        this.servicesCosts = servicesCosts;
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
    public String toString() {
        return "PartnerCourse{" +
                "supplier=" + supplier +
                ", supplierService=" + supplierService +
                ", servicesCosts=" + servicesCosts +
                ", firstPaymentDate=" + firstPaymentDate +
                ", amountFirstPaymen=" + amountFirstPaymen +
                ", secondPaymentDate=" + secondPaymentDate +
                ", amountSecondPaymen=" + amountSecondPaymen +
                ", thirdPaymentDate=" + thirdPaymentDate +
                ", amountThirdPaymen=" + amountThirdPaymen +
                ", subSupplierList=" + subSupplierList +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PartnerCourse that = (PartnerCourse) o;
        return Objects.equals(supplier, that.supplier) &&
                Objects.equals(supplierService, that.supplierService) &&
                Objects.equals(servicesCosts, that.servicesCosts) &&
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
        return Objects.hash(supplier, supplierService, servicesCosts, firstPaymentDate, amountFirstPaymen, secondPaymentDate, amountSecondPaymen, thirdPaymentDate, amountThirdPaymen, subSupplierList);
    }


    public static class SubSupplier implements Serializable {

        @JsonProperty("sub_supplier")
        private PartnerEntity subSupplier;

        @JsonProperty("sub_suplly_service")
        private List<SupplyServicePartnerCourseEnum> subSupplierService;

        public SubSupplier() {
        }

        public SubSupplier(PartnerEntity subSupplier, List<SupplyServicePartnerCourseEnum> subSupplierService) {
            this.subSupplier = subSupplier;
            this.subSupplierService = subSupplierService;
        }

        public PartnerEntity getSubSupplier() {
            return subSupplier;
        }

        public void setSubSupplier(PartnerEntity subSupplier) {
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
