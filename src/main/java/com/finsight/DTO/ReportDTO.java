package com.finsight.DTO;

import lombok.Data;

@Data
public class ReportDTO {
    private String status;

    public ReportDTO() {
        this.status = "Все чикибамбони";
    }
}
