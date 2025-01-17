package com.official.memento.todo.controller;

import com.official.memento.global.annotation.Authorization;
import com.official.memento.global.annotation.AuthorizationUser;
import com.official.memento.global.dto.SuccessResponse;
import com.official.memento.todo.controller.dto.ToDoCreateRequest;
import com.official.memento.todo.domain.ToDo;
import com.official.memento.todo.service.ToDoCreateUseCase;
import com.official.memento.todo.service.command.ToDoCreateCommand;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/todos")
public class ToDoApiController {

    private final ToDoCreateUseCase toDoCreateUseCase;

    public ToDoApiController(
            final ToDoCreateUseCase toDoCreateUseCase
    ) {
        this.toDoCreateUseCase = toDoCreateUseCase;
    }

    @PostMapping
    public ResponseEntity<SuccessResponse<?>> createToDo(
            //@Authorization final AuthorizationUser authorizationUser,
            @RequestBody final ToDoCreateRequest request
    ) {
        //test용, 로그인 구현되면 지우기
        final AuthorizationUser authorizationUser = new AuthorizationUser(2L);

        toDoCreateUseCase.create(ToDoCreateCommand.of(
                        authorizationUser.memberId(),
                        request.date(),
                        request.description(),
                        request.deadline(),
                        request.repeatOption(),
                        request.repeatExpiredDate(),
                        request.tagId(),
                        request.priorityUrgency(),
                        request.priorityImportance()
                )
        );
        return SuccessResponse.of(
                HttpStatus.CREATED,
                "ToDo 생성 성공"
        );
    }
}
