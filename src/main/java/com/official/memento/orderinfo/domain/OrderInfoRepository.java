package com.official.memento.orderinfo.domain;

import java.time.LocalDate;
import java.util.List;

public interface OrderInfoRepository {

    void save(final OrderInfo orderInfo);

    void update(final OrderInfo orderInfo);

    List<OrderWithScheduleOrToDo> findOrderInfoWithDetails(final LocalDate startDate);
}
