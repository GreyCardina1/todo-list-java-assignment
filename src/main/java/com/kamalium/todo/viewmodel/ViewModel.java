package com.kamalium.todo.viewmodel;

import com.kamalium.todo.entity.Item;

import java.util.ArrayList;

import javax.validation.Valid;

public class ViewModel {
	@Valid
	private ArrayList<Item> todoList = new ArrayList<Item>();
	public ViewModel() {}
	public ViewModel(Iterable<Item> items) {
		items.forEach(todoList:: add);
	}
	public ViewModel(ArrayList<Item> todoList) {
		this.todoList = todoList;
	}
	public ArrayList<Item> getTodoList() {
		return todoList;
	}
	public void setTodoList(ArrayList<Item> todoList) {
		this.todoList = todoList;
	}
}