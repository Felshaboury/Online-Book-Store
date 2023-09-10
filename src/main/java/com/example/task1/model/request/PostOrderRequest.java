package com.example.task1.model.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PostOrderRequest {

    private List<Long> bookIds;
}
