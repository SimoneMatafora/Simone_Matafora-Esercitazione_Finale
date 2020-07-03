package it.tcgroup.vilear.coursemanager.pagination;

import com.fasterxml.jackson.annotation.JsonProperty;
import it.tcgroup.vilear.coursemanager.controller.payload.response.PaginationResponseV1.*;

import java.util.List;

public class Pagination<T> {

    @JsonProperty("stats")
    private InfoPagination stats;

    @JsonProperty("items")
    private List<T> items;

    public Pagination() {
    }

    public Pagination(InfoPagination stats, List<T> items) {
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
        return "Pagination{" +
                "stats=" + stats +
                ", items=" + items +
                '}';
    }
}
