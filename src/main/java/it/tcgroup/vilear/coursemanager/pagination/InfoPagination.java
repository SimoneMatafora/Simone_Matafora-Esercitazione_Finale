package it.tcgroup.vilear.coursemanager.pagination;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class InfoPagination {

    @JsonProperty(value = "item_count")
    private Integer itemCount;
    @JsonProperty(value = "current_page")
    private Integer currentPage;
    @JsonProperty(value = "page_count")
    private Integer pageCount;
    @JsonProperty(value = "page_size")
    private Integer pageSize;

    public InfoPagination(){}

    public InfoPagination(Integer itemCount, Integer currentPage, Integer pageSize) {
        this.itemCount = itemCount;
        this.setPageSize(pageSize);
        this.setPageCount(this.itemCount, this.pageSize);
        this.setCurrentPage(currentPage);
    }

    public Integer getItemCount() {
        return itemCount;
    }

    public void setItemCount(Integer itemCount) {
        this.itemCount = itemCount;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        if(currentPage > this.pageCount || currentPage < 1)
            currentPage = 1;
        this.currentPage = currentPage;
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer itemCount, Integer pageSize) {

        this.pageCount = (int) Math.ceil((double) itemCount / pageSize);
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        if(pageSize <= 0)
            pageSize = 20;
        this.pageSize = pageSize;
    }

    @JsonIgnore
    public int getStartPage(){
        return ((currentPage - 1) * pageSize) + 1;
    }

    @Override
    public String toString() {
        return "InfoPagination{" +
                "itemCount=" + itemCount +
                ", currentPage=" + currentPage +
                ", pageCount=" + pageCount +
                ", pageSize=" + pageSize +
                '}';
    }
}