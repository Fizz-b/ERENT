package se.project.database.api.history;

import java.util.ArrayList;

import se.project.model.history.ItemHistory;

public interface IHistory {
	  public ArrayList<ItemHistory> getRentHistory(int custId);
	  public ArrayList<ItemHistory> getReturnHistory(int custId);
}
