package com.official.memento.todo.service

import com.official.memento.global.entity.enums.RepeatOption
import com.official.memento.orderinfo.domain.OrderInfo
import com.official.memento.orderinfo.domain.OrderInfoRepository
import com.official.memento.orderinfo.domain.OrderWithScheduleOrToDo
import com.official.memento.orderinfo.domain.PlanType
import com.official.memento.todo.domain.BrainDumpClientOutputPort
import com.official.memento.todo.domain.ToDo
import com.official.memento.todo.domain.ToDoRepository
import com.official.memento.todo.domain.enums.PriorityType
import com.official.memento.todo.domain.enums.ToDoType
import com.official.memento.todo.domain.vo.BrainDump
import com.official.memento.todo.domain.vo.ToDoBrainDump
import com.official.memento.todo.service.command.BrainDumpCreateCommand
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.LocalDateTime

@Service
class BrainDumpService(
    private val brainDumpClientOutputPort: BrainDumpClientOutputPort,
    private val toDoRepository: ToDoRepository,
    private val orderInfoRepository: OrderInfoRepository
) : BrainDumpCreateUseCase {
    override fun create(command: BrainDumpCreateCommand): ToDoBrainDump {
        val toDoBrainDump =
            brainDumpClientOutputPort.createByBrainDump(
                BrainDump(
                    command.content,
                ),
            )
        val toDo =
            ToDo.of(
                command.memberId,
                null,
                toDoBrainDump.createdDate,
                toDoBrainDump.task,
                toDoBrainDump.deadline,
                false,
                RepeatOption.NONE,
                null,
                toDoBrainDump.urgency.toDouble(),
                toDoBrainDump.importance.toDouble(),
                toDoBrainDump.urgency * 0.3 + toDoBrainDump.importance * 0.7,
                PriorityType.findPriorityType(toDoBrainDump.urgency.toDouble(), toDoBrainDump.importance.toDouble()),
                ToDoType.NORMAL,
            )
        toDoRepository.save(toDo)
        assignOrder(toDoBrainDump.createdDate, toDo)
        return toDoBrainDump
    }

    private fun assignOrder(date: LocalDate, toDo: ToDo) {
        val toDoList = orderInfoRepository.findOrderInfoWithDetails(date)
        val insertOrder: Int = getInsertOrder(date, toDoList, toDo)
        createOrderInfo(date, toDo, insertOrder)
    }

    private fun getInsertOrder(date: LocalDate, toDoList: List<OrderWithScheduleOrToDo>, toDo: ToDo): Int {
        var insertOrder = 1
        var isInserted = false
        for (existingOrder in toDoList) {
            if (!isInserted && existingOrder.type == PlanType.TODO) {
                if (toDo.priorityValue > existingOrder.priorityValue) {
                    insertOrder = existingOrder.order
                    isInserted = true
                } else if (toDo.priorityValue.equals(existingOrder.priorityValue)) {
                    if (toDo.createdAt.isBefore(existingOrder.createdAt)) {
                        insertOrder = existingOrder.order
                        isInserted = true
                    }
                }
            }

            if (isInserted) {
                existingOrder.shiftBack()
                orderInfoRepository.update(
                    OrderInfo.withId(
                        existingOrder.orderInfoId,
                        existingOrder.scheduleId,
                        existingOrder.toDoId,
                        existingOrder.order,
                        date,
                        existingOrder.type,
                        existingOrder.createdAt
                    )
                )
            }
        }

        if (!isInserted) {
            insertOrder = if (toDoList.isEmpty()) 1 else toDoList[toDoList.size - 1].order + 1
        }
        return insertOrder
    }
    private fun createOrderInfo(date: LocalDate, toDo: ToDo, insertOrder: Int) {
        orderInfoRepository.save(
            OrderInfo.of(
                null,
                toDo.id,
                insertOrder,
                date,
                PlanType.TODO,
                LocalDateTime.now()
            )
        )
    }

}

