package g58594.atlg4.sorting.controller;

import g58594.atlg4.sorting.model.Difficulty;
import g58594.atlg4.sorting.model.Model;
import g58594.atlg4.sorting.model.SortType;
import g58594.atlg4.sorting.view.View;

public class Controller {
    private Model model;

    private View view;

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
    }

    public void launchSorting() {
        view.resetProgressBar();
        SortType sortType = view.getSortType();
        Difficulty diff = view.getDifficulty();
        int nbThreads = view.getNbThreads();
        this.model.startSorting(sortType,diff,nbThreads);
    }
}
