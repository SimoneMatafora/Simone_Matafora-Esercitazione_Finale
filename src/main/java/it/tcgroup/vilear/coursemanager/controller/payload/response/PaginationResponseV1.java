package it.tcgroup.vilear.coursemanager.controller.payload.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.tcgroup.vilear.coursemanager.pagination.InfoPagination;

import java.util.List;

public class PaginationResponseV1<T> {

    @JsonProperty("stats")
    private InfoPagination stats;

    @JsonProperty("items")
    private List<T> items;

    public PaginationResponseV1() {
    }

    public PaginationResponseV1(InfoPagination stats, List<T> items) {
        this.stats = stats;
        this.items = items;
    }

    public InfoPagination getStats() {
        return stats;
    }

    public void setStats(InfoPagination stats) {
        this.stats = stats;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "PaginationResponseV1{" +
                "stats=" + stats +
                ", items=" + items +
                '}';
    }


}
