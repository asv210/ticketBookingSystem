package com.darkSProject.ticketBooking.common.dto;

import lombok.Builder;
import org.springframework.data.domain.Page;

import java.util.List;

@Builder
public record PaginationResponseDTO<T>(

        List<T> content,

        int page,

        int size,

        long totalElements,

        int totalPages,

        boolean last
) {

    public static <T> PaginationResponseDTO<T>
    from(Page<T> pageData) {

        return PaginationResponseDTO
                .<T>builder()

                .content(
                        pageData.getContent()
                )

                .page(
                        pageData.getNumber()
                )

                .size(
                        pageData.getSize()
                )

                .totalElements(
                        pageData.getTotalElements()
                )

                .totalPages(
                        pageData.getTotalPages()
                )

                .last(
                        pageData.isLast()
                )

                .build();
    }
}
